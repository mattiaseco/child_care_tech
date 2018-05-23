package common.Classes;

import java.io.Serializable;

public class Ingredienti implements Serializable {

    private String nome_i;
    private int quantità;
    public Ingredienti (String nome_i,int quantità){

        this.nome_i=nome_i;
        this.quantità = quantità;
    }


    public String getNome_i() {
        return nome_i;
    }
    public void setNome_i(String nome_p) {
        this.nome_i = nome_i;
    }

    public int getQuantità() {
        return quantità;
    }
    public void setQuantità(int quantità) {
        this.quantità = quantità;
    }

}


