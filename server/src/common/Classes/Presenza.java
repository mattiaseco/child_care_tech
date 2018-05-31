package common.Classes;

import java.io.Serializable;

public class Presenza implements Serializable {
    private Bambino cf;


    public Presenza (Bambino cf){
        this.cf = cf;

    }

    public  Bambino getCf(){return cf;}

    public void setCf(Bambino cf) {
        this.cf = cf;
    }

    public boolean equals(Object obj) {
        if(obj==this)
            return true;
        if(!(obj instanceof Bambino))
            return false;
        return this.cf.equals(((Bambino) obj).getCf());
    }
}
