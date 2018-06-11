package client.SocketController;

import common.Interface.iLoginController;
import common.SocketRequest;
import common.SocketRequestType;
import common.SocketResponse;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.UnknownHostException;
import java.rmi.RemoteException;

public class SocketLoginController implements iLoginController {
    private Socket client;
    private ObjectInputStream in;
    private ObjectOutputStream out;

    public SocketLoginController(Socket client, ObjectInputStream in,ObjectOutputStream out){
        this.client = client;
        this.in = in;
        this.out = out;
    }

    @Override
    public boolean login(String username, String password) throws RemoteException {
        SocketResponse response;

        try {

            SocketRequest r = new SocketRequest(SocketRequestType.LOGIN, username, password);

            out.writeObject(r);
            response = (SocketResponse) in.readObject();
            out.flush();

            if(response.eccezione) throw new RemoteException(((Exception)response.returnValue).getMessage());
            return (Boolean)response.returnValue;

        } catch (UnknownHostException e) {
            e.printStackTrace();
            System.exit(1);
            return false;
        } catch (IOException e) {
            e.printStackTrace();
            System.exit(1);
            return false;
        }
        catch (ClassNotFoundException e){
            e.printStackTrace();
            System.exit(1);
            return false;
        }
    }


}
