Custom HTTP Server (Java)
A high-performance, multi-threaded HTTP/1.1 server built from scratch using Java Sockets. This project demonstrates core networking concepts, manual HTTP parsing, and concurrent system design.
![Java](https://img.shields.io/badge/java-%23ED8B00.svg?style=for-the-badge&logo=openjdk&logoColor=white)
![Build](https://img.shields.io/badge/build-passing-brightgreen?style=for-the-badge)
![License](https://img.shields.io/badge/license-MIT-blue?style=for-the-badge)
### 🛠 Tech Stack & Environment
<p align="left">
  <img src="https://skillicons.dev/icons?i=java,maven,ubuntu,github,git" />
</p>

* **Build Tool:** Maven (Dependency management and lifecycle)
* **OS Environment:** Ubuntu/Linux (Targeted for deployment and testing)
* **Version Control:** Git & GitHub
sequenceDiagram
    Participant Client
    Participant Server
    Participant ThreadPool
    
    Client->>Server: TCP Connection (Port 4221)
    Server->>ThreadPool: Submit handling(clientSocket)
    ThreadPool->>Server: Process Request (GET/POST)
    Server->>Client: HTTP/1.1 200 OK (with GZIP if req)
    Note right of Client: Connection Closed/Keep-Alive

### 🧪 Automated Testing
This project was developed using a Test-Driven approach. The server logic is validated against a rigorous suite of concurrent connection and protocol compliance tests.

* **Environment:** Tested on **Ubuntu Linux** to ensure POSIX socket compatibility.
* **Build Lifecycle:** Managed via **Maven** for consistent builds and dependency resolution.
* **CI/CD:** Integrated with automated testers to validate:
    * Concurrent request handling (10+ simultaneous clients).
    * GZIP compression integrity.
    * File I/O edge cases (missing files, large uploads).


A basic HTTP server implemented from scratch in Java using low-level socket programming. This project focuses on understanding how HTTP works under the hood without relying on frameworks.

🧠 Technical Implementation
Concurrent Processing
To prevent "head-of-line blocking," the server utilizes an ExecutorService. Each incoming socket is handed off to a worker thread, allowing the main server loop to remain responsive to new connections.

Manual Protocol Parsing
The server manually implements the HTTP/1.1 protocol:

Request Parsing: Extracting method, path, and headers via BufferedReader.

GZIP Logic: Using GZIPOutputStream to compress data only when the client specifies support in headers.

Connection Management: Monitoring the Connection header to determine whether to persist or terminate the socket.

🧪 Automated Testing
This implementation successfully passed a rigorous suite of automated tests covering:

HTTP Compression: Proper header signaling and compression integrity.

Multiple Connections: Stress testing with simultaneous requests.

Persistent Connections: Efficiently reusing or closing sockets based on client intent.
