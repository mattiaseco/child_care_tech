package common.Interface;

import common.Classes.Ingredienti;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.sql.SQLException;
import java.util.List;

public interface iIngredientiDAO extends Remote {
    void inserisciIngrediente(String nome_i) throws RemoteException, SQLException;
    void modificaIngrediente(String nome_i) throws RemoteException, SQLException;
    List<Ingredienti> getAllIngredienti() throws RemoteException,SQLException;
    void cancellaIngredienti(String nome_i)throws RemoteException,SQLException;
    Ingredienti getIngrediente(String nome_i)throws RemoteException,SQLException;
}
