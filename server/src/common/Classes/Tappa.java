package common.Classes;

import java.io.Serializable;

public class Tappa implements Serializable{
    private String codice_t;

    public Tappa (String codice_t){

        this.codice_t=codice_t;
    }


    public String getCodice_t() {
        return codice_t;
    }
    public void setCodice_t(String codice_t) {
        this.codice_t = codice_t;
    }

}
