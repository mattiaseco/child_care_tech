package client.controller;

import common.Classes.Fornitore;
import common.Classes.Genitore;
import common.Classes.Personale;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;

import java.io.IOException;

public class VisualizzaFornitoreController extends AnchorPane {
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
    @FXML Label partitaIvaLabel;

    private Pane tabellePane;
    private TabellePaneController tabellePaneController;

    public void inizializza(Fornitore fornitore, Pane tabellePane, TabellePaneController tabellePaneController){
        nomeLabel.setText(fornitore.getNome());
        cognomeLabel.setText(fornitore.getCognome());
        dataLabel.setText(String.valueOf(fornitore.getData()));
        cfLabel.setText(fornitore.getCf());
        indirizzoLabel.setText(fornitore.getIndirizzo());
        telefonoLabel.setText(fornitore.getTelefono());
        partitaIvaLabel.setText(fornitore.getPartita_iva());

        this.tabellePane = tabellePane;
        this.tabellePaneController = tabellePaneController;

    }


    @FXML
    public void returnToTabellePane()throws IOException {

        ((BorderPane)visualizzapane.getParent()).setCenter(tabellePane);
        tabellePaneController.refreshTabelle();
    }
}
