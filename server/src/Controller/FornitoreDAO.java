package Controller;

import common.Classes.Fornitore;

import common.Interface.iFornitoreDAO;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class FornitoreDAO extends UnicastRemoteObject implements iFornitoreDAO {

    public FornitoreDAO() throws RemoteException {}

    @Override
    public void inserisciFornitore(String partita_iva, String cf, String nome, String cognome, LocalDate data, String indirizzo, String telefono) throws SQLException {

        try {
            createFornitore (partita_iva, cf, nome, cognome, data, indirizzo, telefono);
        } catch (SQLException e) {

            System.out.println(e.getMessage());
            e.printStackTrace();
            return;
        }

    }

    private static void createFornitore (String partita_iva, String cf, String nome, String cognome, LocalDate data, String indirizzo, String telefono) throws SQLException {

        Connection conn = DriverManager.getConnection("jdbc:mariadb://localhost:3306/progetto?user=root&password=root");
        Statement st = conn.createStatement();
        ResultSet rs;
        String sql = buildCreateFornitoreSQL(partita_iva, cf, nome, cognome, data, indirizzo, telefono);

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

    private static String buildCreateFornitoreSQL(String partita_iva, String cf, String nome, String cognome, LocalDate data, String indirizzo, String telefono){

        return "INSERT INTO Fornitore(partita_iva,cf,nome,cognome,data,indirizzo,telefono,contatto1,contatto2)" +
                "VALUES('"+partita_iva+"','"+cf+"','"+nome+"','"+cognome+"','"+data+"','"+indirizzo+"','"+telefono+"')";

    }

    public void modificaFornitore(String partita_iva, String cf, String nome, String cognome, LocalDate data, String indirizzo, String telefono) throws SQLException {
        try{
            updateFornitore(partita_iva, cf, nome, cognome, data, indirizzo, telefono);
        }catch (SQLException e){
            System.err.println(e.getMessage());
            e.printStackTrace();
            return;
        }

    }

    private static void updateFornitore(String partita_iva, String cf, String nome, String cognome, LocalDate data, String indirizzo, String telefono) throws SQLException {

        Connection conn = DriverManager.getConnection("jdbc:mariadb://localhost:3306/progetto?user=root&password=root");
        Statement st = conn.createStatement();
        ResultSet rs;
        String sql = buildUpdateFornitoreSQL(partita_iva, cf, nome, cognome, data, indirizzo, telefono);

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

    public static String buildUpdateFornitoreSQL(String partita_iva, String cf, String nome, String cognome, LocalDate data, String indirizzo, String telefono) throws SQLException {
        return "UPDATE Personale SET partita_iva = '"+partita_iva+"' and cf = '"+cf+"' and nome = '"+nome+"'and cognome ='"+cognome+"'and data ='"+data+"'and indirizzo ='"+indirizzo+"'and telefono ='"+telefono+"'";
    }

    public List<Fornitore> getAllFornitore() throws RemoteException,SQLException {
        Connection conn = DriverManager.getConnection("jdbc:mariadb://localhost:3306/progetto?user=root&password=root");
        Statement stmt = conn.createStatement();

        String sql = "SELECT * FROM Fornitore";
        ResultSet rs=stmt.executeQuery(sql);
        List<Fornitore> fornitoreList = new ArrayList<>();

        while (rs.next()) {
            String cf = rs.getString("cf");
            String partita_iva= rs.getString("partita_iva");
            String nome = rs.getString("nome");
            String cognome = rs.getString("cognome");
            LocalDate data= LocalDate.parse(rs.getString("data"));
            String indirizzo = rs.getString("indirizzo");
            String telefono = rs.getString("telefono");

            Fornitore fornitore= new Fornitore(partita_iva,cf,nome,cognome,data,indirizzo,telefono);

            fornitoreList.add(fornitore);
        }
        return fornitoreList;

    }
    public void cancellaFornitore(String cf)throws SQLException {
        try {
            deleteFornitore(cf);
        }catch (SQLException e){
            System.err.println(e.getMessage());
            e.printStackTrace();
            return;
        }
    }
    private static void deleteFornitore(String cf) throws SQLException {

        Connection conn = DriverManager.getConnection("jdbc:mariadb://localhost:3306/progetto?user=root&password=root");
        Statement st = conn.createStatement();
        ResultSet rs;
        String sql = buildDeleteFornitoreSQL(cf);

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
    private static String buildDeleteFornitoreSQL(String cf){
        return "DELETE FROM Fornitore WHERE cf='"+cf+"'";

    }
}
