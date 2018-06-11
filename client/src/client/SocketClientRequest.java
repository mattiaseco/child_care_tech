package client;

import client.controller.LoginController;
import common.SocketRequest;
import common.SocketRequestType;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.UnknownHostException;

public class SocketClientRequest {

    public SocketClientRequest(){}

    public static void sendRequest(){

        Socket client = null;
        ObjectOutputStream out = null;
        ObjectInputStream in = null;

        try {

            SocketRequest r = new SocketRequest(SocketRequestType.LOGIN,"username","password");
            client = new Socket("127.0.0.1", 1337);
            out = new ObjectOutputStream(client.getOutputStream());
            in = new ObjectInputStream(client.getInputStream());

            out.writeObject(r);
            out.flush();

            out.close();
            in.close();
            client.close();

        } catch (UnknownHostException e) {
        e.printStackTrace();
        System.exit(1);
    } catch (IOException e) {
        e.printStackTrace();
        System.exit(1);
    }

    }
}
