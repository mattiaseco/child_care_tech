package client.controller;

import client.NamingContextManager;
import common.Classes.Gita;
import common.Classes.Menu;
import common.Classes.Piatto;
import common.Interface.iGitaDAO;
import common.Interface.iMenuDAO;
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
    @FXML private TableColumn<Menu, Integer> menuColumn;
    /*@FXML private TableColumn<Menu, Piatto> primoColumn;
    @FXML private TableColumn<Menu, Piatto> secondoColumn;
    @FXML private TableColumn<Menu, Piatto> contornoColumn;
    */
    @FXML private TableColumn<Menu, String> primoColumn;
    @FXML private TableColumn<Menu, String> secondoColumn;
    @FXML private TableColumn<Menu, String> contornoColumn;

    @FXML AnchorPane menuPane;

    private ObservableList<Menu> menus = FXCollections.observableArrayList();

    private iMenuDAO menuDAO;

    @FXML
    public void initialize() {
        menuDAO = NamingContextManager.getMenuController();
        initTables();
        initColumns();
    }

    private void initColumns() {

        menuColumn.setCellValueFactory(data -> new SimpleObjectProperty<>(data.getValue().getNumero()));
        primoColumn.setCellValueFactory(data -> new SimpleObjectProperty<>(data.getValue().getPiatto1().getNome_p()));
        secondoColumn.setCellValueFactory(data -> new SimpleObjectProperty<>(data.getValue().getPiatto2().getNome_p()));
        contornoColumn.setCellValueFactory(data -> new SimpleObjectProperty<>(data.getValue().getPiatto3().getNome_p()));
    }

    private void initTables() {
        mensaTable.setItems(menus);
        refreshMenuTable();

    }


    public void refreshMenuTable() {

        List<Menu> menuList = new ArrayList<>();
        try {
            menuList = menuDAO.getAllMenu();
        } catch(RemoteException ex) {
            System.err.println(ex.getMessage());
            ex.printStackTrace();
        } catch(SQLException ex) {
            System.err.println(ex.getMessage());
            ex.printStackTrace();
        }
        menus.clear();
        menus.addAll(menuList);
    }

}
