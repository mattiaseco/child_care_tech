package client.SocketController;

import client.NamingContextManager;
import common.Classes.*;
import common.Interface.iMenuDAO;
import common.SocketRequest;
import common.SocketRequestType;
import common.SocketResponse;

import javax.print.attribute.standard.MediaSize;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.UnknownHostException;
import java.rmi.RemoteException;
import java.sql.SQLException;
import java.util.List;

public class SocketMenuController implements iMenuDAO {
    private Socket client;
    private ObjectInputStream in;
    private ObjectOutputStream out;

    public SocketMenuController(Socket client, ObjectInputStream in,ObjectOutputStream out){
        this.client = client;
        this.in = in;
        this.out = out;
    }
    @Override
    public void inserisciPrimo(int numeroMenu, Piatto piatto1) throws RemoteException, SQLException {

            SocketResponse response;

            try {

                SocketRequest r = new SocketRequest(SocketRequestType.CREATE_MENU, numeroMenu, piatto1);


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
    public void inserisciSecondo(int numeroMenu, Piatto piatto2) throws RemoteException, SQLException {


            SocketResponse response;


            try {

                SocketRequest r = new SocketRequest(SocketRequestType.CREATE_STEP2_MENU, numeroMenu, piatto2);


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
    public void inserisciContorno(int numeroMenu, Piatto piatto3) throws RemoteException, SQLException {

            SocketResponse response;


            try {

                SocketRequest r = new SocketRequest(SocketRequestType.CREATE_STEP3_MENU, numeroMenu, piatto3);


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
    public void modificaMenu(int numero, Piatto piatto1, Piatto piatto2, Piatto piatto3) throws RemoteException, SQLException {

    }

    @Override
    public List<Menu> getAllMenu() throws RemoteException, SQLException {
        SocketResponse response;


        try {

            SocketRequest r = new SocketRequest(SocketRequestType.GET_ALL_MENU);

            out.writeObject(r);
            response = (SocketResponse) in.readObject();
            out.flush();

            if(response.eccezione) throw new RemoteException(((Exception)response.returnValue).getMessage());
            return (List<Menu>)response.returnValue;

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
    public void cancellaMenu(int numero) throws RemoteException, SQLException {

            SocketResponse response;


            try {

                SocketRequest r = new SocketRequest(SocketRequestType.DELETE_MENU, numero);

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
    public List<Mangia> GetAllBambiniMenuMangia(Menu menu) throws RemoteException, SQLException {
        return null;
    }

    @Override
    public List<Bambino> getAllBambiniMenu(Menu menu) throws RemoteException, SQLException {
        return null;
    }

    @Override
    public void inserisciBambinoMangia(Menu menu, Bambino bambino) throws RemoteException, SQLException {

    }

    @Override
    public void cancellaBambinoMangia(Menu menu, Bambino bambino) throws RemoteException, SQLException {

    }

    @Override
    public Piatto getPiatto1(int numero) throws RemoteException, SQLException {
        return null;
    }

    @Override
    public Piatto getPiatto2(int numero) throws RemoteException, SQLException {
        return null;
    }

    @Override
    public Piatto getPiatto3(int numero) throws RemoteException, SQLException {
        return null;
    }

    @Override
    public List<Ingredienti> getAllIngredientiMenu(Menu menu) throws RemoteException, SQLException {
        return null;
    }

    @Override
    public List<Intolleranze> getAllBambiniPresentiSenzaMenu(Menu menu) throws RemoteException, SQLException {
        return null;
    }

    @Override
    public List<Integer> getAllNumMenu() throws RemoteException, SQLException {
        return null;
    }

    @Override
    public Menu getMenuNumero(int numero) throws RemoteException, SQLException {
        return null;
    }
}
