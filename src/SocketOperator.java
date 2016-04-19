
import TCPSockets.TCPClient;
import TCPSockets.TCPServer;
import TCPSockets.AbstractTCPSocket;
import Utils.SocketUtils;

import java.io.IOException;

public class SocketOperator extends SocketUtils {

    // TODO: 4/19/2016 find how to save TCPServer and TCPClient as cCass types constants
    private static final Class<? extends TCPClient> CLIENT_CLASS = TCPClient.class;
    private static final Class<? extends TCPServer> SERVER_CLASS = TCPServer.class;

    public static void main(String[] args) {
        Object[] parsedArguments = parseArguments(args);
        try {
            // FIXME: Change The Class from TCPClient to TCPServer or overturn!
            AbstractTCPSocket myServerOrClient = new TCPClient((String) parsedArguments[0], (int) parsedArguments[1]);    // Creating a new TCP Server/Client
            openTCPServerOrClientSocketForCommunication(myServerOrClient, (String) parsedArguments[0], (int) parsedArguments[1]);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void openTCPServerOrClientSocketForCommunication(AbstractTCPSocket ServerOrClient, String host, int portNumber) {
        try {
//            Class ServerOtClientClass = ServerOrClient.newInstance();
            /* If the handshake with the Client\Server has complete we will start with the session */
            if (ServerOrClient.preformHandshake()) ServerOrClient.startCommunication();
            ServerOrClient.closeAssets(); // Deallocating myServerOrClients instance fields
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
