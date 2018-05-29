package common.Interface;

import common.Classes.Bambino;
import common.Classes.Gateway;
import common.Classes.Intolleranze;
import common.Classes.Mangia;
import org.mariadb.jdbc.internal.util.SqlStates;

import java.awt.*;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;

public interface iGatewayDAO extends Remote {
    void inserisciGatway(int cod_porta, LocalDate data_gate) throws RemoteException, SQLException;
    void modificaGatway(int cod_porta, LocalDate data_gate) throws RemoteException, SQLException;
    List<Gateway> getAllGatway() throws RemoteException, SQLException;
    void cancellaGatway(int cod_porta) throws RemoteException, SQLException;
    List<Bambino> getAllBambiniPresenti(Gateway gateway) throws RemoteException, SQLException;
    void inserisciBambinoGateway(Bambino bambino, Gateway gateway) throws RemoteException, SQLException;
    List<Intolleranze> getAllBambiniPresentiIntolleranze(Gateway gateway) throws RemoteException, SQLException;
}