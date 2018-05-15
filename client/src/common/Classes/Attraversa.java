package common.Classes;

public class Attraversa {
    private Gateway cod_porta;
    private Bambino cf;
    private Gateway data_gate;

    public Attraversa (Gateway cod_porta, Bambino cf, Gateway data_gate){

        this.cod_porta=cod_porta;
        this.cf = cf;
        this.data_gate = data_gate;
    }

    public Gateway getCod_porta() {
        return cod_porta;
    }

    public void setCod_porta(Gateway cod_porta) {
        this.cod_porta = cod_porta;
    }

    public Gateway getData_gate() {
        return data_gate;
    }

    public void setData_gate(Gateway data_gate) {
        this.data_gate = data_gate;
    }
    public  Bambino getCf(){return cf;}

    public void setCf(Bambino cf) {
        this.cf = cf;
    }
}
