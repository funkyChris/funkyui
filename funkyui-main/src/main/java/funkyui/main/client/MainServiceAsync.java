package funkyui.main.client;

import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;
import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.rpc.ServiceDefTarget;

public interface MainServiceAsync
{

    /**
     * GWT-RPC service  asynchronous (client-side) interface
     * @see funkyui.main.client.MainService
     */
    void getTime( AsyncCallback<java.lang.String> callback );


    /**
     * Utility class to get the RPC Async interface from client-side code
     */
    public static final class Util 
    { 
        private static MainServiceAsync instance;

        public static final MainServiceAsync getInstance()
        {
            if ( instance == null )
            {
                instance = (MainServiceAsync) GWT.create( MainService.class );
                ServiceDefTarget target = (ServiceDefTarget) instance;
                target.setServiceEntryPoint( GWT.getModuleBaseURL() + "main" );
            }
            return instance;
        }

        private Util()
        {
            // Utility class should not be instanciated
        }
    }
}
