package client;

import client.SocketController.*;
import common.Interface.*;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.MalformedURLException;
import java.net.Socket;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;



public class NamingContextManager {

  public static boolean network;
  public static Socket client = null;
  public static ObjectInputStream in;
  public static ObjectOutputStream out;

  public static void setRMI() {
      network = true;
  }

  public static void setSocket() {
      network = false;
  }

  public static void initSocket() {
      try{
        client = new Socket("127.0.0.1", 1337);
        in = new ObjectInputStream(client.getInputStream());
        out = new ObjectOutputStream(client.getOutputStream());
      } catch (IOException e) {
          e.printStackTrace();
      }
  }

  public static void closeSocket() {
      try{
          in.close();
          out.close();
          client.close();
          client = null;
      } catch (IOException e) {
          e.printStackTrace();
      }
  }

    public static iLoginController getLoginController() {
        if (network) {

            try {
                String url = "rmi://127.0.0.1:1099/login_controller";
                return (iLoginController) Naming.lookup(url);
            } catch (NotBoundException | MalformedURLException | RemoteException e) {
                System.err.println(e.getMessage());
                e.printStackTrace();
                return null;
            }
        }
        else {
            if(client == null) initSocket();
            return new SocketLoginController(client, in, out);

        }

    }

    public static iRegisterController getRegisterController() {
        if(network) {
            try {
                String url = "rmi://127.0.0.1:1099/register_controller";
                return (iRegisterController) Naming.lookup(url);
            } catch (NotBoundException | MalformedURLException | RemoteException e) {
                System.err.println(e.getMessage());
                e.printStackTrace();
                return null;
            }
        }
        else {
            if(client == null) initSocket();
            return new SocketRegisterController(client, in, out);
        }

    }

    public static iBambinoDAO getKidController() {
        if(network) {
            try {
                String url = "rmi://127.0.0.1:1099/kid_controller";
                return (iBambinoDAO) Naming.lookup(url);
            } catch (NotBoundException | MalformedURLException | RemoteException e) {
                System.err.println(e.getMessage());
                e.printStackTrace();
                return null;
            }

        }
        else {
            if(client == null) initSocket();
            return new SocketKidController(client, in, out);
        }

    }

    public static iGenitoreDAO getParentsController() {
        if(network) {
            try {
                String url = "rmi://127.0.0.1:1099/parents_controller";
                return (iGenitoreDAO) Naming.lookup(url);
            } catch (NotBoundException | MalformedURLException | RemoteException e) {
                System.err.println(e.getMessage());
                e.printStackTrace();
                return null;
            }
        }
        else {
            if(client == null) initSocket();
            return new SocketParentsController(client, in, out);
        }

    }

    public static iPediatraDAO getPediatraController() {
        if(network) {
            try {
                String url = "rmi://127.0.0.1:1099/pediatra_controller";
                return (iPediatraDAO) Naming.lookup(url);
            } catch (NotBoundException | MalformedURLException | RemoteException e) {
                System.err.println(e.getMessage());
                e.printStackTrace();
                return null;
            }
        }
        else {
            if(client == null) initSocket();
            return new SocketPediatraController(client, in, out);
        }


    }

    public static iPersonaleDAO getPersonalController() {
        if(network) {

            try {
                String url = "rmi://127.0.0.1:1099/personal_controller";
                return (iPersonaleDAO) Naming.lookup(url);
            } catch (NotBoundException | MalformedURLException | RemoteException e) {
                System.err.println(e.getMessage());
                e.printStackTrace();
                return null;
            }
        }
        else {
            if(client == null) initSocket();
            return new SocketPersonalController(client, in, out);
        }

    }

    public static iFornitoreDAO getProvidersController() {
        if(network) {

            try {
                String url = "rmi://127.0.0.1:1099/providers_controller";
                return (iFornitoreDAO) Naming.lookup(url);
            } catch (NotBoundException | MalformedURLException | RemoteException e) {
                System.err.println(e.getMessage());
                e.printStackTrace();
                return null;
            }
        }
        else {
            if(client == null) initSocket();
            return new SocketProvidersController(client, in, out);
        }

    }

    public static iGitaDAO getTripsController() {
        if(network) {
            try {
                String url = "rmi://127.0.0.1:1099/trips_controller";
                return (iGitaDAO) Naming.lookup(url);
            } catch (NotBoundException | MalformedURLException | RemoteException e) {
                System.err.println(e.getMessage());
                e.printStackTrace();
                return null;
            }
        }
        else {
            if(client == null) initSocket();
            return new SocketTripController(client, in, out);
        }


    }

    public static iIngredientiDAO getIngredientiController() {
        if(network) {
            try {
                String url = "rmi://127.0.0.1:1099/ingredienti_controller";
                return (iIngredientiDAO) Naming.lookup(url);
            } catch (NotBoundException | MalformedURLException | RemoteException e) {
                System.err.println(e.getMessage());
                e.printStackTrace();
                return null;
            }
        }
        else {
            if(client == null) initSocket();
            return new SocketIngredientiController(client, in, out);
        }

    }


    public static iMenuDAO getMenuController() {
        if(network) {
            try {
                String url = "rmi://127.0.0.1:1099/menu_controller";
                return (iMenuDAO) Naming.lookup(url);
            } catch (NotBoundException | MalformedURLException | RemoteException e) {
                System.err.println(e.getMessage());
                e.printStackTrace();
                return null;
            }
        }
        else {
            if(client == null) initSocket();
            return new SocketMenuController(client, in, out);
        }



    }

    public static iPullmanDAO getPullmanController() {
        if(network) {
            try {
                String url = "rmi://127.0.0.1:1099/pullman_controller";
                return (iPullmanDAO) Naming.lookup(url);
            } catch (NotBoundException | MalformedURLException | RemoteException e) {
                System.err.println(e.getMessage());
                e.printStackTrace();
                return null;
            }
        }
        else {
            if(client == null) initSocket();
            return new SocketPullmanController(client, in, out);
        }


    }

    public static iPiattoDAO getPiattoController() {
        if(network) {
            try {
                String url = "rmi://127.0.0.1:1099/piatto_controller";
                return (iPiattoDAO) Naming.lookup(url);
            } catch (NotBoundException | MalformedURLException | RemoteException e) {
                System.err.println(e.getMessage());
                e.printStackTrace();
                return null;
            }
        }
        else {
            if(client == null) initSocket();
            return new SocketPiattoController(client, in, out);
        }


    }
}



