package client.controller;

import client.NamingContextManager;
import common.Classes.Gita;
import common.Classes.Menu;
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
    @FXML private TableColumn<Menu, String> menuColumn;
    @FXML private TableColumn<Menu, String> primoColumn;
    @FXML private TableColumn<Menu, String> secondoColumn;
    @FXML private TableColumn<Menu, String> contornoColumn;

    @FXML AnchorPane tabelleMenu;


    private ObservableList<Menu> menus = FXCollections.observableArrayList();

    private iMenuDAO menuDAO;

    @FXML
    public void initialize() {
        menuDAO = NamingContextManager.getMenuController();
        initTables();
        initColumns();
    }

    private void initTables() {
        mensaTable.setItems(menus);
        refreshMenuTable();

    }
    private void initColumns() {

        menuColumn.setCellValueFactory(data -> new SimpleStringProperty(String.valueOf(data.getValue().getNumero())));
        primoColumn.setCellValueFactory(data -> new SimpleObjectProperty(data.getValue().getPiatto1()));
        secondoColumn.setCellValueFactory(data -> new SimpleObjectProperty(data.getValue().getPiatto2()));
        contornoColumn.setCellValueFactory(data -> new SimpleObjectProperty(data.getValue().getPiatto3()));
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
