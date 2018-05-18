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
    public void inserisciPiatto(String nome_p, String tipo, int quantità) throws SQLException {

        try {
            createPiatto (nome_p,tipo,quantità);
        } catch (SQLException e) {

            System.out.println(e.getMessage());
            e.printStackTrace();
            return;
        }

    }

    private static void createPiatto (String nome_p, String tipo, int quantità) throws SQLException {

        Connection conn = DriverManager.getConnection("jdbc:mariadb://localhost:3306/progetto?user=root&password=root");
        Statement st = conn.createStatement();
        ResultSet rs;
        String sql = buildCreatePiattoSQL(nome_p,tipo,quantità);

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

    private static String buildCreatePiattoSQL(String nome_p, String tipo, int quantità){

        return "INSERT INTO Piatto(nome_p,tipo,quantità)" +
                "VALUES('"+nome_p+"','"+tipo+"','"+quantità+"')";

    }

    public void modificaPiatto(String nome_p, String tipo, int quantità) throws SQLException {
        try{
            updatePiatto(nome_p,tipo,quantità);
        }catch (SQLException e){
            System.err.println(e.getMessage());
            e.printStackTrace();
            return;
        }

    }

    private static void updatePiatto(String nome_p, String tipo, int quantità) throws SQLException {

        Connection conn = DriverManager.getConnection("jdbc:mariadb://localhost:3306/progetto?user=root&password=root");
        Statement st = conn.createStatement();
        ResultSet rs;
        String sql = buildUpdatePiattoSQL(nome_p,tipo,quantità);

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

    public static String buildUpdatePiattoSQL( String nome_p, String tipo, int quantità) throws SQLException {
        return "UPDATE Piatto SET nome_p = '"+nome_p+"' and tipo = '"+tipo+"'and quantita ='"+quantità+"'";
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
            int quantità=rs.getInt("quantita");
            Piatto piatto= new Piatto(nome_p,tipo,quantità);

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


}
