package common.Interface;

import common.Classes.Pullman;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.sql.SQLException;
import java.util.List;

public interface iPullmanDAO extends Remote {
    void inserisciPullman(String targa,int capienza) throws RemoteException, SQLException;
    void modificaPullman(String targa,int capienza) throws RemoteException, SQLException;
    List<Pullman> getAllPullman() throws RemoteException,SQLException;
    void cancellaPullman(String targa)throws RemoteException,SQLException;
}
