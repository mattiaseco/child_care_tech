package Controller;

import common.Classes.Genitore;
import common.Classes.Person;
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
    public boolean inserisciGenitore(String cf, String nome, String cognome, LocalDate data, String indirizzo, String telefono) throws SQLException {

        try {
            return createGenitore(cf, nome, cognome, data, indirizzo, telefono);
        } catch (SQLException e) {

            System.out.println(e.getMessage());
            e.printStackTrace();
            return false;
        }

    }

    private static boolean createGenitore(String cf, String nome, String cognome, LocalDate data, String indirizzo, String telefono) throws SQLException {

        Connection conn = DriverManager.getConnection("jdbc:mariadb://localhost:3306/progetto?user=root&password=root");
        Statement st = conn.createStatement();
        ResultSet rs;
        String sql = buildCreateGenitoreSQL(cf, nome, cognome, data, indirizzo, telefono);

        try {
            rs = st.executeQuery(sql);
            conn.close();
            return rs.next();

        } catch (SQLException ex) {
            System.err.println("sql exception");
            ex.printStackTrace();
            conn.close();
            return false;
        }
    }

    private static String buildCreateGenitoreSQL(String cf, String nome, String cognome, LocalDate data, String indirizzo, String telefono) {

        return "INSERT INTO Genitore(cf,nome,cognome,data,indirizzo,telefono)" +
                "VALUES('" + cf + "','" + nome + "','" + cognome + "','" + data + "','" + indirizzo + "','" + telefono + "')";

    }

    public boolean modificaGenitore(String cf, String nome, String cognome, LocalDate data, String indirizzo, String telefono) throws SQLException {
        try {
            return updateGenitore(cf, nome, cognome, data, indirizzo, telefono);
        } catch (SQLException e) {
            System.err.println(e.getMessage());
            e.printStackTrace();
            return false;
        }

    }

    private static boolean updateGenitore(String cf, String nome, String cognome, LocalDate data, String indirizzo, String telefono) throws SQLException {

        Connection conn = DriverManager.getConnection("jdbc:mariadb://localhost:3306/progetto?user=root&password=root");
        Statement st = conn.createStatement();
        ResultSet rs;
        String sql = buildUpdateGenitoreSQL(cf, nome, cognome, data, indirizzo, telefono);

        try {
            rs = st.executeQuery(sql);
            conn.close();
            return rs.next();

        } catch (SQLException ex) {
            System.err.println("sql exception");
            ex.printStackTrace();
            conn.close();
            return false;
        }

    }

    public static String buildUpdateGenitoreSQL(String cf, String nome, String cognome, LocalDate data, String indirizzo, String telefono) throws SQLException {
        return "UPDATE Genitore SET cf = '" + cf + "' and nome = '" + nome + "'"; //TODO da finire!!!!
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
}
