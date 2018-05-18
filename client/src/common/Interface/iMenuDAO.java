package common.Interface;

import common.Classes.Menu;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;

public interface iMenuDAO extends Remote{


        void inserisciMenu(int numero, LocalDate data) throws RemoteException, SQLException;
        void modificaMenu(int numero, LocalDate data) throws RemoteException, SQLException;
        List<Menu> getAllMenu() throws RemoteException,SQLException;
        void cancellaMenu( int numero)throws RemoteException,SQLException;


}
