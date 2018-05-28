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
import javafx.scene.text.Text;

import javax.print.attribute.standard.MediaSize;
import java.io.IOException;
import java.rmi.RemoteException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class Step3MenuController {

    @FXML public TableView<Piatto> contorniTable;
    @FXML private TableColumn<Piatto, String> contorniColumn;

    @FXML private Text alertbox;

    @FXML public AnchorPane step3MenuPane;

    private TabelleMenuController tabelleMenuController;
    private Pane tabelleMenuPane;
    private BorderPane mainpane;
    private int numeroMenu;


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

        ((BorderPane)step3MenuPane.getParent()).setCenter(tabelleMenuPane);
        tabelleMenuController.refreshMenuTable();
    }

    @FXML
    public void salvaMenu() throws IOException, SQLException {

        //((BorderPane)step3MenuPane.getParent()).setCenter(FXMLLoader.load(getClass().getResource("../view/TabelleMenu.fxml")));

        alertbox.setText("");

        //FXMLLoader loader;
        //Pane tabelleMenuPane;

        iMenuDAO menuDAO = NamingContextManager.getMenuController();
        Piatto piatto = contorniTable.getSelectionModel().getSelectedItem();

        if (piatto == null)
            alertbox.setText("ATTENZIONE: Selezionare un piatto!");
        else {
            menuDAO.inserisciContorno(numeroMenu,piatto);

            //loader = new FXMLLoader(getClass().getResource("../view/TabelleMenu.fxml"));
            //tabelleMenuPane = loader.load();
            //TabelleMenuController controller = loader.getController();
            //controller.inizializza(tabelleMenuController, tabelleMenuPane);
            ((BorderPane)step3MenuPane.getParent()).setCenter(tabelleMenuPane);
            tabelleMenuController.refreshMenuTable();


        }
        //tabelleMenuController.refreshMenuTable();

    }
}
