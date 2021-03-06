import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class Database {
    public static void avviaDatabase() throws ClassNotFoundException, SQLException{
        Connection conn = null;
        try {
            Class.forName("org.mariadb.jdbc.Driver");
            conn = DriverManager.getConnection("jdbc:mariadb://localhost:3306/progetto?createDatabaseIfNotExist=true", "root", "root");
            Database.creaTabelle(conn);
        } catch (ClassNotFoundException | SQLException e) {
            System.err.println(e.getMessage());
            e.printStackTrace();
        } finally {
            if(conn != null)
                conn.close();
        }
    }

    private static void creaTabelle(Connection conn) throws SQLException, ClassNotFoundException {

        Statement stmt;
        stmt = conn.createStatement();

        String sql=" CREATE TABLE IF NOT EXISTS Utente" +
                "(UserName VARCHAR(20) NOT NULL," +
                " Password VARCHAR(20) NOT NULL)";
        stmt.executeUpdate(sql);

        String sql1 = "CREATE TABLE IF NOT EXISTS Bambino " +
                "(cf VARCHAR(16) NOT NULL PRIMARY KEY," +
                "nome VARCHAR(25) NOT NULL," +
                "cognome VARCHAR(25) NOT NULL," +
                "data DATE NOT NULL," +
                "indirizzo VARCHAR(30) NOT NULL," +
                "contatto1 VARCHAR(10) NOT NULL,"+
                "contatto2 VARCHAR(10))";
        stmt.executeUpdate(sql1);

        String sql2 = "CREATE TABLE IF NOT EXISTS Genitore " +
                "(cf VARCHAR(16) NOT NULL PRIMARY KEY," +
                "nome VARCHAR(25) NOT NULL," +
                "cognome VARCHAR(25) NOT NULL," +
                "data DATE," +
                "indirizzo VARCHAR(30) NOT NULL," +
                "telefono VARCHAR(10))";
        stmt.executeUpdate(sql2);

        String sql3= "CREATE TABLE IF NOT EXISTS Personale " +
                "(cf VARCHAR(16) NOT NULL PRIMARY KEY ," +
                "nome VARCHAR(25) NOT NULL," +
                "cognome VARCHAR(25) NOT NULL," +
                "data DATE," +
                "indirizzo VARCHAR(30) NOT NULL,"+
                "telefono VARCHAR(10))";
        stmt.executeUpdate(sql3);

        String sql4 = "CREATE TABLE IF NOT EXISTS Pediatra" +
                "(cf VARCHAR(16) NOT NULL PRIMARY KEY," +
                "nome VARCHAR(20) NOT NULL,"+
                "cognome VARCHAR(25) NOT NULL," +
                "data DATE," +
                "indirizzo VARCHAR(30) NOT NULL," +
                "telefono VARCHAR(10))";
        stmt.executeUpdate(sql4);

        String sql5 = "CREATE TABLE IF NOT EXISTS Fornitore" +
                "(partita_iva VARCHAR(11) NOT NULL," +
                "cf VARCHAR(16) NOT NULL," +
                "nome VARCHAR(25) NOT NULL," +
                "cognome VARCHAR(25) NOT NULL," +
                "data DATE," +
                "indirizzo VARCHAR(30) NOT NULL," +
                "telefono VARCHAR(10),"+
                "PRIMARY KEY(partita_iva,cf))";
        stmt.executeUpdate(sql5);

        /*String sql6 = "CREATE TABLE IF NOT EXISTS Contatti" +
                "(cf VARCHAR(16) NOT NULL," +
                "nome VARCHAR(25) NOT NULL," +
                "cognome VARCHAR(25) NOT NULL," +
                "data DATE," +
                "indirizzo VARCHAR(30) NOT NULL," +
                "telefono VARCHAR(10)," +
                "PRIMARY KEY(cf))";
        stmt.executeUpdate(sql6);*/



        String sql7 = "CREATE TABLE IF NOT EXISTS Piatto " +
                "(nome_p VARCHAR(30) NOT NULL," +
                "tipo VARCHAR(30) NOT NULL ,"+
                 "PRIMARY KEY(nome_p))";
        stmt.executeUpdate(sql7);

        String sql8 = "CREATE TABLE IF NOT EXISTS Menu " +
                "(numero INT NOT NULL," +
                "piatto1 VARCHAR(30) NOT NULL,"+
                "piatto2 VARCHAR(30),"+
                "piatto3 VARCHAR(30),"+
                "INDEX(piatto1), FOREIGN KEY (piatto1) REFERENCES Piatto(nome_p) ON DELETE CASCADE ON UPDATE CASCADE,"+
                "INDEX(piatto2), FOREIGN KEY (piatto2) REFERENCES Piatto(nome_p) ON DELETE CASCADE ON UPDATE CASCADE,"+
                "INDEX(piatto3), FOREIGN KEY (piatto3) REFERENCES Piatto(nome_p) ON DELETE CASCADE ON UPDATE CASCADE,"+
                "PRIMARY KEY(numero))";
        stmt.executeUpdate(sql8);

        String sql9 = "CREATE TABLE IF NOT EXISTS Ingrediente " +
                "(nome_i VARCHAR(30) NOT NULL," +
                "quantita INT ,"+
                "PRIMARY KEY(nome_i))";
        stmt.executeUpdate(sql9);

        String sql21 = "CREATE TABLE IF NOT EXISTS Mangia " +
                "(cf VARCHAR(16) NOT NULL," +
                "numero INT,"+
                "INDEX(cf), FOREIGN KEY (cf) REFERENCES Bambino(cf) ON DELETE CASCADE ON UPDATE CASCADE,"+
                "INDEX(numero), FOREIGN KEY (numero) REFERENCES Menu(numero) ON DELETE CASCADE ON UPDATE CASCADE,"+
                "PRIMARY KEY(cf,numero))";
        stmt.executeUpdate(sql21);



        String sql10 = "CREATE TABLE IF NOT EXISTS Gita " +
                "(codice_g INT NOT NULL  ," +
                "num_pullman INT ," +
                "destinazione VARCHAR(20) NOT NULL ," +
                "num_partecipanti INT ,"+
                "data_partenza DATE NOT NULL,"+
                "data_ritorno DATE NOT NULL,"+
                "costo DOUBLE(6,2) NOT NULL,"+
                "descrizione VARCHAR(256),"+
                "PRIMARY KEY(codice_g))";
        stmt.executeUpdate(sql10);

        String sql11 = "CREATE TABLE IF NOT EXISTS Pullman " +
                "(targa VARCHAR(30) NOT NULL," +
                "capienza INT,"+
                "PRIMARY KEY(targa))";
        stmt.executeUpdate(sql11);

        /*String sql22= "CREATE TABLE IF NOT EXISTS Sale "+//BAMBINO PULLMAN
                "(cf VARCHAR(16),"+
                "targa VARCHAR(30),"+
                "INDEX (cf), FOREIGN KEY (cf) REFERENCES Bambino(cf) ON DELETE CASCADE ON UPDATE CASCADE,"+
                "INDEX(targa), FOREIGN KEY (targa) REFERENCES Pullman(targa) ON DELETE CASCADE ON UPDATE CASCADE,"+
                "PRIMARY KEY( cf, targa))";
        stmt.executeUpdate(sql22);*/

        /*String sql12 = "CREATE TABLE IF NOT EXISTS Tappa " +
                "(codice_t INT NOT NULL AUTO_INCREMENT,"+
                "PRIMARY KEY(codice_t))";
        stmt.executeUpdate(sql12);*/

        /*String sql13 = "CREATE TABLE IF NOT EXISTS Gateway "+
                "(data_gate DATE NOT NULL ,"+
                "cod_porta VARCHAR(30) NOT NULL ," +
                "PRIMARY KEY(cod_porta,data_gate))";
        stmt.executeUpdate(sql13);*/

        /*String sql14 = "CREATE TABLE IF NOT EXISTS Attraversa " + //PERSONA GATEWAY
                "(data_gate DATE, " +
                "cod_porta VARCHAR(30), " +
                "cf VARCHAR(16) , "+
                "INDEX(cod_porta,data_gate), FOREIGN KEY (cod_porta,data_gate) REFERENCES Gateway(cod_porta,data_gate) ON DELETE CASCADE ON UPDATE CASCADE,"+
                "INDEX (cf), FOREIGN KEY (cf) REFERENCES Bambino(cf) ON DELETE CASCADE ON UPDATE CASCADE,"+
                "PRIMARY KEY(cod_porta ,data_gate, cf))";
        stmt.executeUpdate(sql14);*/


        String sql15 = "CREATE TABLE IF NOT EXISTS Intolleranza "+//BAMBINO INGREDIENTE
                "(cf VARCHAR(16) NOT NULL,"+
                "ingrediente VARCHAR(30) NOT NULL,"+
                "INDEX (cf), FOREIGN KEY (cf) REFERENCES Bambino(cf) ON DELETE CASCADE ON UPDATE CASCADE,"+
                "INDEX(ingrediente), FOREIGN KEY (ingrediente) REFERENCES Ingrediente(nome_i) ON DELETE CASCADE ON UPDATE CASCADE,"+
                "PRIMARY KEY(cf,ingrediente))";
        stmt.executeUpdate(sql15);

        String sql16 = "CREATE TABLE IF NOT EXISTS Contiene "+//PIATTO INGREDIENTE
                "(nome_p VARCHAR(30),"+
                "nome_i VARCHAR(30),"+
                "INDEX (nome_p), FOREIGN KEY (nome_p) REFERENCES Piatto(nome_p) ON DELETE CASCADE ON UPDATE CASCADE,"+
                "INDEX(nome_i), FOREIGN KEY (nome_i) REFERENCES Ingrediente(nome_i) ON DELETE CASCADE ON UPDATE CASCADE,"+
                "PRIMARY KEY( nome_p, nome_i))";
        stmt.executeUpdate(sql16);

        String sql17 = "CREATE TABLE IF NOT EXISTS Aderisce"+//GITA BAMBINO
                "(cf VARCHAR(16),"+
                "codice_g int,"+
                "INDEX (cf), FOREIGN KEY (cf) REFERENCES Bambino(cf) ON DELETE CASCADE ON UPDATE CASCADE,"+
                "INDEX(codice_g), FOREIGN KEY (codice_g) REFERENCES Gita(codice_g) ON DELETE CASCADE ON UPDATE CASCADE,"+
                "PRIMARY KEY( cf, codice_g))";
        stmt.executeUpdate(sql17);

        String sql18 = "CREATE TABLE IF NOT EXISTS Utilizza"+//GITA PULLMAN
                "(targa VARCHAR(30),"+
                "codice_g INT,"+
                "INDEX(targa),FOREIGN KEY (targa) REFERENCES Pullman(targa) ON DELETE CASCADE ON UPDATE CASCADE," +
                "INDEX(codice_g),FOREIGN KEY (codice_g) REFERENCES Gita(codice_g) ON DELETE CASCADE ON UPDATE CASCADE,"+
                "PRIMARY KEY(codice_g , targa))";
        stmt.executeUpdate(sql18);

        /*String sql19 = "CREATE TABLE IF NOT EXISTS Effettua"+//PULLMAN TAPPA
                "(targa VARCHAR(30),"+
                "codice_t INT,"+
                "INDEX(targa),FOREIGN KEY (targa) REFERENCES Pullman(targa) ON DELETE CASCADE ON UPDATE CASCADE," +
                "INDEX(codice_t),FOREIGN KEY (codice_t) REFERENCES Tappa(codice_t) ON DELETE CASCADE ON UPDATE CASCADE,"+
                "PRIMARY KEY(targa , codice_t))";
        stmt.executeUpdate(sql19);*/

        /*String sql20 = "CREATE TABLE IF NOT EXISTS Controllo"+//PULLMAN TAPPA BAMBINO
                "(targa VARCHAR(16),"+
                "cf VARCHAR(16),"+
                "INDEX(targa),FOREIGN KEY (targa) REFERENCES Pullman(targa) ON DELETE CASCADE ON UPDATE CASCADE," +
                "INDEX(cf),FOREIGN KEY (cf) REFERENCES Bambino(cf) ON DELETE CASCADE ON UPDATE CASCADE,"+
                "PRIMARY KEY(targa , cf))";
        stmt.executeUpdate(sql20);*/

        String sql23 = "CREATE TABLE IF NOT EXISTS Presenza"+//PULLMAN TAPPA BAMBINO
                "(cf VARCHAR(16),"+
                "INDEX(cf),FOREIGN KEY (cf) REFERENCES Bambino(cf) ON DELETE CASCADE ON UPDATE CASCADE,"+
                "PRIMARY KEY(cf))";
        stmt.executeUpdate(sql23);

        conn.close();

    }

}