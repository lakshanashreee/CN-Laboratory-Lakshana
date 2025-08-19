// Client.java
import java.io.*;
import java.net.*;

public class Client {
    public static void main(String[] args) {
        try {
            // Step 1: Setup input reader for user
            BufferedReader userInput = new BufferedReader(new InputStreamReader(System.in));

            // Step 2: Connect to the server at localhost:3636
            Socket socket = new Socket("127.0.0.1", 3636);
            System.out.println("Connected to server.");

            // Step 3: Setup input/output streams with server
            BufferedReader serverInput = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            DataOutputStream serverOutput = new DataOutputStream(socket.getOutputStream());

            // Step 4: Prompt user and send IP to server
            System.out.print("Enter the Logical Address (IP): ");
            String ipAddress = userInput.readLine();
            serverOutput.writeBytes(ipAddress + "\n");

            // Step 5: Read and display MAC address from server
            String macAddress = serverInput.readLine();
            System.out.println("The Physical Address is: " + macAddress);

            // Step 6: Close all resources
            userInput.close();
            serverInput.close();
            serverOutput.close();
            socket.close();

        } catch (IOException e) {
            System.out.println("Client error: " + e.getMessage());
        }
    }
}
