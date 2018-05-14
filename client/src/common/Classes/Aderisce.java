package common.Classes;

public class Aderisce {
    private Bambino cf;
    private Gita codice_g;

    public Aderisce (Pullman targa, Gita codice_g){

        this.codice_g=codice_g;
        this.cf = cf;
    }

    public Gita getCodice_g() {
        return codice_g;
    }

    public void setCodice_g(Gita codice_g) {
        this.codice_g = codice_g;
    }

    public  Bambino getCf(){return cf;}

    public void setCf(Bambino cf) {
        this.cf = cf;
    }
}
