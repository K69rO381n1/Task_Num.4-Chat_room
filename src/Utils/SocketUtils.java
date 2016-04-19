package Utils;

public abstract class SocketUtils {
    private static final String ILLEGAL_ARGUMENTS_PROMPT = "Expected two arguments: <host> <port>";
    protected static final String UNABLE_TO_OPEN_SOCKET_PROMPT = "Unable to open the socket, Please check your arguments.";
    protected static final String COMMUNICATION_ERROR_OCCURRED = "Communication error occurred during session with a client";

    protected static Object[] parseArguments(String[] argsFromUser) {
        Object[] hostAndPort = new Object[2]; // Creating an integers list that will contain the host and the port numbers.
        try {   // Trying to parse the arguments from the user.
            hostAndPort[0] = argsFromUser[0];
            hostAndPort[1] = Integer.parseInt(argsFromUser[1]);
        } catch (NumberFormatException | IndexOutOfBoundsException e) { // If The parse operation fail we will throw to the user a msg to fix it.
            throw new IllegalArgumentException(ILLEGAL_ARGUMENTS_PROMPT);
        }
        return hostAndPort;
    }
}
