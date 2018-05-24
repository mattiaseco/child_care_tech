package client.controller;

import client.NamingContextManager;
import common.Classes.Bambino;
import common.Interface.iBambinoDAO;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;

import java.io.IOException;
import java.rmi.RemoteException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AdesioniController {

    @FXML
    AnchorPane adesionipane;
    @FXML
    Button confermabutton;
    @FXML
    Button annullabutton;
    @FXML
    Text alertbox;
    @FXML
    Label numeroMenuLabel;

    @FXML public TableView<Bambino> adesioniTable;
    @FXML private TableColumn<Bambino, String> cfColumn;
    @FXML private TableColumn<Bambino, String> cognomeColumn;
    @FXML private TableColumn<Bambino, String> nomeColumn;
    @FXML private TableColumn<Bambino, String> selectColumn;

    private ObservableList<Bambino> kids = FXCollections.observableArrayList();
    private iBambinoDAO kidDAO;

    private BorderPane mainpane;
    private TabelleMenuController tabelleMenuController;
    private Pane tabelleMenu;

    public void inizializza(BorderPane pane,Pane tabelleMenu, TabelleMenuController tabelleMenuController){
        this.mainpane = pane;
        this.tabelleMenu = tabelleMenu;
        this.tabelleMenuController = tabelleMenuController;
    }


    public void initialize() {
        kidDAO = NamingContextManager.getKidController();

        initTables();
        initColumns();
    }
    private void initColumns() {
        cfColumn.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getCf()));
        cognomeColumn.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getCognome()));
        nomeColumn.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getNome()));
        selectColumn.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getCognome()));

    }

    private void initTables() {
        adesioniTable.setItems(kids);
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

        @FXML
    public void aggiornaAdesioni()throws IOException {
            alertbox.setText("");

            FXMLLoader loader;
            Pane tabelleMenu;
            loader = new FXMLLoader(getClass().getResource("../view/TabelleMenu.fxml"));
            tabelleMenu = loader.load();
            mainpane.setCenter(tabelleMenu);

            //AggiungiBambinoController controller = loader.getController();
            //Bambino bambino = tabellePaneController.bambinoTable.getSelectionModel().getSelectedItem();

            //controller.inizializza(tabellePane, tabellePaneController);


    }
    @FXML
    public void returnToTabelleMenu()throws IOException {
        //((BorderPane)adesionipane.getParent()).setCenter(tabellePane);
        //tabellePaneController.refreshMenuTable();
        alertbox.setText("");

        FXMLLoader loader;
        Pane tabelleMenu;
        loader = new FXMLLoader(getClass().getResource("../view/TabelleMenu.fxml"));
        tabelleMenu = loader.load();
        mainpane.setCenter(tabelleMenu);

        //AggiungiBambinoController controller = loader.getController();
        //Bambino bambino = tabellePaneController.bambinoTable.getSelectionModel().getSelectedItem();

        //controller.inizializza(tabellePane, tabellePaneController);


    }





}
