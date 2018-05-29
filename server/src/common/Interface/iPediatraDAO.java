package common.Interface;

import common.Classes.Pediatra;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;

public interface iPediatraDAO extends Remote {

    void inserisciPediatra(String cf, String nome, String cognome, LocalDate data, String indirizzo, String telefono) throws RemoteException, SQLException;
    void modificaPediatra(String cf, String nome, String cognome, LocalDate data, String indirizzo, String telefono) throws RemoteException, SQLException;
    List<Pediatra> getAllPediatra() throws RemoteException,SQLException;
    void cancellaPediatra(String cf) throws RemoteException,SQLException;
    List<String>getAllCf()throws RemoteException,SQLException;

}
