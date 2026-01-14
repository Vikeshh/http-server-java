import java.io.IOException;
import java.io.*;
import java.net.*;
//import java.net.Socket;

public class Main {
  public static void main(String[] args) {
    // You can use print statements as follows for debugging, they'll be visible when running tests.
    System.out.println("Logs from your program will appear here!");
    //
     try {
       ServerSocket serverSocket = new ServerSocket(4221);

       // Since the tester restarts your program quite often, setting SO_REUSEADDR
       // ensures that we don't run into 'Address already in use' errors
       serverSocket.setReuseAddress(true);

       // Wait for connection from client.
       Socket clientSocket = serverSocket.accept();
       InputStream input = clientSocket.getInputStream();
       BufferedReader reader = new BufferedReader(new InputStreamReader(input));
       String line = reader.readLine();
       if(line==null) return;
       String[] split=line.split(" ");
       String method=split[0];
       String path=split[1];
       String response="HTTP/1.1 404 Not Found\r\n\r\n";
       if(path.equals("/")) response =  "HTTP/1.1 200 OK\r\n\r\n";
         //or flush
       System.out.println("accepted new connection");
       clientSocket.getOutputStream().write(response.getBytes());

       clientSocket.close();
     } catch (IOException e) {
       System.out.println("IOException: " + e.getMessage());
     }
  }
}
