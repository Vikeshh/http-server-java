import java.io.IOException;
import java.io.*;
import java.net.*;
import java.util.Locale;
//import java.net.Socket;

public class Main {
  public static void handling(Socket clientSocket) {
    try (clientSocket) {
      // Since the tester restarts your program quite often, setting SO_REUSEADDR
      // ensures that we don't run into 'Address already in use' errors
      // Wait for connection from client.
      InputStream input = clientSocket.getInputStream();
      BufferedReader reader = new BufferedReader(new InputStreamReader(input));
      String line = reader.readLine();
      if (line == null) return;
      String[] split = line.split(" ");
      String method = split[0];
      String path = split[1];
      // User Agent Block
      String uA = "";
      String header;
      int len2 = -1;
      while ((header = reader.readLine()) != null && !header.isEmpty()) {
        if (header.toLowerCase().startsWith("user-agent: ")) {
          uA = header.substring(12).trim();
          len2 = uA.length();
        }
      }
      // Extraction block
      String response = "HTTP/1.1 404 Not Found\r\n\r\n";
      if (path.equals("/")) response = "HTTP/1.1 200 OK\r\n\r\n";
        //or flush
        // ECHO
      else if (path.startsWith("/echo/")) {
        String text = path.substring(6);
        int len = text.length();
        response = "HTTP/1.1 200 OK\r\n" + "Content-Type: text/plain\r\n" + "Content-Length: " + len + "\r\n\r\n" + text;
      } else if (path.equals("/user-agent")) {
        response = "HTTP/1.1 200 OK\r\n" + "Content-Type: text/plain\r\n" + "Content-Length: " + len2 + "\r\n\r\n" + uA;
      }
      System.out.println("accepted new connection");
      clientSocket.getOutputStream().write(response.getBytes());

      clientSocket.close();
    } catch (IOException e) {
      System.out.println("IOException: " + e.getMessage());
    }
  }

  public static void main(String[] args) {
    // You can use print statements as follows for debugging, they'll be visible when running tests.
//    System.out.println("Logs from your program will appear here!");

      try{
        ServerSocket serverSocket = new ServerSocket(4221);
        serverSocket.setReuseAddress(true);
        while(true){
          Socket clientSocket = serverSocket.accept();
          Thread t= new Thread(() -> handling(clientSocket));
          t.start();
        }
      }catch (IOException e) {
        System.out.println("IOException: " + e.getMessage());
      }
    //

  }
}
