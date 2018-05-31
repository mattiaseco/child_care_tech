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
import javafx.scene.text.Text;


import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDate;

public class Step1AggiungiGitaController {

    @FXML private TextField numeroField;
    @FXML private TextField destinazioneField;
    @FXML private TextArea noteField;
    @FXML private DatePicker dataPartenzaField;
    @FXML private DatePicker dataRitornoField;
    @FXML private TextField prezzoField;

    @FXML
    AnchorPane gitepane;

    private Pane tabelleGitaPene;
    private TabelleGiteController tabelleGiteController;
    private BorderPane mainpane;
    @FXML private Text alertbox;


    public void inizializza( Pane tabelleGitaPene, TabelleGiteController tabelleGitaController, BorderPane mainpane){

        this.tabelleGitaPene = tabelleGitaPene;
        this.tabelleGiteController = tabelleGitaController;
        this.mainpane = mainpane;
    }


    @FXML
    private void returnToGitePane() throws IOException{


        ((BorderPane)gitepane.getParent()).setCenter(tabelleGitaPene);
        tabelleGiteController.refreshGiteTables();


    }

    @FXML
    private void goToStep2() throws IOException, SQLException {

        iGitaDAO gitaController = NamingContextManager.getTripsController();
        FXMLLoader loader;
        Pane gitepane2;
        int codice_gita;
        String codice_g,destinazione, note;
        LocalDate data_partenza, data_ritorno;
        String prezzo;
        double prezzoGita;


        codice_g = numeroField.getText();
        destinazione = destinazioneField.getText();
        note = noteField.getText();
        data_partenza = dataPartenzaField.getValue();
        data_ritorno = dataRitornoField.getValue();
        prezzo = prezzoField.getText();

        try {
            codice_gita = Integer.parseInt(codice_g);
        }catch (NumberFormatException e){
            alertbox.setText("Attenzione: inserire un numero!");
            return;
        }

        if(gitaController.getAllNumGite().contains(codice_gita)){
            alertbox.setText("Attenzione: codice gita già presente!");
        }
        else if(destinazione.isEmpty() || data_partenza == null || data_ritorno == null || codice_g.isEmpty())
            alertbox.setText("Attenzione: inserire campi obbligatori (*)");
        else if (note.length() > 256 )
            alertbox.setText("Attenzione: nota troppo lunga!");
        else if(data_ritorno.isBefore(data_partenza))
            alertbox.setText("Attenzione: data di ritorno precedente a quella di partenza!");
        else {
            try{
                prezzoGita = Double.parseDouble(prezzo);
            } catch (NumberFormatException e){
                alertbox.setText("Attenzione: inserire un prezzo valido ");
                return;
            }

            gitaController.inserisciGita(codice_gita,destinazione,data_partenza,data_ritorno,prezzoGita,note);
            loader = new FXMLLoader(getClass().getResource("../view/Step2AggiungiGita.fxml"));
            gitepane2= loader.load();
            Step2AggiungiGitaController controller = loader.getController();
            controller.inizializza(tabelleGitaPene,tabelleGiteController,mainpane,codice_gita);
            mainpane.setCenter(gitepane2);
            tabelleGiteController.refreshGiteTables();

        }


    }
}
