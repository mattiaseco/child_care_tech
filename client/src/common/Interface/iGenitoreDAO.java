package common.Interface;

import common.Classes.Genitore;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;

public interface iGenitoreDAO extends Remote{

    void inserisciGenitore(String cf, String nome, String cognome, LocalDate data, String indirizzo, String telefono) throws RemoteException, SQLException;
    void modificaGenitore(String cf, String nome, String cognome, LocalDate data, String indirizzo, String telefono) throws RemoteException, SQLException;
    List<Genitore> getAllGenitori() throws RemoteException,SQLException;
    void cancellaGenitore(String cf) throws  RemoteException, SQLException;
    List<String>getAllCf()throws RemoteException,SQLException;
    Genitore getGenitore(String cf)throws RemoteException,SQLException;


}
