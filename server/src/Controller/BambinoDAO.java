package Controller;

import common.Classes.Bambino;
import common.Classes.Ingredienti;
import common.Classes.Intolleranze;
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

        Connection conn = null;
        Statement st = null;
        ResultSet rs = null;
        String sql = buildCreateKidSQL(cf, nome, cognome, data, indirizzo, contatto1, contatto2);

        try {
            conn = DriverManager.getConnection("jdbc:mariadb://localhost:3306/progetto?user=root&password=root");
            st = conn.createStatement();
            rs = st.executeQuery(sql);
            rs.next();

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
            ex.printStackTrace();
        } finally {
            if(rs != null) rs.close();
            if(st != null) st.close();
            if(conn != null) conn.close();
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
        return "UPDATE Bambino SET cf = '" + cf + "' , nome = '" + nome + "', cognome ='" + cognome + "', data ='" + data + "', indirizzo ='" + indirizzo + "', contatto1 ='" + contatto1 + "', contatto2='" + contatto2 + "'WHERE cf='" + cf + "'";
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
        rs.close();
        stmt.close();
        conn.close();
        return kids;
    }

    public void cancellaBambino(String cf) throws SQLException {
        try {
            deleteKid(cf);
        } catch (SQLException e) {
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

    private static String buildDeleteKidSQL(String cf) {
        return "DELETE FROM Bambino WHERE cf='" + cf + "'";

    }

    @Override
    public void inserisciAllergia(Bambino bambino, Ingredienti ingrediente) throws SQLException {
        try {
            createIntolleranza(bambino.getCf(), ingrediente.getNome_i());
        } catch (SQLException e) {

            System.out.println(e.getMessage());
            e.printStackTrace();
            return;
        }
    }

    private static void createIntolleranza(String cf, String ingrediente) throws SQLException {
        Connection conn = DriverManager.getConnection("jdbc:mariadb://localhost:3306/progetto?user=root&password=root");
        Statement st = conn.createStatement();
        ResultSet rs;
        String sql = buildCreateIntolleranzaSQL(cf, ingrediente);

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

    private static String buildCreateIntolleranzaSQL(String cf, String ingrediente) throws SQLException {
        return "INSERT INTO Intolleranza(cf,ingrediente) VALUES('" + cf + "','" + ingrediente + "')";


    }

    public List<Ingredienti> getAllAllergie(Bambino bambino) throws RemoteException, SQLException {
        Connection conn = DriverManager.getConnection("jdbc:mariadb://localhost:3306/progetto?user=root&password=root");
        Statement stmt = conn.createStatement();

        String sql = "SELECT * FROM Ingrediente WHERE nome_i IN (" +
                "SELECT ingrediente FROM Intolleranza WHERE cf = '" + bambino.getCf() + "')";
        ResultSet rs = stmt.executeQuery(sql);
        List<Ingredienti> allergieList = new ArrayList<>();

        while (rs.next()) {
            String nome_i = rs.getString("nome_i");
            int quantita = rs.getInt("quantita");
            Ingredienti allergia = new Ingredienti(nome_i, quantita);

            allergieList.add(allergia);

        }
        rs.close();
        stmt.close();
        conn.close();
        return allergieList;
    }

    public void cancellaAllergia(Bambino bambino, Ingredienti ingrediente) throws RemoteException, SQLException {
        try {
            deleteAllergia(bambino.getCf(), ingrediente.getNome_i());
        } catch (SQLException e) {
            System.err.println(e.getMessage());
            e.printStackTrace();
            return;
        }
    }

    private static void deleteAllergia(String cf, String nome_i) throws SQLException {
        Connection conn = DriverManager.getConnection("jdbc:mariadb://localhost:3306/progetto?user=root&password=root");
        Statement st = conn.createStatement();
        ResultSet rs;
        String sql = buildDeleteAllergiaSQL(cf, nome_i);

        try {
            rs = st.executeQuery(sql);
            conn.close();
            rs.next();


        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
            ex.printStackTrace();
            conn.close();
            return;
        }
    }

    private static String buildDeleteAllergiaSQL(String cf, String nome_i) {
        return "DELETE FROM Intolleranza WHERE cf='" + cf + "' AND ingrediente='" + nome_i + "'";

    }

    public List<String> getAllCf() throws RemoteException, SQLException {
        Connection conn = DriverManager.getConnection("jdbc:mariadb://localhost:3306/progetto?user=root&password=root");
        Statement stmt = conn.createStatement();

        String sql = "SELECT * FROM Bambino ";
        ResultSet rs = stmt.executeQuery(sql);
        List<String> kidsCf = new ArrayList<>();

        while (rs.next()) {
            String cf = rs.getString("cf");

            kidsCf.add(cf);

        }
        rs.close();
        stmt.close();
        conn.close();
        return kidsCf;


    }

    public Bambino getKid(String cod_f) throws RemoteException, SQLException {
        Connection conn = DriverManager.getConnection("jdbc:mariadb://localhost:3306/progetto?user=root&password=root");
        Statement stmt = conn.createStatement();
        String sql = "SELECT * FROM Bambino WHERE cf='" + cod_f + "'";
        ResultSet rs = stmt.executeQuery(sql);
        rs.next();
        String cf = rs.getString("cf");
        String nome = rs.getString("nome");
        String cognome = rs.getString("cognome");
        LocalDate data = LocalDate.parse(rs.getString("data"));
        String indirizzo = rs.getString("indirizzo");
        String contatto1 = rs.getString("contatto1");
        String contatto2 = rs.getString("contatto2");

        Bambino kid = new Bambino(cf, nome, cognome, data, indirizzo, contatto1, contatto2);
        rs.close();
        stmt.close();
        conn.close();
        return kid;

    }

    public void inserisciBambinoPresente(String cf) throws SQLException {

        try {
            createBambinoPresente(cf);
        } catch (SQLException e) {

            System.out.println(e.getMessage());
            e.printStackTrace();
            return;
        }

    }

    private static void createBambinoPresente(String cf) throws SQLException {

        Connection conn = DriverManager.getConnection("jdbc:mariadb://localhost:3306/progetto?user=root&password=root");
        Statement st = conn.createStatement();
        ResultSet rs;
        String sql = buildCreateBambinoPresenteSQL(cf);

        try {

            rs = st.executeQuery(sql);
            conn.close();
            rs.next();

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
            ex.printStackTrace();
            conn.close();
            return;
        }

    }

    private static String buildCreateBambinoPresenteSQL(String cf) {

        return "INSERT INTO Presenza(cf)" +
                "VALUES('" + cf + "')";

    }


    public List<Bambino> getAllPresenti() throws  RemoteException,SQLException{
        Connection conn = DriverManager.getConnection("jdbc:mariadb://localhost:3306/progetto?user=root&password=root");
        Statement stmt = conn.createStatement();

        String sql = "SELECT * FROM Bambino WHERE cf IN(SELECT cf FROM Presenza)";
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
        rs.close();
        stmt.close();
        conn.close();
        return kids;

    }

    public List<Intolleranze> getIntolleranzeBambino(Bambino bambino) throws RemoteException,SQLException{
        Connection conn = DriverManager.getConnection("jdbc:mariadb://localhost:3306/progetto?user=root&password=root");
        Statement stmt = conn.createStatement();

        String sql ="SELECT * FROM Intolleranza WHERE cf = '" + bambino.getCf() + "')";
        ResultSet rs = stmt.executeQuery(sql);
        List<Intolleranze> allergieList = new ArrayList<>();

        while (rs.next()) {
            String cf = rs.getString("cf");
            String ingrediente = rs.getString("ingrediente");
            Intolleranze allergia = new Intolleranze(getKid(cf), getIngrediente(ingrediente));

            allergieList.add(allergia);

        }
        rs.close();
        stmt.close();
        conn.close();
        return allergieList;


    }
    private Ingredienti getIngrediente(String ingrediente) throws RemoteException,SQLException{

        Connection conn = DriverManager.getConnection("jdbc:mariadb://localhost:3306/progetto?user=root&password=root");
        Statement stmt = conn.createStatement();
        String sql = "SELECT * FROM Ingrediente WHERE nome_i='" + ingrediente + "'";
        ResultSet rs = stmt.executeQuery(sql);
        rs.next();
        String nome_i = rs.getString("nome_i");
        int quantita = rs.getInt("quantita");

        Ingredienti kid = new Ingredienti(nome_i,quantita);
        rs.close();
        stmt.close();
        conn.close();
        return kid;


    }
    public List<Bambino>getAllBambiniNomeCognome(String name,String sourname)throws RemoteException,SQLException{
        Connection conn = DriverManager.getConnection("jdbc:mariadb://localhost:3306/progetto?user=root&password=root");
        Statement stmt = conn.createStatement();

        String sql = "SELECT * FROM Bambino WHERE nome='"+name+"'OR cognome='"+sourname+"'";
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
        rs.close();
        stmt.close();
        conn.close();
        return kids;

    }
    public void cancellaPresenti()throws RemoteException,SQLException{
        try {
            deleteBambiniPresenti();
        } catch (SQLException e) {
            System.err.println(e.getMessage());
            e.printStackTrace();
            return;
        }
    }

    private static void deleteBambiniPresenti() throws SQLException {

        Connection conn = DriverManager.getConnection("jdbc:mariadb://localhost:3306/progetto?user=root&password=root");
        Statement st = conn.createStatement();
        ResultSet rs;
        String sql = buildDeleteBambiniPresentiSQL();

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

    private static String buildDeleteBambiniPresentiSQL() {
        return "DELETE FROM Presenza";

    }

}
