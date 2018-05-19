package client.controller;

import client.NamingContextManager;
import common.Classes.*;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.util.Callback;

import java.io.IOException;
import java.rmi.RemoteException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import common.Interface.*;

public class TabellePaneController {

    @FXML
    private TabPane tabellePane;
    @FXML
    private Tab kidTab;
    @FXML
    private Tab genitoreTab;
    @FXML
    private Tab fornitoreTab;
    @FXML
    private Tab pediatraTab;
    @FXML
    private Tab personaleTab;
    @FXML
    private Tab contattiTab;


    public String tabellaAttiva;

    @FXML public TableView<Bambino> bambinoTable;
    @FXML private TableColumn<Bambino, String> cfColumn1;
    @FXML private TableColumn<Bambino, String> nomeColumn1;
    @FXML private TableColumn<Bambino, String> cognomeColumn1;

    @FXML public TableView<Genitore> genitoreTable;
    @FXML private TableColumn<Genitore, String> cfColumn2;
    @FXML private TableColumn<Genitore, String> nomeColumn2;
    @FXML private TableColumn<Genitore, String> cognomeColumn2;

    @FXML public TableView<Personale> personaleTable;
    @FXML private TableColumn<Personale, String> cfColumn3;
    @FXML private TableColumn<Personale, String> nomeColumn3;
    @FXML private TableColumn<Personale, String> cognomeColumn3;

    @FXML public TableView<Pediatra> pediatraTable;
    @FXML private TableColumn<Pediatra, String> cfColumn4;
    @FXML private TableColumn<Pediatra, String> nomeColumn4;
    @FXML private TableColumn<Pediatra, String> cognomeColumn4;

    @FXML public TableView<Fornitore> fornitoreTable;
    @FXML private TableColumn<Fornitore, String> cfColumn5;
    @FXML private TableColumn<Fornitore, String> nomeColumn5;
    @FXML private TableColumn<Fornitore, String> cognomeColumn5;

    @FXML public TableView<Contatti> contattiTable;
    @FXML private TableColumn<Contatti, String> cfColumn6;
    @FXML private TableColumn<Contatti, String> nomeColumn6;
    @FXML private TableColumn<Contatti, String> cognomeColumn6;


    private ObservableList<Bambino> kids = FXCollections.observableArrayList();
    private ObservableList<Genitore> parents = FXCollections.observableArrayList();
    private ObservableList<Personale> personal = FXCollections.observableArrayList();
    private ObservableList<Pediatra> pediatra = FXCollections.observableArrayList();
    private ObservableList<Fornitore> providers = FXCollections.observableArrayList();
    private ObservableList<Contatti> contacts = FXCollections.observableArrayList();

    private iBambinoDAO kidDAO;
    private iGenitoreDAO parentsDAO;
    private iPersonaleDAO personalDAO;
    private iPediatraDAO pediatraDAO;
    private iFornitoreDAO providersDAO;
    private iContattiDAO contactsDAO;


    public void initialize() {
        kidDAO = NamingContextManager.getKidController();
        parentsDAO = NamingContextManager.getParentsController();
        personalDAO = NamingContextManager.getPersonalController();
        pediatraDAO = NamingContextManager.getPediatraController();
        providersDAO = NamingContextManager.getProvidersController();
        contactsDAO = NamingContextManager.getContactsController();

        initTables();
        initColumns();
    }

    private void initTables() {
        bambinoTable.setItems(kids);
        genitoreTable.setItems(parents);
        personaleTable.setItems(personal);
        pediatraTable.setItems(pediatra);
        fornitoreTable.setItems(providers);
        contattiTable.setItems(contacts);

        tabellaAttiva = "kid";
        tabellePane.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if(newValue.equals(kidTab)) {
                tabellaAttiva = "kid";
            } else if(newValue.equals(genitoreTab)){
                tabellaAttiva="genitore";
            } else if(newValue.equals(personaleTab)){
                tabellaAttiva="personale";
            } else if(newValue.equals(pediatraTab)){
                tabellaAttiva="pediatra";
            } else if(newValue.equals(fornitoreTab)){
                tabellaAttiva="fornitore";
            } else if(newValue.equals(contattiTab)){
                tabellaAttiva="contatti";
            }
        });

        refreshKidTable();
        refreshGenitoreTable();
        refreshPersonaleTable();
        refreshPediatraTable();
        refreshFornitoreTable();
        refreshContattiTable();

    }

    public void refreshTabelle(){
        refreshKidTable();
        refreshGenitoreTable();
        refreshPersonaleTable();
        refreshPediatraTable();
        refreshFornitoreTable();
        refreshContattiTable();
    }

    private void initColumns() {
        cfColumn1.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getCf()));
        nomeColumn1.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getNome()));
        cognomeColumn1.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getCognome()));
        cfColumn2.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getCf()));
        nomeColumn2.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getNome()));
        cognomeColumn2.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getCognome()));
        cfColumn3.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getCf()));
        nomeColumn3.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getNome()));
        cognomeColumn3.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getCognome()));
        cfColumn4.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getCf()));
        nomeColumn4.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getNome()));
        cognomeColumn4.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getCognome()));
        cfColumn5.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getCf()));
        nomeColumn5.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getNome()));
        cognomeColumn5.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getCognome()));
        cfColumn6.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getCf()));
        nomeColumn6.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getNome()));
        cognomeColumn6.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getCognome()));
    }
    private void refreshKidTable() {
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
    private void refreshGenitoreTable() {
        List<Genitore> parentList = new ArrayList<>();
        try {
            parentList = parentsDAO.getAllGenitori();
        } catch(RemoteException ex) {
            System.err.println(ex.getMessage());
            ex.printStackTrace();
        } catch(SQLException ex) {
            System.err.println(ex.getMessage());
            ex.printStackTrace();
        }
        parents.clear();
        parents.addAll(parentList);
    }
    private void refreshPersonaleTable() {
        List<Personale> personalList = new ArrayList<>();
        try {
            personalList = personalDAO.getAllPersonale();
        } catch(RemoteException ex) {
            System.err.println(ex.getMessage());
            ex.printStackTrace();
        } catch(SQLException ex) {
            System.err.println(ex.getMessage());
            ex.printStackTrace();
        }
        personal.clear();
        personal.addAll(personalList);
    }
    private void refreshPediatraTable() {
        List<Pediatra> pediatraList = new ArrayList<>();
        try {
            pediatraList = pediatraDAO.getAllPediatra();
        } catch(RemoteException ex) {
            System.err.println(ex.getMessage());
            ex.printStackTrace();
        } catch(SQLException ex) {
            System.err.println(ex.getMessage());
            ex.printStackTrace();
        }
        pediatra.clear();
        pediatra.addAll(pediatraList);
    }
    private void refreshFornitoreTable() {
        List<Fornitore> fornitoreList = new ArrayList<>();
        try {
            fornitoreList = providersDAO.getAllFornitore();
        } catch(RemoteException ex) {
            System.err.println(ex.getMessage());
            ex.printStackTrace();
        } catch(SQLException ex) {
            System.err.println(ex.getMessage());
            ex.printStackTrace();
        }
        providers.clear();
        providers.addAll(fornitoreList);
    }
    private void refreshContattiTable() {
        List<Contatti> contactsList = new ArrayList<>();
        try {
            contactsList = contactsDAO.getAllContatti();
        } catch(RemoteException ex) {
            System.err.println(ex.getMessage());
            ex.printStackTrace();
        } catch(SQLException ex) {
            System.err.println(ex.getMessage());
            ex.printStackTrace();
        }
        contacts.clear();
        contacts.addAll(contactsList);
    }

}
