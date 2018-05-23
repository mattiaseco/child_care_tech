package common.Classes;

import java.io.Serializable;

public class Contiene implements Serializable{

    private Piatto nome_p;
    private Ingredienti nome_i;

    public Contiene(Piatto nome_p, Ingredienti nome_i){

        this.nome_i=nome_i;
        this.nome_p = nome_p;
    }

    public Piatto getNome_p() {
        return nome_p;
    }

    public void setNome_p(Piatto nome_p) {
        this.nome_p = nome_p;
    }

    public Ingredienti getNome_i() {
        return nome_i;
    }

    public void setNome_i(Ingredienti nome_i) {
        this.nome_i = nome_i;
    }

}
