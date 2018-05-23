package client.controller;

import client.NamingContextManager;

import common.Interface.iGitaDAO;
import javafx.fxml.FXML;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;

import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDate;

public class VisualizzaGitaController extends AnchorPane{

    @FXML private AnchorPane aggiungiGitePane;

    @FXML private TextField destField;
    @FXML private TextArea descgitaField;
    @FXML private DatePicker datapartField;
    @FXML private DatePicker dataritField;
    @FXML private TextField costoField;
    @FXML private TextField numpartField;
    @FXML private TextField numpullmanField;

    @FXML private TabelleGiteController tabellePaneController;
    @FXML private Pane tabellePane;

    public void inizializza(TabelleGiteController tabellePaneController, Pane tabellePane){

        this.tabellePane = tabellePane;
        this.tabellePaneController = tabellePaneController;

    }

    @FXML
    public void returnToGitePane() throws IOException {

        ((BorderPane)aggiungiGitePane.getParent()).setCenter(tabellePane);
        tabellePaneController.refreshTripsTable();
    }

    private void goToGitePane() throws IOException, SQLException{

        iGitaDAO gitaController = NamingContextManager.getTripsController();

        String destinazione, descrizione, costo;
        int num_partecipanti, num_pullman;
        LocalDate data_rit, data_part;


        destinazione = destField.getText();
        descrizione = descgitaField.getText();
        costo = costoField.getText();
        num_partecipanti = Integer.valueOf(numpartField.getText());
        num_pullman = Integer.valueOf(numpullmanField.getText());
        data_part = datapartField.getValue();
        data_rit = dataritField.getValue();

        gitaController.inserisciGita(destinazione, num_pullman,num_partecipanti, data_part,data_rit, costo,descrizione);
        tabellePaneController.refreshTripsTable();
        ((BorderPane)aggiungiGitePane.getParent()).setCenter(tabellePane);


    }
}
