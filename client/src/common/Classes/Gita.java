package common.Classes;

public class Gita {

    private String codice_g;
    private String destinazione;
    private int num_pullman;
    private int num_partecipanti;

    public Gita (String codice_g, String destinazione, int num_pullman, int num_partecipanti){

        this.codice_g = codice_g;
        this.destinazione = destinazione;
        this.num_partecipanti = num_partecipanti;
        this.num_pullman = num_pullman;

    }


    public String getCodice_g() {
        return codice_g;
    }
    public void setCodice_g(String codice_g) {
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
}
