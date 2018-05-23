package common.Classes;

import java.io.Serializable;

public class Utilizza implements Serializable {
    private Gita codice_g;
    private Pullman targa;

    public Utilizza (Gita codice_g, Pullman targa){

        this.codice_g=codice_g;
        this.targa = targa;
    }

    public Gita getCodice_g() {
        return codice_g;
    }

    public void setCodice_g(Gita codice_g) {
        this.codice_g = codice_g;
    }

    public Pullman getTarga() {
        return targa;
    }

    public void setTarga(Pullman targa) {
        this.targa = targa;
    }

}
