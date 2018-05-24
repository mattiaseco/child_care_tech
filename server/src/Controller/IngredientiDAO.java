package Controller;


import common.Classes.Ingredienti;
import common.Interface.iIngredientiDAO;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class IngredientiDAO extends UnicastRemoteObject implements iIngredientiDAO {

    public IngredientiDAO() throws RemoteException {
    }
    @Override
    public void inserisciIngrediente(String nome_i)  throws SQLException {

        try {
            createIngrediente(nome_i);
        } catch (SQLException e) {

            System.out.println(e.getMessage());
            e.printStackTrace();
            return;
        }

    }

    private static void createIngrediente(String nome_i) throws SQLException {

        Connection conn = DriverManager.getConnection("jdbc:mariadb://localhost:3306/progetto?user=root&password=root");
        Statement st = conn.createStatement();
        ResultSet rs;
        String sql = buildCreateIngredienteSQL(nome_i);

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

    private static String buildCreateIngredienteSQL(String nome_i) {

        return "INSERT INTO Ingrediente(nome_i)" +
                "VALUES('" + nome_i + "')";

    }

    public void modificaIngrediente(String nome_i) throws SQLException {
        try {
            updateIngrediente(nome_i);
        } catch (SQLException e) {
            System.err.println(e.getMessage());
            e.printStackTrace();
            return;
        }

    }

    private static void updateIngrediente(String nome_i) throws SQLException {

        Connection conn = DriverManager.getConnection("jdbc:mariadb://localhost:3306/progetto?user=root&password=root");
        Statement st = conn.createStatement();
        ResultSet rs;
        String sql = buildUpdateIngredienteSQL(nome_i);

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

    public static String buildUpdateIngredienteSQL(String nome_i) throws SQLException {
        return "UPDATE Ingrediente SET nome_i = '" + nome_i + "'";
    }

    public List<Ingredienti> getAllIngredienti() throws RemoteException, SQLException {
        Connection conn = DriverManager.getConnection("jdbc:mariadb://localhost:3306/progetto?user=root&password=root");
        Statement stmt = conn.createStatement();

        String sql = "SELECT * FROM Ingrediente";
        ResultSet rs = stmt.executeQuery(sql);
        List<Ingredienti> ingredientiList = new ArrayList<>();

        while (rs.next()) {
            String nome_i = rs.getString("nome_i");
            int quantità=rs.getInt("quantita");


            Ingredienti ingrediente = new Ingredienti(nome_i,quantità);

            ingredientiList.add(ingrediente);
        }
        return ingredientiList;
    }

    public void cancellaIngredienti(String nome_i)throws SQLException {
        try {
            deleteIngrediente(nome_i);
        }catch (SQLException e){
            System.err.println(e.getMessage());
            e.printStackTrace();
            return;
        }
    }
    private static void deleteIngrediente(String nome_I) throws SQLException {

        Connection conn = DriverManager.getConnection("jdbc:mariadb://localhost:3306/progetto?user=root&password=root");
        Statement st = conn.createStatement();
        ResultSet rs;
        String sql = buildDeleteIngredienteSQL(nome_I);

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
    private static String buildDeleteIngredienteSQL(String nome_i){
        return "DELETE FROM Ingrediente WHERE nome_i='"+nome_i+"'";

    }


}
