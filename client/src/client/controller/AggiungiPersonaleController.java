package client.controller;

import client.NamingContextManager;
import common.Interface.iPersonaleDAO;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;


import javax.print.attribute.standard.MediaSize;
import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDate;

public class AggiungiPersonaleController extends AnchorPane{
    @FXML
    AnchorPane aggiungipane;

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
    private void aggiungiPersonale() throws IOException, SQLException {

        alertbox.setText("");

        iPersonaleDAO personalController = NamingContextManager.getPersonalController();

        String nome, cognome, cf, indirizzo, telefono;
        LocalDate data;

        nome = nomeField.getText();
        cognome = cognField.getText();
        data = dataField.getValue();
        cf = cfField.getText();
        indirizzo = indField.getText();
        telefono = telField.getText();

        if(cf.isEmpty() || nome.isEmpty() || cognome.isEmpty() || indirizzo.isEmpty() || data == null || telefono.isEmpty()){

            alertbox.setText("Attenzione: inserire campi obbligatori (*)");

        }
        else if( cf.length() < 16) {
            alertbox.setText("Attenzione: Codice Fiscale troppo corto !");
        }
        else if ( cf.length() > 16){
            alertbox.setText("Attenzione: Codice Fiscale troppo lungo !");
        }
        else if ( telefono.length() > 10){
            alertbox.setText("Attenzione: numero di telefono troppo lungo !");
        }
        else {

            personalController.inserisciPersonale(cf,nome,cognome,data,indirizzo,telefono);
            ((BorderPane)aggiungipane.getParent()).setCenter(tabellePane);
            tabellePaneController.refreshTabelle();        }

    }
}
