package Controller;

import common.Classes.Contatti;
import common.Classes.Person;
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
    public boolean inserisciContatti(String cf, String nome, String cognome, LocalDate data, String indirizzo, String telefono) throws SQLException {

        try {
            return createContatti(cf, nome, cognome, data, indirizzo, telefono);
        } catch (SQLException e) {

            System.out.println(e.getMessage());
            e.printStackTrace();
            return false;
        }

    }

    private static boolean createContatti(String cf, String nome, String cognome, LocalDate data, String indirizzo, String telefono) throws SQLException {

        Connection conn = DriverManager.getConnection("jdbc:mariadb://localhost:3306/progetto?user=root&password=root");
        Statement st = conn.createStatement();
        ResultSet rs;
        String sql = buildCreateContattiSQL(cf, nome, cognome, data, indirizzo, telefono);

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

    private static String buildCreateContattiSQL(String cf, String nome, String cognome, LocalDate data, String indirizzo, String telefono) {

        return "INSERT INTO Contatti(cf,nome,cognome,data,indirizzo,telefono)" +
                "VALUES('" + cf + "','" + nome + "','" + cognome + "','" + data + "','" + indirizzo + "','" + telefono + "')";

    }

    public boolean modificaContatti(String cf, String nome, String cognome, LocalDate data, String indirizzo, String telefono) throws SQLException {
        try {
            return updateContatti(cf, nome, cognome, data, indirizzo, telefono);
        } catch (SQLException e) {
            System.err.println(e.getMessage());
            e.printStackTrace();
            return false;
        }

    }

    private static boolean updateContatti(String cf, String nome, String cognome, LocalDate data, String indirizzo, String telefono) throws SQLException {

        Connection conn = DriverManager.getConnection("jdbc:mariadb://localhost:3306/progetto?user=root&password=root");
        Statement st = conn.createStatement();
        ResultSet rs;
        String sql = buildUpdateContattiSQL(cf, nome, cognome, data, indirizzo, telefono);

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

    public static String buildUpdateContattiSQL(String cf, String nome, String cognome, LocalDate data, String indirizzo, String telefono) throws SQLException {
        return "UPDATE Contatti SET cf = '" + cf + "' and nome = '" + nome + "'"; //TODO da finire!!!!
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


}
