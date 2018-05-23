package common.Classes;

import java.io.Serializable;

public class Intolleranze implements Serializable{

    private Piatto nomePiatto;
    private Ingredienti nomeIngr;

    public Intolleranze (Piatto nomePiatto, Ingredienti nomeIngr){

        this.nomePiatto = nomePiatto;
        this.nomeIngr = nomeIngr;
    }

    public Piatto getNomePiatto() {
        return nomePiatto;
    }

    public void setNomePiatto(Piatto nomePiatto) {
        this.nomePiatto = nomePiatto;
    }

    public Ingredienti getNomeIngr() {
        return nomeIngr;
    }

    public void setNomeIngr(Ingredienti nomeIngr) {
        this.nomeIngr = nomeIngr;
    }
}
