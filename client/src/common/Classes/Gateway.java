package common.Classes;
import java.time.LocalDate;

public class Gateway {

    private int cod_porta;
    private LocalDate data_gate;




    public int getCod_porta() {
        return cod_porta;
    }
    public void setCod_porta(int cod_porta) {
        this.cod_porta = cod_porta;
    }
    public LocalDate getData_gate() {
        return data_gate;
    }
    public void setData_gate(LocalDate data_gate) {
        this.data_gate = data_gate;
    }
}
