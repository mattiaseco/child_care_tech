package Controller.Test

import Controller.PersonaleDAO
import Controller.PiattoDAO
import common.Classes.Personale
import common.Classes.Piatto
import org.junit.Assert
import org.junit.Test

import java.sql.SQLException
import java.time.LocalDate

class PiattoTest {
    @Test
    void createTest() {
        Piatto piatto1 = new Piatto("ppppp", "ppppppp")
        PiattoDAO piatto = new PiattoDAO()
        String nomeAggiornato = "carlo";

        //Test Creazione + Read
        piatto.inserisciPiatto(piatto1.getNome_p(),piatto1.getTipo())
        Assert.assertEquals(piatto1, piatto.getPiatto(piatto1.getNome_p()))


        //Test cancellazione + Read
        //Assert eccezione in junit vecchio
        piatto.cancellaPiatti(piatto1.getNome_p())
        try{
            piatto.getPiatto(piatto1.getNome_p())
            Assert.fail();
        } catch (SQLException e) {}
    }
}
