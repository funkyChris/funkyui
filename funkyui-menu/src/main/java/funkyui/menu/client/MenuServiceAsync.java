package funkyui.menu.client;

import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;
import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.rpc.ServiceDefTarget;

public interface MenuServiceAsync
{

    /**
     * GWT-RPC service  asynchronous (client-side) interface
     * @see funkyui.menu.client.MenuService
     */
    void getTime( AsyncCallback<java.lang.String> callback );


    /**
     * Utility class to get the RPC Async interface from client-side code
     */
    public static final class Util 
    { 
        private static MenuServiceAsync instance;

        public static final MenuServiceAsync getInstance()
        {
            if ( instance == null )
            {
                instance = (MenuServiceAsync) GWT.create( MenuService.class );
                ServiceDefTarget target = (ServiceDefTarget) instance;
                target.setServiceEntryPoint( GWT.getModuleBaseURL() + "menu" );
            }
            return instance;
        }

        private Util()
        {
            // Utility class should not be instanciated
        }
    }
}
