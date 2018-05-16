package client.controller;

import client.NamingContextManager;
import common.Interface.iGenitoreDAO;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.DatePicker;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;

import java.awt.*;
import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDate;

public class AggiungiGenitoreController {
    @FXML
    AnchorPane aggiungipane;
    @FXML private TextField nomeField;
    @FXML private TextField cognField;
    @FXML private TextField cfField;
    @FXML private TextField telField;
    @FXML private TextField indField;
    @FXML private DatePicker dataField;

    @FXML
    private void returnToTabellePane() throws IOException {

        ((BorderPane) aggiungipane.getParent()).setCenter((Pane) FXMLLoader.load(getClass().getResource("../view/TabellePane.fxml")));

    }
    @FXML
    private void aggiungiGenitore() throws IOException, SQLException {

        iGenitoreDAO parentsControll = NamingContextManager.getParentsController();

        String nome, cognome, indirizzo, cf, telefono;
        LocalDate data;

        nome = nomeField.getText();
        cognome = cognField.getText();
        data = dataField.getValue();
        cf = cfField.getText();
        indirizzo = indField.getText();
        telefono = telField.getText();

        if(cf == "" || nome == "" || cognome == "" || indirizzo == "" || data == null){

            //TODO aggiungere alterbox per segnalare un errore

        }
        else {

            parentsControll.inserisciGenitore(cf,nome,cognome,data,indirizzo,telefono);

            ((BorderPane) aggiungipane.getParent()).setCenter((Pane) FXMLLoader.load(getClass().getResource("../view/TabellePane.fxml")));
        }
    }
}
