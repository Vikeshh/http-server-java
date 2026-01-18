Custom HTTP Server in Java

A lightweight, multithreaded HTTP server built from scratch in Java using low-level socket programming. Designed to demonstrate a deep understanding of networking fundamentals, HTTP protocol handling, and concurrent request processing.

Features

Handles basic HTTP requests over TCP

Supports multiple client connections using multithreading

Parses HTTP request headers manually

Serves static responses

Clean separation of concerns for extensibility

Built using modern Java (Java 21+ compatible)

Tech Stack

Language: Java 21+

Networking: ServerSocket, Socket

Concurrency: Threads / Lambdas

Protocol: HTTP/1.1

I/O: Buffered Streams

Project Structure
src/
 ├── server/
 │   ├── HttpServer.java
 │   ├── ClientHandler.java
 │   └── RequestParser.java
 └── utils/
     └── ResponseBuilder.java

How It Works

Server binds to a specified port using ServerSocket

Listens continuously for incoming client connections

Each connection is handled in a separate thread

HTTP request is read and parsed manually

Appropriate HTTP response is constructed and sent back

Connection is closed after response delivery

Getting Started
Prerequisites

Java 21 or later

Any Java IDE or terminal

Run Locally
javac HttpServer.java
java HttpServer


By default, the server runs on:

http://localhost:8080


You can test it using:

Browser

Postman

curl

curl http://localhost:8080

Sample Response
HTTP/1.1 200 OK
Content-Type: text/plain

Hello from Custom Java HTTP Server

Future Improvements

Support for multiple HTTP methods (POST, PUT, DELETE)

Static file serving

Thread pool implementation

HTTP status code handling

Logging and metrics

Keep-alive connections

Why This Project?

This project was built to:

Strengthen understanding of TCP/IP and HTTP

Explore server-side concurrency in Java

Avoid framework abstractions and work close to the metal
