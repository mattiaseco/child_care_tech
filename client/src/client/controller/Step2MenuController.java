package client.controller;

import client.NamingContextManager;
import common.Classes.Bambino;
import common.Classes.Ingredienti;
import common.Classes.Menu;
import common.Classes.Piatto;
import common.Interface.iIngredientiDAO;
import common.Interface.iMenuDAO;
import common.Interface.iPiattoDAO;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;

import javax.print.attribute.standard.MediaSize;
import java.io.IOException;
import java.rmi.RemoteException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class Step2MenuController {
    @FXML
    public TableView<Piatto> secondiTable;
    @FXML
    private TableColumn<Piatto, String> secondiColumn;

    @FXML
    private Text alertbox;
    @FXML
    public AnchorPane step2MenuPane;

    private TabelleMenuController tabelleMenuController;
    private Pane tabelleMenuPane;
    private BorderPane mainpane;
    private int numeroMenu;


    private ObservableList<Piatto> secondi = FXCollections.observableArrayList();
    private iPiattoDAO piattoDAO;

    public void initialize() {

        piattoDAO = NamingContextManager.getPiattoController();

        initColumns();
        initTable();
    }

    private void initColumns() {
        secondiColumn.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getNome_p()));

    }

    private void initTable() {

        secondiTable.setItems(secondi);
        refreshSecondiTable();

    }

    public void refreshSecondiTable() {
        List<Piatto> secondiList = new ArrayList<>();
        try {
            secondiList = piattoDAO.getAllSecondi();
        } catch (RemoteException ex) {
            System.err.println(ex.getMessage());
            ex.printStackTrace();
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
            ex.printStackTrace();
        }
        secondi.clear();
        secondi.addAll(secondiList);
    }


    public void inizializza(TabelleMenuController tabelleMenuController, Pane tabelleMenuPane, BorderPane mainpane, int numeroMenu) {

        this.tabelleMenuController = tabelleMenuController;
        this.tabelleMenuPane = tabelleMenuPane;
        this.mainpane = mainpane;
        this.numeroMenu = numeroMenu;

    }

    @FXML
    public void returnToTabelleMenu() throws IOException, SQLException {

        iMenuDAO menuDAO = NamingContextManager.getMenuController();

        menuDAO.cancellaMenu(numeroMenu);

        ((BorderPane) step2MenuPane.getParent()).setCenter(tabelleMenuPane);
        tabelleMenuController.refreshMenuTable();

    }

    @FXML
    public void goToStep3() throws IOException, SQLException {
        alertbox.setText("");

        FXMLLoader loader;
        Pane step3MenuPane;

        iMenuDAO menuDAO = NamingContextManager.getMenuController();
        Piatto piatto = secondiTable.getSelectionModel().getSelectedItem();

        if (piatto == null)
            alertbox.setText("ATTENZIONE: Selezionare un piatto!");
        else {
            //((BorderPane)step1MenuPane.getParent()).setCenter(FXMLLoader.load(getClass().getResource("../view/Step2Menu.fxml")))
            menuDAO.inserisciSecondo(numeroMenu, piatto);
            loader = new FXMLLoader(getClass().getResource("../view/Step3Menu.fxml"));
            step3MenuPane = loader.load();
            Step3MenuController controller = loader.getController();
            controller.inizializza(tabelleMenuController, tabelleMenuPane, mainpane, numeroMenu);
            ((BorderPane) step2MenuPane.getParent()).setCenter(step3MenuPane);


        }
        //((BorderPane)step2MenuPane.getParent()).setCenter(FXMLLoader.load(getClass().getResource("../view/Step3Menu.fxml")));

    }

}