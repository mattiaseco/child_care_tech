package Controller.Test

import Controller.FornitoreDAO
import common.Classes.Fornitore
import org.junit.Assert
import org.junit.Test

import java.sql.SQLException
import java.time.LocalDate

class FornitoreTest {
    @Test
    void createTest() {
        Fornitore forn1 = new Fornitore("12345678901","1234567890123456", "luca", "tussi", LocalDate.now(), "", "1236547890")
        FornitoreDAO forn = new FornitoreDAO()
        String nomeAggiornato = "carlo";

        //Test Creazione + Read
        forn.inserisciFornitore(forn1.getPartita_iva(),forn1.getCf(), forn1.getNome(), forn1.getCognome(), forn1.getData(), forn1.getIndirizzo(), forn1.getTelefono())
        Assert.assertEquals(forn1, forn.getFornitore(forn1.getCf()))

        //Test aggiornamento + Read
        forn.modificaFornitore(forn1.getPartita_iva(),forn1.getCf(), nomeAggiornato, forn1.getCognome(), forn1.getData(), forn1.getIndirizzo(), forn1.getTelefono())
        Fornitore aggiornato = forn.getFornitore(forn1.getCf());
        Assert.assertEquals(nomeAggiornato, aggiornato.getNome());

        //Test cancellazione + Read
        //Assert eccezione in junit vecchio
        forn.cancellaFornitore(forn1.getCf())
        try{
            forn.getFornitore(forn1.getCf())
            Assert.fail();
        } catch (SQLException e) {}
    }
}
