package common.Interface;

import common.Classes.Pediatra;

import java.rmi.RemoteException;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;

public interface iPediatraDAO {

    boolean inserisciPediatra(String cod_qr,String cf, String nome, String cognome, LocalDate data, String indirizzo, String telefono) throws RemoteException, SQLException;
    boolean modificaPediatra(String cod_qr, String cf, String nome, String cognome, LocalDate data, String indirizzo, String telefono) throws RemoteException, SQLException;
    List<Pediatra> getAllPediatra() throws RemoteException,SQLException;
}