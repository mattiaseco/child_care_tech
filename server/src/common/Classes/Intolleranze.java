package common.Classes;

public class Intolleranze {

    private Bambino cf;
    private Ingredienti nomeIngr;

    public Intolleranze (Bambino cf, Ingredienti nomeIngr){

        this.cf = cf;
        this.nomeIngr = nomeIngr;
    }

    public Bambino getCf() {
        return cf;
    }

    public void setCf(Bambino cf) {
        this.cf = cf;
    }

    public Ingredienti getNomeIngr() {
        return nomeIngr;
    }

    public void setNomeIngr(Ingredienti nomeIngr) {
        this.nomeIngr = nomeIngr;
    }
}
