package client.controller;

import client.NamingContextManager;
import common.Classes.Bambino;
import common.Interface.iBambinoDAO;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;

import java.io.IOException;
import java.rmi.RemoteException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AggiungiAllergieController {
    @FXML Button annullabutton;
    @FXML Button confermabutton;
    @FXML Label nomeLabel;
    @FXML Label cognomeLabel;
    @FXML Text alertbox;
    @FXML AnchorPane allergiepane;



    @FXML public TableView<Bambino> intolleranzeTable;
    @FXML private TableColumn<Bambino, String> intolleranzaColumn;
    @FXML private TableColumn<Bambino, CheckBox> selectColumn;


    private ObservableList<Bambino> kids = FXCollections.observableArrayList();

    private iBambinoDAO kidDAO;

    public void initialize() {
        kidDAO = NamingContextManager.getKidController();
        initTables();
        initColumns();
    }
    private void initTables() {
        intolleranzeTable.setItems(kids);
        refreshIntolleranzeTable();
    }
    private void initColumns() {
        intolleranzaColumn.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getCf()));
        //selectColumn.setCellValueFactory();
    }
    public void refreshIntolleranzeTable(){
        /*List<Bambino> kidsList = new ArrayList<>();
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
        kids.addAll(kidsList);*/
    }

    @FXML
    public void returnToTabellePane()throws IOException {
        ((BorderPane)allergiepane.getParent()).setCenter(FXMLLoader.load(getClass().getResource("../view/TabellePane.fxml")));
    }
    @FXML
    private void aggiornaIntolleranze() throws IOException, SQLException {

        alertbox.setText("");



    }
}
