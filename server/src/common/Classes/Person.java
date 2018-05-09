package common.Classes;

import java.time.LocalDate;


public class Person {

    private String cf;
    private String nome;
    private String cognome;
    private LocalDate data;
    private String indirizzo;

    //TODO manca gestione allergie!!!!

    public Person ( String cf, String nome, String cognome, LocalDate data, String indirizzo){

        this.cf = cf;
        this.nome = nome;
        this.cognome = cognome;
        this.data = data;
        this.indirizzo = indirizzo;

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

}
