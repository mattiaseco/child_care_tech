package common.Classes;

public class Pediatra {

    private int cod_qr;
    private Person persona;
    private String telefono;

    public  Pediatra (int cod_qr, Person persona, String telefono){

        this.cod_qr = cod_qr;
        this.persona = persona;
        this.telefono = telefono;
    }

    public int getCod_qr() {
        return cod_qr;
    }

    public void setCod_qr(int cod_qr) {
        this.cod_qr = cod_qr;
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
