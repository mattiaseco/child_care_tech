package common.Classes;

import java.io.Serializable;

public class Mangia implements Serializable {

    private Bambino cf;
    private Menu numero;


    public Mangia (Bambino cf,Menu numero){

        this.cf=cf;
        this.numero = numero;
    }

    public Bambino getCf() {
        return cf;
    }
    public void setCf(Bambino cf) {
        this.cf = cf;
    }

    public Menu getNumero(){ return  numero;}
    public void setNumero(Menu numero) {
        this.numero = numero;
    }
}
