package common.Interface;

import common.Classes.Personale;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;

public interface iPersonaleDAO extends Remote {

    void inserisciPersonale(String cf, String nome, String cognome, LocalDate data, String indirizzo, String telefono) throws RemoteException, SQLException;
    void modificaPersonale(String cf, String nome, String cognome, LocalDate data, String indirizzo, String telefono) throws RemoteException, SQLException;
    List<Personale> getAllPersonale() throws RemoteException,SQLException;
    void cancellaPersonale(String cf) throws RemoteException,SQLException;

}
