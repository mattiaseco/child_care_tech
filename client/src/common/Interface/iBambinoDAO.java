package common.Interface;

import common.Classes.Bambino;
import common.Classes.Ingredienti;

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


}
