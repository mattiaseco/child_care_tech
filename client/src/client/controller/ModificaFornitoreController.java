package client.controller;

import client.NamingContextManager;
import common.Classes.Fornitore;
import common.Classes.Genitore;
import common.Interface.iFornitoreDAO;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;

import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDate;

public class ModificaFornitoreController {

    @FXML private TextField nomeField;
    @FXML private TextField cognField;
    @FXML private DatePicker dataField;
    @FXML private TextField cfField;
    @FXML private TextField indField;
    @FXML private TextField partivaField;
    @FXML private TextField telField;
    @FXML private Text alertbox;

    @FXML
    AnchorPane modificapane;
    private Pane tabellePane;
    private TabellePaneController tabellePaneController;


    public void inizializza(Fornitore fornitore, Pane tabellePane,TabellePaneController tabellePaneController){
        nomeField.setText(fornitore.getNome());
        cognField.setText(fornitore.getCognome());
        dataField.setValue(fornitore.getData());
        cfField.setText(fornitore.getCf());
        indField.setText(fornitore.getIndirizzo());
        telField.setText(fornitore.getTelefono());
        partivaField.setText(fornitore.getPartita_iva());
        this.tabellePane = tabellePane;
        this.tabellePaneController = tabellePaneController;

    }

    @FXML
    public void returnToTabellePane()throws IOException {

        ((BorderPane)modificapane.getParent()).setCenter(tabellePane);
        tabellePaneController.refreshTabelle();
    }
    @FXML
    private void modificaFornitore() throws IOException, SQLException {
        alertbox.setText("");

        iFornitoreDAO providersController = NamingContextManager.getProvidersController();

        String nome, cognome, cf, partitaIVA, telefono, indirizzo;
        LocalDate data;

        nome = nomeField.getText();
        cognome = cognField.getText();
        data = dataField.getValue();
        cf = cfField.getText();
        indirizzo = indField.getText();
        telefono =telField.getText();
        partitaIVA = partivaField.getText();

        if(cf == "" || nome == "" || cognome == "" || indirizzo == "" ||  data == null || partitaIVA == "" || telefono == ""){

            alertbox.setText("Attenzione: inserire campi obbligatori (*)");

        }
        else if( cf.length() < 16) {
            alertbox.setText("Attenzione: Codice Fiscale troppo corto !");
        }
        else if ( cf.length() > 17){
            alertbox.setText("Attenzione: Codice Fiscale troppo lungo !");
        }
        else if( partitaIVA.length() < 11) {
            alertbox.setText("Attenzione: Partita IVA troppo corta !");
        }
        else if ( partitaIVA.length() > 12) {
            alertbox.setText("Attenzione: Partita IVA troppo lunga !");
        }
        else {

            providersController.modificaFornitore(partitaIVA,cf,nome,cognome,data,indirizzo, telefono);
            ((BorderPane)modificapane.getParent()).setCenter(tabellePane);
            tabellePaneController.refreshTabelle();
        }


    }
}
