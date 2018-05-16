package client.controller;

import client.NamingContextManager;
import common.Interface.iBambinoDAO;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;

import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDate;


public class AggiungiBambinoController extends AnchorPane {

    @FXML
    Button annullabutton;

    @FXML
    Button confermabutton;

    @FXML private TextField cfField;
    @FXML private TextField nomeField;
    @FXML private TextField cognField;
    @FXML private TextField indField;
    @FXML private TextField cont1Field;
    @FXML private TextField cont2Field;

    @FXML private DatePicker dateField;

    @FXML
    AnchorPane aggiungipane;

    @FXML
    private void returnToTabellePane()throws IOException {

        ((BorderPane)aggiungipane.getParent()).setCenter((Pane)FXMLLoader.load(getClass().getResource("../view/TabellePane.fxml")));

        //Parent root= FXMLLoader.load(getClass().getResource("../view/Anagrafica.fxml"));
        //actual =(Stage)annullabutton.getScene().getWindow();
        //actual.setScene(new Scene(root,annullabutton.getScene().getWidth(),annullabutton.getScene().getHeight()));
        //actual.show();
    }

    @FXML
    private void aggiungiPersona() throws IOException, SQLException {

        iBambinoDAO kidController = NamingContextManager.getKidController();
        String cf, nome, cognome, indirizzo, contatto1, contatto2;
        LocalDate data;

        cf = cfField.getText();
        nome = nomeField.getText();
        cognome = cognField.getText();
        data = dateField.getValue();
        indirizzo = indField.getText();
        contatto1 = cont1Field.getText();
        contatto2 = cont2Field.getText();

        if(cf == "" || nome == "" || cognome == "" || indirizzo == "" || contatto1 == "" || data == null){

            //TODO aggiungere alterbox per segnalare un errore
        }
        else if( cf.length() < 16) {
            //TODO segnalare errore "CODICE FISCALE TROPPO CORTO"
        }
        else if ( cf.length() > 17){
            //TODO segnalare errore"CODICE FISCALE TROPPO LUNGO"
        }
        else {
            if(kidController != null) {
                kidController.inserisciBambino(cf, nome, cognome, data, indirizzo, contatto1, contatto2);
                ((BorderPane) aggiungipane.getParent()).setCenter((Pane) FXMLLoader.load(getClass().getResource("../view/TabellePane.fxml")));
            }

        }



    }
}
