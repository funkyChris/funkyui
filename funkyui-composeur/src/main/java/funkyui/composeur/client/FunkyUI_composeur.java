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
package funkyui.composeur.client;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.DOM;
import com.google.gwt.user.client.Element;
import com.google.gwt.user.client.Timer;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.Widget;

/**
 * Entry point classes define <code>onModuleLoad()</code>.
 * 
 * @author <a href="mailto:christophe.bouthier@loria.fr">Christophe Bouthier</a>
 * @date 20 October 2009
 */
public class FunkyUI_composeur implements EntryPoint {
    private final ComposeurServiceAsync composeur = GWT.create(ComposeurService.class);

    public void addScript(String url) {
        Element e = DOM.createElement("script");
        e.setAttribute("type", "text/javascript");
        e.setAttribute("language", "JavaScript");
        e.setAttribute("src", url);
        DOM.appendChild(RootPanel.getBodyElement(), e);
      }
    
    private Widget composedWidget;
    
    @Override
    public void onModuleLoad() {
        final Label loadingLabel = new Label("Loading...");
        registerLabel(loadingLabel);
        
        final Label label = new Label("Composeur 1");
        label.addStyleName("composeur1Label");
        
        RootPanel.get("compoComponent").add(label);
        
        final Button serverButton = new Button("serveur composeur");
        RootPanel.get("compoComponent").add(serverButton);
        
        class ServerHandler implements ClickHandler {
            @Override
            public void onClick(ClickEvent event) {      
                composeur.getTime(new AsyncCallback<String>() {
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
    
        final Button actButton = new Button("act on composed");
        RootPanel.get("compoComponent").add(actButton);
        
        class ActHandler implements ClickHandler {
            @Override
            public void onClick(ClickEvent event) {
                composedWidget.setVisible(!composedWidget.isVisible());
            }
        }
        actButton.addClickHandler(new ActHandler());
        
        
        final Button composeButton = new Button("Compose");
        RootPanel.get("compoComponent").add(composeButton);
        
        class ComposeHandler implements ClickHandler {
            @Override
            public void onClick(ClickEvent event) {      
                addScript("/funkyui-clock/funkyui_clock/funkyui_clock.nocache.js");
                final Widget w = getWidgetFor("funkyui_clock");
                RootPanel.get("compoComponent").add(w);
                
                if (w == loadingLabel) {
                    Timer t = new Timer() {
                        @Override
                        public void run() {
                            Widget w2 = getWidgetFor("funkyui_clock");
                            if (w2 != w) {
                                RootPanel.get("compoComponent").remove(w);
                                RootPanel.get("compoComponent").add(w2);
                                composedWidget = w2;
                                cancel();
                            }
                        }
                      };

                    t.schedule(500);
                } else {
                    composedWidget = w;
                }
            }
        }
        composeButton.addClickHandler(new ComposeHandler());
        
    }
    
    private native void registerLabel(Widget loadingWidget) /*-{
        $wnd.loading = loadingWidget;
    }-*/;

    private native Widget getWidgetFor(String string) /*-{
        if ($wnd.widgets) {
            return $wnd.widgets[string];
        } else {
            return $wnd.loading;
        }
    }-*/;
    
}
