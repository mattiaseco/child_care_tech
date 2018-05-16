package client.controller;

import client.NamingContextManager;
import common.Interface.iPediatraDAO;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;

import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.concurrent.TimeoutException;

public class AggiungiPediatraController {

    @FXML
    AnchorPane aggiungipane;

    @FXML private TextField nomeField;
    @FXML private TextField cognField;
    @FXML private TextField cfField;
    @FXML private TextField indField;
    @FXML private TextField telField;
    @FXML private DatePicker dataField;


    @FXML
    private void returnToTabellePane() throws IOException {

        ((BorderPane) aggiungipane.getParent()).setCenter((Pane) FXMLLoader.load(getClass().getResource("../view/TabellePane.fxml")));

    }
    @FXML
    private void aggiungiPediatra() throws IOException, SQLException {

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

            //TODO aggiungere alterbox per segnalare un errore

        }
        else if( cf.length() < 16) {
            //TODO segnalare errore "CODICE FISCALE TROPPO CORTO"
        }
        else if ( cf.length() > 17){
            //TODO segnalare errore"CODICE FISCALE TROPPO LUNGO"
        }
        else {

            pediatraController.inserisciPediatra(cf, nome,cognome,data,indirizzo,telefono);
            ((BorderPane) aggiungipane.getParent()).setCenter((Pane) FXMLLoader.load(getClass().getResource("../view/TabellePane.fxml")));
        }


    }
}
