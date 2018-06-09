package common.Classes;

import java.io.Serializable;
import java.time.LocalDate;

public class Gita implements Serializable{

    private int codice_g;
    private String destinazione;
    private int num_pullman;
    private int num_partecipanti;
    private LocalDate data_partenza;
    private LocalDate data_ritorno;
    private Double costo;
    private String descrizione;


    public Gita (int codice_g, String destinazione, int num_pullman, int num_partecipanti,LocalDate data_partenza, LocalDate data_ritorno,Double costo, String descrizione){

        this.codice_g = codice_g;
        this.destinazione = destinazione;
        this.num_partecipanti = num_partecipanti;
        this.num_pullman = num_pullman;
        this.data_partenza=data_partenza;
        this.data_ritorno=data_ritorno;
        this.costo=costo;
        this.descrizione=descrizione;

    }


    public int getCodice_g() {
        return codice_g;
    }
    public void setCodice_g(int codice_g) {
        this.codice_g = codice_g;
    }

    public String getDestinazione() {
        return destinazione;
    }
    public void setDestinazione(String destinazione) {
        this.destinazione = destinazione;
    }

    public int getNum_pullman() {
        return num_pullman;
    }
    public void setNum_pullman(int num_pullman) {
        this.num_pullman = num_pullman;
    }

    public int getNum_partecipanti(){return num_partecipanti;}
    public void setNum_partecipanti(int num_partecipanti){this.num_partecipanti= num_partecipanti;}

    public LocalDate getData_partenza(){return data_partenza;}
    public void setData_partenza(LocalDate data_partenza){this.data_partenza=data_partenza;}

    public LocalDate getData_ritorno() { return data_ritorno;}
    public void setData_ritorno(LocalDate data_ritorno) { this.data_ritorno = data_ritorno;}

    public Double getCosto(){return costo;}
    public void setCosto(Double costo) { this.costo = costo;}

    public String getDescrizione(){return descrizione;}
    public void setDescrizione(String descrizione) { this.descrizione = descrizione;}
}
