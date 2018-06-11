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

    void inserisciGita(int codice_g, String destinazione,LocalDate data_partenza, LocalDate data_ritorno,Double costo,String descrizione) throws RemoteException, SQLException;
    List<Gita> getAllGite() throws RemoteException,SQLException;
    void insertNumPartecipanti(int codice_g,int num_partecipanti) throws RemoteException,SQLException;
    void insertNumPullman(int codice_g,int num_pullman) throws RemoteException,SQLException;
    void cancellaGita(int codice_g)throws RemoteException,SQLException;
    void inserisciBambinoGita(int codice_g, Bambino bambino)throws RemoteException,SQLException;
    void cancellaBambinoGita(int codice_g, Bambino bambino)throws RemoteException,SQLException;
    void inserisciBambinoPullman(Pullman pullman, Bambino bambino)throws RemoteException,SQLException;
    List<Bambino>getAllBambiniGita(Gita gita)throws RemoteException,SQLException;
    List<Integer> getAllNumGite()throws RemoteException,SQLException;
    Bambino getKidPresente(String cf)throws RemoteException,SQLException;
    Gita getGita(int codice_g)throws RemoteException,SQLException;

}

