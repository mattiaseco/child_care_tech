package client.SocketController;

import common.Classes.Bambino;
import common.Classes.Ingredienti;
import common.Classes.Intolleranze;
import common.Interface.iBambinoDAO;
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

public class SocketKidController implements iBambinoDAO {

    private Socket client;
    private ObjectInputStream in;
    private ObjectOutputStream out;

    public SocketKidController(Socket client, ObjectInputStream in,ObjectOutputStream out){
        this.client = client;
        this.in = in;
        this.out = out;
    }
    @Override
    public void inserisciBambino(String cf, String nome, String cognome, LocalDate data, String indirizzo, String contatto1, String contatto2) throws RemoteException, SQLException {

            SocketResponse response;

            try {

                SocketRequest r = new SocketRequest(SocketRequestType.CREATE_KID, cf,nome,cognome,data,indirizzo,contatto1,contatto2);

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
    public void modificaBambino(String cf, String nome, String cognome, LocalDate data, String indirizzo, String contatto1, String contatto2) throws RemoteException, SQLException {

            SocketResponse response;

            try {

                SocketRequest r = new SocketRequest(SocketRequestType.UPDATE_KID, cf,nome,cognome,data,indirizzo,contatto1,contatto2);

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
    public List<Bambino> getAllBambini() throws RemoteException, SQLException {
        return null;
    }

    @Override
    public void cancellaBambino(String cf) throws RemoteException, SQLException {

            SocketResponse response;

            try {

                SocketRequest r = new SocketRequest(SocketRequestType.DELETE_KID, cf);

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
    public void inserisciAllergia(Bambino bambino, Ingredienti ingrediente) throws RemoteException, SQLException {

            SocketResponse response;

            try {

                SocketRequest r = new SocketRequest(SocketRequestType.ADD_ALLERGY, bambino,ingrediente);

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
    public List<Ingredienti> getAllAllergie(Bambino bambino) throws RemoteException, SQLException {
        return null;
    }

    @Override
    public void cancellaAllergia(Bambino bambino, Ingredienti ingrediente) throws RemoteException, SQLException {



            SocketResponse response;

            try {

                SocketRequest r = new SocketRequest(SocketRequestType.REMOVE_ALLERGY, bambino, ingrediente);

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
    public List<String> getAllCf() throws RemoteException, SQLException {
        return null;
    }

    @Override
    public Bambino getKid(String cf) throws RemoteException, SQLException {
        return null;
    }

    @Override
    public void inserisciBambinoPresente(String cf) throws RemoteException, SQLException {

    }

    @Override
    public List<Bambino> getAllPresenti() throws RemoteException, SQLException {
        return null;
    }

    @Override
    public List<Intolleranze> getIntolleranzeBambino(Bambino bambino) throws RemoteException, SQLException {
        return null;
    }

    @Override
    public List<Bambino> getAllBambiniNomeCognome(String nome, String cognome) throws RemoteException, SQLException {
        return null;
    }

    @Override
    public void cancellaPresenti() throws RemoteException, SQLException {

    }
}
