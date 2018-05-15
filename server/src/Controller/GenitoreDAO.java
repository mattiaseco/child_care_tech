package Controller;

import common.Classes.Genitore;
import common.Interface.iGenitoreDAO;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;

import java.util.List;

public class GenitoreDAO extends UnicastRemoteObject implements iGenitoreDAO {

    public GenitoreDAO() throws RemoteException {
    }

    @Override
    public void inserisciGenitore(String cf, String nome, String cognome, LocalDate data, String indirizzo, String telefono) throws SQLException {

        try {
            createGenitore(cf, nome, cognome, data, indirizzo, telefono);
        } catch (SQLException e) {

            System.out.println(e.getMessage());
            e.printStackTrace();
            return;
        }

    }

    private static void createGenitore(String cf, String nome, String cognome, LocalDate data, String indirizzo, String telefono) throws SQLException {

        Connection conn = DriverManager.getConnection("jdbc:mariadb://localhost:3306/progetto?user=root&password=root");
        Statement st = conn.createStatement();
        ResultSet rs;
        String sql = buildCreateGenitoreSQL(cf, nome, cognome, data, indirizzo, telefono);

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

    private static String buildCreateGenitoreSQL(String cf, String nome, String cognome, LocalDate data, String indirizzo, String telefono) {

        return "INSERT INTO Genitore(cf,nome,cognome,data,indirizzo,telefono)" +
                "VALUES('" + cf + "','" + nome + "','" + cognome + "','" + data + "','" + indirizzo + "','" + telefono + "')";

    }

    public void modificaGenitore(String cf, String nome, String cognome, LocalDate data, String indirizzo, String telefono) throws SQLException {
        try {
            updateGenitore(cf, nome, cognome, data, indirizzo, telefono);
        } catch (SQLException e) {
            System.err.println(e.getMessage());
            e.printStackTrace();
            return;
        }

    }

    private static void updateGenitore(String cf, String nome, String cognome, LocalDate data, String indirizzo, String telefono) throws SQLException {

        Connection conn = DriverManager.getConnection("jdbc:mariadb://localhost:3306/progetto?user=root&password=root");
        Statement st = conn.createStatement();
        ResultSet rs;
        String sql = buildUpdateGenitoreSQL(cf, nome, cognome, data, indirizzo, telefono);

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

    public static String buildUpdateGenitoreSQL(String cf, String nome, String cognome, LocalDate data, String indirizzo, String telefono) throws SQLException {
        return "UPDATE Genitore SET cf = '"+cf+"' and nome = '"+nome+"'and cognome ='"+cognome+"'and data ='"+data+"'and indirizzo ='"+indirizzo+"'and telefono ='"+telefono+"'";
    }


    public List<Genitore> getAllGenitori() throws RemoteException, SQLException {
        Connection conn = DriverManager.getConnection("jdbc:mariadb://localhost:3306/progetto?user=root&password=root");
        Statement stmt = conn.createStatement();

        String sql = "SELECT * FROM Genitore";
        ResultSet rs = stmt.executeQuery(sql);
        List<Genitore> genitoreList = new ArrayList<>();

        while (rs.next()) {
            String cf = rs.getString("cf");
            String nome = rs.getString("nome");
            String cognome = rs.getString("cognome");
            LocalDate data = LocalDate.parse(rs.getString("data"));
            String indirizzo = rs.getString("indirizzo");
            String telefono = rs.getString("telefono");

            Genitore Genitore = new Genitore(cf, nome, cognome, data, indirizzo, telefono);

            genitoreList.add(Genitore);
        }
        return genitoreList;

    }
    public void cancellaGenitore(String cf)throws SQLException {
        try {
            deleteGenitore(cf);
        }catch (SQLException e){
            System.err.println(e.getMessage());
            e.printStackTrace();
            return;
        }
    }
    private static void deleteGenitore(String cf) throws SQLException {

        Connection conn = DriverManager.getConnection("jdbc:mariadb://localhost:3306/progetto?user=root&password=root");
        Statement st = conn.createStatement();
        ResultSet rs;
        String sql = buildDeleteGenitoreSQL(cf);

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
    private static String buildDeleteGenitoreSQL(String cf){
        return "DELETE FROM Genitore WHERE cf='"+cf+"'";

    }
}
