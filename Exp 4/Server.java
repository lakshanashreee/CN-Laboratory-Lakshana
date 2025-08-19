import java.io.*;
import java.net.*;

public class Server {
    public static void main(String[] args) {
        try {
            DatagramSocket serverSocket = new DatagramSocket(1309);
            System.out.println("DNS Server is running...");

            while (true) {
                byte[] receiveBuffer = new byte[1024];
                byte[] sendBuffer;

                DatagramPacket receivePacket = new DatagramPacket(receiveBuffer, receiveBuffer.length);
                serverSocket.receive(receivePacket);

                String query = new String(receivePacket.getData()).trim();
                System.out.println("Received query: " + query);

                InetAddress clientAddress = receivePacket.getAddress();
                int clientPort = receivePacket.getPort();

                String[] domainNames = {
                    "www.aptitudeguru.com",
                    "www.downloadcyclone.blogspot.com"
                };
                String[] ipAddresses = {
                    "165.165.80.80",
                    "165.165.79.1"
                };

                String response = "Not found";
                for (int i = 0; i < domainNames.length; i++) {
                    if (query.equalsIgnoreCase(domainNames[i])) {
                        response = ipAddresses[i];
                        break;
                    } else if (query.equals(ipAddresses[i])) {
                        response = domainNames[i];
                        break;
                    }
                }

                sendBuffer = response.getBytes();
                DatagramPacket sendPacket = new DatagramPacket(sendBuffer, sendBuffer.length, clientAddress, clientPort);
                serverSocket.send(sendPacket);

                System.out.println("Response sent: " + response);
                break; // Remove this `break;` if you want the server to run continuously
            }

            serverSocket.close();
        } catch (Exception e) {
            System.out.println("Server Error: " + e.getMessage());
        }
    }
}
