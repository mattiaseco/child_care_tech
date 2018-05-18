package common.Interface;

import common.Classes.Ingredienti;

import java.rmi.RemoteException;
import java.sql.SQLException;
import java.util.List;

public interface iIngredientiDAO {
    void inserisciIngrediente(String nome_i,int quantità) throws RemoteException, SQLException;
    void modificaIngrediente(String nome_i,int quantità) throws RemoteException, SQLException;
    List<Ingredienti> getAllIngredienti() throws RemoteException,SQLException;
    void cancellaIngredienti(String nome_i)throws RemoteException,SQLException;
}
