package client.SocketController;

import common.Interface.iRegisterController;
import common.SocketRequest;
import common.SocketRequestType;
import common.SocketResponse;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.net.Socket;
import java.net.UnknownHostException;
import java.rmi.RemoteException;
import java.sql.SQLException;

public class SocketRegisterController implements iRegisterController {
    private Socket client;
    private ObjectInputStream in;
    private ObjectOutputStream out;

    public SocketRegisterController(Socket client, ObjectInputStream in,ObjectOutputStream out){
        this.client = client;
        this.in = in;
        this.out = out;
    }

    @Override
    public void register(String username, String password) throws RemoteException{
            SocketResponse response;
            try {

                SocketRequest r = new SocketRequest(SocketRequestType.REGISTER, username, password);
                out.writeObject(r);
                response = (SocketResponse) in.readObject();
                out.flush();
                if(response.eccezione) throw new RemoteException(((Exception)response.returnValue).getMessage());
                //response.returnValue;

            } catch (IOException | ClassNotFoundException e) {
                e.printStackTrace();
                System.exit(1);

            }
    }
}
