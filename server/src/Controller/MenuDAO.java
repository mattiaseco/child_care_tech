package Controller;

import common.Classes.*;
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
    public void inserisciPrimo(int numeroMenu,Piatto piatto1) throws SQLException {

        try {
            createPrimo(numeroMenu,piatto1);
        } catch (SQLException e) {

            System.out.println(e.getMessage());
            e.printStackTrace();
            return;
        }

    }
    private static void createPrimo(int numeroMenu,Piatto piatto1) throws SQLException {


            Connection conn = DriverManager.getConnection("jdbc:mariadb://localhost:3306/progetto?user=root&password=root");
            Statement st = conn.createStatement();
            ResultSet rs;
            String sql = buildCreatePrimoSQL(numeroMenu, piatto1);

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
    private static String buildCreatePrimoSQL(int numeroMenu,Piatto piatto1) {

        return "INSERT INTO Menu(numero,piatto1)" +
                "VALUES('"+numeroMenu+"','"+piatto1.getNome_p()+"')";

    }

    @Override
    public void inserisciSecondo(int numeroMenu,Piatto piatto2) throws SQLException {

        try {
            createSecondo(numeroMenu,piatto2);
        } catch (SQLException e) {

            System.out.println(e.getMessage());
            e.printStackTrace();
            return;
        }

    }
    private static void createSecondo(int numeroMenu,Piatto piatto2) throws SQLException {

        Connection conn = DriverManager.getConnection("jdbc:mariadb://localhost:3306/progetto?user=root&password=root");
        Statement st = conn.createStatement();
        ResultSet rs;
        String sql = buildCreateSecondoSQL(numeroMenu,piatto2);

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
    private static String buildCreateSecondoSQL(int numeroMenu,Piatto piatto2) {

        return "UPDATE Menu SET piatto2 = '"+piatto2.getNome_p()+"' WHERE numero = '"+numeroMenu+"'";

    }

    @Override
    public void inserisciContorno(int numeroMenu,Piatto piatto3) throws SQLException {

        try {
            createContorno(numeroMenu,piatto3);
        } catch (SQLException e) {

            System.out.println(e.getMessage());
            e.printStackTrace();
            return;
        }

    }
    private static void createContorno(int numeroMenu,Piatto piatto3) throws SQLException {


        Connection conn = DriverManager.getConnection("jdbc:mariadb://localhost:3306/progetto?user=root&password=root");
        Statement st = conn.createStatement();
        ResultSet rs;
        String sql = buildCreateContornoSQL(numeroMenu,piatto3);

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
    private static String buildCreateContornoSQL(int numeroMenu,Piatto piatto3) {

        return "UPDATE Menu SET piatto3 = '"+piatto3.getNome_p()+"' WHERE numero ='"+numeroMenu+"'";

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
        rs.next();
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

    public List<Mangia> GetAllBambiniMenuMangia(Menu menu) throws SQLException{
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
        rs.next();


        int numero=rs.getInt("numero");
        LocalDate data = LocalDate.parse(rs.getString("data"));
        String piatto1=rs.getString("piatto1");
        String piatto2=rs.getString("piatto2");
        String piatto3=rs.getString("piatto3");

        Menu menu1 = new Menu(numero,getPiatto(piatto1),getPiatto(piatto2),getPiatto(piatto3));
        return menu1;

    }
    public List<Bambino>getAllBambiniMenu(Menu menu)throws RemoteException,SQLException{
        Connection conn = DriverManager.getConnection("jdbc:mariadb://localhost:3306/progetto?user=root&password=root");
        Statement stmt = conn.createStatement();

        String sql="SELECT * FROM Bambino WHERE cf IN (" +
                "SELECT cf FROM Mangia WHERE numero = '" + menu.getNumero() + "')";
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
        return bambinoList;


    }

    public void inserisciBambinoMangia(Menu menu, Bambino bambino)throws RemoteException,SQLException{
        try {
            createBambinoMangia(menu.getNumero(), bambino.getCf());
        } catch (SQLException e) {

            System.out.println(e.getMessage());
            e.printStackTrace();
            return;
        }
    }
    private static void createBambinoMangia(int numero,String cf)throws SQLException{
        Connection conn = DriverManager.getConnection("jdbc:mariadb://localhost:3306/progetto?user=root&password=root");
        Statement st = conn.createStatement();
        ResultSet rs;
        String sql = buildCreateBambinoMangiaSQL(numero, cf);

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
    private static String buildCreateBambinoMangiaSQL(int numero, String cf)throws SQLException{
        return "INSERT INTO Mangia(cf,numero) VALUES('" + cf + "','" + numero+ "')";
    }

    public void cancellaBambinoMangia(Menu menu, Bambino bambino) throws RemoteException, SQLException {
        try {
        deleteBambinoMangia(menu.getNumero(),bambino.getCf());
    }catch (SQLException e){
        System.err.println(e.getMessage());
        e.printStackTrace();
        return;
    }
    }
    private static void deleteBambinoMangia(int numero,String cf )throws SQLException{
        Connection conn = DriverManager.getConnection("jdbc:mariadb://localhost:3306/progetto?user=root&password=root");
        Statement st = conn.createStatement();
        ResultSet rs;
        String sql = buildDeleteBambinoMangiaSQL(numero,cf);

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
    private static String buildDeleteBambinoMangiaSQL(int numero,String cf){
        return "DELETE FROM Mangia WHERE numero='"+numero+"' AND cf='"+cf+"'";

    }



    public Piatto getPiatto1(int numero)throws RemoteException,SQLException{
        Connection conn = DriverManager.getConnection("jdbc:mariadb://localhost:3306/progetto?user=root&password=root");
        Statement stmt = conn.createStatement();
        String sql="SELECT piatto1 FROM Menu WHERE numero='"+numero+"'";
        ResultSet rs=stmt.executeQuery(sql);
        rs.next();

        String nome_p = rs.getString("piatto1");


        return getPiatto(nome_p);
    }
    public Piatto getPiatto2(int numero)throws RemoteException,SQLException{
        Connection conn = DriverManager.getConnection("jdbc:mariadb://localhost:3306/progetto?user=root&password=root");
        Statement stmt = conn.createStatement();
        String sql="SELECT piatto2 FROM Menu WHERE numero='"+numero+"'";
        ResultSet rs=stmt.executeQuery(sql);
        rs.next();

        String nome_p = rs.getString("piatto2");


        return getPiatto(nome_p);
    }
    public Piatto getPiatto3(int numero)throws RemoteException,SQLException{
        Connection conn = DriverManager.getConnection("jdbc:mariadb://localhost:3306/progetto?user=root&password=root");
        Statement stmt = conn.createStatement();
        String sql="SELECT piatto3 FROM Menu WHERE numero='"+numero+"'";
        ResultSet rs=stmt.executeQuery(sql);
        rs.next();

        String nome_p = rs.getString("piatto3");


        return getPiatto(nome_p);
    }

    public List<Ingredienti>getAllIngredientiMenu(Menu menu)throws RemoteException,SQLException{
        List<Ingredienti>ingredientiPiatto1List=new ArrayList<>(getAllIngredientiPiatto(getPiatto1(menu.getNumero())));
        List<Ingredienti>ingredientiPiatto2List=new ArrayList<>(getAllIngredientiPiatto(getPiatto2(menu.getNumero())));
        List<Ingredienti>ingredientiPiatto3List=new ArrayList<>(getAllIngredientiPiatto(getPiatto3(menu.getNumero())));
        List<Ingredienti> ingredientiMenu = new ArrayList<Ingredienti>();
        ingredientiMenu.addAll(ingredientiPiatto1List);
        ingredientiMenu.addAll(ingredientiPiatto2List);
        ingredientiMenu.addAll(ingredientiPiatto3List);

        return ingredientiMenu;
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
        return ingredientiList;
    }
    public List<Intolleranze> getAllBambiniPresentiSenzaMenu(Menu menu) throws RemoteException, SQLException{

        Connection conn = DriverManager.getConnection("jdbc:mariadb://localhost:3306/progetto?user=root&password=root");
        Statement stmt = conn.createStatement();

        String sql="SELECT * FROM Intolleranza WHERE cf IN (" +
                "SELECT cf FROM Mangia WHERE numero = '" + menu.getNumero()+"')";
        ResultSet rs=stmt.executeQuery(sql);
        List<Intolleranze> bambinoList = new ArrayList<>();

        while (rs.next()) {
            String cf = rs.getString("cf");
            String ingrediente = rs.getString("ingrediente");

            Intolleranze bambino_allergico= new Intolleranze(getBambinoMngia(cf),getIngrediente(ingrediente));

            bambinoList.add(bambino_allergico);
        }
        return bambinoList;
    }
    private Bambino getBambinoMngia(String cod_f)throws RemoteException,SQLException{
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
