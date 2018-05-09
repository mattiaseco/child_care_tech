package common.Interface;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface iLoginController extends Remote {
    boolean login(String username, String password) throws RemoteException;
}
