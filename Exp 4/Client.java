import java.io.*;
import java.net.*;

public class Client {
    public static void main(String[] args) {
        try {
            DatagramSocket clientSocket = new DatagramSocket();
            InetAddress serverAddress = InetAddress.getByName("127.0.0.1");

            byte[] sendBuffer;
            byte[] receiveBuffer = new byte[1024];

            BufferedReader userInput = new BufferedReader(new InputStreamReader(System.in));
            System.out.print("Enter a Domain Name or IP Address: ");
            String input = userInput.readLine();

            sendBuffer = input.getBytes();
            DatagramPacket sendPacket = new DatagramPacket(sendBuffer, sendBuffer.length, serverAddress, 1309);
            clientSocket.send(sendPacket);

            DatagramPacket receivePacket = new DatagramPacket(receiveBuffer, receiveBuffer.length);
            clientSocket.receive(receivePacket);

            String response = new String(receivePacket.getData()).trim();
            System.out.println("Response from server: " + response);

            clientSocket.close();
        } catch (Exception e) {
            System.out.println("Client Error: " + e.getMessage());
        }
    }
}
