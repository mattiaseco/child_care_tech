package Controller;

import common.Classes.Bambino;
import common.Classes.Gita;
import common.Classes.Pullman;
import common.Interface.iGitaDAO;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class GitaDAO extends UnicastRemoteObject implements iGitaDAO {

    public GitaDAO() throws RemoteException {}

    @Override
    public void inserisciGita(int codice_g ,String destinazione, LocalDate data_partenza, LocalDate data_ritorno, Double costo,String descrizione) throws SQLException {

        try {
            createGita(codice_g,destinazione, data_partenza, data_ritorno,costo,descrizione);
        } catch (SQLException e) {

            System.out.println(e.getMessage());
            e.printStackTrace();
            return;
        }

    }

    private static void createGita(int codice_g, String destinazione, LocalDate data_partenza, LocalDate data_ritorno, Double costo,String descrizione) throws SQLException {

        Connection conn = DriverManager.getConnection("jdbc:mariadb://localhost:3306/progetto?user=root&password=root");
        Statement st = conn.createStatement();
        ResultSet rs;
        String sql = buildCreateGitaSQL(codice_g, destinazione, data_partenza, data_ritorno,costo,descrizione);

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

    private static String buildCreateGitaSQL(int codice_g, String destinazione, LocalDate data_partenza, LocalDate data_ritorno, Double costo,String descrizione) {

        return "INSERT INTO Gita(codice_g,destinazione,data_partenza,data_ritorno,costo,descrizione)" +
                " VALUES('"+codice_g+"','"+ destinazione + "','"+data_partenza+"','"+data_ritorno+"','"+costo+"','"+descrizione+"')";

    }

    public void insertNumPartecipanti(int codice_g, int num_partecipanti) throws SQLException {
        try {
            updateGita(codice_g,num_partecipanti);
        } catch (SQLException e) {
            System.err.println(e.getMessage());
            e.printStackTrace();
            return;
        }

    }

    private static void updateGita(int codice_g, int num_partecipanti) throws SQLException {

        Connection conn = DriverManager.getConnection("jdbc:mariadb://localhost:3306/progetto?user=root&password=root");
        Statement st = conn.createStatement();
        ResultSet rs;
        String sql = buildUpdateGitaSQL(codice_g,num_partecipanti);

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

    public static String buildUpdateGitaSQL(int codice_g, int num_partecipanti) throws SQLException {
        return "UPDATE Gita SET num_partecipanti ='" + num_partecipanti + "' WHERE codice_g = '"+codice_g+"'";
    }

    public void insertNumPullman(int codice_g,int num_pullman) throws SQLException {
        try {
            updateGitaPullman(codice_g,num_pullman);
        } catch (SQLException e) {
            System.err.println(e.getMessage());
            e.printStackTrace();
            return;
        }

    }

    private static void updateGitaPullman(int codice_g,int num_pullman) throws SQLException {

        Connection conn = DriverManager.getConnection("jdbc:mariadb://localhost:3306/progetto?user=root&password=root");
        Statement st = conn.createStatement();
        ResultSet rs;
        String sql = buildUpdateGitaPullmanSQL(codice_g,num_pullman);

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

    public static String buildUpdateGitaPullmanSQL(int codice_g,int num_pullman) throws SQLException {
        return "UPDATE Gita SET num_pullman ='" + num_pullman + "' WHERE codice_g = '"+codice_g+"'";
    }

    public List<Gita> getAllGite() throws RemoteException, SQLException {
        Connection conn = DriverManager.getConnection("jdbc:mariadb://localhost:3306/progetto?user=root&password=root");
        Statement stmt = conn.createStatement();

        String sql = "SELECT * FROM Gita";
        ResultSet rs = stmt.executeQuery(sql);
        List<Gita> gitaList = new ArrayList<>();

        while (rs.next()) {
            int codice_g = rs.getInt("codice_g");
            String destinazione = rs.getString("destinazione");
            int num_pullman=rs.getInt("num_pullman");
            int num_partecipanti=rs.getInt("num_partecipanti");
            LocalDate data_partenza= LocalDate.parse(rs.getString("data_partenza"));
            LocalDate data_ritorno=LocalDate.parse(rs.getString("data_ritorno"));
            Double costo=Double.parseDouble(rs.getString("costo"));
            String descrizione=rs.getString("descrizione");

            Gita gita = new Gita(codice_g,destinazione,num_pullman,num_partecipanti,data_partenza,data_ritorno,costo,descrizione);

            gitaList.add(gita);
        }
        rs.close();
        stmt.close();
        conn.close();
        return gitaList;
    }

    public void cancellaGita(int codice_g)throws SQLException {
        try {
            deleteGita(codice_g);
        }catch (SQLException e){
            System.err.println(e.getMessage());
            e.printStackTrace();
            return;
        }
    }
    private static void deleteGita(int codice_g) throws SQLException {

        Connection conn = DriverManager.getConnection("jdbc:mariadb://localhost:3306/progetto?user=root&password=root");
        Statement st = conn.createStatement();
        ResultSet rs;
        String sql = buildDeleteGitaSQL(codice_g);

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
    private static String buildDeleteGitaSQL(int codice_g){
        return "DELETE FROM Gita WHERE codice_g='"+codice_g+"'";

    }


    public void inserisciBambinoGita(int codice_g, Bambino bambino)throws RemoteException,SQLException{


        try {
            createBambinoGita(codice_g,bambino.getCf());
        } catch (SQLException e) {

            System.out.println(e.getMessage());
            e.printStackTrace();
            return;
        }

    }
    private static void createBambinoGita(int codice_g,String cf) throws SQLException {

        Connection conn = DriverManager.getConnection("jdbc:mariadb://localhost:3306/progetto?user=root&password=root");
        Statement st = conn.createStatement();
        ResultSet rs;
        String sql = buildCreateBambinoGitaSQL(codice_g,cf);

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
    private static String buildCreateBambinoGitaSQL(int codice_g, String cf) {

        return "INSERT INTO Aderisce(cf, codice_g)" +
                " VALUES('"+ cf + "','" + codice_g + "')";

    }

    public void cancellaBambinoGita(int codice_g, Bambino bambino)throws RemoteException,SQLException{
        try {
            deleteBambinoGita(codice_g,bambino.getCf());
        }catch (SQLException e){
            System.err.println(e.getMessage());
            e.printStackTrace();
            return;
        }
    }
    private static void deleteBambinoGita(int codice_g,String cf )throws SQLException{
        Connection conn = DriverManager.getConnection("jdbc:mariadb://localhost:3306/progetto?user=root&password=root");
        Statement st = conn.createStatement();
        ResultSet rs;
        String sql = buildDeleteBambinoGitaSQL(codice_g,cf);

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
    private static String buildDeleteBambinoGitaSQL(int codice_g,String cf){
        return "DELETE FROM Aderisce WHERE codice_g='"+codice_g+"' AND cf='"+cf+"'";

    }



    public void inserisciBambinoPullman(Pullman pullman, Bambino bambino)throws RemoteException,SQLException{


        try {
            createBambinoPullman(pullman.getTarga(),bambino.getCf());
        } catch (SQLException e) {

            System.out.println(e.getMessage());
            e.printStackTrace();
            return;
        }

    }

    private static void createBambinoPullman(String targa, String cf) throws SQLException {

        Connection conn = DriverManager.getConnection("jdbc:mariadb://localhost:3306/progetto?user=root&password=root");
        Statement st = conn.createStatement();
        ResultSet rs;
        String sql = buildCreateBambinoPullmanSQL(targa,cf);

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

    private static String buildCreateBambinoPullmanSQL(String targa, String cf) {

        return "INSERT INTO Sale( cf, targa)" +
                " VALUES('"+ cf + "','" + targa + "')";

    }
    public List<Bambino>getAllBambiniGita(Gita gita)throws RemoteException,SQLException{
        Connection conn = DriverManager.getConnection("jdbc:mariadb://localhost:3306/progetto?user=root&password=root");
        Statement stmt = conn.createStatement();

        String sql="SELECT * FROM Bambino WHERE cf IN (" +
                "SELECT cf FROM Aderisce WHERE codice_g = '" + gita.getCodice_g() + "')";
        ResultSet rs=stmt.executeQuery(sql);
        List<Bambino> bambinoList = new ArrayList<>();

        while (rs.next()) {
            String cf = rs.getString("cf");
            String nome = rs.getString("nome");
            String cognome = rs.getString("cognome");
            LocalDate data = LocalDate.parse(rs.getString("data"));
            String indirizzo = rs.getString("indirizzo");
            String contatto1 = rs.getString("contatto1");
            String contatto2 = rs.getString("contatto2");


            Bambino bambino_menu= new Bambino(cf,nome,cognome,data,indirizzo,contatto1,contatto2);

            bambinoList.add(bambino_menu);

        }
        rs.close();
        stmt.close();
        conn.close();
        return bambinoList;

    }

    public List<Integer> getAllNumGite()throws RemoteException,SQLException{
        Connection conn = DriverManager.getConnection("jdbc:mariadb://localhost:3306/progetto?user=root&password=root");
        Statement stmt = conn.createStatement();

        String sql = "SELECT * FROM Gita ";
        ResultSet rs = stmt.executeQuery(sql);
        List<Integer> numGiteList = new ArrayList<>();

        while (rs.next()) {
            int numGite = Integer.parseInt(rs.getString("codice_g"));

            numGiteList.add(numGite);

        }
        rs.close();
        stmt.close();
        conn.close();
        return numGiteList;


    }
    public Bambino getKidPresente(String cod_f)throws RemoteException,SQLException{
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
    public Gita getGita(int cod_g)throws RemoteException,SQLException{

        Connection conn = DriverManager.getConnection("jdbc:mariadb://localhost:3306/progetto?user=root&password=root");
        Statement stmt = conn.createStatement();
        String sql = "SELECT * FROM Gita WHERE codice_g='" + cod_g + "'";
        ResultSet rs = stmt.executeQuery(sql);
        rs.next();
        int codice_g = rs.getInt("codice_g");
        String destinazione = rs.getString("destinazione");
        int num_pullman=rs.getInt("num_pullman");
        int num_partecipanti=rs.getInt("num_partecipanti");
        LocalDate data_partenza= LocalDate.parse(rs.getString("data_partenza"));
        LocalDate data_ritorno=LocalDate.parse(rs.getString("data_ritorno"));
        Double costo=Double.parseDouble(rs.getString("costo"));
        String descrizione=rs.getString("descrizione");


        Gita gita = new Gita(codice_g,destinazione,num_pullman,num_partecipanti,data_partenza,data_ritorno,costo,descrizione);
        rs.close();
        stmt.close();
        conn.close();
        return gita;
    }


}
