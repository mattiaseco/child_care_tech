package client.controller;

import common.Classes.Bambino;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;

import java.io.IOException;

public class RicercaController {

    @FXML private TextField nomeField;
    @FXML private TextField cognomeField;
    @FXML private AnchorPane riercaPane;

    @FXML private Button cercaButton;
    @FXML private Button fineButton;

    @FXML
    public TableView<Bambino> bambinoTable;
    @FXML private TableColumn<Bambino, String> cfColumn;
    @FXML private TableColumn<Bambino, String> nomeColumn;
    @FXML private TableColumn<Bambino, String> cognomeColumn;

    @FXML
    private void ricercaGlobale() throws IOException {



    }

    @FXML
    private void returnToTabellePane() throws IOException {



    }


}
