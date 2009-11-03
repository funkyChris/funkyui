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
package funkyui.composeur2.client;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.RootPanel;

/**
 * Entry point classes define <code>onModuleLoad()</code>.
 * 
 * @author <a href="mailto:christophe.bouthier@loria.fr">Christophe Bouthier</a>
 * @date 20 October 2009
 */
public class FunkyUI_composeur2 implements EntryPoint {
    private final Composeur2ServiceAsync composeur2 = GWT.create(Composeur2Service.class);
    
    @Override
    public void onModuleLoad() {
        final Label label = new Label("Composeur 2");
        label.addStyleName("composeur2Label");
        
        RootPanel.get("compoComponent").add(label);
        
        final Button serverButton = new Button("serveur composeur 2");
        RootPanel.get("compoComponent").add(serverButton);
        
        class ServerHandler implements ClickHandler {
            @Override
            public void onClick(ClickEvent event) {      
                composeur2.getTime(new AsyncCallback<String>() {
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
