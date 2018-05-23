package Controller;

import common.Classes.Menu;
import common.Interface.iMunuDAO;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class MenuDAO extends UnicastRemoteObject implements iMunuDAO  {
    public MenuDAO() throws RemoteException {
    }
    @Override
    public void inserisciMenu(String numero, LocalDate data) throws SQLException {

        try {
            createMenu(numero,data);
        } catch (SQLException e) {

            System.out.println(e.getMessage());
            e.printStackTrace();
            return;
        }

    }

    private static void createMenu(String numero, LocalDate data) throws SQLException {

        Connection conn = DriverManager.getConnection("jdbc:mariadb://localhost:3306/progetto?user=root&password=root");
        Statement st = conn.createStatement();
        ResultSet rs;
        String sql = buildCreateMenuSQL(numero, data);

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

    private static String buildCreateMenuSQL(String numero, LocalDate data) {

        return "INSERT INTO Menu(numero,data)" +
                "VALUES('" + numero + "','" + data + "')";

    }

    public void modificaMenu(String numero, LocalDate data) throws SQLException {
        try {
            updateMenu(numero, data);
        } catch (SQLException e) {
            System.err.println(e.getMessage());
            e.printStackTrace();
            return;
        }

    }

    private static void updateMenu(String numero, LocalDate data) throws SQLException {

        Connection conn = DriverManager.getConnection("jdbc:mariadb://localhost:3306/progetto?user=root&password=root");
        Statement st = conn.createStatement();
        ResultSet rs;
        String sql = buildUpdateMenuSQL(numero, data);

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

    public static String buildUpdateMenuSQL(String numero, LocalDate data)  throws SQLException {
        return "UPDATE Menu SET numero = '" + numero + "' and data = '" + data + "'";
    }

    public List<common.Classes.Menu> getAllMenu()  throws RemoteException, SQLException {
        Connection conn = DriverManager.getConnection("jdbc:mariadb://localhost:3306/progetto?user=root&password=root");
        Statement stmt = conn.createStatement();

        String sql = "SELECT * FROM Menu";
        ResultSet rs = stmt.executeQuery(sql);
        List<common.Classes.Menu> menuList = new ArrayList<>();

        while (rs.next()) {

            String numero=rs.getString("numero");
            LocalDate data = LocalDate.parse(rs.getString("data"));

            common.Classes.Menu menu = new Menu(numero,data);

            menuList.add(menu);
        }
        return menuList;
    }

    public void cancellaMenu(String numero)throws SQLException {
        try {
            deleteMenu(numero);
        }catch (SQLException e){
            System.err.println(e.getMessage());
            e.printStackTrace();
            return;
        }
    }
    private static void deleteMenu(String numero) throws SQLException {

        Connection conn = DriverManager.getConnection("jdbc:mariadb://localhost:3306/progetto?user=root&password=root");
        Statement st = conn.createStatement();
        ResultSet rs;
        String sql = buildDeleteMenuSQL(numero);

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
    private static String buildDeleteMenuSQL( String numero){
        return "DELETE FROM Menu WHERE numero='"+numero+"'";

    }

}
