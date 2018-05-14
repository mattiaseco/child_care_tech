package common.Classes;

public class Effettua {
    private Pullman targa;
    private Tappa codice_t;

    public Effettua (Pullman targa, Tappa codice_t){

        this.targa=targa;
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
        this.codice_t = codice_t; }
}
