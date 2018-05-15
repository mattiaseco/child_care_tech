package common.Interface;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.sql.SQLException;

public interface iRegisterController extends Remote {

    void register(String username, String password) throws RemoteException, SQLException;

}
