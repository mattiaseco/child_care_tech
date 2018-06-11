package Controller.Test

import Controller.FornitoreDAO
import Controller.PediatraDAO
import Controller.PiattoDAO
import common.Classes.Fornitore
import common.Classes.Pediatra
import org.junit.Assert
import org.junit.Test

import java.sql.SQLException
import java.time.LocalDate

class PediatraTest {
    @Test
    void createTest() {
        Pediatra ped1 = new Pediatra("1234567890123456", "luca", "tussi", LocalDate.now(), "", "1236547890")
        PediatraDAO ped = new PediatraDAO()
        String nomeAggiornato = "carlo";

        //Test Creazione + Read
        ped.inserisciPediatra(ped1.getCf(), ped1.getNome(), ped1.getCognome(), ped1.getData(), ped1.getIndirizzo(), ped1.getTelefono())
        Assert.assertEquals(ped1, ped.getPediatra(ped1.getCf()))

        //Test aggiornamento + Read
        ped.modificaPediatra(ped1.getCf(), nomeAggiornato, ped1.getCognome(), ped1.getData(), ped1.getIndirizzo(), ped1.getTelefono())
        Pediatra aggiornato = ped.getPediatra(ped1.getCf());
        Assert.assertEquals(nomeAggiornato, aggiornato.getNome());

        //Test cancellazione + Read
        //Assert eccezione in junit vecchio
        ped.cancellaPediatra(ped1.getCf())
        try{
            ped.getPediatra(ped1.getCf())
            Assert.fail();
        } catch (SQLException e) {}
    }
}
