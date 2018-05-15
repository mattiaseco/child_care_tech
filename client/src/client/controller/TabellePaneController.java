package client.controller;

import common.Classes.Bambino;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.util.Callback;

import java.io.IOException;


public class TabellePaneController {

    @FXML
    private TabPane tabellePane;
    @FXML
    private Tab kidTab;
    @FXML
    private Tab parentTab;
    //finiscili

    @FXML
    private TableView bambinotable;
    @FXML
    private TableColumn fcColumn;
    @FXML
    private TableView genitoretable;
    @FXML
    private TableView personaletable;
    @FXML
    private TableView pediatratable;
    @FXML
    private TableView fornitoretable;
    @FXML
    private TableView contattitable;

    private ObservableList<Bambino> kids = FXCollections.observableArrayList();

    @FXML
    public void initialize() {
        bambinotable.setItems(kids);

    }

/*
    @FXML
    AnchorPane aggiungipane;

    @FXML
    private void returntoanagrafica()throws IOException {

        aggiungipane= FXMLLoader.load(getClass().getResource("../view/Anagrafica.fxml"));
    }
*/

}
