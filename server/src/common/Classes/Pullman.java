package common.Classes;

public class Pullman {

    private String targa;
    private int capienza;

    public Pullman (String targa,int capienza){

        this.targa=targa;
        this.capienza=capienza;

    }


    public String getTarga() {
        return targa;
    }
    public void setTarga(String targa) {
        this.targa = targa;
    }

    public int getCapienza() {
        return capienza;
    }
    public void setCapienza(int capienza) {
        this.capienza = capienza;
    }
}
