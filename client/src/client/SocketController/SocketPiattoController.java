package client.SocketController;

import common.Classes.Fornitore;
import common.Classes.Ingredienti;
import common.Classes.Piatto;
import common.Interface.iPiattoDAO;
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
import java.util.List;

public class SocketPiattoController implements iPiattoDAO {
    private Socket client;
    private ObjectInputStream in;
    private ObjectOutputStream out;

    public SocketPiattoController(Socket client, ObjectInputStream in,ObjectOutputStream out){
        this.client = client;
        this.in = in;
        this.out = out;
    }
    @Override
    public void inserisciPiatto(String nome_p, String tipo) throws RemoteException, SQLException {

            SocketResponse response;


            try {

                SocketRequest r = new SocketRequest(SocketRequestType.CREATE_DISHES, nome_p, tipo);

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
    public void modificaPiatto(String nome_p, String tipo) throws RemoteException, SQLException {

    }

    @Override
    public List<Piatto> getAllPiatti() throws RemoteException, SQLException {

        SocketResponse response;

        try {

            SocketRequest r = new SocketRequest(SocketRequestType.GET_ALL_PIATTI);


            out.writeObject(r);
            response = (SocketResponse) in.readObject();
            out.flush();


            if(response.eccezione) throw new RemoteException(((Exception)response.returnValue).getMessage());
            return (List<Piatto>) response.returnValue;

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
    public void cancellaPiatti(String nome_p) throws RemoteException, SQLException {

            SocketResponse response;


            try {

                SocketRequest r = new SocketRequest(SocketRequestType.DELETE_DISHES, nome_p);

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
    public List<Piatto> getAllPrimi() throws RemoteException, SQLException {

        SocketResponse response;

        try {

            SocketRequest r = new SocketRequest(SocketRequestType.GET_ALL_PRIMI);


            out.writeObject(r);
            response = (SocketResponse) in.readObject();
            out.flush();


            if(response.eccezione) throw new RemoteException(((Exception)response.returnValue).getMessage());
            return (List<Piatto>) response.returnValue;

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
    public List<Piatto> getAllSecondi() throws RemoteException, SQLException {

        SocketResponse response;

        try {

            SocketRequest r = new SocketRequest(SocketRequestType.GET_ALL_SECONDI);


            out.writeObject(r);
            response = (SocketResponse) in.readObject();
            out.flush();


            if(response.eccezione) throw new RemoteException(((Exception)response.returnValue).getMessage());
            return (List<Piatto>) response.returnValue;

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
    public List<Piatto> getAllContorni() throws RemoteException, SQLException {

        SocketResponse response;

        try {

            SocketRequest r = new SocketRequest(SocketRequestType.GET_ALL_CONTORNI);


            out.writeObject(r);
            response = (SocketResponse) in.readObject();
            out.flush();


            if(response.eccezione) throw new RemoteException(((Exception)response.returnValue).getMessage());
            return (List<Piatto>) response.returnValue;

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
    public void inserisciIngredientePiatto(Piatto piatto, Ingredienti ingrediente) throws RemoteException, SQLException {

        SocketResponse response;

        try {

            SocketRequest r = new SocketRequest(SocketRequestType.ADD_INGREDIENTE_PIATTO,piatto,ingrediente);


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
    public void cancellaIngredientePiatto(Piatto piatto, Ingredienti ingredienti) throws RemoteException, SQLException {

        SocketResponse response;

        try {

            SocketRequest r = new SocketRequest(SocketRequestType.DELETE_INGREDIENTE_PIATTO,piatto,ingredienti);


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
    public List<Ingredienti> getAllIngredientiPiatto(Piatto piatto) throws RemoteException, SQLException {

        SocketResponse response;

        try {

            SocketRequest r = new SocketRequest(SocketRequestType.GET_ALL_INGREDIENTE_PIATTO, piatto);


            out.writeObject(r);
            response = (SocketResponse) in.readObject();
            out.flush();


            if(response.eccezione) throw new RemoteException(((Exception)response.returnValue).getMessage());
            return (List<Ingredienti>) response.returnValue;

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
    public List<String> getAllNomiPiatti() throws RemoteException, SQLException {

        SocketResponse response;

        try {

            SocketRequest r = new SocketRequest(SocketRequestType.GET_ALL_NOME_PIATTI);


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
    public Piatto getPiatto(String nome_p)throws RemoteException,SQLException{
        return null;
    }
}
