package Controller.Test

import Controller.IngredientiDAO
import Controller.PiattoDAO
import common.Classes.Ingredienti
import common.Classes.Piatto
import org.junit.Assert
import org.junit.Test

import java.sql.SQLException

class IngrredienteTest {
    @Test
    void createTest() {
        Ingredienti ingr1 = new Ingredienti("ppppp", 888)
        IngredientiDAO ingr = new IngredientiDAO()

        //Test Creazione + Read
        ingr.inserisciIngrediente(ingr1.getNome_i())
        Assert.assertEquals(ingr1, ingr.getIngrediente(ingr1.getNome_i()))


        //Test cancellazione + Read
        //Assert eccezione in junit vecchio
        ingr.cancellaIngredienti(ingr1.getNome_i())
        try{
            ingr.getIngrediente(ingr1.getNome_i())
            Assert.fail();
        } catch (SQLException e) {}
    }
}
