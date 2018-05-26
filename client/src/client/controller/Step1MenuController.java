package client.controller;

import client.NamingContextManager;
import com.sun.javafx.binding.SelectBinding;
import common.Classes.Menu;
import common.Classes.Piatto;
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
import javafx.scene.control.TextField;
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
import java.util.Observable;

public class Step1MenuController extends AnchorPane {


    @FXML
    public TableView<Piatto> primiTable;
    @FXML
    public TableColumn<Piatto, String> primiColumn;

    @FXML
    private Text alertbox;

    @FXML
    public AnchorPane step1MenuPane;

    private TabelleMenuController tabelleMenuController;
    private Pane tabelleMenuPane;
    private BorderPane mainpane;

    private ObservableList<Piatto> primi = FXCollections.observableArrayList();

    private iPiattoDAO piattoDAO;

    public void initialize() {

        piattoDAO = NamingContextManager.getPiattoController();

        initTable();
        initColumns();
    }

    private void initTable() {
        primiTable.setItems(primi);
        refreshPrimiTable();

    }

    private void initColumns() {
        primiColumn.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getNome_p()));
    }


    public void refreshPrimiTable() {
        List<Piatto> primiList = new ArrayList<>();
        try {
            primiList = piattoDAO.getAllPrimi();
        } catch (RemoteException ex) {
            System.err.println(ex.getMessage());
            ex.printStackTrace();
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
            ex.printStackTrace();
        }
        primi.clear();
        primi.addAll(primiList);
    }


    public void inizializza(TabelleMenuController tabelleMenuController, Pane tabelleMenuPane, BorderPane mainpane) {

        this.tabelleMenuController = tabelleMenuController;
        this.tabelleMenuPane = tabelleMenuPane;
        this.mainpane = mainpane;
    }

    @FXML
    public void returnToTabelleMenu() throws IOException {
        ((BorderPane) step1MenuPane.getParent()).setCenter(tabelleMenuPane);
        //tabelleMenuController.refreshMenuTable();
    }

    @FXML
    public void goToStep2() throws IOException, SQLException {

        alertbox.setText("");

        iMenuDAO menuDAO = NamingContextManager.getMenuController();

        FXMLLoader loader;
        Pane step2MenuPane;

        Piatto piatto = primiTable.getSelectionModel().getSelectedItem();

        if (piatto == null) {

            alertbox.setText("ATTENZIONE: Selezionare una riga!");

        } else {

            menuDAO.inserisciPrimo(piatto);
            loader = new FXMLLoader(getClass().getResource("../view/Step2Menu.fxml"));
            step2MenuPane = loader.load();
            Step2MenuController controller = loader.getController();
            controller.inizializza(tabelleMenuController, tabelleMenuPane, mainpane);
            ((BorderPane)step1MenuPane.getParent()).setCenter(step2MenuPane);



        }


/*
    @FXML private TextField numeroMenuField;
    @FXML private TextField nomePrimoField;
    @FXML private TextField nomeSecondoField;
    @FXML private TextField nomeContornoField;
    @FXML private Button annullabutton;
    @FXML private Button confermaButton;
    @FXML private Button ingredientiprimobutton;
    @FXML private Button ingredientisecondobutton;
    @FXML private Button ingredienticontornobutton;

    @FXML public AnchorPane step1MenuPane;

    @FXML private Text alertbox;
    private Pane tabellePane;
    private TabellePaneController tabellePaneController;


    @FXML
    public void returnToTabelleMenu()throws IOException {

    }
    //public void inizializza(Menu menu, Pane tabellePane, TabellePaneController tabellePaneController){ }
    @FXML
    public void goToIngredientiPrimo()throws IOException {
        FXMLLoader loader;
        Pane step2MenuPane;
        loader = new FXMLLoader(getClass().getResource("../view/Step2MenuController.fxml"));
        step2MenuPane = loader.load();
        mainpane.setCenter(step2MenuPane);
        AggiungiAllergieController controller = loader.getController();
        controller.inizializza(bambino, tabellePane, tabellePaneController);
        */
/*
        ((BorderPane)step1MenuPane.getParent()).setCenter(FXMLLoader.load(getClass().getResource("../view/Step1Menu.fxml")));

    }

    @FXML
    public void goToIngredientiSecondo()throws IOException {
        ((BorderPane)step1MenuPane.getParent()).setCenter(FXMLLoader.load(getClass().getResource("../view/Step1Menu.fxml")));

    }
    @FXML
    public void goToIngredientiContorno()throws IOException {
        ((BorderPane)step1MenuPane.getParent()).setCenter(FXMLLoader.load(getClass().getResource("../view/Step1Menu.fxml")));

    }
    @FXML
    public void salvaMenu()throws IOException {


    }
*/
    }
}
