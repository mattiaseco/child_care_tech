package common.Interface;

import common.Classes.Gita;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.sql.SQLException;
import java.util.List;

public interface iGitaDAO extends Remote {
    void inserisciGita(String codice_g, String destinazione, int num_pullman, int num_partecipanti) throws RemoteException, SQLException;
    void modificaGenitore(String codice_g, String destinazione, int num_pullman, int num_partecipanti) throws RemoteException, SQLException;
    List<Gita> getAllGite() throws RemoteException,SQLException;
    void cancellaGita(String codice_g)throws RemoteException,SQLException;
}
