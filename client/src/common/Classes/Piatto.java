package common.Classes;

import java.io.Serializable;

public class Piatto implements Serializable{

    private String nome_p;
    private String tipo;
    private int quantità;
    public Piatto (String nome_p,String tipo,int quantità){

        this.nome_p=nome_p;
        this.tipo = tipo;
        this.quantità=quantità;
    }


    public String getNome_p() {
        return nome_p;
    }
    public void setNome_p(String nome_p) {
        this.nome_p = nome_p;
    }

    public String getTipo() {
        return tipo;
    }
    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public int getQuantità() {
        return quantità;
    }
    public void setQuantità(int quantità) {
        this.quantità = quantità;
    }



}
