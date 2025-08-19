import java.io.*;
import java.net.*;

public class EchoServer {
    public static void main(String[] args) {
        try {
            ServerSocket serverSocket = new ServerSocket(6666);
            System.out.println("Echo Server is running...");

            Socket socket = serverSocket.accept();
            System.out.println("Client connected.");

            DataInputStream dis = new DataInputStream(socket.getInputStream());
            DataOutputStream dos = new DataOutputStream(socket.getOutputStream());

            String message;
            while (!(message = dis.readUTF()).equalsIgnoreCase("stop")) {
                System.out.println("Received: " + message);
                dos.writeUTF("Echo: " + message);
            }

            dis.close();
            dos.close();
            socket.close();
            serverSocket.close();
            System.out.println("Server stopped.");
        } catch (IOException e) {
            System.out.println("Server Error: " + e.getMessage());
        }
    }
}
