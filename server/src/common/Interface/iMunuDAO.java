package common.Interface;

import common.Classes.Bambino;
import common.Classes.Mangia;
import common.Classes.Menu;
import common.Classes.Piatto;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;

public interface iMunuDAO extends Remote {

    void inserisciMenu(String numero, LocalDate data,Piatto piatto1, Piatto piatto2, Piatto piatto3) throws RemoteException, SQLException;
    void modificaMenu(String numero, LocalDate data,Piatto piatto1,Piatto piatto2,Piatto piatto3) throws RemoteException, SQLException;
    List<Menu> getAllMenu() throws RemoteException,SQLException;
    void cancellaMenu( String numero)throws RemoteException,SQLException;
    List<Mangia> GetAllBambiniMenu(Menu menu)throws RemoteException,SQLException;


}
