package common.Interface;

import common.Classes.Genitore;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;

public interface iGenitoreDAO extends Remote {

    boolean inserisciGenitore(String cf, String nome, String cognome, LocalDate data, String indirizzo, String telefono) throws RemoteException, SQLException;
    boolean modificaGenitore(String cf, String nome, String cognome, LocalDate data, String indirizzo, String telefono) throws RemoteException, SQLException;
    List<Genitore> getAllGenitori() throws RemoteException,SQLException;


}