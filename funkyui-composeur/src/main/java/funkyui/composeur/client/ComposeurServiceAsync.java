package funkyui.composeur.client;

import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.rpc.ServiceDefTarget;

public interface ComposeurServiceAsync
{

    /**
     * GWT-RPC service  asynchronous (client-side) interface
     * @see funkyui.composeur.client.ComposeurService
     */
    void getTime( AsyncCallback<java.lang.String> callback );


    /**
     * Utility class to get the RPC Async interface from client-side code
     */
    public static final class Util 
    { 
        private static ComposeurServiceAsync instance;

        public static final ComposeurServiceAsync getInstance()
        {
            if ( instance == null )
            {
                instance = (ComposeurServiceAsync) GWT.create( ComposeurService.class );
                ServiceDefTarget target = (ServiceDefTarget) instance;
                target.setServiceEntryPoint( GWT.getModuleBaseURL() + "composeur" );
            }
            return instance;
        }

        private Util()
        {
            // Utility class should not be instanciated
        }
    }
}
