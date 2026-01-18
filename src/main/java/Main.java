import java.io.IOException;
import java.io.*;
import java.net.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;
//import java.net.Socket;

public class Main {
  public static void handling(Socket clientSocket,String directory) {
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
      String header;

      // User Agent Block
      String uA = "";
      int len2 = 0;
      int contentLen=0;
      boolean supportsGzip = false;
      Map<String, String> headers = new HashMap<>();

      while ((header = reader.readLine()) != null && !header.isEmpty()) {
        String[] headerParts = header.split(": ");
        if (headerParts.length == 2) {
          headers.put(headerParts[0].toLowerCase(), headerParts[1]);
        }
      }
      String acceptEncoding = headers.get("accept-encoding");
      if (acceptEncoding != null) {
        String[] encodings = acceptEncoding.split(",");
        for (String encoding : encodings) {
          if (encoding.trim().equalsIgnoreCase("gzip")) {
            supportsGzip = true;
            break;
          }
        }
      }
      uA = headers.getOrDefault("user-agent", "");
      len2 = uA.length();
      String cLenStr = headers.get("content-length");
      if (cLenStr != null) contentLen = Integer.parseInt(cLenStr.trim());
      // Extraction block
      String response = "HTTP/1.1 404 Not Found\r\n\r\n";
      if (path.equals("/")) response = "HTTP/1.1 200 OK\r\n\r\n";
        //or flush
        // ECHO
      else if (path.startsWith("/echo/")) {
        String text = path.substring(6);
        int len = text.length();
        StringBuilder responseBdr = new StringBuilder();
        responseBdr.append("HTTP/1.1 200 OK\r\n" + "Content-Type: text/plain\r\n");
        if(supportsGzip){
          responseBdr.append("Content-Encoding: gzip\r\n");
        }
        responseBdr.append("Content-Length: " + len + "\r\n\r\n");
        responseBdr.append(text);
        response = responseBdr.toString();
      }
      else if (path.equals("/user-agent")) {
        response = "HTTP/1.1 200 OK\r\n" + "Content-Type: text/plain\r\n" + "Content-Length: " + len2 + "\r\n\r\n" + uA;
      }else if(path.startsWith("/files/")){
        String file= path.substring(7);
        Path filePath= Paths.get(directory,file);
        if(method.equals("GET")){
          if (Files.exists(filePath)) {
            byte[] content = Files.readAllBytes(filePath);
            int lenF = content.length;
            String hdr = "HTTP/1.1 200 OK\r\n" + "Content-Type: application/octet-stream\r\n" + "Content-Length: " + lenF + "\r\n\r\n";
            clientSocket.getOutputStream().write(hdr.getBytes());
            clientSocket.getOutputStream().write(content);
            return;
          }
        }else if(method.equals("POST")){
          char[] buffer = new char[contentLen];
          reader.read(buffer,0,contentLen);
          String body = new String(buffer);
          Files.writeString(filePath,body);
          response ="HTTP/1.1 201 Created\r\n\r\n";
        }
      }

      System.out.println("accepted new connection");
      clientSocket.getOutputStream().write(response.getBytes());

      clientSocket.close();
    } catch(IOException e) {
      System.out.println("IOException: " + e.getMessage());
    }
  }

  public static void main(String[] args) {
    // You can use print statements as follows for debugging, they'll be visible when running tests.
//    System.out.println("Logs from your program will appear here!");
      String directory="";

      for(int i=0;i<args.length;i++){
        if(args[i].equals("--directory")&&i+1<args.length){
          directory=args[i+1];
        }
      }
      final String fdr = directory;
      try{
        ServerSocket serverSocket = new ServerSocket(4221);
        serverSocket.setReuseAddress(true);
        while(true){
          Socket clientSocket = serverSocket.accept();
          Thread t= new Thread(() -> handling(clientSocket,fdr));
          t.start();
        }
      }catch (IOException e) {
        System.out.println("IOException: " + e.getMessage());
      }
    //

  }
}