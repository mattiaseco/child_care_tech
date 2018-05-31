package client.controller;

import client.NamingContextManager;
import common.Classes.Aderisce;
import common.Classes.Bambino;
import common.Interface.iBambinoDAO;
import common.Interface.iGitaDAO;
import common.Interface.iMenuDAO;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
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

    @FXML private Button removeButton;
    @FXML private Button addButton;
    @FXML private Label totaleAderenzeLabel;

    @FXML public TableView<Bambino> cfTable;
    @FXML private TableColumn<Bambino, String> cfColumn;

    @FXML public TableView<Bambino> adesioniTable;
    @FXML private TableColumn<Bambino, String> adesioniColumn;

    @FXML
    AnchorPane gitepane2;


    private ObservableList<Bambino> kids = FXCollections.observableArrayList();
    private iBambinoDAO bambinoDAO;

    private ObservableList<Bambino> adesioni = FXCollections.observableArrayList();
    private iGitaDAO gitaDAO;

    private int totPartecipazioni;
    private Pane tabelleGitaPene;
    private TabelleGiteController tabelleGiteController;
    private BorderPane mainpane;
    private int codice_g;

    public void initialize(){
        totaleAderenzeLabel.setText("0");
        bambinoDAO = NamingContextManager.getKidController();
        gitaDAO = NamingContextManager.getTripsController();

        initColumns();
        initTable();

    }

    private void initColumns(){


        List<Bambino> tempKids = new ArrayList<>();
        cfColumn.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getCf()));
        adesioniColumn.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getCf()));
        adesioniTable.setItems(adesioni);
        cfTable.setItems(kids);

        try {
            tempKids = bambinoDAO.getAllBambini();
        } catch(RemoteException ex) {
            System.err.println(ex.getMessage());
            ex.printStackTrace();
        } catch(SQLException ex) {
            System.err.println(ex.getMessage());
            ex.printStackTrace();
        }
        kids.clear();
        adesioni.clear();
        kids.addAll(tempKids);


    }
    private void initTable(){
        removeButton.setDisable(true);
        addButton.setDisable(true);

        adesioniTable.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
            if (newSelection != null) {
                removeButton.setDisable(false);
                addButton.setDisable(true);
            }
            else {
                removeButton.setDisable(true);
                addButton.setDisable(true);
            }
        });
        cfTable.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
            if (newSelection != null) {
                removeButton.setDisable(true);
                addButton.setDisable(false);
            }
            else {
                removeButton.setDisable(true);
                addButton.setDisable(true);
            }
        });
    }

    public void refreshAdesioniTable() {
        removeButton.setDisable(true);
        addButton.setDisable(true);

        adesioniTable.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
            if (newSelection != null) {
                removeButton.setDisable(false);
                addButton.setDisable(false);
            }
            else {
                removeButton.setDisable(true);
                addButton.setDisable(true);
            }
        });
        cfTable.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
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



    public void refreshKidTable() {
        List<Bambino> kidsList = new ArrayList<>();
        try {
            kidsList = bambinoDAO.getAllBambini();
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

    public void inizializza( Pane tabelleGitaPene, TabelleGiteController tabelleGitaController, BorderPane mainpane,int codice_g){

        this.tabelleGitaPene = tabelleGitaPene;
        this.tabelleGiteController = tabelleGitaController;
        this.mainpane = mainpane;
        this.codice_g = codice_g;
    }


    @FXML
    private void returnToGitePane() throws IOException, SQLException {
        iGitaDAO gitaController = NamingContextManager.getTripsController();
        gitaController.cancellaGita(codice_g);
        ((BorderPane)gitepane2.getParent()).setCenter(tabelleGitaPene);
        tabelleGiteController.refreshGiteTables();

    }

    @FXML
    public void addButtonAction(ActionEvent event ) throws RemoteException, SQLException {

        totPartecipazioni = adesioniTable.getItems().size() + 1;
        totaleAderenzeLabel.setText(Integer.toString(totPartecipazioni));
        Bambino selected = cfTable.getSelectionModel().getSelectedItem();
        if(selected == null) return;
        adesioni.add(selected);
        kids.remove(selected);
        gitaDAO.inserisciBambinoGita(codice_g,selected);
    }
    @FXML
    public void removeButtonAction(ActionEvent event ) throws RemoteException, SQLException {
        totPartecipazioni = totPartecipazioni -1;
        totaleAderenzeLabel.setText(Integer.toString(totPartecipazioni));
        Bambino selected = adesioniTable.getSelectionModel().getSelectedItem();
        if(selected == null) return;
        kids.add(selected);
        adesioni.remove(selected);
        gitaDAO.cancellaBambinoGita(codice_g,selected);
    }


    @FXML
    public void goToStep3() throws IOException, SQLException {

        Pane gitepane3;
        FXMLLoader loader;

        gitaDAO.insertNumPartecipanti(codice_g,totPartecipazioni);
        loader = new FXMLLoader(getClass().getResource("../view/Step3AggiungiGita.fxml"));
        gitepane3= loader.load();
        Step3AggiungiGitaController controller = loader.getController();
        controller.inizializza(tabelleGitaPene,tabelleGiteController,mainpane,codice_g);
        mainpane.setCenter(gitepane3);
        tabelleGiteController.refreshGiteTables();

    }

}
