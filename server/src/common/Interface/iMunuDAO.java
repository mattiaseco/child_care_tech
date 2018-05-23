package common.Interface;

import common.Classes.Menu;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;

public interface iMunuDAO extends Remote {

    void inserisciMenu(String numero, LocalDate data) throws RemoteException, SQLException;
    void modificaMenu(String numero, LocalDate data) throws RemoteException, SQLException;
    List<Menu> getAllMenu() throws RemoteException,SQLException;
    void cancellaMenu( String numero)throws RemoteException,SQLException;

}
