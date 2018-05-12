package client;

import common.Interface.iLoginController;

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
}
