package client.SocketController;

import common.Classes.Fornitore;
import common.Interface.iFornitoreDAO;
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

public class SocketProvidersController implements iFornitoreDAO {

    private Socket client;
    private ObjectInputStream in;
    private ObjectOutputStream out;

    public SocketProvidersController(Socket client, ObjectInputStream in,ObjectOutputStream out){
        this.client = client;
        this.in = in;
        this.out = out;
    }
    @Override
    public void inserisciFornitore(String partita_iva, String cf, String nome, String cognome, LocalDate data, String indirizzo, String telefono) throws RemoteException, SQLException {
            SocketResponse response;


            try {

                SocketRequest r = new SocketRequest(SocketRequestType.CREATE_PROVIDERS, partita_iva, cf, nome, cognome, data, indirizzo, telefono);

                out.writeObject(r);
                response = (SocketResponse) in.readObject();
                out.flush();

                if(response.eccezione) throw new RemoteException(((Exception)response.returnValue).getMessage());
                //return (Boolean)response.returnValue;

            } catch (UnknownHostException e) {
                e.printStackTrace();
                System.exit(1);

            } catch (IOException e) {
                e.printStackTrace();
                System.exit(1);

            }
            catch (ClassNotFoundException e){
                e.printStackTrace();
                System.exit(1);

            }
        }

    @Override
    public void modificaFornitore(String partita_iva, String cf, String nome, String cognome, LocalDate data, String indirizzo, String telefono) throws RemoteException, SQLException {

            SocketResponse response;

            try {

                SocketRequest r = new SocketRequest(SocketRequestType.UPDATE_PROVIDERS, partita_iva, cf, nome, cognome, data, indirizzo, telefono);


                out.writeObject(r);
                response = (SocketResponse) in.readObject();
                out.flush();

                if(response.eccezione) throw new RemoteException(((Exception)response.returnValue).getMessage());
                //return (Boolean)response.returnValue;

            } catch (UnknownHostException e) {
                e.printStackTrace();
                System.exit(1);

            } catch (IOException e) {
                e.printStackTrace();
                System.exit(1);

            }
            catch (ClassNotFoundException e){
                e.printStackTrace();
                System.exit(1);

            }
        }


    @Override
    public List<Fornitore> getAllFornitore() throws RemoteException, SQLException {

        SocketResponse response;

        try {

            SocketRequest r = new SocketRequest(SocketRequestType.GET_ALL_PROVIDERS);


            out.writeObject(r);
            response = (SocketResponse) in.readObject();
            out.flush();


            if(response.eccezione) throw new RemoteException(((Exception)response.returnValue).getMessage());
            return (List<Fornitore>) response.returnValue;

        } catch (UnknownHostException e) {
            e.printStackTrace();
            System.exit(1);
            return null;

        } catch (IOException e) {
            e.printStackTrace();
            System.exit(1);
            return null;

        }
        catch (ClassNotFoundException e){
            e.printStackTrace();
            System.exit(1);
            return null;

        }


    }

    @Override
    public void cancellaFornitore(String cf) throws RemoteException, SQLException {

            SocketResponse response;

            try {

                SocketRequest r = new SocketRequest(SocketRequestType.DELETE_PROVIDERS, cf);


                out.writeObject(r);
                response = (SocketResponse) in.readObject();
                out.flush();

                if(response.eccezione) throw new RemoteException(((Exception)response.returnValue).getMessage());


            } catch (UnknownHostException e) {
                e.printStackTrace();
                System.exit(1);

            } catch (IOException e) {
                e.printStackTrace();
                System.exit(1);

            }
            catch (ClassNotFoundException e){
                e.printStackTrace();
                System.exit(1);

            }
        }



    @Override
    public List<String> getAllCf() throws RemoteException, SQLException {

        SocketResponse response;

        try {

            SocketRequest r = new SocketRequest(SocketRequestType.GET_ALL_CF_PROVIDERS);


            out.writeObject(r);
            response = (SocketResponse) in.readObject();
            out.flush();


            if(response.eccezione) throw new RemoteException(((Exception)response.returnValue).getMessage());
            return (List<String>) response.returnValue;

        } catch (UnknownHostException e) {
            e.printStackTrace();
            System.exit(1);
            return null;

        } catch (IOException e) {
            e.printStackTrace();
            System.exit(1);
            return null;

        }
        catch (ClassNotFoundException e){
            e.printStackTrace();
            System.exit(1);
            return null;

        }


    }

    @Override
    public List<String> getAllPartitaIVA() throws RemoteException, SQLException {

        SocketResponse response;

        try {

            SocketRequest r = new SocketRequest(SocketRequestType.GET_ALL_PARTITA_IVA);


            out.writeObject(r);
            response = (SocketResponse) in.readObject();
            out.flush();


            if(response.eccezione) throw new RemoteException(((Exception)response.returnValue).getMessage());
            return (List<String>) response.returnValue;

        } catch (UnknownHostException e) {
            e.printStackTrace();
            System.exit(1);
            return null;

        } catch (IOException e) {
            e.printStackTrace();
            System.exit(1);
            return null;

        }
        catch (ClassNotFoundException e){
            e.printStackTrace();
            System.exit(1);
            return null;

        }


    }
}
