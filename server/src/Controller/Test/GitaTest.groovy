package Controller.Test

import Controller.FornitoreDAO
import Controller.GitaDAO
import common.Classes.Fornitore
import common.Classes.Gita
import org.junit.Assert
import org.junit.Test

import java.sql.SQLException
import java.time.LocalDate

class GitaTest {
    @Test
    void createTest() {
        Gita gita1 = new Gita(000,"rrrr",97, 8888, LocalDate.now(),LocalDate.now().plusYears(1), new Double(99.22), "qqqq")
        GitaDAO gita = new GitaDAO()

        //Test Creazione + Read
        gita.inserisciGita(gita1.getCodice_g(), gita1.getDestinazione(),gita1.getData_partenza(), gita1.getData_ritorno(), gita1.getCosto(),gita1.getDescrizione())
        Assert.assertEquals(gita1, gita.getGita(gita1.getCodice_g()))


        //Test cancellazione + Read
        //Assert eccezione in junit vecchio
        gita.cancellaGita(gita1.getCodice_g())
        try{
            gita.getGita(gita1.getCodice_g())
            Assert.fail();
        } catch (SQLException e) {}
    }
}
