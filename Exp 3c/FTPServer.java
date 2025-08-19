import java.io.*;
import java.net.*;

public class FTPServer {
    public static void main(String[] args) throws Exception {
        ServerSocket serverSocket = new ServerSocket(1024);
        System.out.println("ServerSocket Generated. Waiting for client...");

        Socket socket = serverSocket.accept();
        System.out.println("Client connected!");

        BufferedReader keyboardReader = new BufferedReader(new InputStreamReader(System.in));
        PrintStream out = new PrintStream(socket.getOutputStream());

        System.out.print("Enter the file name to send: ");
        String fileName = keyboardReader.readLine();
        File file = new File(fileName);

        if (file.exists()) {
            BufferedReader fileReader = new BufferedReader(new FileReader(file));
            String line;
            while ((line = fileReader.readLine()) != null) {
                out.println(line);
            }
            fileReader.close();
            System.out.println("File sent successfully.");
        } else {
            System.out.println("File not found.");
            out.println("ERROR: File not found on server.");
        }

        out.close();
        socket.close();
        serverSocket.close();
    }
}
