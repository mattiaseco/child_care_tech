package common.Classes;

import java.io.Serializable;
import java.time.LocalDate;

public class Fornitore implements Serializable {
    private String partita_iva;
    private String cf;
    private String nome;
    private String cognome;
    private LocalDate data;
    private String indirizzo;
    private String telefono;

    public  Fornitore (String partita_iva, String cf, String nome, String cognome, LocalDate data, String indirizzo, String telefono){

        this.partita_iva = partita_iva;
        this.cf = cf;
        this.nome = nome;
        this.cognome = cognome;
        this.data = data;
        this.indirizzo = indirizzo;
        this.telefono = telefono;
    }

    public String getPartita_iva() {
        return partita_iva;
    }

    public void setCod_qr(String partita_iva) {
        this.partita_iva = partita_iva;
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

    @Override
    public boolean equals(Object obj) {
        if(obj==this)
            return true;
        if(!(obj instanceof Fornitore))
            return false;
        return this.cf.equals(((Fornitore) obj).cf);
    }

}


