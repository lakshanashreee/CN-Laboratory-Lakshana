import java.io.*;
import java.net.*;

public class FTPClient {
    public static void main(String[] args) throws Exception {
        InetAddress serverAddress = InetAddress.getLocalHost();
        Socket socket = new Socket(serverAddress, 1024);

        BufferedReader keyboardReader = new BufferedReader(new InputStreamReader(System.in));
        System.out.print("Enter the name to save the received file as: ");
        String fileName = keyboardReader.readLine();

        BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        PrintWriter fileWriter = new PrintWriter(new FileWriter(fileName));

        String line;
        while ((line = in.readLine()) != null) {
            fileWriter.println(line);
        }

        fileWriter.close();
        in.close();
        socket.close();

        System.out.println("File received and saved as " + fileName);
    }
}
