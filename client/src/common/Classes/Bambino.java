package common.Classes;

import java.io.Serializable;

public class Bambino implements Serializable{

    private int cod_qr;
    private Person persona;
    private String contatto1;
    private String contatto2;

    public Bambino (int cod_qr, Person persona, String contatto1, String contatto2){

        this.cod_qr = cod_qr;
        this.persona = persona;
        this.contatto1 = contatto1;
        this.contatto2 = contatto2;
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

}
