package Controller;

import common.Classes.Bambino;
import common.Classes.Mangia;
import common.Classes.Menu;
import common.Classes.Piatto;
import common.Interface.iMenuDAO;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class MenuDAO extends UnicastRemoteObject implements iMenuDAO {
    public MenuDAO() throws RemoteException {
    }
    @Override
    public void inserisciPrimo(Piatto piatto1) throws SQLException {

        try {
            createPrimo(piatto1);
        } catch (SQLException e) {

            System.out.println(e.getMessage());
            e.printStackTrace();
            return;
        }

    }

    private static void createPrimo(Piatto piatto1) throws SQLException {

        Connection conn = DriverManager.getConnection("jdbc:mariadb://localhost:3306/progetto?user=root&password=root");
        Statement st = conn.createStatement();
        ResultSet rs;
        String sql = buildCreatePrimoSQL(piatto1);

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

    private static String buildCreatePrimoSQL(Piatto piatto1) {

        return "INSERT INTO Menu(piatto1)" +
                "VALUES('"+piatto1.getNome_p()+"')";

    }
    @Override
    public void inserisciSecondo(Piatto piatto2) throws SQLException {

        try {
            createSecondo(piatto2);
        } catch (SQLException e) {

            System.out.println(e.getMessage());
            e.printStackTrace();
            return;
        }

    }

    private static void createSecondo(Piatto piatto2) throws SQLException {

        Connection conn = DriverManager.getConnection("jdbc:mariadb://localhost:3306/progetto?user=root&password=root");
        Statement st = conn.createStatement();
        ResultSet rs;
        String sql = buildCreateSecondoSQL(piatto2);

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

    private static String buildCreateSecondoSQL(Piatto piatto2) {

        return "UPDATE Menu SET piatto2 = '"+piatto2.getNome_p()+"'";

    }
    @Override
    public void inserisciContorno(Piatto piatto3) throws SQLException {

        try {
            createContorno(piatto3);
        } catch (SQLException e) {

            System.out.println(e.getMessage());
            e.printStackTrace();
            return;
        }

    }

    private static void createContorno(Piatto piatto3) throws SQLException {

        Connection conn = DriverManager.getConnection("jdbc:mariadb://localhost:3306/progetto?user=root&password=root");
        Statement st = conn.createStatement();
        ResultSet rs;
        String sql = buildCreateContornoSQL(piatto3);

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

    private static String buildCreateContornoSQL(Piatto piatto3) {

        return "UPDATE Menu SET piatto3 = '"+piatto3.getNome_p()+"'";

    }

    public void modificaMenu(int numero, Piatto piatto1, Piatto piatto2, Piatto piatto3) throws SQLException {
        try {
            updateMenu(numero, piatto1.getNome_p(),piatto2.getNome_p(),piatto3.getNome_p());
        } catch (SQLException e) {
            System.err.println(e.getMessage());
            e.printStackTrace();
            return;
        }

    }

    private static void updateMenu(int numero, String piatto1,String piatto2,String piatto3) throws SQLException {

        Connection conn = DriverManager.getConnection("jdbc:mariadb://localhost:3306/progetto?user=root&password=root");
        Statement st = conn.createStatement();
        ResultSet rs;
        String sql = buildUpdateMenuSQL(numero, piatto1,piatto2,piatto3);

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

    public static String buildUpdateMenuSQL(int numero, String piatto1,String piatto2,String piatto3)  throws SQLException {
        return "UPDATE Menu SET numero = '" + numero + "' ,piatto1='"+piatto1+"',piatto2='"+piatto2+"',piatto3='"+piatto3+"'";
    }

    public List<common.Classes.Menu> getAllMenu()  throws RemoteException, SQLException {
        Connection conn = DriverManager.getConnection("jdbc:mariadb://localhost:3306/progetto?user=root&password=root");
        Statement stmt = conn.createStatement();

        String sql = "SELECT * FROM Menu";
        ResultSet rs = stmt.executeQuery(sql);
        List<common.Classes.Menu> menuList = new ArrayList<>();

        while (rs.next()) {

            int numero=rs.getInt("numero");
            String piatto1=rs.getString("piatto1");
            String piatto2=rs.getString("piatto2");
            String piatto3=rs.getString("piatto3");

            common.Classes.Menu menu = new Menu(numero,getPiatto(piatto1),getPiatto(piatto2),getPiatto(piatto3));

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

        Piatto piatto1= new Piatto(nome_p,tipo);
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

        Menu menu1 = new Menu(numero,getPiatto(piatto1),getPiatto(piatto2),getPiatto(piatto3));
        return menu1;

    }
}
