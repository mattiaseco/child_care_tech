package Controller.Test

import Controller.BambinoDAO
import common.Classes.Bambino
import org.junit.After
import org.junit.Assert
import org.junit.Test

import java.sql.SQLException
import java.time.LocalDate


class BambinoTest {

    @Test
    void createTest() {
        Bambino bam1 = new Bambino("1234567890123456", "luca", "tussi", LocalDate.now(), "", "01", "02")
        BambinoDAO bam = new BambinoDAO()
        String nomeAggiornato = "carlo";

        //Test Creazione + Read
        bam.inserisciBambino(bam1.getCf(), bam1.getNome(), bam1.getCognome(), bam1.getData(), bam1.getIndirizzo(), bam1.getContatto1(), bam1.getContatto2())
        Assert.assertEquals(bam1, bam.getKid(bam1.getCf()))

        //Test aggiornamento + Read
        bam.modificaBambino(bam1.getCf(), nomeAggiornato, bam1.getCognome(), bam1.getData(), bam1.getIndirizzo(), bam1.getContatto1(), bam1.getContatto2())
        Bambino aggiornato = bam.getKid(bam1.getCf());
        Assert.assertEquals(nomeAggiornato, aggiornato.getNome());

        //Test cancellazione + Read
        //Assert eccezione in junit vecchio
        bam.cancellaBambino(bam1.getCf())
        try{
            bam.getKid(bam1.getCf())
            Assert.fail();
        } catch (SQLException e) {}
    }
}