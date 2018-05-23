package Controller;

import common.Classes.Menu;
import common.Classes.Piatto;
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
    public void inserisciMenu(String numero, LocalDate data, Piatto piatto1, Piatto piatto2, Piatto piatto3) throws SQLException {

        try {
            createMenu(numero,data,piatto1.getNome_p(),piatto2.getNome_p(),piatto3.getNome_p());
        } catch (SQLException e) {

            System.out.println(e.getMessage());
            e.printStackTrace();
            return;
        }

    }

    private static void createMenu(String numero, LocalDate data,String piatto1,String piatto2,String piatto3) throws SQLException {

        Connection conn = DriverManager.getConnection("jdbc:mariadb://localhost:3306/progetto?user=root&password=root");
        Statement st = conn.createStatement();
        ResultSet rs;
        String sql = buildCreateMenuSQL(numero, data,piatto1,piatto2,piatto3);

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

    private static String buildCreateMenuSQL(String numero, LocalDate data,String piatto1,String piatto2,String piatto3) {

        return "INSERT INTO Menu(numero,data,piatto1,piatto2,piatto3)" +
                "VALUES('" + numero + "','" + data + "','"+piatto1+"','"+piatto2+"','"+piatto3+"')";

    }

    public void modificaMenu(String numero, LocalDate data,Piatto piatto1, Piatto piatto2, Piatto piatto3) throws SQLException {
        try {
            updateMenu(numero, data,piatto1.getNome_p(),piatto2.getNome_p(),piatto3.getNome_p());
        } catch (SQLException e) {
            System.err.println(e.getMessage());
            e.printStackTrace();
            return;
        }

    }

    private static void updateMenu(String numero, LocalDate data,String piatto1,String piatto2,String piatto3) throws SQLException {

        Connection conn = DriverManager.getConnection("jdbc:mariadb://localhost:3306/progetto?user=root&password=root");
        Statement st = conn.createStatement();
        ResultSet rs;
        String sql = buildUpdateMenuSQL(numero, data,piatto1,piatto2,piatto3);

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

    public static String buildUpdateMenuSQL(String numero, LocalDate data,String piatto1,String piatto2,String piatto3)  throws SQLException {
        return "UPDATE Menu SET numero = '" + numero + "' , data = '" + data + "',piatto1='"+piatto1+"',piatto2='"+piatto2+"',piatto3='"+piatto3+"'";
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
            String piatto1=rs.getString("piatto1");
            String piatto2=rs.getString("piatto2");
            String piatto3=rs.getString("piatto3");

            common.Classes.Menu menu = new Menu(numero,data,getPiatto(piatto1),getPiatto(piatto2),getPiatto(piatto3));

            menuList.add(menu);
        }
        return menuList;
    }
    private Piatto getPiatto(String piatto)throws SQLException{
        Connection conn = DriverManager.getConnection("jdbc:mariadb://localhost:3306/progetto?user=root&password=root");
        Statement stmt = conn.createStatement();
        String sql="SELECT * FROM Piatto WHERE nome_p='"+piatto+"'";
        ResultSet rs=stmt.executeQuery(sql);
        String nome_p = rs.getString("nome_p");
        String tipo=rs.getString("tipo");
        int quantità=rs.getInt("quantita");

        Piatto piatto1= new Piatto(nome_p,tipo,quantità);
         return piatto1;

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
