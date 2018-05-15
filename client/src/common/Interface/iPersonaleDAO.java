package common.Interface;

import common.Classes.Personale;
import java.rmi.RemoteException;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;


public interface iPersonaleDAO {
    boolean inserisciPersonale(String cod_qr,String cf, String nome, String cognome, LocalDate data, String indirizzo, String telefono) throws RemoteException, SQLException;
    boolean modificaPersonale(String cod_qr, String cf, String nome, String cognome, LocalDate data, String indirizzo, String telefono) throws RemoteException, SQLException;
    List<Personale> getAllPersonale() throws RemoteException,SQLException;
}
