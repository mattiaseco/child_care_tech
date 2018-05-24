package common.Interface;


import common.Classes.Piatto;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.sql.SQLException;
import java.util.List;

public interface iPiattoDAO extends Remote {
    void inserisciPiatto(String nome_p, String tipo) throws RemoteException, SQLException;
    void modificaPiatto(String nome_p, String tipo) throws RemoteException, SQLException;
    List<Piatto> getAllPiatti() throws RemoteException,SQLException;
    void cancellaPiatti(String nome_p)throws RemoteException,SQLException;
    List<Piatto>getAllPrimi()throws  RemoteException,SQLException;
    List<Piatto>getAllSecondi()throws  RemoteException,SQLException;
    List<Piatto>getAllContorni()throws  RemoteException,SQLException;



}
