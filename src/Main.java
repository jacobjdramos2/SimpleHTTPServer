import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.charset.StandardCharsets;
import java.util.Date;

public class Main {
    public static void main(String[] args) throws IOException {

        final ServerSocket server = new ServerSocket(8080);
        System.out.println("Listening for connection on port 8080");
        while (true) {
            // spin forever
            // Allows to keep program running, without this infinite loop output will finish execution and the
            // server will be shut down.

            final Socket clientSocket = server.accept();

            // 1. Listen and Read HTTP request from the client socket
            InputStreamReader isr = new InputStreamReader(clientSocket.getInputStream());
            BufferedReader reader = new BufferedReader(isr);
            String line = reader.readLine();
            while (!line.isEmpty()) {
                System.out.println(line);
                line = reader.readLine();
            }

            // 2. Preparing an HTTP response
            Date today = new Date();
            String httpResponse = "HTTP/1.1 200 OK\r\n\r\n" + today;

            // 3. Send HTTP response to the client
            clientSocket.getOutputStream().write(httpResponse.getBytes(StandardCharsets.UTF_8));

            // 4. Close the socket
            clientSocket.close();
        }
    }
}