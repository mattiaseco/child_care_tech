package client.controller;

import common.Classes.Bambino;
import common.Classes.Genitore;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;

import java.io.IOException;

public class VisualizzaGenitoreController extends AnchorPane{
    @FXML
    Button annullabutton;

    @FXML
    AnchorPane visualizzapane;


    @FXML Label nomeLabel;
    @FXML Label cognomeLabel;
    @FXML Label dataLabel;
    @FXML Label cfLabel;
    @FXML Label indirizzoLabel;
    @FXML Label telefonoLabel;

    private Pane tabellePane;
    private TabellePaneController tabellePaneController;


    public void inizializza(Genitore genitore, Pane tabellePane, TabellePaneController tabellePaneController){
        nomeLabel.setText(genitore.getNome());
        cognomeLabel.setText(genitore.getCognome());
        dataLabel.setText(String.valueOf(genitore.getData()));
        cfLabel.setText(genitore.getCf());
        indirizzoLabel.setText(genitore.getIndirizzo());
        telefonoLabel.setText(genitore.getTelefono());
        this.tabellePane = tabellePane;
        this.tabellePaneController = tabellePaneController;

    }



    @FXML
    public void returnToTabellePane()throws IOException {

        ((BorderPane)visualizzapane.getParent()).setCenter(tabellePane);
        tabellePaneController.refreshTabelle();
    }
}
