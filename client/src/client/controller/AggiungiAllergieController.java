package client.controller;

import common.Classes.Bambino;
import javafx.beans.property.SimpleStringProperty;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.text.Text;

import java.io.IOException;
import java.rmi.RemoteException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AggiungiAllergieController {
    @FXML
    private Button annullabutton;
    @FXML private Button confermabutton;


    @FXML Label nomeLabel;
    @FXML Label cognomeLabel;

    @FXML public TableView<Bambino> intolleranzeTable;
    @FXML private TableColumn<Bambino, String> intolleranzaColumn;


    @FXML private Text alertbox;
    @FXML private AnchorPane allergiepane;


    private void initColumns() {
        intolleranzaColumn.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getCf()));

    }
    public void refreshTabella(){
        List<Bambino> kidsList = new ArrayList<>();
        try {
            kidsList = kidDAO.getAllBambini();
        } catch(RemoteException ex) {
            System.err.println(ex.getMessage());
            ex.printStackTrace();
        } catch(SQLException ex) {
            System.err.println(ex.getMessage());
            ex.printStackTrace();
        }
        kids.clear();
        kids.addAll(kidsList);
    }

    @FXML
    public void returnToTabellePane()throws IOException {
        ((BorderPane)allergiepane.getParent()).setCenter(tabellePane);
        allergiepane.refreshTabella();
    }
    @FXML
    private void aggiornaIntolleranze() throws IOException, SQLException {

        alertbox.setText("");



    }
}
