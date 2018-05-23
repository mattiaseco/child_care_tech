package client.controller;

import client.NamingContextManager;
import common.Classes.Gita;
import common.Classes.Menu;
import common.Interface.iGitaDAO;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.AnchorPane;

import java.rmi.RemoteException;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class TabelleMenuController {

    @FXML public TableView<Menu> mensaTable;
    @FXML private TableColumn<Menu, String> menuColumn;
    @FXML private TableColumn<Menu, String> primoColumn;
    @FXML private TableColumn<Menu, String> secondoColumn;
    @FXML private TableColumn<Menu, String> contornoColumn;

    @FXML AnchorPane mensaPane;


    private ObservableList<Menu> menus = FXCollections.observableArrayList();
/*
    private iGitaDAO gitaDAO;

    public void initialize() {

        gitaDAO = NamingContextManager.getTripsController();
        initTables();
        initColumns();
    }

    private void initTables() {
        giteTable.setItems(trips);
        refreshTripsTable();

    }
    private void initColumns() {

        dest.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getDestinazione()));
        dat_part.setCellValueFactory(data -> new SimpleObjectProperty<>(data.getValue().getData_partenza()));
        dat_rit.setCellValueFactory(data -> new SimpleObjectProperty<>(data.getValue().getData_ritorno()));
        num_part.setCellValueFactory(data -> new SimpleObjectProperty<>(data.getValue().getNum_partecipanti()));
    }

    public void refreshTripsTable() {

        List<Gita> gitaList = new ArrayList<>();
        try {
            gitaList = gitaDAO.getAllGite();
        } catch(RemoteException ex) {
            System.err.println(ex.getMessage());
            ex.printStackTrace();
        } catch(SQLException ex) {
            System.err.println(ex.getMessage());
            ex.printStackTrace();
        }
        trips.clear();
        trips.addAll(gitaList);
    }
    */

}
