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

    public GitaDAO() throws RemoteException {
    }
    @Override
    public void inserisciGita(String destinazione, int num_pullman, int num_partecipanti, LocalDate data_partenza, LocalDate data_ritorno, String costo,String descrizione) throws SQLException {

        try {
            createGita(destinazione,num_pullman,num_partecipanti, data_partenza, data_ritorno,costo,descrizione);
        } catch (SQLException e) {

            System.out.println(e.getMessage());
            e.printStackTrace();
            return;
        }

    }

    private static void createGita(String destinazione, int num_pullman, int num_partecipanti,LocalDate data_partenza, LocalDate data_ritorno,String costo,String descrizione) throws SQLException {

        Connection conn = DriverManager.getConnection("jdbc:mariadb://localhost:3306/progetto?user=root&password=root");
        Statement st = conn.createStatement();
        ResultSet rs;
        String sql = buildCreateGitaSQL(destinazione,num_pullman,num_partecipanti, data_partenza, data_ritorno,costo,descrizione);

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

    private static String buildCreateGitaSQL(String destinazione, int num_pullman, int num_partecipanti,LocalDate data_partenza, LocalDate data_ritorno, String costo,String descrizione) {

        return "INSERT INTO Gita(destinazione,num_pullman,num_partecipanti,data_partenza,data_ritorno,costo,descrizione)" +
                " VALUES('"+ destinazione + "','" + num_pullman + "','" + num_pullman +"','"+data_partenza+"',"+data_ritorno+"','"+costo+"',"+descrizione+"')";

    }

    public void modificaGita(int codice_g, String destinazione, int num_pullman, int num_partecipanti,LocalDate data_partenza, LocalDate data_ritorno,String costo,String descrizione) throws SQLException {
        try {
            updateGita(codice_g,destinazione,num_pullman,num_partecipanti,data_partenza,data_ritorno, costo, descrizione);
        } catch (SQLException e) {
            System.err.println(e.getMessage());
            e.printStackTrace();
            return;
        }

    }

    private static void updateGita(int codice_g, String destinazione, int num_pullman, int num_partecipanti,LocalDate data_partenza, LocalDate data_ritorno, String costo, String descrizione) throws SQLException {

        Connection conn = DriverManager.getConnection("jdbc:mariadb://localhost:3306/progetto?user=root&password=root");
        Statement st = conn.createStatement();
        ResultSet rs;
        String sql = buildUpdateGitaSQL(codice_g,destinazione,num_pullman,num_partecipanti,data_partenza,data_ritorno,costo,descrizione);

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

    public static String buildUpdateGitaSQL(int codice_g, String destinazione, int num_pullman, int num_partecipanti,LocalDate data_partenza, LocalDate data_ritorno, String costo, String descrizione) throws SQLException {
        return "UPDATE Gita SET codice_g = '" + codice_g + "' , destinazione = '" + destinazione + "', num_pulman ='" + num_pullman + "', num_partecipanti ='" + num_partecipanti + "',data_partenza='"+data_partenza+"',data_ritorno='"+data_ritorno+"',costo='"+costo+"',descrizione='"+descrizione+"'WHERE codice_g='"+codice_g+"'";
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
            LocalDate data_partenza=LocalDate.parse("data_partenza");
            LocalDate data_ritorno=LocalDate.parse("data_ritorno");
            String costo=rs.getString("costo");
            String descrizione=rs.getString("descrizione");

            Gita gita = new Gita(codice_g,destinazione,num_pullman,num_partecipanti,data_partenza,data_ritorno,costo,descrizione);

            gitaList.add(gita);
        }
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


    public void inserisciBambinoGita(Gita gita, Bambino bambino)throws RemoteException,SQLException{


        try {
            createBambinoGita(gita.getCodice_g(),bambino.getCf());
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

}
