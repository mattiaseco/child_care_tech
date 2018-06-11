package client.SocketController;

import common.Classes.Ingredienti;
import common.Interface.iIngredientiDAO;
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

public class SocketIngredientiController implements iIngredientiDAO {

    private Socket client;
    private ObjectInputStream in;
    private ObjectOutputStream out;

    public SocketIngredientiController(Socket client, ObjectInputStream in,ObjectOutputStream out){
        this.client = client;
        this.in = in;
        this.out = out;
    }
    @Override
    public void inserisciIngrediente(String nome_i) throws RemoteException, SQLException {

            SocketResponse response;
            try {

                SocketRequest r = new SocketRequest(SocketRequestType.CREATE_INGREDIENTS, nome_i);

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
    public void modificaIngrediente(String nome_i) throws RemoteException, SQLException {

    }

    @Override
    public List<Ingredienti> getAllIngredienti() throws RemoteException, SQLException {

        SocketResponse response;

        try {

            SocketRequest r = new SocketRequest(SocketRequestType.GET_ALL_INGREDIENTI);


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
    public void cancellaIngredienti(String nome_i) throws RemoteException, SQLException {

            SocketResponse response;


            try {

                SocketRequest r = new SocketRequest(SocketRequestType.DELETE_INGREDIENTS, nome_i);

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


}
