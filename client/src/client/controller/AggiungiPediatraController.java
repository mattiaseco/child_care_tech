package client.controller;

import client.NamingContextManager;
import common.Interface.iPediatraDAO;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;

import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.concurrent.TimeoutException;

public class AggiungiPediatraController {

    @FXML AnchorPane aggiungipane;

    @FXML private TextField nomeField;
    @FXML private TextField cognField;
    @FXML private TextField cfField;
    @FXML private TextField indField;
    @FXML private TextField telField;
    @FXML private DatePicker dataField;

    @FXML private Text alertbox;
    private TabellePaneController tabellePaneController;
    private Pane tabellePane;

    public void inizializza(Pane tabellePane, TabellePaneController tabellePaneController){
        this.tabellePane = tabellePane;
        this.tabellePaneController = tabellePaneController;
    }


    @FXML
    public void returnToTabellePane() throws IOException {

        ((BorderPane)aggiungipane.getParent()).setCenter(tabellePane);
        tabellePaneController.refreshTabelle();
    }
    @FXML
    private void aggiungiPediatra() throws IOException, SQLException {

        alertbox.setText("");

        iPediatraDAO pediatraController = NamingContextManager.getPediatraController();

        String nome, cognome, cf, indirizzo, telefono;
        LocalDate data;

        nome = nomeField.getText();
        cognome = cognField.getText();
        data = dataField.getValue();
        cf = cfField.getText();
        indirizzo = indField.getText();
        telefono = telField.getText();

        if(cf == "" || nome == "" || cognome == "" || indirizzo == "" || data == null || telefono == ""){

            alertbox.setText("Attenzione: inserire campi obbligatori (*)");

        }
        else if( cf.length() < 16) {
            alertbox.setText("Attenzione: Codice Fiscale troppo corto !");
        }
        else if ( cf.length() > 17){
            alertbox.setText("Attenzione: Codice Fiscale troppo lungo !");
        }
        else {

            pediatraController.inserisciPediatra(cf, nome,cognome,data,indirizzo,telefono);
            ((BorderPane)aggiungipane.getParent()).setCenter(tabellePane);
            tabellePaneController.refreshTabelle();
        }


    }
}
