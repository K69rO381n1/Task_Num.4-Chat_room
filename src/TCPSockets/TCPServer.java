package TCPSockets;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.InetAddress;
import java.net.ServerSocket;

import Utils.*;

public class TCPServer extends AbstractTCPSocket {

	// TODO: 4/17/2016 find what these number use for
	private static final int BACKLOG = 50;
	
	private ServerSocket myService;

	public TCPServer(String host, int port) throws IOException{
		myService = new ServerSocket(port, BACKLOG, InetAddress.getByName(host));
		myClient = myService.accept();

		inputToThisSocket = new BufferedReader(new InputStreamReader(myClient.getInputStream()));
		outputFromThisSocket = new PrintStream(myClient.getOutputStream());
	}
	
	public boolean preformHandshake() throws IOException{
		outputFromThisSocket.println(SERVER_HANDSHAKE_KEYWORD);
		return inputToThisSocket.readLine().equals(CLIENT_HANDSHAKE_KEYWORD);
	}
	
	public void startCommunication() throws IOException {
		while (!myClient.isClosed() && myClient.isConnected()) {
			String userInput = inputToThisSocket.readLine();
			if (userInput == null) break;
				outputFromThisSocket.println(buildMessageToUser(userInput));
		}
	}

	private String buildMessageToUser(String userInput) {
		return TimeStamp.getCurrentTime() + " " + StringUtils.reverse(userInput) + " " + MD5.calculateHash(userInput);
	}
	
	public void closeAssets() throws IOException{
			myClient.close();
			inputToThisSocket.close();
			outputFromThisSocket.close();
			myService.close();
	}
	
}
