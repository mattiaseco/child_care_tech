package common.Classes;

import java.io.Serializable;
import java.time.LocalDate;

public class Menu implements Serializable{

    private String numero;
    private LocalDate data;

    public Menu (String numero,LocalDate data){

        this.numero = numero;
        this.data = data;

    }

    public String getNumero() {
        return numero;
    }
    public void setNumero(String numero) {
        this.numero = numero;
    }

    public LocalDate getData() {
        return data;
    }
    public void setData(LocalDate data) {
        this.data = data;
    }

}
