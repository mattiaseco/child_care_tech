package common.Classes;

public class Fornitore {
    private String partita_iva;
    private Person persona;
    private String telefono;

    public  Fornitore (String partita_iva, Person persona, String telefono){

        this.partita_iva = partita_iva;
        this.persona = persona;
        this.telefono = telefono;
    }

    public String getPartita_iva() {
        return partita_iva;
    }

    public void setCod_qr(String partita_iva) {
        this.partita_iva = partita_iva;
    }

    public Person getPersona() {
        return persona;
    }

    public void setPersona(Person persona) {
        this.persona = persona;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

}


