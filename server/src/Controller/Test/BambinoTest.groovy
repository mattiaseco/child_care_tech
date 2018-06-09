package Controller.Test

import Controller.BambinoDAO
import org.junit.Assert
import org.junit.Test


class BambinoTest {
    @Test
     void inserimentoTest(){
        BambinoDAO bam = new BambinoDAO()
        Assert.assertEquals(4, bam.getAllBambini().size())
    }

    @Test
     void inserimentoTest2(){
        BambinoDAO bam = new BambinoDAO()
        Assert.assertNotEquals(5, bam.getAllBambini().size())
    }


}
