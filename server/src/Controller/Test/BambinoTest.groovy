package Controller.Test

import Controller.BambinoDAO
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import sun.net.httpserver.ServerImpl

import java.rmi.RemoteException


class BambinoTest {

    private ServerImpl si;
    private BambinoTest() throws RemoteException{
        si= new ServerImpl();
    }

    @Before
     void inserimentoBambino() throws RemoteException{
        ArrayList<String> kids = new ArrayList<>()
        BambinoDAO bam = new BambinoDAO()
        Assert.assertEquals(6, bam.getAllBambini().size())
    }

    @Test
     void inserimentoTest2(){
        BambinoDAO bam = new BambinoDAO()
        Assert.assertNotEquals(5, bam.getAllBambini().size())
    }

}

