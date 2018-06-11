import common.SocketRequest;
import common.SocketResponse;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class SocketServer {

    private int port;

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


            /*executor.shutdown();
            try {
                serverSocket.close();
            } catch (IOException e) {
                System.err.println(e.getMessage());
                return;
            }*/
        }
    }

    public class EchoServerClientHandler implements Runnable {
        private Socket socket;
        private SocketServerResponse socketServerResponse;
        private ObjectOutputStream out;
        private ObjectInputStream in;
        public EchoServerClientHandler(Socket socket) {
            this.socket = socket;
            this.socketServerResponse = new SocketServerResponse();
            try {
                this.out = new ObjectOutputStream(socket.getOutputStream());
                this.in = new ObjectInputStream(socket.getInputStream());
            }catch (IOException e){
                System.err.println(e.getMessage());

            }
        }
        public void run() {

            try {
                while (true) {

                    SocketRequest request = (SocketRequest) in.readObject();
                    SocketResponse response = socketServerResponse.sendResponse(request);
                    out.writeObject(response);
                    //in.close();
                    //out.close();
                    //socket.close();
                }
            } catch (Exception e) {
                System.err.println(e.getMessage());
            }
        }
    }
}

