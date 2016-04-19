package TCPSockets;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.Socket;
import java.util.Scanner;

import Utils.*;

public class TCPClient extends AbstractTCPSocket {

	private static final String QUIT_KEY = "-q";
	private static final String ENTER_YOUR_TEXT_PROMPT_TO_USER = "Enter your message: ";
	private static final String SELF_CALCULATED_MD5_ON_MSG_PROMPT = "Original message MD5: ";

	private Scanner usersInputReader = new Scanner(System.in);

	public TCPClient(String host, int port) throws IOException{
		myClient = new Socket(host, port);

		inputToThisSocket = new BufferedReader(new InputStreamReader(myClient.getInputStream()));
		outputFromThisSocket = new PrintStream(myClient.getOutputStream());
	}
	
	public boolean preformHandshake() throws IOException{
		if (inputToThisSocket.readLine().equals(SERVER_HANDSHAKE_KEYWORD)) {
			outputFromThisSocket.println(CLIENT_HANDSHAKE_KEYWORD);
			return true;
		}
		return false;
	}
	
	public void startCommunication() throws IOException{
		String msgFromUser = null;
		do {
			if (msgFromUser != null) {
				System.out.println(getMD5SignatureFromServer(msgFromUser));
				System.out.println(SELF_CALCULATED_MD5_ON_MSG_PROMPT + MD5.calculateHash(msgFromUser));
			}
			System.out.println(ENTER_YOUR_TEXT_PROMPT_TO_USER);
			msgFromUser = this.usersInputReader.nextLine();

		} while (!msgFromUser.equals(QUIT_KEY));
	}

	private String getMD5SignatureFromServer (String msgFromUser) throws IOException {
		outputFromThisSocket.println(msgFromUser);
		return inputToThisSocket.readLine();
	}
	
	public void closeAssets() throws IOException{
		usersInputReader.close();
		inputToThisSocket.close();
		outputFromThisSocket.close();
		myClient.close();
	}
}
