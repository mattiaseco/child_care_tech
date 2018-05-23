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

        String sql6 = "CREATE TABLE IF NOT EXISTS Contatti" +
                "(cf VARCHAR(16) NOT NULL," +
                "nome VARCHAR(25) NOT NULL," +
                "cognome VARCHAR(25) NOT NULL," +
                "data DATE," +
                "indirizzo VARCHAR(30) NOT NULL," +
                "telefono VARCHAR(10)," +
                "PRIMARY KEY(cf))";
        stmt.executeUpdate(sql6);

        String sql7 = "CREATE TABLE IF NOT EXISTS Menu " +
                "(numero INT ," +
                "data_menu DATE, " +
                "PRIMARY KEY(numero))";
        stmt.executeUpdate(sql7);

        String sql8 = "CREATE TABLE IF NOT EXISTS Piatto " +
                "(nome_p VARCHAR(15) NOT NULL," +
                "tipo VARCHAR(15) NOT NULL ,"+
                "quantita INT NOT NULL,"+
                 "PRIMARY KEY(nome_p))";
        stmt.executeUpdate(sql8);

        String sql9 = "CREATE TABLE IF NOT EXISTS Ingrediente " +
                "(nome_i VARCHAR(15) NOT NULL," +
                "quantita INT NOT NULL ,"+
                "PRIMARY KEY(nome_i))";
        stmt.executeUpdate(sql9);

        String sql10 = "CREATE TABLE IF NOT EXISTS Gita " +
                "(codice_g INT(10) NOT NULL AUTO_INCREMENT," +
                "num_pullman INT NOT NULL ," +
                "destinazione VARCHAR(20) NOT NULL ," +
                "num_partecipanti INT NOT NULL,"+
                "data_partenza DATE NOT NULL,"+
                "data_ritorno DATE NOT NULL,"+
                "costo VARCHAR(10) NOT NULL,"+
                "descrizione VARCHAR(256),"+
                "PRIMARY KEY(codice_g))";
        stmt.executeUpdate(sql10);

        String sql11 = "CREATE TABLE IF NOT EXISTS Pullman " +
                "(targa VARCHAR(15) NOT NULL," +
                "capienza INT,"+
                "PRIMARY KEY(targa))";
        stmt.executeUpdate(sql11);

        String sql12 = "CREATE TABLE IF NOT EXISTS Tappa " +
                "(codice_t VARCHAR(15) NOT NULL,"+
                "PRIMARY KEY(codice_t))";
        stmt.executeUpdate(sql12);

        String sql13 = "CREATE TABLE IF NOT EXISTS Gateway "+
                "(data_gate DATE NOT NULL ,"+
                "cod_porta VARCHAR(15) NOT NULL ," +
                "PRIMARY KEY(cod_porta,data_gate))";
        stmt.executeUpdate(sql13);

        String sql14 = "CREATE TABLE IF NOT EXISTS Attraversa " + //PERSONA GATEWAY
                "(data_gate DATE REFERENCES Gateway ON DELETE CASCADE , " +
                "cod_porta VARCHAR(15) REFERENCES Gateway ON DELETE CASCADE , " +
                "cf VARCHAR(16)  REFERENCES Bambino ON DELETE CASCADE , "+
                "PRIMARY KEY(cod_porta ,data_gate, cf))";
        stmt.executeUpdate(sql14);

        String sql15 = "CREATE TABLE IF NOT EXISTS Intolleranza "+//BAMBINO INGREDIENTE
                "(cf VARCHAR(16) REFERENCES Bambino ON DELETE CASCADE ,"+
                "ingrediente VARCHAR(15) REFERENCES Ingrediente ON DELETE CASCADE,"+
                "PRIMARY KEY(cf ,ingrediente))";
        stmt.executeUpdate(sql15);

        String sql16 = "CREATE TABLE IF NOT EXISTS Contiene "+//PIATTO INGREDIENTE
                "(nome_p VARCHAR(16) REFERENCES Piatto ON DELETE CASCADE ,"+
                "nome_i VARCHAR(15) REFERENCES Ingrediente ON DELETE CASCADE ,"+
                "PRIMARY KEY(nome_p, nome_i))";
        stmt.executeUpdate(sql16);

        String sql17 = "CREATE TABLE IF NOT EXISTS Aderisce"+//GITA BAMBINO
                "(cf VARCHAR(16) REFERENCES Bambino ON DELETE CASCADE ,"+
                "codice_g VARCHAR(15) REFERENCES Gita ON DELETE CASCADE ,"+
                "PRIMARY KEY(codice_g , cf))";
        stmt.executeUpdate(sql17);

        String sql18 = "CREATE TABLE IF NOT EXISTS Utilizza"+//GITA PULLMAN
                "(targa VARCHAR(16) REFERENCES Pullman ON DELETE CASCADE ,"+
                "codice_g VARCHAR(15) REFERENCES Gita ON DELETE CASCADE ,"+
                "PRIMARY KEY(codice_g , targa))";
        stmt.executeUpdate(sql18);

        String sql19 = "CREATE TABLE IF NOT EXISTS Effettua"+//PULLMAN TAPPA
                "(targa VARCHAR(16) REFERENCES Pullman ON DELETE CASCADE ,"+
                "codice_t VARCHAR(15) REFERENCES Tappa ON DELETE CASCADE ,"+
                "PRIMARY KEY(codice_t ,targa))";
        stmt.executeUpdate(sql19);

        String sql20 = "CREATE TABLE IF NOT EXISTS Controllo"+//PULLMAN TAPPA BAMBINO
                "(targa VARCHAR(16) REFERENCES Pullman ON DELETE CASCADE ,"+
                "codice_t VARCHAR(15) REFERENCES Tappa ON DELETE CASCADE ,"+
                "cf VARCHAR(16) REFERENCES Bambino ON DELETE CASCADE ,"+
                "PRIMARY KEY(codice_t, targa ,cf))";
        stmt.executeUpdate(sql20);

        conn.close();

    }

}
