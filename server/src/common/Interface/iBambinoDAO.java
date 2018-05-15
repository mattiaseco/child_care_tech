package common.Interface;

import common.Classes.Bambino;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;

public interface iBambinoDAO extends Remote {

    void inserisciBambino(int cod_qr, String cf, String nome, String cognome, LocalDate data, String indirizzo, String contatto1, String contatto2) throws RemoteException, SQLException;
    void modificaBambino(int cod_qr, String cf, String nome, String cognome, LocalDate data, String indirizzo, String contatto1, String contatto2) throws RemoteException, SQLException;
    List<Bambino> getAllBambini() throws RemoteException,SQLException;

}
