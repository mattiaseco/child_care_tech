package common.Classes;

import java.io.Serializable;
import java.time.LocalDate;

public class Bambino implements Serializable{

    private String cf;
    private String nome;
    private String cognome;
    private LocalDate data;
    private String indirizzo;
    private String contatto1;
    private String contatto2;

    public Bambino (String cf, String nome, String cognome, LocalDate data, String indirizzo, String contatto1, String contatto2){

        this.cf = cf;
        this.nome = nome;
        this.cognome = cognome;
        this.data = data;
        this.indirizzo = indirizzo;
        this.contatto1 = contatto1;
        this.contatto2 = contatto2;
    }


    public String getCf() {
        return cf;
    }
    public void setCf(String cf) {
        this.cf = cf;
    }

    public String getNome() {
        return nome;
    }
    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCognome() {
        return cognome;
    }
    public void setCognome(String cognome) {
        this.cognome = cognome;
    }

    public LocalDate getData() {
        return data;
    }
    public void setData(LocalDate data) {
        this.data = data;
    }

    public String getIndirizzo() {
        return indirizzo;
    }
    public void setIndirizzo(String indirizzo) {
        this.indirizzo = indirizzo;
    }

    public String getContatto1() {
        return contatto1;
    }
    public void setContatto1(String contatto1) {
        this.contatto1 = contatto1;
    }

    public String getContatto2() {
        return contatto2;
    }
    public void setContatto2(String contatto2) {
        this.contatto2 = contatto2;
    }

    @Override
    public boolean equals(Object obj) {
        if(obj==this)
            return true;
        if(!(obj instanceof Bambino))
            return false;
        return this.cf.equals(((Bambino) obj).cf);
    }

}
