package Controller;


import common.Classes.Contiene;
import common.Classes.Ingredienti;
import common.Classes.Piatto;
import common.Interface.iPiattoDAO;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PiattoDAO extends UnicastRemoteObject implements iPiattoDAO {
    public PiattoDAO() throws RemoteException {}

    @Override
    public void inserisciPiatto(String nome_p, String tipo) throws SQLException {

        try {
            createPiatto (nome_p,tipo);
        } catch (SQLException e) {

            System.out.println(e.getMessage());
            e.printStackTrace();
            return;
        }

    }

    private static void createPiatto (String nome_p, String tipo) throws SQLException {

        Connection conn = DriverManager.getConnection("jdbc:mariadb://localhost:3306/progetto?user=root&password=root");
        Statement st = conn.createStatement();
        ResultSet rs;
        String sql = buildCreatePiattoSQL(nome_p,tipo);

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

    private static String buildCreatePiattoSQL(String nome_p, String tipo){

        return "INSERT INTO Piatto(nome_p,tipo)" +
                "VALUES('"+nome_p+"','"+tipo+"')";

    }

    public void modificaPiatto(String nome_p, String tipo) throws SQLException {
        try{
            updatePiatto(nome_p,tipo);
        }catch (SQLException e){
            System.err.println(e.getMessage());
            e.printStackTrace();
            return;
        }

    }

    private static void updatePiatto(String nome_p, String tipo) throws SQLException {

        Connection conn = DriverManager.getConnection("jdbc:mariadb://localhost:3306/progetto?user=root&password=root");
        Statement st = conn.createStatement();
        ResultSet rs;
        String sql = buildUpdatePiattoSQL(nome_p,tipo);

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

    public static String buildUpdatePiattoSQL( String nome_p, String tipo) throws SQLException {
        return "UPDATE Piatto SET nome_p = '"+nome_p+"' , tipo = '"+tipo+"'";
    }

    public List<Piatto> getAllPiatti() throws RemoteException,SQLException {
        Connection conn = DriverManager.getConnection("jdbc:mariadb://localhost:3306/progetto?user=root&password=root");
        Statement stmt = conn.createStatement();

        String sql = "SELECT * FROM Piatto";
        ResultSet rs=stmt.executeQuery(sql);
        List<Piatto> piattoList = new ArrayList<>();

        while (rs.next()) {
            String nome_p = rs.getString("nome_p");
            String tipo=rs.getString("tipo");
            Piatto piatto= new Piatto(nome_p,tipo);

            piattoList.add(piatto);
        }
        rs.close();
        stmt.close();
        conn.close();
        return piattoList;

    }
    public void cancellaPiatti(String nome_p)throws SQLException {
        try {
            deletePiatto(nome_p);
        }catch (SQLException e){
            System.err.println(e.getMessage());
            e.printStackTrace();
            return;
        }
    }
    private static void deletePiatto(String nome_p) throws SQLException {

        Connection conn = DriverManager.getConnection("jdbc:mariadb://localhost:3306/progetto?user=root&password=root");
        Statement st = conn.createStatement();
        ResultSet rs;
        String sql = buildDeletePiattoSQL(nome_p);

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
    private static String buildDeletePiattoSQL(String nome_p){
        return "DELETE FROM Piatto WHERE nome_p='"+nome_p+"'";

    }

    public List<Piatto>getAllPrimi()throws  RemoteException,SQLException{
        Connection conn = DriverManager.getConnection("jdbc:mariadb://localhost:3306/progetto?user=root&password=root");
        Statement stmt = conn.createStatement();

        String sql = "SELECT * FROM Piatto WHERE tipo='"+"Primo"+"'";
        ResultSet rs=stmt.executeQuery(sql);
        List<Piatto> piattoList = new ArrayList<>();

        while (rs.next()) {
            String nome_p = rs.getString("nome_p");
            String tipo=rs.getString("tipo");
            Piatto piatto= new Piatto(nome_p,tipo);

            piattoList.add(piatto);
        }
        rs.close();
        stmt.close();
        conn.close();
        return piattoList;

    }
    public List<Piatto>getAllSecondi()throws  RemoteException,SQLException{
        Connection conn = DriverManager.getConnection("jdbc:mariadb://localhost:3306/progetto?user=root&password=root");
        Statement stmt = conn.createStatement();

        String sql = "SELECT * FROM Piatto WHERE tipo='"+"Secondo"+"'";
        ResultSet rs=stmt.executeQuery(sql);
        List<Piatto> piattoList = new ArrayList<>();

        while (rs.next()) {
            String nome_p = rs.getString("nome_p");
            String tipo=rs.getString("tipo");
            Piatto piatto= new Piatto(nome_p,tipo);

            piattoList.add(piatto);
        }
        rs.close();
        stmt.close();
        conn.close();
        return piattoList;
    }
    public List<Piatto>getAllContorni()throws  RemoteException,SQLException{

        Connection conn = DriverManager.getConnection("jdbc:mariadb://localhost:3306/progetto?user=root&password=root");
        Statement stmt = conn.createStatement();

        String sql = "SELECT * FROM Piatto WHERE tipo='"+"Contorno"+"'";
        ResultSet rs=stmt.executeQuery(sql);
        List<Piatto> piattoList = new ArrayList<>();

        while (rs.next()) {
            String nome_p = rs.getString("nome_p");
            String tipo=rs.getString("tipo");

            Piatto piatto= new Piatto(nome_p,tipo);

            piattoList.add(piatto);
        }
        rs.close();
        stmt.close();
        conn.close();
        return piattoList;
    }


    public void inserisciIngredientePiatto(Piatto piatto, Ingredienti ingrediente)throws RemoteException,SQLException{
        try {
            createIngredientePiatto(piatto.getNome_p(), ingrediente.getNome_i());
        } catch (SQLException e) {

            System.out.println(e.getMessage());
            e.printStackTrace();
            return;
        }
    }
    private static void createIngredientePiatto(String nome_p,String ingrediente)throws SQLException{
        Connection conn = DriverManager.getConnection("jdbc:mariadb://localhost:3306/progetto?user=root&password=root");
        Statement st = conn.createStatement();
        ResultSet rs;
        String sql = buildCreateIngredientePiattoSQL(nome_p, ingrediente);

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
    private static String buildCreateIngredientePiattoSQL(String nome_p, String ingrediente)throws SQLException{
        return "INSERT INTO Contiene(nome_p,nome_i) VALUES('" + nome_p + "','" + ingrediente+ "')";
    }


    public void cancellaIngredientePiatto(Piatto piatto,Ingredienti ingredienti)throws RemoteException,SQLException{
        try {
        deleteIngrediente(piatto.getNome_p(),ingredienti.getNome_i());
    }catch (SQLException e){
        System.err.println(e.getMessage());
        e.printStackTrace();
        return;
    }
    }
    private static void deleteIngrediente(String nome_p,String nome_i )throws SQLException{
        Connection conn = DriverManager.getConnection("jdbc:mariadb://localhost:3306/progetto?user=root&password=root");
        Statement st = conn.createStatement();
        ResultSet rs;
        String sql = buildDeleteIngredienteSQL(nome_p,nome_i);

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
    private static String buildDeleteIngredienteSQL(String nome_p,String nome_i){
        return "DELETE FROM Contiene WHERE nome_p='"+nome_p+"' AND nome_i='"+nome_i+"'";

    }


    public List<Ingredienti>getAllIngredientiPiatto(Piatto piatto)throws RemoteException,SQLException{

        Connection conn = DriverManager.getConnection("jdbc:mariadb://localhost:3306/progetto?user=root&password=root");
        Statement stmt = conn.createStatement();

        String sql="SELECT * FROM Ingrediente WHERE nome_i IN (" +
                "SELECT nome_i FROM Contiene WHERE nome_p = '" + piatto.getNome_p() + "')";
        ResultSet rs=stmt.executeQuery(sql);
        List<Ingredienti> ingredientiList = new ArrayList<>();

        while (rs.next()) {
            String nome_i = rs.getString("nome_i");
            int quantita = rs.getInt("quantita");
            Ingredienti ing_piatto = new Ingredienti(nome_i,quantita);

            ingredientiList.add(ing_piatto);

        }
        rs.close();
        stmt.close();
        conn.close();
        return ingredientiList;
    }
    public List<String>getAllNomiPiatti()throws RemoteException,SQLException{
        Connection conn = DriverManager.getConnection("jdbc:mariadb://localhost:3306/progetto?user=root&password=root");
        Statement stmt = conn.createStatement();

        String sql="SELECT nome_p FROM Piatto";
        ResultSet rs=stmt.executeQuery(sql);
        List<String> nomi_piattoList = new ArrayList<>();

        while (rs.next()) {
            String nome_p = rs.getString("nome_p");
            String nome_piatto = new String(nome_p);

            nomi_piattoList.add(nome_piatto);

        }
        rs.close();
        stmt.close();
        conn.close();
        return nomi_piattoList;


    }
    public Piatto getPiatto(String nome_piatto)throws RemoteException,SQLException {
        Connection conn = DriverManager.getConnection("jdbc:mariadb://localhost:3306/progetto?user=root&password=root");
        Statement stmt = conn.createStatement();
        String sql = "SELECT * FROM Piatto WHERE nome_p='" + nome_piatto + "'";
        ResultSet rs = stmt.executeQuery(sql);
        rs.next();
        String nome_p = rs.getString("nome_p");
        String tipo = rs.getString("tipo");

        Piatto piatto1 = new Piatto(nome_p, tipo);

        stmt.close();
        rs.close();
        conn.close();
        return piatto1;
    }


}
