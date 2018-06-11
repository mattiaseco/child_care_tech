package common.Interface;

import common.Classes.Fornitore;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;

public interface iFornitoreDAO extends Remote{

    void inserisciFornitore(String partita_iva, String cf, String nome, String cognome, LocalDate data, String indirizzo, String telefono) throws RemoteException, SQLException;
    void modificaFornitore(String partita_iva, String cf, String nome, String cognome, LocalDate data, String indirizzo, String telefono) throws RemoteException, SQLException;
    List<Fornitore> getAllFornitore() throws RemoteException,SQLException;
    void cancellaFornitore(String cf) throws RemoteException,SQLException;
    List<String>getAllCf()throws RemoteException,SQLException;
    List<String>getAllPartitaIVA()throws RemoteException,SQLException;
    Fornitore getFornitore(String cf)throws RemoteException,SQLException;

}
