package client.SocketController;

import common.Classes.Pediatra;
import common.Interface.iPediatraDAO;
import common.SocketRequest;
import common.SocketRequestType;
import common.SocketResponse;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.UnknownHostException;
import java.rmi.RemoteException;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;

public class SocketPediatraController implements iPediatraDAO {
    private Socket client;
    private ObjectInputStream in;
    private ObjectOutputStream out;

    public SocketPediatraController(Socket client, ObjectInputStream in, ObjectOutputStream out) {
        this.client = client;
        this.in = in;
        this.out = out;
    }

    @Override
    public void inserisciPediatra(String cf, String nome, String cognome, LocalDate data, String indirizzo, String telefono) throws RemoteException, SQLException {


        SocketResponse response;


        try {

            SocketRequest r = new SocketRequest(SocketRequestType.CREATE_PEDIATRA, cf, nome, cognome, data, indirizzo, telefono);


            out.writeObject(r);
            response = (SocketResponse) in.readObject();
            out.flush();


            if (response.eccezione) throw new RemoteException(((Exception) response.returnValue).getMessage());
            //return (Boolean)response.returnValue;

        } catch (UnknownHostException e) {
            e.printStackTrace();
            System.exit(1);

        } catch (IOException e) {
            e.printStackTrace();
            System.exit(1);

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            System.exit(1);

        }
    }


    @Override
    public void modificaPediatra(String cf, String nome, String cognome, LocalDate data, String indirizzo, String telefono) throws RemoteException, SQLException {

        SocketResponse response;

        try {

            SocketRequest r = new SocketRequest(SocketRequestType.UPDATE_PEDIATRA, cf, nome, cognome, data, indirizzo, telefono);


            out.writeObject(r);
            response = (SocketResponse) in.readObject();
            out.flush();

            if (response.eccezione) throw new RemoteException(((Exception) response.returnValue).getMessage());
            //return (Boolean)response.returnValue;

        } catch (UnknownHostException e) {
            e.printStackTrace();
            System.exit(1);

        } catch (IOException e) {
            e.printStackTrace();
            System.exit(1);

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            System.exit(1);

        }
    }


    @Override
    public List<Pediatra> getAllPediatra() throws RemoteException, SQLException {

        SocketResponse response;

        try {

            SocketRequest r = new SocketRequest(SocketRequestType.GET_ALL_PEDIATRA);


            out.writeObject(r);
            response = (SocketResponse) in.readObject();
            out.flush();


            if (response.eccezione) throw new RemoteException(((Exception) response.returnValue).getMessage());
            return (List<Pediatra>) response.returnValue;

        } catch (UnknownHostException e) {
            e.printStackTrace();
            System.exit(1);
            return null;

        } catch (IOException e) {
            e.printStackTrace();
            System.exit(1);
            return null;

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            System.exit(1);
            return null;

        }


    }

    @Override
    public void cancellaPediatra(String cf) throws RemoteException, SQLException {

        SocketResponse response;


        try {

            SocketRequest r = new SocketRequest(SocketRequestType.DELETE_PEDIATRA, cf);

            out.writeObject(r);
            response = (SocketResponse) in.readObject();
            out.flush();


            if (response.eccezione) throw new RemoteException(((Exception) response.returnValue).getMessage());
            //return (Boolean)response.returnValue;

        } catch (UnknownHostException e) {
            e.printStackTrace();
            System.exit(1);

        } catch (IOException e) {
            e.printStackTrace();
            System.exit(1);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            System.exit(1);

        }
    }


    @Override
    public List<String> getAllCf() throws RemoteException, SQLException {

        SocketResponse response;

        try {

            SocketRequest r = new SocketRequest(SocketRequestType.GET_ALL_CF_PEDIATRA);


            out.writeObject(r);
            response = (SocketResponse) in.readObject();
            out.flush();


            if (response.eccezione) throw new RemoteException(((Exception) response.returnValue).getMessage());
            return (List<String>) response.returnValue;

        } catch (UnknownHostException e) {
            e.printStackTrace();
            System.exit(1);
            return null;

        } catch (IOException e) {
            e.printStackTrace();
            System.exit(1);
            return null;

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            System.exit(1);
            return null;

        }




    }
    @Override
    public Pediatra getPediatra (String cf)throws RemoteException, SQLException {
        return null;
    }

}
