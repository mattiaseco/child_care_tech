package client;

import common.Interface.*;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;

public class NamingContextManager {
    public static iLoginController getLoginController() {
        try {
            String url = "rmi://127.0.0.1:1099/login_controller";
            return (iLoginController) Naming.lookup(url);
        } catch (NotBoundException | MalformedURLException | RemoteException e){
            System.err.println(e.getMessage());
            e.printStackTrace();
            return null;
        }
    }

    public static iRegisterController getRegisterController(){
        try {
            String url = "rmi://127.0.0.1:1099/register_controller";
            return (iRegisterController) Naming.lookup(url);
        } catch (NotBoundException | MalformedURLException | RemoteException e){
            System.err.println(e.getMessage());
            e.printStackTrace();
            return null;
        }

    }

    public static iBambinoDAO getKidController() {
        try {
            String url = "rmi://127.0.0.1:1099/kid_controller";
            return (iBambinoDAO) Naming.lookup(url);
        } catch (NotBoundException | MalformedURLException | RemoteException e){
            System.err.println(e.getMessage());
            e.printStackTrace();
            return null;
        }

    }
    public static iGenitoreDAO getParentsController() {
        try {
            String url = "rmi://127.0.0.1:1099/parents_controller";
            return (iGenitoreDAO) Naming.lookup(url);
        } catch (NotBoundException | MalformedURLException | RemoteException e){
            System.err.println(e.getMessage());
            e.printStackTrace();
            return null;
        }

    }
    public static iPediatraDAO getPediatraController() {
        try {
            String url = "rmi://127.0.0.1:1099/pediatra_controller";
            return (iPediatraDAO) Naming.lookup(url);
        } catch (NotBoundException | MalformedURLException | RemoteException e){
            System.err.println(e.getMessage());
            e.printStackTrace();
            return null;
        }

    }
    public static iPersonaleDAO getPersonalController() {
        try {
            String url = "rmi://127.0.0.1:1099/personal_controller";
            return (iPersonaleDAO) Naming.lookup(url);
        } catch (NotBoundException | MalformedURLException | RemoteException e){
            System.err.println(e.getMessage());
            e.printStackTrace();
            return null;
        }

    }
    public static iFornitoreDAO getProvidersController() {
        try {
            String url = "rmi://127.0.0.1:1099/providers_controller";
            return (iFornitoreDAO) Naming.lookup(url);
        } catch (NotBoundException | MalformedURLException | RemoteException e){
            System.err.println(e.getMessage());
            e.printStackTrace();
            return null;
        }

    }

    public static iGitaDAO getTripsController(){
        try {
            String url = "rmi://127.0.0.1:1099/trips_controller";
            return (iGitaDAO) Naming.lookup(url);
        } catch (NotBoundException | MalformedURLException | RemoteException e){
            System.err.println(e.getMessage());
            e.printStackTrace();
            return null;
        }

    }

    public static iIngredientiDAO getIngredientiController(){
        try {
            String url = "rmi://127.0.0.1:1099/ingredienti_controller";
            return (iIngredientiDAO) Naming.lookup(url);
        } catch (NotBoundException | MalformedURLException | RemoteException e){
            System.err.println(e.getMessage());
            e.printStackTrace();
            return null;
        }

    }


}
