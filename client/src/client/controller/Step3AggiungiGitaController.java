package client.controller;

import client.NamingContextManager;
import common.Classes.Pullman;
import common.Interface.iGitaDAO;
import common.Interface.iPullmanDAO;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.css.SimpleStyleableObjectProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import javafx.scene.control.TableColumn;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;

import java.io.IOException;
import java.rmi.RemoteException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class Step3AggiungiGitaController {

    @FXML
    private Label totalePullmanPrenotatiLabel;
    @FXML
    private Button removeButton;
    @FXML
    private Button addButton;
    @FXML
    AnchorPane gitepane3;

    private Pane tabelleGitaPene;
    private TabelleGiteController tabelleGiteController;
    private BorderPane mainpane;

    @FXML public TableView<Pullman> pullmanDispTable;
    @FXML private TableColumn<Pullman, String> targaDispColumn;
    @FXML private TableColumn<Pullman, Integer> capienzaDispColumn;

    @FXML public TableView<Pullman> pullmanPrenotatiTable;
    @FXML private TableColumn<Pullman, String> targaPrenotatiColumn;
    @FXML private TableColumn<Pullman, Integer> capienzaPrenotatiColumn;

    private ObservableList<Pullman> pullmanDisp = FXCollections.observableArrayList();
    private ObservableList<Pullman> pullmanPrenotati = FXCollections.observableArrayList();
    private iPullmanDAO pullmandispDAO;
    private iPullmanDAO pullmanpreDAO;
    private int codice_g;

    private int totPullmanPrenotati;

    public void initialize() {
        totalePullmanPrenotatiLabel.setText("0");
        pullmandispDAO = NamingContextManager.getPullmanController();
        pullmanpreDAO = NamingContextManager.getPullmanController();

        initColumns();
        initTable();

    }
    private void initColumns(){


        List<Pullman> tempPullman = new ArrayList<>();
        targaDispColumn.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getTarga()));
        capienzaDispColumn.setCellValueFactory(data -> new SimpleObjectProperty<>(data.getValue().getCapienza()));
        targaPrenotatiColumn.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getTarga()));
        capienzaPrenotatiColumn.setCellValueFactory(data -> new SimpleObjectProperty<>(data.getValue().getCapienza()));
        pullmanPrenotatiTable.setItems(pullmanPrenotati);
        pullmanDispTable.setItems(pullmanDisp);

        try {
            tempPullman = pullmandispDAO.getAllPullman();
        } catch(RemoteException ex) {
            System.err.println(ex.getMessage());
            ex.printStackTrace();
        } catch(SQLException ex) {
            System.err.println(ex.getMessage());
            ex.printStackTrace();
        }
        pullmanDisp.clear();
        pullmanPrenotati.clear();
        pullmanDisp.addAll(tempPullman);


    }

    private void initTable() {
        removeButton.setDisable(true);
        addButton.setDisable(true);

        pullmanPrenotatiTable.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
            if (newSelection != null) {
                removeButton.setDisable(false);
                addButton.setDisable(false);
            }
            else {
                removeButton.setDisable(true);
                addButton.setDisable(true);
            }
        });
        pullmanDispTable.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
            if (newSelection != null) {
                removeButton.setDisable(false);
                addButton.setDisable(false);
            }
            else {
                removeButton.setDisable(true);
                addButton.setDisable(true);
            }
        });
    }

    /*public void refreshPullmanTable() {

        List<Pullman> pullmanList = new ArrayList<>();
        try{
            pullmanList = pullmanDAO.getAllPullman();
        } catch(RemoteException ex) {
            System.err.println(ex.getMessage());
            ex.printStackTrace();
        } catch(SQLException ex) {
            System.err.println(ex.getMessage());
            ex.printStackTrace();
        }
        pullman.clear();
        pullman.addAll(pullmanList);

    }*/


    public void inizializza(Pane tabelleGitaPene, TabelleGiteController tabelleGitaController, BorderPane mainpane,int codice_g){

        this.tabelleGitaPene = tabelleGitaPene;
        this.tabelleGiteController = tabelleGitaController;
        this.mainpane = mainpane;
        this.codice_g = codice_g;
    }


    @FXML
    private void returnToGitePane() throws IOException, SQLException {

        iGitaDAO gitaController = NamingContextManager.getTripsController();
        gitaController.cancellaGita(codice_g);
        ((BorderPane)gitepane3.getParent()).setCenter(tabelleGitaPene);
        tabelleGiteController.refreshGiteTables();

    }
    public void goToAggiungiPullman() throws IOException {

        Pane aggiungipullmanpane;
        FXMLLoader loader;

        loader = new FXMLLoader(getClass().getResource("../view/AggiungiPullman.fxml"));
        aggiungipullmanpane= loader.load();
        AggiungiPullmanController controller = loader.getController();
        controller.inizializza(tabelleGitaPene,tabelleGiteController,mainpane,codice_g);
        mainpane.setCenter(aggiungipullmanpane);

    }

    @FXML
    public void addButtonAction(ActionEvent event ) {

        totPullmanPrenotati = pullmanPrenotatiTable.getItems().size() + 1;
        totalePullmanPrenotatiLabel.setText(Integer.toString(totPullmanPrenotati));
        Pullman selected = pullmanDispTable.getSelectionModel().getSelectedItem();
        if(selected == null) return;
        pullmanPrenotati.add(selected);
        pullmanDisp.remove(selected);
    }
    @FXML
    public void removeButtonAction(ActionEvent event ) {
        totPullmanPrenotati = totPullmanPrenotati -1;
        totalePullmanPrenotatiLabel.setText(Integer.toString(totPullmanPrenotati));
        Pullman selected = pullmanPrenotatiTable.getSelectionModel().getSelectedItem();
        if(selected == null) return;
        pullmanPrenotati.remove(selected);
        pullmanDisp.add(selected);

    }

    public void goToGitePane() throws RemoteException, SQLException {

        iGitaDAO gitaDAO =NamingContextManager.getTripsController();

        gitaDAO.insertNumPullman(codice_g,totPullmanPrenotati);
        ((BorderPane)gitepane3.getParent()).setCenter(tabelleGitaPene);
        tabelleGiteController.refreshGiteTables();

    }


}
