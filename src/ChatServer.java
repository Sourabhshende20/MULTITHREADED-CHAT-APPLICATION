import java.io.*;
import java.net.*;
import java.util.*;

public class ChatServer {

    private static final int PORT = 5000;

    private static Set<ClientHandler> clients = new HashSet<>();

    public static void main(String[] args) {
        System.out.println("═══════════════════════════════");
        System.out.println("    CHAT SERVER STARTED");
        System.out.println("    Port: " + PORT);
        System.out.println("═══════════════════════════════");

        try (ServerSocket serverSocket = new ServerSocket(PORT)) {

            while (true) {
                Socket clientSocket = serverSocket.accept();
                System.out.println("New client connected: " + clientSocket.getInetAddress());


                ClientHandler clientHandler = new ClientHandler(clientSocket);

                clients.add(clientHandler);

                new Thread(clientHandler).start();
            }

        } catch (IOException e) {
            System.out.println("Server error: " + e.getMessage());
        }
    }

    public static void broadcast(String message, ClientHandler sender) {
        for (ClientHandler client : clients) {
            if (client != sender) {
                client.sendMessage(message);
            }
        }
    }

    public static void removeClient(ClientHandler client) {
        clients.remove(client);
        System.out.println("Client disconnected. Total clients: " + clients.size());
    }
}

class ClientHandler implements Runnable {

    private Socket socket;
    private PrintWriter out;
    private BufferedReader in;
    private String username;

    public ClientHandler(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {
        try {
            in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            out = new PrintWriter(socket.getOutputStream(), true); // true = auto-flush

            out.println("Enter your username:");
            username = in.readLine(); // Wait for client to send username

            System.out.println(username + " joined the chat");
            ChatServer.broadcast(username + " joined the chat!", this);

            String message;
            while ((message = in.readLine()) != null) {
                if (message.equalsIgnoreCase("/quit")) {
                    break;
                }

                System.out.println(username + ": " + message);

                ChatServer.broadcast(username + ": " + message, this);
            }

        } catch (IOException e) {
            System.out.println("Error with client " + username + ": " + e.getMessage());
        } finally {

            disconnect();
        }
    }

    public void sendMessage(String message) {
        out.println(message);
    }

    private void disconnect() {
        try {
            if (username != null) {
                System.out.println(username + " left the chat");
                ChatServer.broadcast(username + " left the chat!", this);
            }

            ChatServer.removeClient(this);

            socket.close();
        } catch (IOException e) {
            System.out.println("Error disconnecting: " + e.getMessage());
        }
    }
}