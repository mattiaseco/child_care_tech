package common.Classes;

import java.io.Serializable;
import java.time.LocalDate;

public class Personale {
    private String cod_qr;
    private String cf;
    private String nome;
    private String cognome;
    private LocalDate data;
    private String indirizzo;
    private String telefono;

    public  Personale (String cod_qr, String cf, String nome, String cognome, LocalDate data, String indirizzo, String telefono){

        this.cod_qr = cod_qr;
        this.cf = cf;
        this.nome = nome;
        this.cognome = cognome;
        this.data = data;
        this.indirizzo = indirizzo;
        this.telefono = telefono;
    }

    public String getCod_qr() {
        return cod_qr;
    }

    public void setCod_qr(String cod_qr) {
        this.cod_qr = cod_qr;
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

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

}
