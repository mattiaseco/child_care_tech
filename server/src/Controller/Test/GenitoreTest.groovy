package Controller.Test

import Controller.BambinoDAO
import Controller.GenitoreDAO
import common.Classes.Bambino
import common.Classes.Genitore
import org.junit.Assert
import org.junit.Test

import java.sql.SQLException
import java.time.LocalDate

class GenitoreTest {

    @Test
    void createTest() {
        Genitore gen1 = new Genitore("1234567890123456", "luca", "tussi", LocalDate.now(), "", "1236547890")
        GenitoreDAO gen = new GenitoreDAO()
        String nomeAggiornato = "carlo";

        //Test Creazione + Read
        gen.inserisciGenitore(gen1.getCf(), gen1.getNome(), gen1.getCognome(), gen1.getData(), gen1.getIndirizzo(), gen1.getTelefono())
        Assert.assertEquals(gen1, gen.getGenitore(gen1.getCf()))

        //Test aggiornamento + Read
        gen.modificaGenitore(gen1.getCf(), nomeAggiornato, gen1.getCognome(), gen1.getData(), gen1.getIndirizzo(), gen1.getTelefono())
        Genitore aggiornato = gen.getGenitore(gen1.getCf());
        Assert.assertEquals(nomeAggiornato, aggiornato.getNome());

        //Test cancellazione + Read
        //Assert eccezione in junit vecchio
        gen.cancellaGenitore(gen1.getCf())
        try{
            gen.getGenitore(gen1.getCf())
            Assert.fail();
        } catch (SQLException e) {}
    }
}