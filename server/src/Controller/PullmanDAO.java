package Controller;

import common.Classes.Bambino;
import common.Classes.Gita;
import common.Classes.Pullman;
import common.Interface.iPullmanDAO;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class PullmanDAO extends UnicastRemoteObject implements iPullmanDAO {

    public PullmanDAO() throws RemoteException {}

    @Override
    public void inserisciPullman(String targa,int capienza) throws SQLException {

        try {
            createPullman (targa,capienza);
        } catch (SQLException e) {

            System.out.println(e.getMessage());
            e.printStackTrace();
            return;
        }

    }
    private static void createPullman (String targa,int capienza) throws SQLException {

        Connection conn = DriverManager.getConnection("jdbc:mariadb://localhost:3306/progetto?user=root&password=root");
        Statement st = conn.createStatement();
        ResultSet rs;
        String sql = buildCreatePullmanSQL(targa,capienza);

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
    private static String buildCreatePullmanSQL(String targa,int capienza){

        return "INSERT INTO Pullman(targa,capienza)" +
                "VALUES('"+targa+"','"+capienza+"')";

    }

    public void modificaPullman(String targa,int capienza) throws SQLException {
        try{
            updatePullman(targa,capienza);
        }catch (SQLException e){
            System.err.println(e.getMessage());
            e.printStackTrace();
            return;
        }

    }
    private static void updatePullman(String targa,int capienza) throws SQLException {

        Connection conn = DriverManager.getConnection("jdbc:mariadb://localhost:3306/progetto?user=root&password=root");
        Statement st = conn.createStatement();
        ResultSet rs;
        String sql = buildUpdatePullmanSQL(targa,capienza);

        try {
            rs = st.executeQuery(sql);
            conn.close();
            rs.next();

        } catch(SQLException ex) {
            System.err.println("sql exception");
            ex.printStackTrace();
            conn.close();
            return ;
        }

    }
    public static String buildUpdatePullmanSQL( String targa,int capienza) throws SQLException {
        return "UPDATE Pullman SET targa = '"+targa+"' and capienza = '"+capienza+"'";
    }

    public List<Pullman> getAllPullman() throws RemoteException,SQLException {
        Connection conn = DriverManager.getConnection("jdbc:mariadb://localhost:3306/progetto?user=root&password=root");
        Statement stmt = conn.createStatement();

        String sql = "SELECT * FROM Pullman";
        ResultSet rs=stmt.executeQuery(sql);
        List<Pullman> pullmanList = new ArrayList<>();

        while (rs.next()) {
            String targa = rs.getString("targa");
            int capienza=rs.getInt("capienza");
            Pullman pullman= new Pullman(targa,capienza);

            pullmanList.add(pullman);
        }
        rs.close();
        stmt.close();
        conn.close();
        return pullmanList;

    }

    public void cancellaPullman(String targa)throws SQLException {
        try {
            deletePullman(targa);
        }catch (SQLException e){
            System.err.println(e.getMessage());
            e.printStackTrace();
            return;
        }
    }
    private static void deletePullman(String targa) throws SQLException {

        Connection conn = DriverManager.getConnection("jdbc:mariadb://localhost:3306/progetto?user=root&password=root");
        Statement st = conn.createStatement();
        ResultSet rs;
        String sql = buildDeletePullmanSQL(targa);

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
    private static String buildDeletePullmanSQL(String targa){
        return "DELETE FROM Pullman WHERE targa='"+targa+"'";

    }

    public List<Bambino>getAllBambiniPullman(Pullman pullman)throws RemoteException,SQLException{
        Connection conn = DriverManager.getConnection("jdbc:mariadb://localhost:3306/progetto?user=root&password=root");
        Statement stmt = conn.createStatement();

        String sql="SELECT * FROM Bambino WHERE cf IN (" +
                "SELECT cf FROM Sale WHERE targa = '" + pullman.getTarga() + "')";
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

    public void inserisciBambinoPulman(Bambino bambino,Pullman pullman)throws RemoteException,SQLException{


        try {
            createBambinoPullman(pullman.getTarga(),bambino.getCf());
        } catch (SQLException e) {

            System.out.println(e.getMessage());
            e.printStackTrace();
            return;
        }

    }
    private static void createBambinoPullman(String targa,String cf) throws SQLException {

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

        return "INSERT INTO Sale(cf, targa)" +
                " VALUES('"+ cf + "','" + targa + "')";

    }
    public List<String>getAllTarghe()throws RemoteException,SQLException{

        Connection conn = DriverManager.getConnection("jdbc:mariadb://localhost:3306/progetto?user=root&password=root");
        Statement stmt = conn.createStatement();

        String sql = "SELECT * FROM Pullman ";
        ResultSet rs = stmt.executeQuery(sql);
        List<String> pullmanTarghe = new ArrayList<>();

        while (rs.next()) {
            String targa = rs.getString("targa");

            pullmanTarghe.add(targa);

        }
        rs.close();
        stmt.close();
        conn.close();
        return pullmanTarghe;

    }
}
