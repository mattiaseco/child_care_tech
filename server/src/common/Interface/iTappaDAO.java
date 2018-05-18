package common.Interface;

import common.Classes.Tappa;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.sql.SQLException;
import java.util.List;

public interface iTappaDAO extends Remote {

    void inserisciTappa(String codice_t) throws RemoteException, SQLException;
    void modificaTappa(String codice_t) throws RemoteException, SQLException;
    List<Tappa> getAllTappe() throws RemoteException,SQLException;
    void cancellaTappa(String codice_t)throws RemoteException,SQLException;
}
