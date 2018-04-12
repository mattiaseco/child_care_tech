import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class Database {
    public static void creaTabelle(Connection conn) throws SQLException, ClassNotFoundException {

        Statement stmt;
        stmt = conn.createStatement();

        String sql=" CREATE TABLE IF NOT EXISTS Utente" +
                "(UserName VARCHAR(20) NOT NULL," +
                " Password VARCHAR(20) NOT NULL)";
        stmt.executeUpdate(sql);

        /*String sql1 = "CREATE TABLE Bambino " +
                "(cf VARCHAR(16) NOT NULL ," +
                "cod_qr VARCHAR(10) NOT NULL," +
                "nome VARCHAR(25) NOT NULL," +
                "cognome VARCHAR(25) NOT NULL," +
                "data DATE," +
                "indirizzo VARCHAR(30) NOT NULL," +
                "telefono VARCHAR(10))"+
                "PRIMARY KEY(cf, cod_qr)";
        stmt.executeUpdate(sql1);

        String sql2 = "CREATE TABLE Genitore " +
                "(cf VARCHAR(16) NOT NULL PRIMARY KEY," +
                "nome VARCHAR(25) NOT NULL," +
                "cognome VARCHAR(25) NOT NULL," +
                "data DATE," +
                "indirizzo VARCHAR(30) NOT NULL," +
                "telefono VARCHAR(10))";
        stmt.executeUpdate(sql2);

        String sql3= "CREATE TABLE Personale " +
                "(cf VARCHAR(16) NOT NULL ," +
                "nome VARCHAR(25) NOT NULL," +
                "codqr VARCHAR(10) NOT NULL," +
                "cognome VARCHAR(25) NOT NULL," +
                "data DATE," +
                "indirizzo VARCHAR(30) NOT NULL," +
                "telefono VARCHAR(10))"+
                "PRIMARY KEY(cf, cod_qr)";
        stmt.executeUpdate(sql3);

        String sql4 = "CREATE TABLE Pediatra" +
                "(cf VARCHAR(16) NOT NULL PRIMARY KEY " +
                "codqr VARCHAR(10) NOT NULL " +
                "nome VARCHAR(25) NOT NULL " +
                "cognome VARCHAR(25) NOT NULL " +
                "data DATE " +
                "indirizzo VARCHAR(30) NOT NULL " +
                "telefono VARCHAR(10))";
        stmt.executeUpdate(sql4);

        String sql5 = "CREATE TABLE Fornitore " +
                "(cf VARCHAR(16) NOT NULL PRIMARY KEY," +
                "nome VARCHAR(25) NOT NULL," +
                "cognome VARCHAR(25) NOT NULL," +
                "partita_iva VARCHAR(10) NOT NULL," +
                "data DATE," +
                "indirizzo VARCHAR(30) NOT NULL," +
                "telefono VARCHAR(10))"+
                "PRIMARY KEY(cf, partita_iva)";
        stmt.executeUpdate(sql5);

        String sql6 = "CREATE TABLE Contatti" +
                "(cf VARCHAR(16) NOT NULL PRIMARY KEY," +
                "nome VARCHAR(25) NOT NULL," +
                "cognome VARCHAR(25) NOT NULL," +
                "data DATE," +
                "indirizzo VARCHAR(30) NOT NULL," +
                "telefono VARCHAR(10))";
        stmt.executeUpdate(sql6);

        String sql7 = "CREATE TABLE Menu " +
                "(numero INT NOT NULL PRIMARY KEY," +
                "data DATE)";
        stmt.executeUpdate(sql7);

        String sql8 = "CREATE TABLE Piatto " +
                "(nome_p VARCHAR(15) NOT NULL PRIMARY KEY ," +
                "tipo VARCHAR(15) NOT NULL ,"+
                "quantita INT NOT NULL )";
        stmt.executeUpdate(sql8);

        String sql9 = "CREATE TABLE Ingrediente " +
                "(nome_i VARCHAR(15) NOT NULL PRIMARY KEY ," +
                "quantita INT NOT NULL )";
        stmt.executeUpdate(sql9);

        String sql10 = "CREATE TABLE Gita " +
                "(codice_g VARCHAR(15) NOT NULL PRIMARY KEY ," +
                "num_pullman INT NOT NULL ," +
                "destinazione VARCHAR(20) NOT NULL ," +
                "num_partecipanti INT NOT NULL)";
        stmt.executeUpdate(sql10);

        String sql11 = "CREATE TABLE Pullman " +
                "(targa VARCHAR(15) NOT NULL PRIMARY KEY ," +
                "capienza INT )";
        stmt.executeUpdate(sql11);

        String sql12 = "CREATE TABLE Tappa " +
                "(codice_t VARCHAR(15) NOT NULL PRIMARY KEY" ;
        stmt.executeUpdate(sql12);

        String sql13 = "CREATE TABLE Gateway "+
                "(data DATE NOT NULL ,"+
                "cod_porta VARCHAR(15) NOT NULL ," +
                "PRIMARY KEY(cod_porta,data))";
        stmt.executeUpdate(sql13);

        String sql14 = "CREATE TABLE Attraversa "+ //PERSONA GATEWAY
                "(data DATE REFERENCES Gateway ON DELETE CASCADE ,"+
                "cod_porta VARCHAR(15) REFERENCES Gateway ON DELETE CASCADE ," +
                "(cf VARCHAR(16)  REFERENCES Bambino ON DELETE CASCADE ,"+
                "PRIMARY KEY(cod_porta ,data, cf))";
        stmt.executeUpdate(sql14);

        String sql15 = "CREATE TABLE Intolleranza "+//BAMBINO INGREDIENTE
                "(cf VARCHAR(16) REFERENCES Bambino ON DELETE CASCADE ,"+
                "nome VARCHAR(15) REFERENCES Ingrediente ON DELETE CASCADE ,"+
                "PRIMARY KEY(cf ,nome))";
        stmt.executeUpdate(sql15);

        String sql16 = "CREATE TABLE Contiene "+//PIATTO INGREDIENTE
                "(nome_p VARCHAR(16) REFERENCES Piatto ON DELETE CASCADE ,"+
                "nome_i VARCHAR(15) REFERENCES Ingrediente ON DELETE CASCADE ,"+
                "PRIMARY KEY(nome_p, nome_i))";
        stmt.executeUpdate(sql16);

        String sql17 = "CREATE TABLE Aderisce"+//GITA BAMBINO
                "(cf VARCHAR(16) REFERENCES Bambino ON DELETE CASCADE ,"+
                "codice_g VARCHAR(15) REFERENCES Gita ON DELETE CASCADE ,"+
                "PRIMARY KEY(codice_g , cf))";
        stmt.executeUpdate(sql17);

        String sql18 = "CREATE TABLE Utilizza"+//GITA PULLMAN
                "(targa VARCHAR(16) REFERENCES Pullman ON DELETE CASCADE ,"+
                "codice_g VARCHAR(15) REFERENCES Gita ON DELETE CASCADE ,"+
                "PRIMARY KEY(codice_g , targa))";
        stmt.executeUpdate(sql18);

        String sql19 = "CREATE TABLE Effettua"+//PULLMAN TAPPA
                "(targa VARCHAR(16) REFERENCES Pullman ON DELETE CASCADE ,"+
                "codice_t VARCHAR(15) REFERENCES Tappa ON DELETE CASCADE ,"+
                "PRIMARY KEY(codice_t , targa))";
        stmt.executeUpdate(sql19);

        String sql20 = "CREATE TABLE Controllo"+//PULLMAN TAPPA BAMBINO
                "(targa VARCHAR(16) REFERENCES Pullman ON DELETE CASCADE ,"+
                "codice_t VARCHAR(15) REFERENCES Tappa ON DELETE CASCADE ,"+
                "cf VARCHAR(16) REFERENCES Bambino ON DELETE CASCADE ,"+
                "PRIMARY KEY(codice_t, targa ,cf))";
        stmt.executeUpdate(sql20); */

        conn.close();

    }
}