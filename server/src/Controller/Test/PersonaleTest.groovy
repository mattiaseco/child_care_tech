package Controller.Test

import Controller.PediatraDAO
import Controller.PersonaleDAO
import common.Classes.Pediatra
import common.Classes.Personale
import org.junit.Assert
import org.junit.Test

import java.sql.SQLException
import java.time.LocalDate

class PersonaleTest {
    @Test
    void createTest() {
        Personale pers1 = new Personale("1234567890123456", "luca", "tussi", LocalDate.now(), "", "1236547890")
        PersonaleDAO pers = new PersonaleDAO()
        String nomeAggiornato = "carlo";

        //Test Creazione + Read
        pers.inserisciPersonale(pers1.getCf(), pers1.getNome(), pers1.getCognome(), pers1.getData(), pers1.getIndirizzo(), pers1.getTelefono())
        Assert.assertEquals(pers1, pers.getPersonale(pers1.getCf()))

        //Test aggiornamento + Read
        pers.modificaPersonale(pers1.getCf(), nomeAggiornato, pers1.getCognome(), pers1.getData(), pers1.getIndirizzo(), pers1.getTelefono())
        Personale aggiornato = pers.getPersonale(pers1.getCf());
        Assert.assertEquals(nomeAggiornato, aggiornato.getNome());

        //Test cancellazione + Read
        //Assert eccezione in junit vecchio
        pers.cancellaPersonale(pers1.getCf())
        try{
            pers.getPersonale(pers1.getCf())
            Assert.fail();
        } catch (SQLException e) {}
    }
}
