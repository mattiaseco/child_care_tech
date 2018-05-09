package common;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface InterfaceRMI extends Remote{

    String getAccess(String username, String pass) throws RemoteException;

}
