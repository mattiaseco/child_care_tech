package Controller;

import common.Classes.Pediatra;
import common.Interface.iPediatraDAO;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class PediatraDAO extends UnicastRemoteObject implements iPediatraDAO {

    public PediatraDAO() throws RemoteException {}

    @Override
    public void inserisciPediatra(String cf, String nome, String cognome, LocalDate data, String indirizzo, String telefono) throws SQLException {

        try {
            createPediatra (cf, nome, cognome, data, indirizzo, telefono);
        } catch (SQLException e) {

            System.out.println(e.getMessage());
            e.printStackTrace();
            return;
        }

    }

    private static void createPediatra (String cf, String nome, String cognome, LocalDate data, String indirizzo, String telefono) throws SQLException {

        Connection conn = DriverManager.getConnection("jdbc:mariadb://localhost:3306/progetto?user=root&password=root");
        Statement st = conn.createStatement();
        ResultSet rs;
        String sql = buildCreatePediatraSQL(cf, nome, cognome, data, indirizzo, telefono);

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

    private static String buildCreatePediatraSQL(String cf, String nome, String cognome, LocalDate data, String indirizzo, String telefono){

        return "INSERT INTO Pediatra(cf,nome,cognome,data,indirizzo,telefono)" +
                "VALUES('"+cf+"','"+nome+"','"+cognome+"','"+data+"','"+indirizzo+"','"+telefono+"')";

    }

    public void modificaPediatra(String cf, String nome, String cognome, LocalDate data, String indirizzo, String telefono) throws SQLException {
        try{
            updatePediatra(cf, nome, cognome, data, indirizzo, telefono);
        }catch (SQLException e){
            System.err.println(e.getMessage());
            e.printStackTrace();
            return;
        }

    }

    private static void updatePediatra(String cf, String nome, String cognome, LocalDate data, String indirizzo, String telefono) throws SQLException {

        Connection conn = DriverManager.getConnection("jdbc:mariadb://localhost:3306/progetto?user=root&password=root");
        Statement st = conn.createStatement();
        ResultSet rs;
        String sql = buildUpdatePediatraSQL(cf, nome, cognome, data, indirizzo, telefono);

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

    public static String buildUpdatePediatraSQL(String cf, String nome, String cognome, LocalDate data, String indirizzo, String telefono) throws SQLException {
        return "UPDATE Pediatra SET cf = '"+cf+"' , nome = '"+nome+"', cognome ='"+cognome+"', data ='"+data+"', indirizzo ='"+indirizzo+"', telefono ='"+telefono+"'WHERE cf='"+cf+"'";
    }

    public List<Pediatra> getAllPediatra() throws RemoteException,SQLException {
        Connection conn = DriverManager.getConnection("jdbc:mariadb://localhost:3306/progetto?user=root&password=root");
        Statement stmt = conn.createStatement();

        String sql = "SELECT * FROM Pediatra";
        ResultSet rs=stmt.executeQuery(sql);
        List<Pediatra> pediatraList = new ArrayList<>();

        while (rs.next()) {
            String cf = rs.getString("cf");
            String nome = rs.getString("nome");
            String cognome = rs.getString("cognome");
            LocalDate data= LocalDate.parse(rs.getString("data"));
            String indirizzo = rs.getString("indirizzo");
            String telefono = rs.getString("telefono");

            Pediatra pediatra= new Pediatra(cf,nome,cognome,data,indirizzo,telefono);

            pediatraList.add(pediatra);
        }
        rs.close();
        stmt.close();
        conn.close();
        return pediatraList;

    }
    public void cancellaPediatra(String cf)throws SQLException {
        try {
            deletePediatra(cf);
        }catch (SQLException e){
            System.err.println(e.getMessage());
            e.printStackTrace();
            return;
        }
    }
    private static void deletePediatra(String cf) throws SQLException {

        Connection conn = DriverManager.getConnection("jdbc:mariadb://localhost:3306/progetto?user=root&password=root");
        Statement st = conn.createStatement();
        ResultSet rs;
        String sql = buildDeletePediatraSQL(cf);

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
    private static String buildDeletePediatraSQL(String cf){
        return "DELETE FROM Pediatra WHERE cf='"+cf+"'";

    }
    public List<String>getAllCf()throws RemoteException,SQLException{

        Connection conn = DriverManager.getConnection("jdbc:mariadb://localhost:3306/progetto?user=root&password=root");
        Statement stmt = conn.createStatement();

        String sql = "SELECT * FROM Pediatra ";
        ResultSet rs = stmt.executeQuery(sql);
        List<String> pediatraCf = new ArrayList<>();

        while (rs.next()) {
            String cf = rs.getString("cf");

            pediatraCf.add(cf);

        }
        rs.close();
        stmt.close();
        conn.close();
        return pediatraCf;


    }
    public Pediatra getPediatra(String cod_f)throws RemoteException,SQLException{

        Connection conn = DriverManager.getConnection("jdbc:mariadb://localhost:3306/progetto?user=root&password=root");
        Statement stmt = conn.createStatement();
        String sql = "SELECT * FROM Pediatra WHERE cf='" + cod_f + "'";
        ResultSet rs = stmt.executeQuery(sql);
        rs.next();
        String cf = rs.getString("cf");
        String nome = rs.getString("nome");
        String cognome = rs.getString("cognome");
        LocalDate data = LocalDate.parse(rs.getString("data"));
        String indirizzo = rs.getString("indirizzo");
        String telefono = rs.getString("telefono");


        Pediatra pediatra = new Pediatra(cf, nome, cognome, data, indirizzo, telefono);
        rs.close();
        stmt.close();
        conn.close();
        return pediatra;
    }

}
