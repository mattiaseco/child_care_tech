package client.controller;

import client.NamingContextManager;
import common.Classes.Bambino;
import common.Interface.iBambinoDAO;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;

import java.io.IOException;
import java.rmi.RemoteException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class Step2AggiungiGitaController {

    @FXML public TableView bambinoTable;
    @FXML private TableColumn<Bambino, String> cfColumn;
    @FXML private TableColumn<Bambino, String> nomeColumn;
    @FXML private TableColumn<Bambino, String> cognomeColumn;

    @FXML
    AnchorPane gitepane2;

    private Pane tabelleGitaPene;
    private TabelleGiteController tabelleGiteController;
    private BorderPane mainpane;

    private ObservableList<Bambino> kids = FXCollections.observableArrayList();

    private iBambinoDAO kidDAO;

    public void initialize() {
        kidDAO = NamingContextManager.getKidController();

        initColumns();
        initTable();
    }

    private void initColumns() {
        cfColumn.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getCf()));
        nomeColumn.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getNome()));
        cognomeColumn.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getCognome()));
    }

    private void initTable() {
        bambinoTable.setItems(kids);
        refreshKidTable();
    }

    public void refreshKidTable() {
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

    public void inizializza( Pane tabelleGitaPene, TabelleGiteController tabelleGitaController, BorderPane mainpane){

        this.tabelleGitaPene = tabelleGitaPene;
        this.tabelleGiteController = tabelleGitaController;
        this.mainpane = mainpane;
    }


    @FXML
    private void returnToGitePane() throws IOException{

        ((BorderPane)gitepane2.getParent()).setCenter(tabelleGitaPene);
        tabelleGiteController.refreshGiteTables();

    }


    @FXML
    public void goToStep3() throws IOException{

        Pane gitepane3;
        FXMLLoader loader;

        loader = new FXMLLoader(getClass().getResource("../view/Step3AggiungiGita.fxml"));
        gitepane3= loader.load();
        Step3AggiungiGitaController controller = loader.getController();
        controller.inizializza(tabelleGitaPene,tabelleGiteController,mainpane);
        mainpane.setCenter(gitepane3);
        tabelleGiteController.refreshGiteTables();

    }

}
