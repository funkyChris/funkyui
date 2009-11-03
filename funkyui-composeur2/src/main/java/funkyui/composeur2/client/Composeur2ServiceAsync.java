package funkyui.composeur2.client;

import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.rpc.ServiceDefTarget;

public interface Composeur2ServiceAsync
{

    /**
     * GWT-RPC service  asynchronous (client-side) interface
     * @see funkyui.composeur2.client.Composeur2Service
     */
    void getTime( AsyncCallback<java.lang.String> callback );


    /**
     * Utility class to get the RPC Async interface from client-side code
     */
    public static final class Util 
    { 
        private static Composeur2ServiceAsync instance;

        public static final Composeur2ServiceAsync getInstance()
        {
            if ( instance == null )
            {
                instance = (Composeur2ServiceAsync) GWT.create( Composeur2Service.class );
                ServiceDefTarget target = (ServiceDefTarget) instance;
                target.setServiceEntryPoint( GWT.getModuleBaseURL() + "composeur2" );
            }
            return instance;
        }

        private Util()
        {
            // Utility class should not be instanciated
        }
    }
}
