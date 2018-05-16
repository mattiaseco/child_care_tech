package client.controller;

import client.NamingContextManager;
import common.Interface.iFornitoreDAO;
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

public class ModificaFornitoreController {

    @FXML private TextField nomeField;
    @FXML private TextField cognField;
    @FXML private DatePicker dataField;
    @FXML private TextField cfField;
    @FXML private TextField indField;
    @FXML private TextField partivaField;
    @FXML private TextField telField;

    @FXML
    AnchorPane modificapane;

    @FXML
    private void returnToTabellePane()throws IOException {

        ((BorderPane)modificapane.getParent()).setCenter((Pane) FXMLLoader.load(getClass().getResource("../view/TabellePane.fxml")));

    }
    @FXML
    private void modificaFornitore() throws IOException, SQLException {

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

            //TODO aggiungere alterbox per segnalare un errore "CAMPI VUOTI"

        }
        else if( cf.length() < 16) {
            //TODO segnalare errore "CODICE FISCALE TROPPO CORTO"
        }
        else if ( cf.length() > 17){
            //TODO segnalare errore"CODICE FISCALE TROPPO LUNGO"
        }
        else if( partitaIVA.length() < 11) {
            //TODO segnalare errore "PARTITA IVA TROPPO CORTA"
        }
        else if ( partitaIVA.length() > 12) {
            //TODO segnalare errore "PARTITA IVA TROPPO LUNGA"
        }
        else {

            providersController.modificaFornitore(partitaIVA,cf,nome,cognome,data,indirizzo, telefono);
            ((BorderPane) modificapane.getParent()).setCenter((Pane) FXMLLoader.load(getClass().getResource("../view/TabellePane.fxml")));
        }


    }
}
