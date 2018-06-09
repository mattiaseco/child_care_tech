package client.controller;

import client.NamingContextManager;
import common.Classes.Bambino;
import common.Interface.iBambinoDAO;
import javafx.beans.property.SimpleStringProperty;
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
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.awt.*;
import java.io.IOException;
import java.rmi.RemoteException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class RicercaController {

    @FXML private TextField nomeField;
    @FXML private TextField cognomeField;
    @FXML private AnchorPane riercaPane;

    @FXML private Button cercaButton;
    @FXML private Button fineButton;
    @FXML private Text alertbox;


    @FXML
    public TableView<Bambino> bambinoTable;
    @FXML private TableColumn<Bambino, String> cfColumn;
    @FXML private TableColumn<Bambino, String> nomeColumn;
    @FXML private TableColumn<Bambino, String> cognomeColumn;

    private Stage actual;

    private ObservableList<Bambino> kids = FXCollections.observableArrayList();
    private iBambinoDAO kidDAO = NamingContextManager.getKidController();


    @FXML
    private void ricercaGlobale() throws IOException {

        String nome, cognome;

        nome = nomeField.getText();
        cognome = cognomeField.getText();

        resultKids(nome,cognome);


    }

    @FXML
    public void returnToTabellePane() throws IOException {

        Parent root = FXMLLoader.load(getClass().getResource("../view/Anagrafica.fxml"));
        actual = (Stage) fineButton.getScene().getWindow();
        actual.setScene(new Scene(root, fineButton.getScene().getWidth(), fineButton.getScene().getHeight()));
        actual.show();

    }

    private void resultKids(String nome,String cognome){
        initTable();
        initColumns();
    }

    private void initTable(){
        bambinoTable.setItems(kids);
        refreshKidTable();

    }

    private void initColumns(){

        cfColumn.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getCf()));
        nomeColumn.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getNome()));
        cognomeColumn.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getCognome()));
    }


    public void refreshKidTable() {
        List<Bambino> kidsList = new ArrayList<>();
        try {
            kidsList = kidDAO.getAllBambiniNomeCognome(nomeField.getText(),cognomeField.getText());
        } catch(RemoteException ex) {
            System.err.println(ex.getMessage());
            ex.printStackTrace();
        } catch(SQLException ex) {
            System.err.println(ex.getMessage());
            ex.printStackTrace();
        }
        kids.clear();
        kids.addAll(kidsList);
        if(kids.size()==0)
            alertbox.setText("Attenzione: Bambino non trovato!");
        else
            alertbox.setText("");


    }
}
