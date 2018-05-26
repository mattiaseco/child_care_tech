package client.controller;

import client.NamingContextManager;
import common.Classes.Piatto;
import common.Classes.Menu;
import common.Interface.iMenuDAO;
import common.Interface.iPiattoDAO;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;

import javax.print.attribute.standard.MediaSize;
import java.io.IOException;
import java.rmi.RemoteException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class Step3MenuController {
    @FXML public TableView contorniTable;
    @FXML private TableColumn<Piatto, String> contorniColumn;

    @FXML public AnchorPane step3MenuPane;

    private TabelleMenuController tabelleMenuController;
    private Pane tabelleMenuPane;
    private BorderPane mainpane;

    private ObservableList<Piatto> contorni = FXCollections.observableArrayList();
    private iPiattoDAO piattoDAO;

    public void initialize(){

        piattoDAO = NamingContextManager.getPiattoController();

        initColumns();
        initTable();

    }

    private void initColumns(){

        contorniColumn.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getNome_p()));

    }
    private void  initTable(){
        contorniTable.setItems(contorni);
        refreshContorniTable();

    }


    public void refreshContorniTable() {
        List<Piatto> contorniList = new ArrayList<>();
        try {
            contorniList = piattoDAO.getAllContorni();
        } catch(RemoteException ex) {
            System.err.println(ex.getMessage());
            ex.printStackTrace();
        } catch(SQLException ex) {
            System.err.println(ex.getMessage());
            ex.printStackTrace();
        }
        contorni.clear();
        contorni.addAll(contorniList);
    }

    public void inizializza(TabelleMenuController tabelleMenuController, Pane tabelleMenuPane, BorderPane mainpane) {

        this.tabelleMenuController = tabelleMenuController;
        this.tabelleMenuPane = tabelleMenuPane;
        this.mainpane = mainpane;
    }



    @FXML
    public void returnToTabelleMenu()throws IOException {

        ((BorderPane)step3MenuPane.getParent()).setCenter(tabelleMenuPane);

    }

    @FXML
    public void salvaMenu()throws IOException {
        ((BorderPane)step3MenuPane.getParent()).setCenter(FXMLLoader.load(getClass().getResource("../view/TabelleMenu.fxml")));

    }
}
