import java.io.*;
import java.net.*;

public class SocketHTTPClient {
    public static void main(String[] args) {
        try {
            // Step 1: Create a socket to connect to the website on port 80
            Socket socket = new Socket("www.martinbroadhurst.com", 80);

            // Step 2: Get the output stream to send an HTTP GET request
            PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
            out.println("GET / HTTP/1.1");
            out.println("Host: www.martinbroadhurst.com");
            out.println("Connection: close"); // Ensure server closes connection
            out.println(); // End of headers

            // Step 3: Read and print the server's response using BufferedReader
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            String responseLine;
            while ((responseLine = in.readLine()) != null) {
                System.out.println(responseLine);
            }

            // Step 4: Close all resources
            in.close();
            out.close();
            socket.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
