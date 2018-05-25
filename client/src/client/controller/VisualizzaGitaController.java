package client.controller;

import client.NamingContextManager;

import common.Classes.Gita;
import common.Interface.iGitaDAO;
import javafx.fxml.FXML;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;

import javax.crypto.spec.DESedeKeySpec;
import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDate;

public class VisualizzaGitaController extends AnchorPane{

    @FXML private AnchorPane visualizzaPane;

    @FXML private Label codgitaLable;
    @FXML private Label destLable;
    @FXML private Label noteLable;
    @FXML private Label datapartLable;
    @FXML private Label dataritLable;
    @FXML private Label prezzoLable;
    @FXML private Label numpartLable;
    @FXML private Label numpullmanLable;

    @FXML private TabelleGiteController tabellePaneController;
    @FXML private Pane tabellePane;


    public void inizializza(TabelleGiteController tabellePaneController, Pane tabellePane, Gita gita){

        codgitaLable.setText(Integer.toString(gita.getCodice_g()));
        destLable.setText(gita.getDestinazione());
        noteLable.setText(gita.getDescrizione());
        datapartLable.setText(gita.getData_partenza().toString());
        dataritLable.setText(gita.getData_ritorno().toString());
        prezzoLable.setText(Double.toString(gita.getCosto()));
        numpartLable.setText(Integer.toString(gita.getNum_partecipanti()));
        numpullmanLable.setText(Integer.toString(gita.getNum_pullman()));


        this.tabellePane = tabellePane;
        this.tabellePaneController = tabellePaneController;


    }

    @FXML
    public void returnToGitePane() throws IOException {

        ((BorderPane)visualizzaPane.getParent()).setCenter(tabellePane);
        tabellePaneController.refreshTripsTable();
    }

}
