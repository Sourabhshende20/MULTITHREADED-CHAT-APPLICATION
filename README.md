#  Multithreaded Chat Application

*Company name*: CODTECH IT SOLUTIONS

*Name*: Shende Sourabh Gopal

*Intern ID*: CTIS3927

*Domain*: Java Programming

*Duration*: 4 Weeks

*Mentor*: Neela Santhosh Kumar

# Project Overview
This project implements a multithreaded client-server chat application using Java sockets.​
The system consists of a central server and multiple clients that can connect simultaneously and exchange messages in real time.
It demonstrates fundamental networking concepts such as TCP connections, ports, sockets, and also shows how multithreading is used to handle multiple clients concurrently.
The project is a practical example of real-time communication and serves as a foundation for more advanced chat or messaging platforms.

# Features
Server application that listens on a specific port and accepts incoming client connections using ServerSocket and Socket classes.
​

Creation of a separate thread for each connected client so that many users can chat at the same time without blocking.

Broadcast mechanism where messages from one client are relayed to all other connected clients (or to selected clients depending on implementation).

Clean disconnection handling when a client leaves the chat, with proper resource cleanup.

Console-based user interface for both server and client, keeping the focus on networking and concurrency logic.

# Tools and Technologies Used
Programming language: Java.

# Core APIs:

java.net for sockets and networking (ServerSocket, Socket).

java.io for input/output streams (BufferedReader, PrintWriter, etc.).

java.lang.Thread or ExecutorService for managing multiple client threads.

# Editor/IDE: 
IntelliJ IDEA for project setup, coding, and debugging.

# Project Structure (Example)
src/

ChatServer.java – Main server program that accepts connections and manages clients.

ClientHandler.java – Runnable or thread class that handles communication with a single client.

ChatClient.java – Client-side program for connecting to the server and sending/receiving messages.

# How to Run
Open the project in IntelliJ IDEA and configure the Java SDK.

Run ChatServer first to start the server on a chosen port (e.g., 12345).

Run multiple instances of ChatClient, either from IntelliJ or command line, and enter the server IP and port.

Start sending messages from clients and observe how they are broadcast to all participants.

# Real-World Applications
Basic internal chat tools for small teams within a LAN.​

Educational demonstrations in networking and operating systems courses to explain sockets and threading.

Foundation for more advanced applications like multiplayer game lobbies, real-time collaboration tools, and messaging back-ends.

Prototype for future GUI-based chat systems using JavaFX or web-socket-based services.

# Output:
