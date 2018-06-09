package common;

import java.io.Serializable;

public class SocketResponse implements Serializable {

    public boolean eccezione;

    public Serializable returnValue;

    public SocketResponse(boolean eccezione, Serializable returnValue){
        this.eccezione = eccezione;
        this.returnValue = returnValue;
    }
}
