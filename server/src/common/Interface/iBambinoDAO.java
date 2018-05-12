package common.Interface;

import java.rmi.Remote;
import java.sql.SQLException;
import java.time.LocalDate;

public interface iBambinoDAO extends Remote {

    boolean inserisciBambino(int cod_qr, String cf, String nome, String cognome, LocalDate data, String indirizzo, String contatto1, String contatto2) throws SQLException;
    boolean modificaBambino(int cod_qr, String cf, String nome, String cognome, LocalDate data, String indirizzo, String contatto1, String contatto2) throws SQLException;

}
