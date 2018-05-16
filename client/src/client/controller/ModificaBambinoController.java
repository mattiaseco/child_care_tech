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

import java.awt.*;
import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDate;


public class ModificaBambinoController extends  AnchorPane{

    @FXML private TextField nomeField;
    @FXML private TextField cognField;
    @FXML private DatePicker dataField;
    @FXML private TextField cfField;
    @FXML private TextField indField;
    @FXML private TextField cont1Field;
    @FXML private TextField cont2Field;

    @FXML
    Button annullabutton;

    @FXML
    Button confermabutton;

    @FXML AnchorPane modificapane;

    @FXML
    private void returnToTabellePane()throws IOException {

        ((BorderPane)modificapane.getParent()).setCenter((Pane)FXMLLoader.load(getClass().getResource("../view/TabellePane.fxml")));

    }
    @FXML
    private void modificaPersona() throws IOException, SQLException {

        iBambinoDAO kidController = NamingContextManager.getKidController();

        String nome, cognome, cf, indirizzo, contatto1, contatto2;
        LocalDate data;

        nome= nomeField.getText();
        cognome = cognField.getText();
        cf = cfField.getText();
        indirizzo = indField.getText();
        contatto1 = cont1Field.getText();
        contatto2 = cont2Field.getText();
        data = dataField.getValue();

        if(cf == "" || nome == "" || cognome == "" || indirizzo == "" || data == null || contatto1 == ""){

            //TODO aggiungere alterbox per segnalare un errore

        }
        else if( cf.length() < 16) {
            //TODO segnalare errore "CODICE FISCALE TROPPO CORTO"
        }
        else if ( cf.length() > 17){
            //TODO segnalare errore"CODICE FISCALE TROPPO LUNGO"
        }
        else {
            kidController.modificaBambino(cf, nome, cognome, data, indirizzo, contatto1, contatto2);
            ((BorderPane) modificapane.getParent()).setCenter((Pane) FXMLLoader.load(getClass().getResource("../view/TabellePane.fxml")));
        }



    }

}
