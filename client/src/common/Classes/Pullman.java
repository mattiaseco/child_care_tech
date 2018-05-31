package common.Classes;

import java.io.Serializable;

public class Pullman implements Serializable{

    private String targa;
    private int capienza;
    public Pullman (String targa,int capienza){

        this.capienza=capienza;
        this.targa = targa;
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
    @Override
    public boolean equals(Object obj) {
        if(obj==this)
            return true;
        if(!(obj instanceof Pullman))
            return false;
        return this.targa.equals(((Pullman) obj).targa);
    }
}
