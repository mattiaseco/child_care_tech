package common.Interface;

import common.Classes.Contatti;

import java.rmi.RemoteException;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;

public interface iContattiDAO {
    boolean inserisciContatti(String cod_qr, String cf, String nome, String cognome, LocalDate data, String indirizzo, String telefono) throws RemoteException, SQLException;
    boolean modificaContatti(String cod_qr, String cf, String nome, String cognome, LocalDate data, String indirizzo, String telefono) throws RemoteException, SQLException;
    List<Contatti> getAllContatti() throws RemoteException,SQLException;
}
