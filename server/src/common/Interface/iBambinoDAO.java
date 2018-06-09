package common.Interface;

import common.Classes.Bambino;
import common.Classes.Ingredienti;
import common.Classes.Intolleranze;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;

public interface iBambinoDAO extends Remote {

    void inserisciBambino(String cf, String nome, String cognome, LocalDate data, String indirizzo, String contatto1, String contatto2) throws RemoteException, SQLException;
    void modificaBambino(String cf, String nome, String cognome, LocalDate data, String indirizzo, String contatto1, String contatto2) throws RemoteException, SQLException;
    List<Bambino> getAllBambini() throws RemoteException,SQLException;
    void cancellaBambino(String cf) throws RemoteException,SQLException;
    void inserisciAllergia(Bambino bambino,Ingredienti ingrediente)throws  RemoteException,SQLException;
    List<Ingredienti> getAllAllergie(Bambino bambino) throws  RemoteException,SQLException;
    void cancellaAllergia(Bambino bambino,Ingredienti ingrediente)throws RemoteException,SQLException;
    List<String>getAllCf()throws RemoteException,SQLException;
    Bambino getKid(String cf) throws RemoteException,SQLException;
    void inserisciBambinoPresente(String cf)throws RemoteException,SQLException;
    List<Bambino> getAllPresenti() throws  RemoteException,SQLException;
    List<Intolleranze> getIntolleranzeBambino(Bambino bambino) throws RemoteException,SQLException;//dato un kid ti da quello che ha nella tabella intolleranze
    List<Bambino>getAllBambiniNomeCognome(String nome,String cognome)throws RemoteException,SQLException;
    void cancellaPresenti()throws RemoteException,SQLException;


}
