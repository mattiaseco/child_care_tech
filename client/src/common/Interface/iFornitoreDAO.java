package common.Interface;

import common.Classes.Fornitore;

import java.rmi.RemoteException;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;

public interface iFornitoreDAO {

    void inserisciFornitore(String cod_qr, String cf, String nome, String cognome, LocalDate data, String indirizzo, String telefono) throws RemoteException, SQLException;
    void modificaFornitore(String cod_qr, String cf, String nome, String cognome, LocalDate data, String indirizzo, String telefono) throws RemoteException, SQLException;
    List<Fornitore> getAllFornitore() throws RemoteException,SQLException;
}
