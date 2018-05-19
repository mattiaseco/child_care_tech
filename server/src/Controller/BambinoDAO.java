package Controller;

import common.Classes.Bambino;
import common.Interface.iBambinoDAO;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class BambinoDAO extends UnicastRemoteObject implements iBambinoDAO {

    public BambinoDAO() throws RemoteException {
    }

    @Override
    public void inserisciBambino(String cf, String nome, String cognome, LocalDate data, String indirizzo, String contatto1, String contatto2) throws SQLException {

        try {
            createKid(cf, nome, cognome, data, indirizzo, contatto1, contatto2);
        } catch (SQLException e) {

            System.out.println(e.getMessage());
            e.printStackTrace();
            return;
        }

    }

    private static void createKid(String cf, String nome, String cognome, LocalDate data, String indirizzo, String contatto1, String contatto2) throws SQLException {

        Connection conn = DriverManager.getConnection("jdbc:mariadb://localhost:3306/progetto?user=root&password=root");
        Statement st = conn.createStatement();
        ResultSet rs;
        String sql = buildCreateKidSQL(cf, nome, cognome, data, indirizzo, contatto1, contatto2);

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

    private static String buildCreateKidSQL(String cf, String nome, String cognome, LocalDate data, String indirizzo, String contatto1, String contatto2) {

        return "INSERT INTO Bambino(cf,nome,cognome,data,indirizzo,contatto1,contatto2)" +
                "VALUES('" + cf + "','" + nome + "','" + cognome + "','" + data + "','" + indirizzo + "','" + contatto1 + "','" + contatto2 + "')";

    }

    public void modificaBambino(String cf, String nome, String cognome, LocalDate data, String indirizzo, String contatto1, String contatto2) throws SQLException {
        try {
            updateKid(cf, nome, cognome, data, indirizzo, contatto1, contatto2);
        } catch (SQLException e) {
            System.err.println(e.getMessage());
            e.printStackTrace();
            return;
        }

    }

    private static void updateKid(String cf, String nome, String cognome, LocalDate data, String indirizzo, String contatto1, String contatto2) throws SQLException {

        Connection conn = DriverManager.getConnection("jdbc:mariadb://localhost:3306/progetto?user=root&password=root");
        Statement st = conn.createStatement();
        ResultSet rs;
        String sql = buildUpdateKidSQL(cf, nome, cognome, data, indirizzo, contatto1, contatto2);

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

    public static String buildUpdateKidSQL(String cf, String nome, String cognome, LocalDate data, String indirizzo, String contatto1, String contatto2) throws SQLException {
        return "UPDATE Bambino SET cf = '" + cf + "' , nome = '" + nome + "', cognome ='" + cognome + "', data ='" + data + "', indirizzo ='" + indirizzo + "', contatto1 ='" + contatto1 + "', contatto2='" + contatto2 + "'WHERE cf='"+cf+"'";
    }

    public List<Bambino> getAllBambini() throws RemoteException, SQLException {
        Connection conn = DriverManager.getConnection("jdbc:mariadb://localhost:3306/progetto?user=root&password=root");
        Statement stmt = conn.createStatement();

        String sql = "SELECT * FROM Bambino";
        ResultSet rs = stmt.executeQuery(sql);
        List<Bambino> kids = new ArrayList<>();

        while (rs.next()) {
            String cf = rs.getString("cf");
            String nome = rs.getString("nome");
            String cognome = rs.getString("cognome");
            LocalDate data = LocalDate.parse(rs.getString("data"));
            String indirizzo = rs.getString("indirizzo");
            String contatto1 = rs.getString("contatto1");
            String contatto2 = rs.getString("contatto2");

            Bambino bambino = new Bambino(cf, nome, cognome, data, indirizzo, contatto1, contatto2);

            kids.add(bambino);
        }
        return kids;
    }

    public void cancellaBambino(String cf)throws SQLException {
        try {
            deleteKid(cf);
        }catch (SQLException e){
            System.err.println(e.getMessage());
            e.printStackTrace();
            return;
        }
    }
    private static void deleteKid(String cf) throws SQLException {

        Connection conn = DriverManager.getConnection("jdbc:mariadb://localhost:3306/progetto?user=root&password=root");
        Statement st = conn.createStatement();
        ResultSet rs;
        String sql = buildDeleteKidSQL(cf);

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
    private static String buildDeleteKidSQL(String cf){
        return "DELETE FROM Bambino WHERE cf='"+cf+"'";

    }
}
