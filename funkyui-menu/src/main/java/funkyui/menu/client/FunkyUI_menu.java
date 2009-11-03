/*
 * Funky UI
 * Copyright (C) 2006-2010 INRIA
 * http://www.inria.fr
 *
 * This is free software; you can redistribute it and/or modify it
 * under the terms of the GNU Lesser General Public License version 3
 * as published by the Free Software Foundation. See the GNU
 * Lesser General Public License in LGPL.txt for more details.
 *
 * You should have received a copy of the GNU Lesser General Public
 * License along with this software; if not, write to the Free
 * Software Foundation, Inc., 51 Franklin St, Fifth Floor, Boston, MA
 * 02110-1301 USA, or see the FSF site: http://www.fsf.org.
 *
 * Initial authors :
 *
 * Christophe Bouthier / INRIA
 */
package funkyui.menu.client;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.DOM;
import com.google.gwt.user.client.Element;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.VerticalPanel;

/**
 * Entry point classes define <code>onModuleLoad()</code>.
 * 
 * @author <a href="mailto:christophe.bouthier@loria.fr">Christophe Bouthier</a>
 * @date 20 October 2009
 */
public class FunkyUI_menu implements EntryPoint {
    private final MenuServiceAsync menu = GWT.create(MenuService.class);

    public void addScript(String url) {
        Element e = DOM.createElement("script");
        e.setAttribute("type", "text/javascript");
        e.setAttribute("language", "JavaScript");
        e.setAttribute("src", url);
        DOM.appendChild(RootPanel.getBodyElement(), e);
      }
    
    public void onModuleLoad() {
        final Button composeur1Button = new Button("Composeur 1");
        composeur1Button.addStyleName("menuButton");
        
        final Button composeur2Button = new Button("Composeur 2");
        composeur2Button.addStyleName("menuButton");
        
        final VerticalPanel panel = new VerticalPanel();
        panel.add(composeur1Button);
        panel.add(composeur2Button);        
        
        RootPanel.get("menuComponent").add(panel);

        class CompoHandler implements ClickHandler {

            private String name;
            
            public CompoHandler(String name) {
                this.name = name;
            }
            
            @Override
            public void onClick(ClickEvent event) {
                RootPanel pane = RootPanel.get("compoComponent");
                pane.clear();
                pane.getElement().setInnerHTML("");
                
                if (name == "compo1") {
                    addScript("/funkyui-composeur/funkyui_composeur/funkyui_composeur.nocache.js");
                } else {
                    addScript("/funkyui-composeur2/funkyui_composeur2/funkyui_composeur2.nocache.js");
                }
            }
            
        }
        
        composeur1Button.addClickHandler(new CompoHandler("compo1"));
        composeur2Button.addClickHandler(new CompoHandler("compo2"));
        
        
        final Button serverButton = new Button("serveur");
        RootPanel.get("menuComponent").add(serverButton);
        
        class ServerHandler implements ClickHandler {
            @Override
            public void onClick(ClickEvent event) {      
                menu.getTime(new AsyncCallback<String>() {
                    public void onFailure(Throwable caught) {
                        RootPanel.get("compoComponent").add(new Label("ca plante : " + caught));
                    }

                    public void onSuccess(String result) {
                        RootPanel.get("compoComponent").add(new Label("ca marche : " + result));
                    }
                });
            }
        }
        
        serverButton.addClickHandler(new ServerHandler());
    }
}
