import java.sql.*;
import java.util.Scanner;


public class Server {
    public static String utente = null;
    public static String password = null;


    private static boolean loginAttempt(String name, String pwd, Statement st) {
        ResultSet rs;
        String sql = buildLoginQuery(utente, password);

        try {
            rs = st.executeQuery(sql);
            return rs.next();
        } catch(SQLException ex) {
            System.err.println("sql exception");
            ex.printStackTrace();
            return false;
        }
    }

    private static void Accesso() throws SQLException {

        Connection conn = DriverManager.getConnection("jdbc:mariadb://localhost:3306/progetto?user=root&password=root");
        Statement st = conn.createStatement();

        Scanner in = new Scanner(System.in);

        while(true) {
            System.out.println("Inserire il nome utente:\n ");
            utente = in.nextLine();

            System.out.println("Inserisci la password:\n ");
            password = in.nextLine();

            if (loginAttempt(utente, password, st)) {
                System.out.println("login to PornHub succesful");
                break;
            }
            System.out.println("login errato, riprova");

        }

        conn.close();

    }

    private static String buildLoginQuery(String ute, String pass){
        return "Select * " +
                "From Utente " +
                "Where UserName = '" + utente + "' and Password = '" + password + "';";
    }

    private static void Registrazione()throws SQLException{

        Connection conn = DriverManager.getConnection("jdbc:mariadb://localhost:3306/progetto?user=root&password=root");
        Statement st = conn.createStatement();
        Scanner in = new Scanner(System.in);

        System.out.println("Inserire il nome utente:\n ");
        utente = in.nextLine();

        System.out.println("Inserisci la password:\n ");
        password = in.nextLine();

        String sql=" INSERT INTO Utente(UserName,Password)" +
                "VALUES('"+utente+"','"+password+"')";
        st.executeUpdate(sql);
    }
    private static void Inserire()throws SQLException{
        Connection conn = DriverManager.getConnection("jdbc:mariadb://localhost:3306/progetto?user=root&password=root");
        Statement st = conn.createStatement();
        Scanner in = new Scanner(System.in);
        String c,cf,cod_qr,nome,cognome,data,telefono,indirizzo,partita_iva,contatto,contatto1,contatto2;

        System.out.println("Cosa vuoi inserire Bambino.....\n ");
        c = in.nextLine();

        switch (c) {
            case "fornitore":
                System.out.println("Inserisci codice fiscale:\n ");
                cf = in.nextLine();
                System.out.println("Inserisci il partita iva:\n ");
                partita_iva = in.nextLine();

                System.out.println("Inserire il nome :\n ");
                nome = in.nextLine();

                System.out.println("Inserisci il cognome:\n ");
                cognome = in.nextLine();

                System.out.println("Inserisci la data di nascita:\n ");
                data = in.nextLine();

                System.out.println("Inserisci l' indirizzo:\n ");
                indirizzo = in.nextLine();

                System.out.println("Inserisci il numero di telefono:\n ");
                telefono = in.nextLine();


                String sql4=" INSERT INTO Fornitore(cf,nome,cognome,partita_iva,data,indirizzo,telefono)" +
                        "VALUES('"+cf+"','"+nome+"','"+cognome+"','"+partita_iva+"','"+data+"','"+indirizzo+"','"+telefono+"')";
                st.executeUpdate(sql4);

                break;
            case "contatti":
                System.out.println("Inserisci codice fiscale:\n ");
                cf = in.nextLine();

                System.out.println("Inserire il nome :\n ");
                nome = in.nextLine();

                System.out.println("Inserisci il cognome:\n ");
                cognome = in.nextLine();

                System.out.println("Inserisci la data di nascita:\n ");
                data = in.nextLine();

                System.out.println("Inserisci l' indirizzo:\n ");
                indirizzo = in.nextLine();

                System.out.println("Inserisci il numero di telefono:\n ");
                telefono = in.nextLine();


                String sql5=" INSERT INTO Contatti(cf,nome,cognome,data,indirizzo,telefono)" +
                        "VALUES('"+cf+"','"+nome+"','"+cognome+"','"+data+"','"+indirizzo+"','"+telefono+"')";
                st.executeUpdate(sql5);


                break;

            case "bambino":
                System.out.println("Inserisci codice fiscale:\n ");
                cf = in.nextLine();
                System.out.println("Inserisci il codice QR:\n ");
                cod_qr = in.nextLine();
                System.out.println("Inserire il nome :\n ");
                nome = in.nextLine();

                System.out.println("Inserisci il cognome:\n ");
                cognome = in.nextLine();

                System.out.println("Inserisci la data di nascita:\n ");
                data = in.nextLine();

                System.out.println("Inserisci l' indirizzo:\n ");
                indirizzo = in.nextLine();

                System.out.println("Inserisci il numero di telefono:\n ");
                telefono = in.nextLine();

                System.out.println("Inserisci il numero di telefono di un contatto:\n ");
                contatto = in.nextLine();
                System.out.println("Inserisci il numero di telefono di un'altro contatto(non indispensabile):\n ");
                contatto1 = in.nextLine();
                System.out.println("Inserisci il numero di telefono di un'altro contatto(non indispensabile):\n ");
                contatto2 = in.nextLine();


                String sql=" INSERT INTO Bambino(cf,cod_qr,nome,cognome,data,indirizzo,telefono,contatto,contatto1,contatto2)" +
                        "VALUES('"+cf+"','"+cod_qr+"','"+nome+"','"+cognome+"','"+data+"','"+indirizzo+"','"+telefono+"','"+contatto+"','"+contatto1+"','"+contatto2+"')";
                st.executeUpdate(sql);
                break;

            case "genitore":
                System.out.println("Inserisci codice fiscale:\n ");
                cf = in.nextLine();

                System.out.println("Inserire il nome :\n ");
                nome = in.nextLine();

                System.out.println("Inserisci il cognome:\n ");
                cognome = in.nextLine();

                System.out.println("Inserisci la data di nascita:\n ");
                data = in.nextLine();

                System.out.println("Inserisci l' indirizzo:\n ");
                indirizzo = in.nextLine();

                System.out.println("Inserisci il numero di telefono:\n ");
                telefono = in.nextLine();


                String sql1=" INSERT INTO Genitore(cf,nome,cognome,data,indirizzo,telefono)" +
                        "VALUES('"+cf+"','"+nome+"','"+cognome+"','"+data+"','"+indirizzo+"','"+telefono+"')";
                st.executeUpdate(sql1);

                break;

            case "personale":
                System.out.println("Inserisci codice fiscale:\n ");
                cf = in.nextLine();
                System.out.println("Inserisci il codice QR:\n ");
                cod_qr = in.nextLine();
                System.out.println("Inserire il nome :\n ");
                nome = in.nextLine();

                System.out.println("Inserisci il cognome:\n ");
                cognome = in.nextLine();

                System.out.println("Inserisci la data di nascita:\n ");
                data = in.nextLine();

                System.out.println("Inserisci l' indirizzo:\n ");
                indirizzo = in.nextLine();

                System.out.println("Inserisci il numero di telefono:\n ");
                telefono = in.nextLine();


                String sql2=" INSERT INTO Personale(cf,nome,codpqr,cognome,data,indirizzo,telefono)" +
                        "VALUES('"+cf+"','"+nome+"','"+cod_qr+"','"+cognome+"','"+data+"','"+indirizzo+"','"+telefono+"')";
                st.executeUpdate(sql2);
                break;

            case "pediatra":
                System.out.println("Inserisci codice fiscale:\n ");
                cf = in.nextLine();

                System.out.println("Inserire il nome :\n ");
                nome = in.nextLine();

                System.out.println("Inserire il cod_qr :\n ");
                cod_qr = in.nextLine();

                System.out.println("Inserisci il cognome:\n ");
                cognome = in.nextLine();

                System.out.println("Inserisci la data di nascita:\n ");
                data = in.nextLine();

                System.out.println("Inserisci l' indirizzo:\n ");
                indirizzo = in.nextLine();

                System.out.println("Inserisci il numero di telefono:\n ");
                telefono = in.nextLine();


                String sql3=" INSERT INTO Pediatra(cf,codqr,nome,cognome,data,indirizzo,telefono)" +
                        "VALUES('"+cf+"','"+cod_qr+"','"+nome+"','"+cognome+"','"+data+"','"+indirizzo+"','"+telefono+"')";
                st.executeUpdate(sql3);

                break;


            default:
                System.out.println("PORCO DIO!!!!COGLIONE NON ESISTE QUELLO CHE HAI SCRITTO");
        }




    }

    public static void main(String[] args) throws SQLException, ClassNotFoundException {


        Connection  conn = null;

        try {

            Class.forName("org.mariadb.jdbc.Driver");

        }catch (ClassNotFoundException e)
        {
                System.err.println("fucking!!");
                e.printStackTrace();
        }


        try {
            conn = DriverManager.getConnection("jdbc:mariadb://localhost:3306/progetto?createDatabaseIfNotExist=true", "root", "root");

            Database.creaTabelle(conn);

            Accesso();
            Registrazione();
            Inserire();



        }
        catch (SQLException e){

            System.err.println("connessione al DB non riuscita");
            e.printStackTrace();

        }
        finally {
            if(conn != null) {
                conn.close();
            }
        }
    }

    public static void ViewTablesql(Connection conn, String Utente) throws SQLException{

        Statement stmt = null;
        String query ="select UserName, Password, "+
                "from "+ utente +".Utente";
        try {
            stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()){
                String UserName = rs.getString("UserName");
                String Password = rs.getString("Password");

            }
        }catch(SQLException e ){
            System.out.println("connessione al DB non riuscita");
        }finally {
            if(stmt != null){
                stmt.close();
            }
        }
    }
    public static void ViewTablesql1(Connection conn, String Bambino) throws SQLException{

        Statement stmt = null;
        String query ="select cf , cod_qr, nome, cognome, data, indirizzo, telefono,"+
                "from"+Bambino+".Bambino";
        try {
            stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()){
                String cf = rs.getString("cf");
                String cod_qr = rs.getString("cod_qr");
                String nome = rs.getString("nome");
                String cognome = rs.getString("cognome");
                String indirizzo = rs.getString("indirizzo");
                String telefono = rs.getString("telefono");
                Date data = rs.getDate("data");

                //System.out.println(cf+","+cod_qr+","+nome+","+cognome+","+indirizzo+","+telefono+","+data);

            }
        }catch(SQLException e ){
            System.out.println("connessione al DB non riuscita");
        }finally {
            if(stmt != null){
                stmt.close();
            }
        }
    }
    public static void ViewTablesql2(Connection conn, String Genitore) throws SQLException{

        Statement stmt = null;
        String query ="select cf , nome, cognome, data, indirizzo, telefono,"+
                "from"+Genitore+".Genitore";
        try {
            stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()){
                String cf = rs.getString("cf");
                String nome = rs.getString("nome");
                String cognome = rs.getString("cognome");
                String indirizzo = rs.getString("indirizzo");
                String telefono = rs.getString("telefono");
                Date data = rs.getDate("data");

                //System.out.println(cf+","+nome+","+cognome+","+indirizzo+","+telefono+","+data);

            }
        }catch(SQLException e ){
            System.out.println("connessione al DB non riuscita");
        }finally {
            if (stmt != null) {
                stmt.close();
            }
        }

    }
    public static void ViewTablesql3(Connection conn, String Personale) throws SQLException{

        Statement stmt = null;
        String query ="select cf , cod_qr, nome, cognome, data, indirizzo, telefono,"+
                "from "+Personale+".Personale";
        try {
            stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()){
                String cf = rs.getString("cf");
                String cod_qr = rs.getString("cod_qr");
                String nome = rs.getString("nome");
                String cognome = rs.getString("cognome");
                String indirizzo = rs.getString("indirizzo");
                String telefono = rs.getString("telefono");
                Date data = rs.getDate("data");

                //System.out.println(cf+","+cod_qr+","+nome+","+cognome+","+indirizzo+","+telefono+","+data);

            }
        }catch(SQLException e ){
            System.out.println("connessione al DB non riuscita");
        }finally {
            if(stmt != null){
                stmt.close();
            }
        }
    }
    public static void ViewTablesql4(Connection conn, String Pediatra) throws SQLException{

        Statement stmt = null;
        String query ="select cf , nome, cognome, data, indirizzo, telefono,"+
                "from"+Pediatra+".Pediatra";
        try {
            stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()){
                String cf = rs.getString("cf");
                String nome = rs.getString("nome");
                String cognome = rs.getString("cognome");
                String indirizzo = rs.getString("indirizzo");
                String telefono = rs.getString("telefono");
                Date data = rs.getDate("data");

                //System.out.println(cf+","+nome+","+cognome+","+indirizzo+","+telefono+","+data);

            }
        }catch(SQLException e ){
            System.out.println("connessione al DB non riuscita");
        }finally {
            if(stmt != null){
                stmt.close();
            }
        }
    }
    public static void ViewTablesql5(Connection conn, String Fornitore) throws SQLException{

        Statement stmt = null;
        String query ="select cf , partitaiva, nome, cognome, data, indirizzo, telefono,"+
                "from"+Fornitore+".Fornitore";
        try {
            stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()){
                String cf = rs.getString("cf");
                String partita_iva = rs.getString("partita_iva");
                String nome = rs.getString("nome");
                String cognome = rs.getString("cognome");
                String indirizzo = rs.getString("indirizzo");
                String telefono = rs.getString("telefono");
                Date data = rs.getDate("data");

                //System.out.println(cf+","+partitaiva+","+nome+","+cognome+","+indirizzo+","+telefono+","+data);

            }
        }catch(SQLException e ){
            System.out.println("connessione al DB non riuscita");
        }finally {
            if(stmt != null){
                stmt.close();
            }
        }
    }
    public static void ViewTablesql6(Connection conn, String Contatti) throws SQLException{

        Statement stmt = null;
        String query ="select cf , nome, cognome, data, indirizzo, telefono,"+
                "from"+Contatti+".Contatti";
        try {
            stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()){
                String cf = rs.getString("cf");
                String nome = rs.getString("nome");
                String cognome = rs.getString("cognome");
                String indirizzo = rs.getString("indirizzo");
                String telefono = rs.getString("telefono");
                Date data = rs.getDate("data");

                //System.out.println(cf+","+nome+","+cognome+","+indirizzo+","+telefono+","+data);

            }
        }catch(SQLException e ){
            System.out.println("connessione al DB non riuscita");
        }finally {
            if(stmt != null){
                stmt.close();
            }
        }
    }
    public static void ViewTablesql7(Connection conn, String Menu) throws SQLException{

        Statement stmt = null;
        String query ="select numero , data,"+
                "from"+Menu+".Menu";
        try {
            stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()){
                int numero = rs.getInt("numero");
                Date data = rs.getDate("data");

                //System.out.println(numero+","+data);

            }
        }catch(SQLException e ){
            System.out.println("connessione al DB non riuscita");
        }finally {
            if(stmt != null){
                stmt.close();
            }
        }
    }
    public static void ViewTablesql8(Connection conn, String Piatto) throws SQLException{

        Statement stmt = null;
        String query ="select nome_p, tipo, quantita,"+
                "from"+Piatto+".Piatto";
        try {
            stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()){
                String nome_p= rs.getString("nome_p");
                String tipo= rs.getString("tipo");
                int quantita = rs.getInt("quantita");
                //System.out.println(nome_p+","+tipo+","+quantita);

            }
        }catch(SQLException e ){
            System.out.println("connessione al DB non riuscita");
        }finally {
            if(stmt != null){
                stmt.close();
            }
        }
    }
    public static void ViewTablesql9(Connection conn, String Ingrediente) throws SQLException{

        Statement stmt = null;
        String query ="select nome_i, quantita,"+
                "from"+Ingrediente+".Ingrediente";
        try {
            stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()){
                String nome_i= rs.getString("nome_i");
                int quantita = rs.getInt("quantita");
                //System.out.println(nome_i+","+quantita);

            }
        }catch(SQLException e ){
            System.out.println("connessione al DB non riuscita");
        }finally {
            if(stmt != null){
                stmt.close();
            }
        }
    }
    public static void ViewTablesql10(Connection conn, String Gita) throws SQLException{

        Statement stmt = null;
        String query ="select codice_g, num_pullman, num_partecipanti, destinazione,"+
                "from"+Gita+".Gita";
        try {
            stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()){
                String codice_g= rs.getString("codice_g");
                String destinazione= rs.getString("destinazione");
                int num_pullman = rs.getInt("num_pullman");
                int num_partecipanti = rs.getInt("num_partecipanti");

                //System.out.println(codice_g+","+num_pullman+","+num_partecipanti+","+destinazione);

            }
        }catch(SQLException e ){
            System.out.println("connessione al DB non riuscita");
        }finally {
            if(stmt != null){
                stmt.close();
            }
        }
    }
    public static void ViewTablesql11(Connection conn, String Pullman) throws SQLException{

        Statement stmt = null;
        String query ="select targa, capienza, "+
                "from"+Pullman+".Pullman";
        try {
            stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()){
                String targa= rs.getString("targa");
                int capienza = rs.getInt("capienza");

                //System.out.println(targa+","+capienza);

            }
        }catch(SQLException e ){
            System.out.println("connessione al DB non riuscita");
        }finally {
            if(stmt != null){
                stmt.close();
            }
        }
    }
    public static void ViewTablesql12(Connection conn, String Tappa) throws SQLException{

        Statement stmt = null;
        String query ="select codice_t, "+
                "from"+Tappa+".Tappa";
        try {
            stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()){
                String codice_t= rs.getString("codice_t");


                //System.out.println(codice_t);

            }
        }catch(SQLException e ){
            System.out.println("connessione al DB non riuscita");
        }finally {
            if(stmt != null){
                stmt.close();
            }
        }
    }
    public static void ViewTablesql13(Connection conn, String Gateway) throws SQLException{

        Statement stmt = null;
        String query ="select data,cod_porta "+
                "from"+Gateway+".Gateway";
        try {
            stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()){
                String cod_porta= rs.getString("cod_porta");
                Date data = rs.getDate("data");

                //System.out.println(cod_porta+","+data);

            }
        }catch(SQLException e ){
            System.out.println("connessione al DB non riuscita");
        }finally {
            if(stmt != null){
                stmt.close();
            }
        }
    }
    public static void ViewTablesql14(Connection conn, String Attraversa) throws SQLException{

        Statement stmt = null;
        String query ="select data,cod_porta, cf "+
                "from"+Attraversa+".Attraversa";
        try {
            stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()){
                String cod_porta= rs.getString("cod_porta");
                Date data = rs.getDate("data");
                String cf= rs.getString("cf");

                //System.out.println(cod_porta+","+data+","+cf);

            }
        }catch(SQLException e ){
            System.out.println("connessione al DB non riuscita");
        }finally {
            if(stmt != null){
                stmt.close();
            }
        }
    }
    public static void ViewTablesql15(Connection conn, String Intolleranza) throws SQLException{

        Statement stmt = null;
        String query ="select cf,nome "+
                "from"+Intolleranza+".Intolleranza";
        try {
            stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()){
                String nome= rs.getString("nome");
                String cf= rs.getString("cf");

                //System.out.println(nome+","+cf);

            }
        }catch(SQLException e ){
            System.out.println("connessione al DB non riuscita");
        }finally {
            if(stmt != null){
                stmt.close();
            }
        }
    }
    public static void ViewTablesql16(Connection conn, String Contiene) throws SQLException{

        Statement stmt = null;
        String query ="select nome_p, nome_i "+
                "from "+Contiene+".Contiene";
        try {
            stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()){
                String nome_p= rs.getString("nome_p");
                String nome_i= rs.getString("nome_i");

                //System.out.println(nome_p+","+nome_i);

            }
        }catch(SQLException e ){
            System.out.println("connessione al DB non riuscita");
        }finally {
            if(stmt != null){
                stmt.close();
            }
        }
    }
    public static void ViewTablesql17(Connection conn, String Aderisce) throws SQLException{

        Statement stmt = null;
        String query ="select cf, codice_g "+
                "from "+Aderisce+".Aderisce";
        try {
            stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()){
                String cf= rs.getString("cf");
                String codice_g= rs.getString("codice_g");

                //System.out.println(cf+","+codice_g);

            }
        }catch(SQLException e ){
            System.out.println("connessione al DB non riuscita");
        }finally {
            if(stmt != null){
                stmt.close();
            }
        }
    }
    public static void ViewTablesql18(Connection conn, String Utilizza) throws SQLException{

        Statement stmt = null;
        String query ="select targa, codice_g "+
                "from "+Utilizza+".Utilizza";
        try {
            stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()){
                String targa = rs.getString("targa");
                String codice_g = rs.getString("codice_g");

                //System.out.println(nome_p+","+nome_i);

            }
        }catch(SQLException e ){
            System.out.println("connessione al DB non riuscita");
        }finally {
            if(stmt != null){
                stmt.close();
            }
        }
    }
    public static void ViewTablesql19(Connection conn, String Effettua) throws SQLException{

        Statement stmt = null;
        String query ="select targa, codice_t "+
                "from "+Effettua+".Effettua";
        try {
            stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()){
                String targa= rs.getString("targa");
                String codice_t= rs.getString("codice_t");

                //System.out.println(targa+","+codice_t);

            }
        }catch(SQLException e ){
            System.out.println("connessione al DB non riuscita");
        }finally {
            if(stmt != null){
                stmt.close();
            }
        }
    }
    public static void ViewTablesql20(Connection conn, String Controllo) throws SQLException{

        Statement stmt = null;
        String query ="select targa, codice_t, cf "+
                "from "+Controllo+".Controllo";
        try {
            stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()){
                String targa= rs.getString("targa");
                String codice_t= rs.getString("codice_t");
                String cf= rs.getString("cf");


                //System.out.println(targa+","+codice_t+","+cf);

            }
        }catch(SQLException e ){
            System.out.println("connessione al DB non riuscita");
        }finally {
            if(stmt != null){
                stmt.close();
            }
        }
    }

}
