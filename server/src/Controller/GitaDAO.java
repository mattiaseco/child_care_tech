package Controller;

import common.Classes.Gita;
import common.Interface.iGitaDAO;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class GitaDAO extends UnicastRemoteObject implements iGitaDAO {

    public GitaDAO() throws RemoteException {
    }
    @Override
    public void inserisciGita(String codice_g, String destinazione, int num_pullman, int num_partecipanti) throws SQLException {

        try {
            createGita(codice_g,destinazione,num_pullman,num_partecipanti);
        } catch (SQLException e) {

            System.out.println(e.getMessage());
            e.printStackTrace();
            return;
        }

    }

    private static void createGita(String codice_g, String destinazione, int num_pullman, int num_partecipanti) throws SQLException {

        Connection conn = DriverManager.getConnection("jdbc:mariadb://localhost:3306/progetto?user=root&password=root");
        Statement st = conn.createStatement();
        ResultSet rs;
        String sql = buildCreateGitaSQL(codice_g,destinazione,num_pullman,num_partecipanti);

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

    private static String buildCreateGitaSQL(String codice_g, String destinazione, int num_pullman, int num_partecipanti) {

        return "INSERT INTO Bambino(codice_g,destinazione,num_pullman,num_partecipanti)" +
                "VALUES('" + codice_g + "','" + destinazione + "','" + num_pullman + "','" + num_pullman +"')";

    }

    public void modificaGita(String codice_g, String destinazione, int num_pullman, int num_partecipanti) throws SQLException {
        try {
            updateGita(codice_g,destinazione,num_pullman,num_partecipanti);
        } catch (SQLException e) {
            System.err.println(e.getMessage());
            e.printStackTrace();
            return;
        }

    }

    private static void updateGita(String codice_g, String destinazione, int num_pullman, int num_partecipanti) throws SQLException {

        Connection conn = DriverManager.getConnection("jdbc:mariadb://localhost:3306/progetto?user=root&password=root");
        Statement st = conn.createStatement();
        ResultSet rs;
        String sql = buildUpdateGitaSQL(codice_g,destinazione,num_pullman,num_partecipanti);

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

    public static String buildUpdateGitaSQL(String codice_g, String destinazione, int num_pullman, int num_partecipanti) throws SQLException {
        return "UPDATE Gita SET codice_g = '" + codice_g + "' and destinazione = '" + destinazione + "'and num_pulman ='" + num_pullman + "'and num_partecipanti ='" + num_partecipanti + "'";
    }

    public List<Gita> getAllGite() throws RemoteException, SQLException {
        Connection conn = DriverManager.getConnection("jdbc:mariadb://localhost:3306/progetto?user=root&password=root");
        Statement stmt = conn.createStatement();

        String sql = "SELECT * FROM Gita";
        ResultSet rs = stmt.executeQuery(sql);
        List<Gita> gitaList = new ArrayList<>();

        while (rs.next()) {
            String codice_g = rs.getString("codice_g");
            String destinazione = rs.getString("destinazione");
            int num_pullman=rs.getInt("num_pullman");
            int num_partecipanti=rs.getInt("num_partecipanti");

            Gita gita = new Gita(codice_g,destinazione,num_pullman,num_partecipanti);

            gitaList.add(gita);
        }
        return gitaList;
    }

    public void cancellaGita(String codice_g)throws SQLException {
        try {
            deleteGita(codice_g);
        }catch (SQLException e){
            System.err.println(e.getMessage());
            e.printStackTrace();
            return;
        }
    }
    private static void deleteGita(String codice_g) throws SQLException {

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
    private static String buildDeleteGitaSQL(String codice_g){
        return "DELETE FROM Gita WHERE codice_g='"+codice_g+"'";

    }

}
