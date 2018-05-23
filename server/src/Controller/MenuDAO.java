package Controller;

import common.Classes.Bambino;
import common.Classes.Mangia;
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
    public void inserisciMenu(int numero, LocalDate data, Piatto piatto1, Piatto piatto2, Piatto piatto3) throws SQLException {

        try {
            createMenu(numero,data,piatto1.getNome_p(),piatto2.getNome_p(),piatto3.getNome_p());
        } catch (SQLException e) {

            System.out.println(e.getMessage());
            e.printStackTrace();
            return;
        }

    }

    private static void createMenu(int numero, LocalDate data,String piatto1,String piatto2,String piatto3) throws SQLException {

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

    private static String buildCreateMenuSQL(int numero, LocalDate data,String piatto1,String piatto2,String piatto3) {

        return "INSERT INTO Menu(numero,data,piatto1,piatto2,piatto3)" +
                "VALUES('" + numero + "','" + data + "','"+piatto1+"','"+piatto2+"','"+piatto3+"')";

    }

    public void modificaMenu(int numero, LocalDate data,Piatto piatto1, Piatto piatto2, Piatto piatto3) throws SQLException {
        try {
            updateMenu(numero, data,piatto1.getNome_p(),piatto2.getNome_p(),piatto3.getNome_p());
        } catch (SQLException e) {
            System.err.println(e.getMessage());
            e.printStackTrace();
            return;
        }

    }

    private static void updateMenu(int numero, LocalDate data,String piatto1,String piatto2,String piatto3) throws SQLException {

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

    public static String buildUpdateMenuSQL(int numero, LocalDate data,String piatto1,String piatto2,String piatto3)  throws SQLException {
        return "UPDATE Menu SET numero = '" + numero + "' , data = '" + data + "',piatto1='"+piatto1+"',piatto2='"+piatto2+"',piatto3='"+piatto3+"'";
    }

    public List<common.Classes.Menu> getAllMenu()  throws RemoteException, SQLException {
        Connection conn = DriverManager.getConnection("jdbc:mariadb://localhost:3306/progetto?user=root&password=root");
        Statement stmt = conn.createStatement();

        String sql = "SELECT * FROM Menu";
        ResultSet rs = stmt.executeQuery(sql);
        List<common.Classes.Menu> menuList = new ArrayList<>();

        while (rs.next()) {

            int numero=rs.getInt("numero");
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

    public void cancellaMenu(int numero)throws SQLException {
        try {
            deleteMenu(numero);
        }catch (SQLException e){
            System.err.println(e.getMessage());
            e.printStackTrace();
            return;
        }
    }
    private static void deleteMenu(int numero) throws SQLException {

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
    private static String buildDeleteMenuSQL( int numero){
        return "DELETE FROM Menu WHERE numero='"+numero+"'";

    }

    @Override
    public List<Mangia> GetAllBambiniMenu(Menu menu) throws SQLException{
        Connection conn = DriverManager.getConnection("jdbc:mariadb://localhost:3306/progetto?user=root&password=root");
        Statement stmt = conn.createStatement();

        String sql = "SELECT * FROM Mangia WHERE numero='"+menu.getNumero()+"'";
        ResultSet rs = stmt.executeQuery(sql);
        List<Mangia> mangiaList = new ArrayList<>();

        while (rs.next()) {

            int numero=rs.getInt("numero");
            String cf=rs.getString("cf");


            Mangia mangia = new Mangia(getBambino(cf),getMenu(numero));

            mangiaList.add(mangia);
        }
        return mangiaList;
    }
    private Bambino getBambino(String cod_f)throws SQLException{
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
    private Menu getMenu(int numero_menu)throws SQLException{
        Connection conn = DriverManager.getConnection("jdbc:mariadb://localhost:3306/progetto?user=root&password=root");
        Statement stmt = conn.createStatement();
        String sql="SELECT * FROM Menu WHERE numero='"+numero_menu+"'";
        ResultSet rs=stmt.executeQuery(sql);


        int numero=rs.getInt("numero");
        LocalDate data = LocalDate.parse(rs.getString("data"));
        String piatto1=rs.getString("piatto1");
        String piatto2=rs.getString("piatto2");
        String piatto3=rs.getString("piatto3");

        Menu menu1 = new Menu(numero,data,getPiatto(piatto1),getPiatto(piatto2),getPiatto(piatto3));
        return menu1;

    }
}
