package Controller;

import common.Classes.Pullman;
import common.Interface.iPullmanDAO;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PullmanDAO extends UnicastRemoteObject implements iPullmanDAO {

    public PullmanDAO() throws RemoteException {}

    @Override
    public void inserisciPullman(String targa,int capienza) throws SQLException {

        try {
            createPullman (targa,capienza);
        } catch (SQLException e) {

            System.out.println(e.getMessage());
            e.printStackTrace();
            return;
        }

    }

    private static void createPullman (String targa,int capienza) throws SQLException {

        Connection conn = DriverManager.getConnection("jdbc:mariadb://localhost:3306/progetto?user=root&password=root");
        Statement st = conn.createStatement();
        ResultSet rs;
        String sql = buildCreatePullmanSQL(targa,capienza);

        try {
            rs = st.executeQuery(sql);
            conn.close();
            rs.next();

        } catch(SQLException ex) {
            System.err.println("sql exception");
            ex.printStackTrace();
            conn.close();
            return;
        }
    }

    private static String buildCreatePullmanSQL(String targa,int capienza){

        return "INSERT INTO Pullman(targa,capienza)" +
                "VALUES('"+targa+"','"+capienza+"')";

    }

    public void modificaPullman(String targa,int capienza) throws SQLException {
        try{
            updatePullman(targa,capienza);
        }catch (SQLException e){
            System.err.println(e.getMessage());
            e.printStackTrace();
            return;
        }

    }

    private static void updatePullman(String targa,int capienza) throws SQLException {

        Connection conn = DriverManager.getConnection("jdbc:mariadb://localhost:3306/progetto?user=root&password=root");
        Statement st = conn.createStatement();
        ResultSet rs;
        String sql = buildUpdatePullmanSQL(targa,capienza);

        try {
            rs = st.executeQuery(sql);
            conn.close();
            rs.next();

        } catch(SQLException ex) {
            System.err.println("sql exception");
            ex.printStackTrace();
            conn.close();
            return ;
        }

    }

    public static String buildUpdatePullmanSQL( String targa,int capienza) throws SQLException {
        return "UPDATE Pullman SET targa = '"+targa+"' and capienza = '"+capienza+"'";
    }

    public List<Pullman> getAllPullman() throws RemoteException,SQLException {
        Connection conn = DriverManager.getConnection("jdbc:mariadb://localhost:3306/progetto?user=root&password=root");
        Statement stmt = conn.createStatement();

        String sql = "SELECT * FROM Pullman";
        ResultSet rs=stmt.executeQuery(sql);
        List<Pullman> pullmanList = new ArrayList<>();

        while (rs.next()) {
            String targa = rs.getString("targa");
            int capienza=rs.getInt("capienza");
            Pullman pullman= new Pullman(targa,capienza);

            pullmanList.add(pullman);
        }
        return pullmanList;

    }
    public void cancellaPullman(String targa)throws SQLException {
        try {
            deletePullman(targa);
        }catch (SQLException e){
            System.err.println(e.getMessage());
            e.printStackTrace();
            return;
        }
    }
    private static void deletePullman(String targa) throws SQLException {

        Connection conn = DriverManager.getConnection("jdbc:mariadb://localhost:3306/progetto?user=root&password=root");
        Statement st = conn.createStatement();
        ResultSet rs;
        String sql = buildDeletePullmanSQL(targa);

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
    private static String buildDeletePullmanSQL(String targa){
        return "DELETE FROM Pullman WHERE targa='"+targa+"'";

    }
}
