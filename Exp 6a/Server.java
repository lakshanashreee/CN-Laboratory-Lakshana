// Server.java
import java.io.*;
import java.net.*;

public class Server {
    public static void main(String[] args) {
        try {
            // Step 1: Create a ServerSocket on port 3636
            ServerSocket serverSocket = new ServerSocket(3636);
            System.out.println("Server is running and waiting for connection...");

            // Step 2: Accept a client connection
            Socket clientSocket = serverSocket.accept();
            System.out.println("Client connected.");

            // Step 3: Setup input and output streams
            BufferedReader reader = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            DataOutputStream writer = new DataOutputStream(clientSocket.getOutputStream());

            // Step 4: Hardcoded IP-to-MAC mapping table
            String[] ipTable = {"165.165.80.80", "165.165.79.1"};
            String[] macTable = {"6A:08:AA:C2", "8A:BC:E3:FA"};

            // Step 5: Wait for IP input from client and respond
            while (true) {
                String inputIP = reader.readLine();
                if (inputIP == null) break; // If no input, exit loop

                boolean found = false;
                for (int i = 0; i < ipTable.length; i++) {
                    if (inputIP.equals(ipTable[i])) {
                        writer.writeBytes(macTable[i] + "\n");
                        found = true;
                        break;
                    }
                }

                if (!found) {
                    writer.writeBytes("MAC not found\n");
                }
            }

            // Step 6: Close resources
            reader.close();
            writer.close();
            clientSocket.close();
            serverSocket.close();

        } catch (IOException e) {
            System.out.println("Server error: " + e.getMessage());
        }
    }
}

