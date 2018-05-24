package Controller;


import common.Classes.Piatto;
import common.Interface.iPiattoDAO;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PiattoDAO extends UnicastRemoteObject implements iPiattoDAO {
    public PiattoDAO() throws RemoteException {}

    @Override
    public void inserisciPiatto(String nome_p, String tipo) throws SQLException {

        try {
            createPiatto (nome_p,tipo);
        } catch (SQLException e) {

            System.out.println(e.getMessage());
            e.printStackTrace();
            return;
        }

    }

    private static void createPiatto (String nome_p, String tipo) throws SQLException {

        Connection conn = DriverManager.getConnection("jdbc:mariadb://localhost:3306/progetto?user=root&password=root");
        Statement st = conn.createStatement();
        ResultSet rs;
        String sql = buildCreatePiattoSQL(nome_p,tipo);

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

    private static String buildCreatePiattoSQL(String nome_p, String tipo){

        return "INSERT INTO Piatto(nome_p,tipo)" +
                "VALUES('"+nome_p+"','"+tipo+"')";

    }

    public void modificaPiatto(String nome_p, String tipo) throws SQLException {
        try{
            updatePiatto(nome_p,tipo);
        }catch (SQLException e){
            System.err.println(e.getMessage());
            e.printStackTrace();
            return;
        }

    }

    private static void updatePiatto(String nome_p, String tipo) throws SQLException {

        Connection conn = DriverManager.getConnection("jdbc:mariadb://localhost:3306/progetto?user=root&password=root");
        Statement st = conn.createStatement();
        ResultSet rs;
        String sql = buildUpdatePiattoSQL(nome_p,tipo);

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

    public static String buildUpdatePiattoSQL( String nome_p, String tipo) throws SQLException {
        return "UPDATE Piatto SET nome_p = '"+nome_p+"' , tipo = '"+tipo+"'";
    }

    public List<Piatto> getAllPiatti() throws RemoteException,SQLException {
        Connection conn = DriverManager.getConnection("jdbc:mariadb://localhost:3306/progetto?user=root&password=root");
        Statement stmt = conn.createStatement();

        String sql = "SELECT * FROM Piatto";
        ResultSet rs=stmt.executeQuery(sql);
        List<Piatto> piattoList = new ArrayList<>();

        while (rs.next()) {
            String nome_p = rs.getString("nome_p");
            String tipo=rs.getString("tipo");
            Piatto piatto= new Piatto(nome_p,tipo);

            piattoList.add(piatto);
        }
        return piattoList;

    }
    public void cancellaPiatti(String nome_p)throws SQLException {
        try {
            deletePiatto(nome_p);
        }catch (SQLException e){
            System.err.println(e.getMessage());
            e.printStackTrace();
            return;
        }
    }
    private static void deletePiatto(String nome_p) throws SQLException {

        Connection conn = DriverManager.getConnection("jdbc:mariadb://localhost:3306/progetto?user=root&password=root");
        Statement st = conn.createStatement();
        ResultSet rs;
        String sql = buildDeletePiattoSQL(nome_p);

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
    private static String buildDeletePiattoSQL(String nome_p){
        return "DELETE FROM Piatto WHERE nome_p='"+nome_p+"'";

    }

    public List<Piatto>getAllPrimi()throws  RemoteException,SQLException{
        Connection conn = DriverManager.getConnection("jdbc:mariadb://localhost:3306/progetto?user=root&password=root");
        Statement stmt = conn.createStatement();

        String sql = "SELECT * FROM Piatto WHERE tipo='"+"primo"+"'";
        ResultSet rs=stmt.executeQuery(sql);
        List<Piatto> piattoList = new ArrayList<>();

        while (rs.next()) {
            String nome_p = rs.getString("nome_p");
            String tipo=rs.getString("tipo");
            Piatto piatto= new Piatto(nome_p,tipo);

            piattoList.add(piatto);
        }
        return piattoList;

    }
    public List<Piatto>getAllSecondi()throws  RemoteException,SQLException{
        Connection conn = DriverManager.getConnection("jdbc:mariadb://localhost:3306/progetto?user=root&password=root");
        Statement stmt = conn.createStatement();

        String sql = "SELECT * FROM Piatto WHERE tipo='"+"secondo"+"'";
        ResultSet rs=stmt.executeQuery(sql);
        List<Piatto> piattoList = new ArrayList<>();

        while (rs.next()) {
            String nome_p = rs.getString("nome_p");
            String tipo=rs.getString("tipo");
            Piatto piatto= new Piatto(nome_p,tipo);

            piattoList.add(piatto);
        }
        return piattoList;}
    public List<Piatto>getAllContorni()throws  RemoteException,SQLException{

        Connection conn = DriverManager.getConnection("jdbc:mariadb://localhost:3306/progetto?user=root&password=root");
        Statement stmt = conn.createStatement();

        String sql = "SELECT * FROM Piatto WHERE tipo='"+"contorno"+"'";
        ResultSet rs=stmt.executeQuery(sql);
        List<Piatto> piattoList = new ArrayList<>();

        while (rs.next()) {
            String nome_p = rs.getString("nome_p");
            String tipo=rs.getString("tipo");

            Piatto piatto= new Piatto(nome_p,tipo);

            piattoList.add(piatto);
        }
        return piattoList;
    }


}
