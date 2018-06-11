package client.SocketController;

import common.Classes.Personale;
import common.Interface.iPersonaleDAO;
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

public class SocketPersonalController implements iPersonaleDAO {
    private Socket client;
    private ObjectInputStream in;
    private ObjectOutputStream out;

    public SocketPersonalController(Socket client, ObjectInputStream in,ObjectOutputStream out){
        this.client = client;
        this.in = in;
        this.out = out;
    }
    @Override
    public void inserisciPersonale(String cf, String nome, String cognome, LocalDate data, String indirizzo, String telefono) throws RemoteException, SQLException {

            SocketResponse response;



            try {

                SocketRequest r = new SocketRequest(SocketRequestType.CREATE_PERSONAL, cf,nome,cognome,data,indirizzo,telefono);

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
    public void modificaPersonale(String cf, String nome, String cognome, LocalDate data, String indirizzo, String telefono) throws RemoteException, SQLException {

            SocketResponse response;


            try {

                SocketRequest r = new SocketRequest(SocketRequestType.UPDATE_PERSONAL, cf,nome,cognome,data,indirizzo,telefono);


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
    public List<Personale> getAllPersonale() throws RemoteException, SQLException {
        return null;
    }

    @Override
    public void cancellaPersonale(String cf) throws RemoteException, SQLException {

            SocketResponse response;

            try {

                SocketRequest r = new SocketRequest(SocketRequestType.DELETE_PERSONAL, cf);

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
}
