package common.Interface;

import common.Classes.Mangia;
import common.Classes.Menu;
import common.Classes.Piatto;

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
    List<Mangia> GetAllBambiniMenu(Menu menu)throws RemoteException,SQLException;
}
