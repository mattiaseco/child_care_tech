package common.Classes;

import java.io.Serializable;

public class Piatto implements Serializable{


    private String nome_p;
    private String tipo;


    public Piatto (String nome_p, String tipo){

        this.nome_p = nome_p;
        this.tipo=tipo;

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

    @Override
    public boolean equals(Object obj) {
        if (obj == this)
            return true;
        if (!(obj instanceof Piatto))
            return false;
        return this.nome_p.equals(((Piatto) obj).nome_p);
    }





}
