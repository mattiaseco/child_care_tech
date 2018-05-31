package client.controller;

import client.NamingContextManager;
import common.Classes.Bambino;
import common.Interface.iBambinoDAO;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.awt.*;
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

    private Stage actual;

    private ObservableList<Bambino> kids = FXCollections.observableArrayList();
    private iBambinoDAO bambinoDAO = NamingContextManager.getKidController();


    @FXML
    private void ricercaGlobale() throws IOException {

        searchKids();



    }

    @FXML
    private void returnToTabellePane() throws IOException {

        Parent root = FXMLLoader.load(getClass().getResource("../view/Anagrafica.fxml"));
        actual = (Stage) fineButton.getScene().getWindow();
        actual.setScene(new Scene(root, fineButton.getScene().getWidth(), fineButton.getScene().getHeight()));
        actual.show();

    }

    private void searchKids(){

        String nome, cognome;

        nome = nomeField.getText();
        cognome = cognomeField.getText();



    }


}
