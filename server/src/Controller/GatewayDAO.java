package Controller;

import common.Classes.Bambino;
import common.Classes.Gateway;
import common.Classes.Ingredienti;
import common.Classes.Intolleranze;
import common.Interface.iGatewayDAO;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class GatewayDAO  extends UnicastRemoteObject implements iGatewayDAO {
    public GatewayDAO() throws RemoteException {}

    @Override
    public void inserisciGatway(int cod_porta,LocalDate data_gate)throws SQLException {

        try {
            createGateway (cod_porta,data_gate);
        } catch (SQLException e) {

            System.out.println(e.getMessage());
            e.printStackTrace();
            return;
        }

    }
    private static void createGateway (int cod_porta,LocalDate data_gate) throws SQLException {

        Connection conn = DriverManager.getConnection("jdbc:mariadb://localhost:3306/progetto?user=root&password=root");
        Statement st = conn.createStatement();
        ResultSet rs;
        String sql = buildCreateGatewaySQL(cod_porta, data_gate);

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
    private static String buildCreateGatewaySQL(int cod_porta,LocalDate data_gate){

        return "INSERT INTO Gateway(cod_porta,date_gate)" +
                "VALUES('"+cod_porta+"','"+data_gate+"')";

    }

    public void modificaGatway(int cod_porta,LocalDate data_gate) throws SQLException {
        try{
            updateGateway(cod_porta,data_gate);
        }catch (SQLException e){
            System.err.println(e.getMessage());
            e.printStackTrace();
            return;
        }

    }
    private static void updateGateway(int cod_porta,LocalDate data_gate) throws SQLException {

        Connection conn = DriverManager.getConnection("jdbc:mariadb://localhost:3306/progetto?user=root&password=root");
        Statement st = conn.createStatement();
        ResultSet rs;
        String sql = buildUpdateGatewaySQL(cod_porta,data_gate);

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
    public static String buildUpdateGatewaySQL(int cod_porta,LocalDate data_gate) throws SQLException {
        return "UPDATE Gateway SET cod_porta = '"+cod_porta+"' and data_gate = '"+data_gate+"'";
    }

    public List<Gateway> getAllGatway() throws RemoteException,SQLException {
        Connection conn = DriverManager.getConnection("jdbc:mariadb://localhost:3306/progetto?user=root&password=root");
        Statement stmt = conn.createStatement();

        String sql = "SELECT * FROM Gateway";
        ResultSet rs=stmt.executeQuery(sql);
        List<Gateway> gatewayList = new ArrayList<>();

        while (rs.next()) {
            int cod_porta = rs.getInt("cod_porta");
            LocalDate data_gate=LocalDate.parse(rs.getString("data_gate"));
            Gateway gateway= new Gateway(cod_porta,data_gate);

            gatewayList.add(gateway);
        }
        return gatewayList;

    }

    public void cancellaGatway(int cod_porta)throws SQLException {
        try {
            deleteGateway(cod_porta);
        }catch (SQLException e){
            System.err.println(e.getMessage());
            e.printStackTrace();
            return;
        }
    }
    private static void deleteGateway(int cod_porta) throws SQLException {

        Connection conn = DriverManager.getConnection("jdbc:mariadb://localhost:3306/progetto?user=root&password=root");
        Statement st = conn.createStatement();
        ResultSet rs;
        String sql = buildDeleteGatewaySQL(cod_porta);

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
    private static String buildDeleteGatewaySQL(int cod_porta){
        return "DELETE FROM Gateway WHERE cod_porta='"+cod_porta+"'";

    }

    public List<Bambino>getAllBambiniPresenti(Gateway gateway)throws RemoteException,SQLException{

        Connection conn = DriverManager.getConnection("jdbc:mariadb://localhost:3306/progetto?user=root&password=root");
        Statement stmt = conn.createStatement();

        String sql="SELECT * FROM Bambino WHERE cf IN (" +
                "SELECT cf FROM Gateway WHERE cod_porta = '" + gateway.getCod_porta() + "'AND data_gate='"+gateway.getData_gate()+"')";
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

            Bambino bambino_presente= new Bambino(cf,nome,cognome,data,indirizzo,contatto1,contatto2);

            bambinoList.add(bambino_presente);
        }
        return bambinoList;

    }

    public void inserisciBambinoGateway(Bambino bambino,Gateway gateway)throws RemoteException,SQLException{

        try {
            createGatewayBambino_presente (gateway.getData_gate(),gateway.getCod_porta(),bambino.getCf());
        } catch (SQLException e) {

            System.out.println(e.getMessage());
            e.printStackTrace();
            return;
        }

    }
    private static void createGatewayBambino_presente (LocalDate data_gate, int cod_porta,String cf) throws SQLException {

        Connection conn = DriverManager.getConnection("jdbc:mariadb://localhost:3306/progetto?user=root&password=root");
        Statement st = conn.createStatement();
        ResultSet rs;
        String sql = buildCreateGatewayBambino_presenteSQL(data_gate,cod_porta,cf);

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
    private static String buildCreateGatewayBambino_presenteSQL(LocalDate data_gate, int cod_porta,String cf){

        return "INSERT INTO Attraversa(cod_porta ,data_gate, cf)" +
                "VALUES('"+cod_porta+"','"+data_gate+"','"+cf+"')";

    }

    public List<Intolleranze>getAllBambiniPresentiIntolleranze(Gateway gateway)throws RemoteException,SQLException{

        Connection conn = DriverManager.getConnection("jdbc:mariadb://localhost:3306/progetto?user=root&password=root");
        Statement stmt = conn.createStatement();

        String sql="SELECT * FROM Intolleranza WHERE cf IN (" +
                "SELECT cf FROM Attraversa WHERE cod_porta = '" + gateway.getCod_porta() + "'AND data_gate='"+gateway.getData_gate()+"')";
        ResultSet rs=stmt.executeQuery(sql);
        List<Intolleranze> bambinoList = new ArrayList<>();

        while (rs.next()) {
            String cf = rs.getString("cf");
            String ingrediente = rs.getString("ingrediente");

            Intolleranze bambino_allergico= new Intolleranze(getBambino(cf),getIngrediente(ingrediente));

            bambinoList.add(bambino_allergico);
        }
        return bambinoList;
    }
    private Bambino getBambino(String cod_f)throws RemoteException,SQLException{
        Connection conn = DriverManager.getConnection("jdbc:mariadb://localhost:3306/progetto?user=root&password=root");
        Statement stmt = conn.createStatement();
        String sql="SELECT * FROM Bambino WHERE cf='"+cod_f+"'";
        ResultSet rs=stmt.executeQuery(sql);
        String cf=rs.getString("cf");
        String nome = rs.getString("nome");
        String cognome=rs.getString("cognome");
        LocalDate data = LocalDate.parse(rs.getString("data"));
        String indirizzo=rs.getString("indirizzo");
        String contatto1=rs.getString("contatto1");
        String contatto2=rs.getString("contatto2");

        Bambino kid= new Bambino(cf,nome,cognome,data,indirizzo,contatto1,contatto2);
        return kid;
    }
    private Ingredienti getIngrediente(String ingrediente)throws RemoteException,SQLException{

        Connection conn = DriverManager.getConnection("jdbc:mariadb://localhost:3306/progetto?user=root&password=root");
        Statement stmt = conn.createStatement();
        String sql="SELECT * FROM Ingrediente WHERE nome_i='"+ingrediente+"'";
        ResultSet rs=stmt.executeQuery(sql);
        rs.next();
        String nome_i = rs.getString("nome_i");
        int quantita=rs.getInt("quantita");

        Ingredienti ingredienti= new Ingredienti(nome_i,quantita);
        return ingredienti;
    }

}
