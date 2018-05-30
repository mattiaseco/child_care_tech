package Controller;

import common.Classes.Contatti;
import common.Interface.iContattiDAO;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class ContattiDAO extends UnicastRemoteObject implements iContattiDAO {

    public ContattiDAO() throws RemoteException {
    }

    @Override
    public void inserisciContatti(String cf, String nome, String cognome, LocalDate data, String indirizzo, String telefono) throws SQLException {

        try {
            createContatti(cf, nome, cognome, data, indirizzo, telefono);
        } catch (SQLException e) {

            System.out.println(e.getMessage());
            e.printStackTrace();
            return;
        }

    }

    private static void createContatti(String cf, String nome, String cognome, LocalDate data, String indirizzo, String telefono) throws SQLException {

        Connection conn = DriverManager.getConnection("jdbc:mariadb://localhost:3306/progetto?user=root&password=root");
        Statement st = conn.createStatement();
        ResultSet rs;
        String sql = buildCreateContattiSQL(cf, nome, cognome, data, indirizzo, telefono);

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

    private static String buildCreateContattiSQL(String cf, String nome, String cognome, LocalDate data, String indirizzo, String telefono) {

        return "INSERT INTO Contatti(cf,nome,cognome,data,indirizzo,telefono)" +
                "VALUES('" + cf + "','" + nome + "','" + cognome + "','" + data + "','" + indirizzo + "','" + telefono + "')";

    }

    public void modificaContatti(String cf, String nome, String cognome, LocalDate data, String indirizzo, String telefono) throws SQLException {
        try {
            updateContatti(cf, nome, cognome, data, indirizzo, telefono);
        } catch (SQLException e) {
            System.err.println(e.getMessage());
            e.printStackTrace();
            return;
        }

    }

    private static void updateContatti(String cf, String nome, String cognome, LocalDate data, String indirizzo, String telefono) throws SQLException {

        Connection conn = DriverManager.getConnection("jdbc:mariadb://localhost:3306/progetto?user=root&password=root");
        Statement st = conn.createStatement();
        ResultSet rs;
        String sql = buildUpdateContattiSQL(cf, nome, cognome, data, indirizzo, telefono);

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

    public static String buildUpdateContattiSQL(String cf, String nome, String cognome, LocalDate data, String indirizzo, String telefono) throws SQLException {
        return "UPDATE Contatti SET cf = '"+cf+"' , nome = '"+nome+"', cognome ='"+cognome+"', data ='"+data+"', indirizzo ='"+indirizzo+"', telefono ='"+telefono+"'WHERE cf='"+cf+"'";
    }
    public List<Contatti> getAllContatti() throws RemoteException, SQLException {
        Connection conn = DriverManager.getConnection("jdbc:mariadb://localhost:3306/progetto?user=root&password=root");
        Statement stmt = conn.createStatement();

        String sql = "SELECT * FROM Contatti";
        ResultSet rs = stmt.executeQuery(sql);
        List<Contatti> contattiList = new ArrayList<>();

        while (rs.next()) {
            String cf = rs.getString("cf");
            String nome = rs.getString("nome");
            String cognome = rs.getString("cognome");
            LocalDate data = LocalDate.parse(rs.getString("data"));
            String indirizzo = rs.getString("indirizzo");
            String telefono = rs.getString("telefono");

            Contatti Contatto = new Contatti(cf, nome, cognome, data, indirizzo, telefono);

            contattiList.add(Contatto);
        }
        return contattiList;

    }

    public void cancellaContatti(String cf)throws SQLException {
        try {
            deleteContatto(cf);
        }catch (SQLException e){
            System.err.println(e.getMessage());
            e.printStackTrace();
            return;
        }
    }
    private static void deleteContatto(String cf) throws SQLException {

        Connection conn = DriverManager.getConnection("jdbc:mariadb://localhost:3306/progetto?user=root&password=root");
        Statement st = conn.createStatement();
        ResultSet rs;
        String sql = buildDeleteContattoSQL(cf);

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
    private static String buildDeleteContattoSQL(String cf){
        return "DELETE FROM Contatti WHERE cf='"+cf+"'";

    }
    public List<String>getAllCf()throws RemoteException,SQLException{

        Connection conn = DriverManager.getConnection("jdbc:mariadb://localhost:3306/progetto?user=root&password=root");
        Statement stmt = conn.createStatement();

        String sql = "SELECT * FROM Contatti ";
        ResultSet rs = stmt.executeQuery(sql);
        List<String> contattiCf = new ArrayList<>();

        while (rs.next()) {
            String cf = rs.getString("cf");

            contattiCf.add(cf);

        }
        return contattiCf;


    }


}
