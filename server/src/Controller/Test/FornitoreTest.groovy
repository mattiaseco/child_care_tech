package Controller.Test

import Controller.FornitoreDAO
import org.junit.Assert
import org.junit.Test

class FornitoreTest {
    @Test
    void inserimentoTest(){
        FornitoreDAO forn = new FornitoreDAO()
        Assert.assertEquals(4, forn.getAllFornitore().size())
    }
}
