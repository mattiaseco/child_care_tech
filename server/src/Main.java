import Controller.PediatraDAO;

import java.rmi.RemoteException;
import java.sql.SQLException;

public class Main {
    public static void main(String [] args) throws SQLException, ClassNotFoundException {
        Database.avviaDatabase();

        RMIServer rmiServer = new RMIServer();
        rmiServer.start();
        try {
            new PediatraDAO().getAllPediatra();
        } catch (RemoteException e) {}
    }
}