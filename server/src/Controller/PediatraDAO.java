package Controller;

import common.Classes.Pediatra;
import common.Classes.Person;
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
    public boolean inserisciPediatra(String cod_qr, String cf, String nome, String cognome, LocalDate data, String indirizzo, String telefono) throws SQLException {

        try {
            return createPediatra (cod_qr, cf, nome, cognome, data, indirizzo, telefono);
        } catch (SQLException e) {

            System.out.println(e.getMessage());
            e.printStackTrace();
            return false;
        }

    }

    private static boolean createPediatra (String cod_qr, String cf, String nome, String cognome, LocalDate data, String indirizzo, String telefono) throws SQLException {

        Connection conn = DriverManager.getConnection("jdbc:mariadb://localhost:3306/progetto?user=root&password=root");
        Statement st = conn.createStatement();
        ResultSet rs;
        String sql = buildCreatePediatraSQL(cod_qr, cf, nome, cognome, data, indirizzo, telefono);

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

    private static String buildCreatePediatraSQL(String cod_qr, String cf, String nome, String cognome, LocalDate data, String indirizzo, String telefono){

        return "INSERT INTO Pediatra(cod_qr,cf,nome,cognome,data,indirizzo,telefono,contatto1,contatto2)" +
                "VALUES('"+cod_qr+"','"+cf+"','"+nome+"','"+cognome+"','"+data+"','"+indirizzo+"','"+telefono+"')";

    }

    public boolean modificaPediatra(String cod_qr, String cf, String nome, String cognome, LocalDate data, String indirizzo, String telefono) throws SQLException {
        try{
            return updatePediatra(cod_qr, cf, nome, cognome, data, indirizzo, telefono);
        }catch (SQLException e){
            System.err.println(e.getMessage());
            e.printStackTrace();
            return false;
        }

    }

    private static boolean updatePediatra(String cod_qr, String cf, String nome, String cognome, LocalDate data, String indirizzo, String telefono) throws SQLException {

        Connection conn = DriverManager.getConnection("jdbc:mariadb://localhost:3306/progetto?user=root&password=root");
        Statement st = conn.createStatement();
        ResultSet rs;
        String sql = buildUpdatePediatraSQL(cod_qr, cf, nome, cognome, data, indirizzo, telefono);

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

    public static String buildUpdatePediatraSQL(String cod_qr, String cf, String nome, String cognome, LocalDate data, String indirizzo, String telefono) throws SQLException {
        return "UPDATE Pediatra SET cod_qr = '"+cod_qr+"' and cf = '"+cf+"' and nome = '"+nome+"'"; //TODO da finire!!!!
    }

    public List<Pediatra> getAllPediatra() throws RemoteException,SQLException {
        Connection conn = DriverManager.getConnection("jdbc:mariadb://localhost:3306/progetto?user=root&password=root");
        Statement stmt = conn.createStatement();

        String sql = "SELECT * FROM Pediatra";
        ResultSet rs=stmt.executeQuery(sql);
        List<Pediatra> pediatraList = new ArrayList<>();

        while (rs.next()) {
            String cf = rs.getString("cf");
            String cod_qr= rs.getString("codqr");
            String nome = rs.getString("nome");
            String cognome = rs.getString("cognome");
            LocalDate data= LocalDate.parse(rs.getString("data"));
            String indirizzo = rs.getString("indirizzo");
            String telefono = rs.getString("telefono");

            Person personaPediatra=new Person(cf,nome,cognome,data,indirizzo);
            Pediatra pediatra= new Pediatra(cod_qr,cf,nome,cognome,data,indirizzo,telefono);

            pediatraList.add(pediatra);
        }
        return pediatraList;

    }

}
