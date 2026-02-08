import java.io.*;
import java.net.*;
import java.util.Scanner;

public class ChatClient {

    private static final String SERVER_ADDRESS = "localhost";
    private static final int SERVER_PORT = 5000;

    public static void main(String[] args) {

        try (Socket socket = new Socket(SERVER_ADDRESS, SERVER_PORT)) {

            System.out.println("═══════════════════════════════");
            System.out.println("  Connected to Chat Server");
            System.out.println("═══════════════════════════════");

            BufferedReader in = new BufferedReader(
                    new InputStreamReader(socket.getInputStream())
            );
            PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
            Scanner scanner = new Scanner(System.in);

            Thread receiveThread = new Thread(() -> {
                try {
                    String message;

                    while ((message = in.readLine()) != null) {
                        System.out.println(message);
                    }
                } catch (IOException e) {
                    System.out.println("Connection lost");
                }
            });
            receiveThread.start();

            String userInput;
            while (true) {
                userInput = scanner.nextLine();
                out.println(userInput);

                if (userInput.equalsIgnoreCase("/quit")) {
                    System.out.println("Disconnected from server");
                    break;
                }
            }

            scanner.close();

        } catch (IOException e) {
            System.out.println("Error connecting to server: " + e.getMessage());
        }
    }
}
