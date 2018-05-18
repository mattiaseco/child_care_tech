package common.Classes;

import java.time.LocalDate;

public class Menu {

    private int numero;
    private LocalDate data;

    public Menu (int numero,LocalDate data){

        this.numero = numero;
        this.data = data;

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
