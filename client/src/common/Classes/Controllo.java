package common.Classes;

import java.io.Serializable;

public class Controllo implements Serializable{
    private Pullman targa;
    private Bambino cf;
    private Tappa codice_t;

    public Controllo (Pullman targa, Bambino cf, Tappa codice_t){

        this.targa=targa;
        this.cf = cf;
        this.codice_t = codice_t;
    }

    public Pullman getTarga() {
        return targa;
    }

    public void setTarga(Pullman targa) {
        this.targa = targa;
    }

    public Tappa getCodice_t() {
        return codice_t;
    }

    public void setCodice_t(Tappa codice_t) {
        this.codice_t = codice_t;
    }
    public  Bambino getCf(){return cf;}

    public void setCf(Bambino cf) {
        this.cf = cf;
    }
}
