package common.Interface;

import common.Classes.*;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;

public interface iMenuDAO extends Remote{


    void inserisciPrimo(int numeroMenu,Piatto piatto1) throws RemoteException,SQLException;
    void inserisciSecondo(int numeroMenu,Piatto piatto2) throws RemoteException,SQLException;
    void inserisciContorno(int numeroMenu,Piatto piatto3) throws RemoteException,SQLException;
    void modificaMenu(int numero, Piatto piatto1,Piatto piatto2,Piatto piatto3) throws RemoteException, SQLException;
    List<Menu> getAllMenu() throws RemoteException,SQLException;
    void cancellaMenu( int numero)throws RemoteException,SQLException;
    List<Mangia> GetAllBambiniMenuMangia(Menu menu)throws RemoteException,SQLException;
    List<Bambino>getAllBambiniMenu(Menu menu)throws RemoteException,SQLException;
    void inserisciBambinoMangia(Menu menu, Bambino bambino)throws RemoteException,SQLException;
    void cancellaBambinoMangia(Menu menu, Bambino bambino)throws RemoteException,SQLException;
    Piatto getPiatto1(int numero)throws RemoteException,SQLException;
    Piatto getPiatto2(int numero)throws RemoteException,SQLException;
    Piatto getPiatto3(int numero)throws RemoteException,SQLException;
    List<Ingredienti>getAllIngredientiMenu(Menu menu)throws RemoteException,SQLException;
    List<Intolleranze> getAllBambiniPresentiSenzaMenu(Menu menu) throws RemoteException, SQLException;
    List<Integer> getAllNumMenu() throws RemoteException, SQLException;
    Menu getMenuNumero(int numero)throws RemoteException,SQLException;

}
