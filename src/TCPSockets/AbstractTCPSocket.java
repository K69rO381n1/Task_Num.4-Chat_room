package TCPSockets;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintStream;
import java.net.Socket;


public abstract class AbstractTCPSocket {

    String CLIENT_HANDSHAKE_KEYWORD = "hi";
    String SERVER_HANDSHAKE_KEYWORD = "HI";

    BufferedReader inputToThisSocket;
    PrintStream outputFromThisSocket;
    protected Socket myClient;

    abstract public boolean preformHandshake() throws IOException;

    abstract public void startCommunication() throws IOException;

    abstract public void closeAssets() throws IOException;
}
