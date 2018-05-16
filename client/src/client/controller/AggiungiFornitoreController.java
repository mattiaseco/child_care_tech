package client.controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;

import java.io.IOException;

public class AggiungiFornitoreController extends AnchorPane {

    @FXML
    Button annullabutton;

    @FXML
    Button confermabutton;

    @FXML
    private TextField cfField;
    @FXML
    private TextField nomeField;
    @FXML
    private TextField cognField;
    @FXML
    private TextField indField;
    @FXML
    private TextField telField;
    @FXML
    private TextField partivaField;

    @FXML
    private DatePicker dateField;

    @FXML
    AnchorPane aggiungipane;


    @FXML
    private void returnToTabellePane() throws IOException {

        ((BorderPane) aggiungipane.getParent()).setCenter((Pane) FXMLLoader.load(getClass().getResource("../view/TabellePane.fxml")));

    }



}