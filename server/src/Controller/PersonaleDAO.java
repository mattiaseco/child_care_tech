package Controller;

import common.Classes.Personale;
import common.Interface.iPersonaleDAO;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class PersonaleDAO extends UnicastRemoteObject implements iPersonaleDAO {

    public PersonaleDAO() throws RemoteException {}

    @Override
    public boolean inserisciPersonale(String cod_qr, String cf, String nome, String cognome, LocalDate data, String indirizzo, String telefono) throws SQLException {

        try {
            return createPersonale (cod_qr, cf, nome, cognome, data, indirizzo, telefono);
        } catch (SQLException e) {

            System.out.println(e.getMessage());
            e.printStackTrace();
            return false;
        }

    }

    private static boolean createPersonale (String cod_qr, String cf, String nome, String cognome, LocalDate data, String indirizzo, String telefono) throws SQLException {

        Connection conn = DriverManager.getConnection("jdbc:mariadb://localhost:3306/progetto?user=root&password=root");
        Statement st = conn.createStatement();
        ResultSet rs;
        String sql = buildCreatePersonaleSQL(cod_qr, cf, nome, cognome, data, indirizzo, telefono);

        try {
            rs = st.executeQuery(sql);
            conn.close();
            return rs.next();

        } catch(SQLException ex) {
            System.err.println("sql exception");
            ex.printStackTrace();
            conn.close();
            return false;
        }
    }

    private static String buildCreatePersonaleSQL(String cod_qr, String cf, String nome, String cognome, LocalDate data, String indirizzo, String telefono){

        return "INSERT INTO Personale(cod_qr,cf,nome,cognome,data,indirizzo,telefono,contatto1,contatto2)" +
                "VALUES('"+cod_qr+"','"+cf+"','"+nome+"','"+cognome+"','"+data+"','"+indirizzo+"','"+telefono+"')";

    }

    public boolean modificaPersonale(String cod_qr, String cf, String nome, String cognome, LocalDate data, String indirizzo, String telefono) throws SQLException {
        try{
            return updatePersonale(cod_qr, cf, nome, cognome, data, indirizzo, telefono);
        }catch (SQLException e){
            System.err.println(e.getMessage());
            e.printStackTrace();
            return false;
        }

    }

    private static boolean updatePersonale(String cod_qr, String cf, String nome, String cognome, LocalDate data, String indirizzo, String telefono) throws SQLException {

        Connection conn = DriverManager.getConnection("jdbc:mariadb://localhost:3306/progetto?user=root&password=root");
        Statement st = conn.createStatement();
        ResultSet rs;
        String sql = buildUpdatePersonaleSQL(cod_qr, cf, nome, cognome, data, indirizzo, telefono);

        try {
            rs = st.executeQuery(sql);
            conn.close();
            return rs.next();

        } catch(SQLException ex) {
            System.err.println("sql exception");
            ex.printStackTrace();
            conn.close();
            return false;
        }

    }

    public static String buildUpdatePersonaleSQL(String cod_qr, String cf, String nome, String cognome, LocalDate data, String indirizzo, String telefono) throws SQLException {
        return "UPDATE Personale SET cod_qr = '"+cod_qr+"' and cf = '"+cf+"' and nome = '"+nome+"'"; //TODO da finire!!!!
    }

    public List<Personale> getAllPersonale() throws RemoteException,SQLException {
        Connection conn = DriverManager.getConnection("jdbc:mariadb://localhost:3306/progetto?user=root&password=root");
        Statement stmt = conn.createStatement();

        String sql = "SELECT * FROM Personale";
        ResultSet rs=stmt.executeQuery(sql);
        List<Personale> personaleList = new ArrayList<>();

        while (rs.next()) {
            String cf = rs.getString("cf");
            String cod_qr= rs.getString("codqr");
            String nome = rs.getString("nome");
            String cognome = rs.getString("cognome");
            LocalDate data= LocalDate.parse(rs.getString("data"));
            String indirizzo = rs.getString("indirizzo");
            String telefono = rs.getString("telefono");

            Personale personale= new Personale(cod_qr,cf,nome,cognome,data,indirizzo,telefono);

            personaleList.add(personale);
        }
        return personaleList;

    }
}
