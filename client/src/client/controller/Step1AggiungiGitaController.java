package client.controller;

import client.NamingContextManager;
import common.Interface.iGitaDAO;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;


import java.io.IOException;
import java.time.LocalDate;

public class Step1AggiungiGitaController {

    @FXML private TextField destinazioneField;
    @FXML private TextArea noteField;
    @FXML private DatePicker dataPartenzaField;
    @FXML private DatePicker dataRitornoField;
    @FXML private TextField prezzoField;

    @FXML
    private AnchorPane gitepane;

    private Pane tabelleGitaPene;
    private TabelleGiteController tabelleGiteController;
    private BorderPane mainpane;

   private void inizializza( Pane tabelleGitaPene, TabelleGiteController tabelleGitaController, BorderPane mainpane){

       this.tabelleGitaPene = tabelleGitaPene;
       this.tabelleGiteController = tabelleGitaController;
       this.mainpane = mainpane;
   }


    @FXML
    private void returnToGitePane() throws IOException{

        ((BorderPane)gitepane.getParent()).setCenter(tabelleGitaPene);
       // tabelleGiteController.refreshGiteTables();

    }

    @FXML
    private void goToStep2() throws IOException {

        iGitaDAO gitaController = NamingContextManager.getTripsController();

        FXMLLoader loader;
        Pane gitepane;

        String destinazione, note;
        LocalDate data_partenza, data_ritorno;
        Double prezzo;

        destinazione = destinazioneField.getText();
        note = noteField.getText();
        data_partenza = dataPartenzaField.getValue();
        data_ritorno = dataRitornoField.getValue();
        prezzo = Double.parseDouble(prezzoField.getText());

        if(destinazione.isEmpty() || note.isEmpty() || data_partenza == null || data_ritorno == null || prezzo.toString().isEmpty()|| prezzo.isNaN()){

            //TODO aggiungere alterbox "Riempire campi obbligatori"

        }
        else if (note.length() > 256 ){

            //TODO errore "nota troppo lunga"

        }
        else {


            loader = new FXMLLoader(getClass().getResource("../view/Step2AggiungiGita.fxml"));
            gitepane= loader.load();
            mainpane.setCenter(gitepane);

        }


    }
}
