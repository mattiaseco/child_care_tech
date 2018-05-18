package common.Interface;

import common.Classes.Gateway;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;

public interface iGatewayDAO extends Remote {
    void inserisciGatway(int cod_porta,LocalDate data_gate) throws RemoteException, SQLException;
    void modificaGatway(int cod_porta,LocalDate data_gate) throws RemoteException, SQLException;
    List<Gateway> getAllGatway() throws RemoteException,SQLException;
    void cancellaGatway(int cod_porta)throws RemoteException,SQLException;
}
