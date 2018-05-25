package client.controller;

import client.NamingContextManager;
import common.Classes.Pullman;
import common.Interface.iPullmanDAO;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
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
    AnchorPane gitepane3;

    private Pane tabelleGitaPene;
    private TabelleGiteController tabelleGiteController;
    private BorderPane mainpane;

    @FXML public TableView pullmanTable;
    @FXML private TableColumn<Pullman, String> targaColumn;
    @FXML private TableColumn<Pullman, Integer> capienzaColumn;

    private ObservableList<Pullman> pullman = FXCollections.observableArrayList();

    private iPullmanDAO pullmanDAO;

    public void initialize() {
        pullmanDAO = NamingContextManager.getPullmanController();

        initColumns();
        initTable();

    }
    private void initColumns(){

        targaColumn.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getTarga()));
        capienzaColumn.setCellValueFactory(data -> new SimpleObjectProperty<>(data.getValue().getCapienza()));
    }

    private void initTable() {
        pullmanTable.setItems(pullman);
        refreshPullmanTable();

    }

    public void refreshPullmanTable() {

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

    }


    public void inizializza(Pane tabelleGitaPene, TabelleGiteController tabelleGitaController, BorderPane mainpane){

        this.tabelleGitaPene = tabelleGitaPene;
        this.tabelleGiteController = tabelleGitaController;
        this.mainpane = mainpane;
    }


    @FXML
    private void returnToGitePane() throws IOException {

        ((BorderPane)gitepane3.getParent()).setCenter(tabelleGitaPene);
        tabelleGiteController.refreshGiteTables();

    }
    public void goToAggiungiPullman() throws IOException {

        Pane aggiungipullmanpane;
        FXMLLoader loader;

        loader = new FXMLLoader(getClass().getResource("../view/AggiungiPullman.fxml"));
        aggiungipullmanpane= loader.load();
        AggiungiPullmanController controller = loader.getController();
        controller.inizializza(tabelleGitaPene,tabelleGiteController,mainpane);
        mainpane.setCenter(aggiungipullmanpane);

    }

    public void goToGitePane(){

        ((BorderPane)gitepane3.getParent()).setCenter(tabelleGitaPene);

    }

}
