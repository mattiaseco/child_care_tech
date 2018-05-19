package client.controller;


import common.Classes.Personale;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;

import java.io.IOException;

public class VisualizzaPersonaleController {
    @FXML
    Button annullabutton;

    @FXML
    AnchorPane visualizzapane;

    @FXML
    Label nomeLabel;
    @FXML Label cognomeLabel;
    @FXML Label dataLabel;
    @FXML Label cfLabel;
    @FXML Label indirizzoLabel;
    @FXML Label telefonoLabel;

    private Pane tabellePane;
    private TabellePaneController tabellePaneController;



    public void inizializza(Personale personale, Pane tabellePane, TabellePaneController tabellePaneController){
        nomeLabel.setText(personale.getNome());
        cognomeLabel.setText(personale.getCognome());
        dataLabel.setText(String.valueOf(personale.getData()));
        cfLabel.setText(personale.getCf());
        indirizzoLabel.setText(personale.getIndirizzo());
        telefonoLabel.setText(personale.getTelefono());
        this.tabellePane = tabellePane;
        this.tabellePaneController = tabellePaneController;

    }



    @FXML
    private void returnToTabellePane()throws IOException {

        ((BorderPane)visualizzapane.getParent()).setCenter(tabellePane);
        tabellePaneController.refreshTabelle();

    }
}
