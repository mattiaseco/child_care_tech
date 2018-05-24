package common.Interface;

import common.Classes.Bambino;
import common.Classes.Gita;
import common.Classes.Pullman;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;

public interface iGitaDAO extends Remote {

    void inserisciGita(String destinazione, int num_pullman, int num_partecipanti, LocalDate data_partenza, LocalDate data_ritorno,String costo,String descrizione) throws RemoteException, SQLException;
    List<Gita> getAllGite() throws RemoteException,SQLException;
    void cancellaGita(int codice_g)throws RemoteException,SQLException;
    void inserisciBambinoGita(Gita gita, Bambino bambino)throws RemoteException,SQLException;
    void inserisciBambinoPullman(Pullman pullman, Bambino bambino)throws RemoteException,SQLException;
}
