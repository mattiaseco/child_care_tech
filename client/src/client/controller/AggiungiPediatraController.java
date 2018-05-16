package client.controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;

import java.io.IOException;

public class AggiungiPediatraController {

    @FXML
    AnchorPane aggiungipane;


    @FXML
    private void returnToTabellePane() throws IOException {

        ((BorderPane) aggiungipane.getParent()).setCenter((Pane) FXMLLoader.load(getClass().getResource("../view/TabellePane.fxml")));

    }
    @FXML
    private void aggiungiPediatra() throws IOException {

        ((BorderPane) aggiungipane.getParent()).setCenter((Pane) FXMLLoader.load(getClass().getResource("../view/TabellePane.fxml")));

    }
}
