package common.Classes;

import java.io.Serializable;

public class Intolleranze implements Serializable{

    private Bambino cf;
    private Ingredienti nomeIngr;

    public Intolleranze (Bambino cf, Ingredienti nomeIngr){

        this.cf = cf;
        this.nomeIngr = nomeIngr;
    }

    public Bambino getCf() { return cf; }

    public void setCf(Bambino cf) {
        this.cf = cf;
    }

    public Ingredienti getNomeIngr() {
        return nomeIngr;
    }

    public void setNomeIngr(Ingredienti nomeIngr) {
        this.nomeIngr = nomeIngr;
    }

    @Override
    public boolean equals(Object obj) {
        if(obj==this)
            return true;
        if(!(obj instanceof Intolleranze))
            return false;
        return this.cf.equals(((Intolleranze) obj).cf);
    }

}
