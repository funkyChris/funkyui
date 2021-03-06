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
package funkyui.composeur2.server;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;

import funkyui.composeur2.client.Composeur2Service;

/**
 * Implementation of the Composeur2Service RPC service.
 * 
 * @author <a href="mailto:christophe.bouthier@loria.fr">Christophe Bouthier</a>
 * @date 20 October 2009
 */
@SuppressWarnings("serial")
public class Composeur2ServiceImpl extends RemoteServiceServlet implements Composeur2Service {

    public String getTime() {
        return "composeur 2 server message";
    }
}
