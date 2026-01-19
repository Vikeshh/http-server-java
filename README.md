Custom HTTP Server (Java)
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

Tech Stack

Language: Java 21+

Networking: TCP/IP (ServerSocket, Socket)

Concurrency: Multithreading (Java Threads, Lambdas)

Protocol: HTTP/1.1

What It Does

Listens for incoming HTTP connections

Handles multiple clients concurrently

Parses raw HTTP requests

Sends basic HTTP responses

Why This Project

Built to gain hands-on experience with:

TCP socket communication

HTTP request/response flow

Multithreaded server design in Java

Status

Core functionality implemented. Further enhancements planned.
