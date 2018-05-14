package common.Classes;

public class Contatti {

    private Person persona;
    private String telefono;

    public Contatti (int cod_qr, Person persona, String contatto1, String contatto2){

        this.persona = persona;
        this.telefono = telefono;
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
