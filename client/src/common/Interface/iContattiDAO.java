package common.Interface;

import common.Classes.Contatti;

import java.rmi.RemoteException;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;

public interface iContattiDAO {
    void inserisciContatti(String cf, String nome, String cognome, LocalDate data, String indirizzo, String telefono) throws RemoteException, SQLException;
    void modificaContatti(String cf, String nome, String cognome, LocalDate data, String indirizzo, String telefono) throws RemoteException, SQLException;
    List<Contatti> getAllContatti() throws RemoteException,SQLException;
}
