package common.Classes;

import java.io.Serializable;
import java.time.LocalDate;

public class Menu implements Serializable{

    private int numero;
    private LocalDate data;
    private Piatto piatto1;
    private Piatto piatto2;
    private Piatto piatto3;

    public Menu (int numero,LocalDate data,Piatto piatto1,Piatto piatto2,Piatto piatto3){

        this.numero = numero;
        this.data = data;
        this.piatto1=piatto1;
        this.piatto2=piatto2;
        this.piatto3=piatto3;

    }

    public Piatto getPiatto1() {return piatto1;}
    public void setPiatto1(Piatto piatto1) {this.piatto1 = piatto1;}

    public Piatto getPiatto2() {
        return piatto2;
    }
    public void setPiatto2(Piatto piatto2) {
        this.piatto2 = piatto2;
    }

    public Piatto getPiatto3() {
        return piatto3;
    }
    public void setPiatto3(Piatto piatto3) {
        this.piatto3 = piatto3;
    }

    public int getNumero() {
        return numero;
    }
    public void setNumero(int numero) {
        this.numero = numero;
    }

    public LocalDate getData() {
        return data;
    }
    public void setData(LocalDate data) {
        this.data = data;
    }
}
