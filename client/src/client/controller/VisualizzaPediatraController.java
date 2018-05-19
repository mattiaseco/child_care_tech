package client.controller;

import common.Classes.Pediatra;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;

import java.io.IOException;

public class VisualizzaPediatraController {
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


    public void inizializza(Pediatra pediatra){
        nomeLabel.setText(pediatra.getNome());
        cognomeLabel.setText(pediatra.getCognome());
        dataLabel.setText(String.valueOf(pediatra.getData()));
        cfLabel.setText(pediatra.getCf());
        indirizzoLabel.setText(pediatra.getIndirizzo());
        telefonoLabel.setText(pediatra.getTelefono());

    }



    @FXML
    private void returnToTabellePane()throws IOException {

        ((BorderPane)visualizzapane.getParent()).setCenter((Pane) FXMLLoader.load(getClass().getResource("../view/TabellePane.fxml")));

    }
}
