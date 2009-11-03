package funkyui.clock.client;

import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;
import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.rpc.ServiceDefTarget;

public interface ClockServiceAsync
{

    /**
     * GWT-RPC service  asynchronous (client-side) interface
     * @see funkyui.clock.client.ClockService
     */
    void getTime( AsyncCallback<java.lang.String> callback );


    /**
     * Utility class to get the RPC Async interface from client-side code
     */
    public static final class Util 
    { 
        private static ClockServiceAsync instance;

        public static final ClockServiceAsync getInstance()
        {
            if ( instance == null )
            {
                instance = (ClockServiceAsync) GWT.create( ClockService.class );
                ServiceDefTarget target = (ServiceDefTarget) instance;
                target.setServiceEntryPoint( GWT.getModuleBaseURL() + "clock" );
            }
            return instance;
        }

        private Util()
        {
            // Utility class should not be instanciated
        }
    }
}
