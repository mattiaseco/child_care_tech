package client.SocketController;

import common.Classes.Bambino;
import common.Classes.Gita;
import common.Classes.Pullman;
import common.Interface.iGitaDAO;
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

public class SocketTripController implements iGitaDAO {
    private Socket client;
    private ObjectInputStream in;
    private ObjectOutputStream out;

    public SocketTripController(Socket client, ObjectInputStream in,ObjectOutputStream out){
        this.client = client;
        this.in = in;
        this.out = out;
    }
    @Override
    public void inserisciGita(int codice_g, String destinazione, LocalDate data_partenza, LocalDate data_ritorno, Double costo, String descrizione) throws RemoteException, SQLException {

            SocketResponse response;


            try {

                SocketRequest r = new SocketRequest(SocketRequestType.CREATE_TRIP, codice_g,destinazione,data_partenza,data_ritorno,costo,descrizione);


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
    public List<Gita> getAllGite() throws RemoteException, SQLException {

        SocketResponse response;

        try {

            SocketRequest r = new SocketRequest(SocketRequestType.GET_ALL_TRIP);


            out.writeObject(r);
            response = (SocketResponse) in.readObject();
            out.flush();


            if(response.eccezione) throw new RemoteException(((Exception)response.returnValue).getMessage());
            return (List<Gita>) response.returnValue;

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
    public void insertNumPartecipanti(int codice_g, int num_partecipanti) throws RemoteException, SQLException {
            SocketResponse response;


            try {

                SocketRequest r = new SocketRequest(SocketRequestType.CREATE_STEP2_TRIP, codice_g, num_partecipanti);

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
    public void insertNumPullman(int codice_g, int num_pullman) throws RemoteException, SQLException {
            SocketResponse response;


            try {

                SocketRequest r = new SocketRequest(SocketRequestType.CREATE_STEP3_TRIP, codice_g, num_pullman);


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
    public void cancellaGita(int codice_g) throws RemoteException, SQLException {

            SocketResponse response;

            try {

                SocketRequest r = new SocketRequest(SocketRequestType.DELETE_TRIP, codice_g);

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
    public void inserisciBambinoGita(int codice_g, Bambino bambino) throws RemoteException, SQLException {

        SocketResponse response;

        try {

            SocketRequest r = new SocketRequest(SocketRequestType.ADD_KID_TRIP,codice_g,bambino);

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
    public void cancellaBambinoGita(int codice_g, Bambino bambino) throws RemoteException, SQLException {

        SocketResponse response;

        try {

            SocketRequest r = new SocketRequest(SocketRequestType.DELETE_KID_TRIP,codice_g,bambino);

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
    public void inserisciBambinoPullman(Pullman pullman, Bambino bambino) throws RemoteException, SQLException {

        SocketResponse response;

        try {

            SocketRequest r = new SocketRequest(SocketRequestType.INS_KID_PULLMAN,pullman,bambino);

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
    public List<Bambino> getAllBambiniGita(Gita gita) throws RemoteException, SQLException {

        SocketResponse response;

        try {

            SocketRequest r = new SocketRequest(SocketRequestType.GET_ALL_KID_TRIP, gita);


            out.writeObject(r);
            response = (SocketResponse) in.readObject();
            out.flush();


            if(response.eccezione) throw new RemoteException(((Exception)response.returnValue).getMessage());
            return (List<Bambino>) response.returnValue;

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
    public List<Integer> getAllNumGite() throws RemoteException, SQLException {

        SocketResponse response;

        try {

            SocketRequest r = new SocketRequest(SocketRequestType.GET_ALL_NUM_TRIP);


            out.writeObject(r);
            response = (SocketResponse) in.readObject();
            out.flush();


            if(response.eccezione) throw new RemoteException(((Exception)response.returnValue).getMessage());
            return (List<Integer>) response.returnValue;

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
    public Bambino getKidPresente(String cf) throws RemoteException, SQLException {

        SocketResponse response;

        try {

            SocketRequest r = new SocketRequest(SocketRequestType.GET_KID_PRESENTE, cf);


            out.writeObject(r);
            response = (SocketResponse) in.readObject();
            out.flush();


            if(response.eccezione) throw new RemoteException(((Exception)response.returnValue).getMessage());
            return (Bambino) response.returnValue;

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
    public Gita getGita(int codice_g)throws RemoteException,SQLException{
        return null;
    }
}
