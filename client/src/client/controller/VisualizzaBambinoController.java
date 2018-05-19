package client.controller;

import common.Classes.Bambino;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;

import java.io.IOException;


public class VisualizzaBambinoController extends AnchorPane {

    @FXML
    Button annullabutton;

    @FXML AnchorPane visualizzapane;
    @FXML Label nomeLabel;
    @FXML Label cognomeLabel;
    @FXML Label dataLabel;
    @FXML Label cfLabel;
    @FXML Label indirizzoLabel;
    @FXML Label contatto1Label;
    @FXML Label contatto2Label;


    public void inizializza(Bambino bambino){
        nomeLabel.setText(bambino.getNome());
        cognomeLabel.setText(bambino.getCognome());
        dataLabel.setText(String.valueOf(bambino.getData()));
        cfLabel.setText(bambino.getCf());
        indirizzoLabel.setText(bambino.getIndirizzo());
        contatto1Label.setText(bambino.getContatto1());
        contatto2Label.setText(bambino.getContatto2());
    }
    @FXML
    public BorderPane mainpane;


    @FXML
    private void returntotabellepane()throws IOException {


        ((BorderPane)visualizzapane.getParent()).setCenter((Pane)FXMLLoader.load(getClass().getResource("../view/TabellePane.fxml")));

    }

}
