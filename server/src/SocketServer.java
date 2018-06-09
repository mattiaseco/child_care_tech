import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class SocketServer {

    private int port;
    private ServerSocket serverSocket;

    public SocketServer(int port) {
        this.port = port;
    }


    public void startServer() throws IOException {
        ExecutorService executor = Executors.newCachedThreadPool();
        ServerSocket serverSocket;
        try {
            serverSocket = new ServerSocket(port);
        } catch (IOException e) {
            System.err.println(e.getMessage()); // porta non disponibile
            return;
        }
        System.out.println("[SOCKET]Server ready");
        while (true) {
            try {
                Socket socket = serverSocket.accept();
                executor.submit(new EchoServerClientHandler(socket));
            } catch (IOException e) {
                break; // entrerei qui se serverSocket venisse chiuso
            }

            executor.shutdown();
            try {
                serverSocket.close();
            } catch (IOException e) {
                System.err.println(e.getMessage());
                return;
            }
        }
    }

    public class EchoServerClientHandler implements Runnable {
        private Socket socket;
        public EchoServerClientHandler(Socket socket) {
            this.socket = socket;
        }
        public void run() {
            try {
                Scanner in = new Scanner(socket.getInputStream());
                PrintWriter out = new PrintWriter(socket.getOutputStream());
                // leggo e scrivo nella connessione finche' non ricevo "quit"
                while (true) {
                    String line = in.nextLine();
                    if (line.equals("quit")) {
                        break;
                    } else {
                        out.println("Received: " + line);
                        out.flush();
                    }
                }
                // chiudo gli stream e il socket
                in.close();
                out.close();
                socket.close();
            } catch (IOException e) {
                System.err.println(e.getMessage());
            }
        }
    }
}

