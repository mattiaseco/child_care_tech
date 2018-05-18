package Controller;


import common.Classes.Tappa;
import common.Interface.iTappaDAO;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class TappaDAO extends UnicastRemoteObject implements iTappaDAO {

    public TappaDAO() throws RemoteException {
    }
    @Override
    public void  inserisciTappa(String codice_t) throws SQLException {

        try {
            createTappa(codice_t);
        } catch (SQLException e) {

            System.out.println(e.getMessage());
            e.printStackTrace();
            return;
        }

    }

    private static void createTappa(String codice_t) throws SQLException {

        Connection conn = DriverManager.getConnection("jdbc:mariadb://localhost:3306/progetto?user=root&password=root");
        Statement st = conn.createStatement();
        ResultSet rs;
        String sql = buildCreateTappaSQL(codice_t);

        try {

            rs = st.executeQuery(sql);
            conn.close();
            rs.next();

        } catch (SQLException ex) {
            System.err.println("sql exception");
            ex.printStackTrace();
            conn.close();
            return;
        }

    }

    private static String buildCreateTappaSQL(String codice_t) {

        return "INSERT INTO Tappa(codice_t)" +
                "VALUES('" + codice_t + "')";

    }

    public void modificaTappa(String codice_t) throws SQLException {
        try {
            updateTappa(codice_t);
        } catch (SQLException e) {
            System.err.println(e.getMessage());
            e.printStackTrace();
            return;
        }

    }

    private static void updateTappa(String codice_t) throws SQLException {

        Connection conn = DriverManager.getConnection("jdbc:mariadb://localhost:3306/progetto?user=root&password=root");
        Statement st = conn.createStatement();
        ResultSet rs;
        String sql = buildUpdateTappaSQL(codice_t);

        try {
            rs = st.executeQuery(sql);
            conn.close();
            rs.next();


        } catch (SQLException ex) {
            System.err.println("sql exception");
            ex.printStackTrace();
            conn.close();
            return;
        }


    }

    public static String buildUpdateTappaSQL(String codice_t) throws SQLException {
        return "UPDATE Tappa SET codice_t = '" + codice_t + "'";
    }

    public List<Tappa> getAllTappe()  throws RemoteException, SQLException {
        Connection conn = DriverManager.getConnection("jdbc:mariadb://localhost:3306/progetto?user=root&password=root");
        Statement stmt = conn.createStatement();

        String sql = "SELECT * FROM Tappa";
        ResultSet rs = stmt.executeQuery(sql);
        List<Tappa> tappaList = new ArrayList<>();

        while (rs.next()) {
            String codice_t = rs.getString("codice_t");

            Tappa tappe = new Tappa(codice_t);

            tappaList.add(tappe);
        }
        return tappaList;
    }

    public void  cancellaTappa(String codice_t)throws SQLException {
        try {
            deleteTappa(codice_t);
        }catch (SQLException e){
            System.err.println(e.getMessage());
            e.printStackTrace();
            return;
        }
    }
    private static void deleteTappa(String codice_t) throws SQLException {

        Connection conn = DriverManager.getConnection("jdbc:mariadb://localhost:3306/progetto?user=root&password=root");
        Statement st = conn.createStatement();
        ResultSet rs;
        String sql = buildDeleteTappaSQL(codice_t);

        try {
            rs = st.executeQuery(sql);
            conn.close();
            rs.next();


        } catch (SQLException ex) {
            System.err.println("sql exception");
            ex.printStackTrace();
            conn.close();
            return;
        }

    }
    private static String buildDeleteTappaSQL(String codice_t){
        return "DELETE FROM Tappa WHERE codice_t='"+codice_t+"'";

    }

}
