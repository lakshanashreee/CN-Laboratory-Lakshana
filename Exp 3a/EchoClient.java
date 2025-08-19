import java.io.*;
import java.net.*;
import java.util.Scanner;

public class EchoClient {
    public static void main(String[] args) {
        try {
            Socket socket = new Socket("localhost", 6666);
            System.out.println("Connected to Echo Server.");

            DataInputStream dis = new DataInputStream(socket.getInputStream());
            DataOutputStream dos = new DataOutputStream(socket.getOutputStream());
            Scanner scanner = new Scanner(System.in);

            String message = "";
            while (!message.equalsIgnoreCase("stop")) {
                System.out.print("Enter message: ");
                message = scanner.nextLine();
                dos.writeUTF(message);

                if (!message.equalsIgnoreCase("stop")) {
                    String echo = dis.readUTF();
                    System.out.println("Server echoed: " + echo);
                }
            }

            dis.close();
            dos.close();
            socket.close();
            System.out.println("Client disconnected.");
        } catch (IOException e) {
            System.out.println("Client Error: " + e.getMessage());
        }
    }
}
