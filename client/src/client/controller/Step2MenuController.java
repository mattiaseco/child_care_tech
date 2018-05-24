package client.controller;

import client.NamingContextManager;
import common.Classes.Bambino;
import common.Classes.Ingredienti;
import common.Interface.iIngredientiDAO;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.*;
import javafx.scene.control.cell.CheckBoxTableCell;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;

import java.io.IOException;
import java.rmi.RemoteException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class Step2MenuController {
    @FXML private Button annullabutton;
    @FXML private Button proseguibutton;

    @FXML public AnchorPane step2MenuPane;

    @FXML
    public void returnToTabelleMenu()throws IOException {

    }

    @FXML
    public void goToStep3()throws IOException {
        ((BorderPane)step2MenuPane.getParent()).setCenter(FXMLLoader.load(getClass().getResource("../view/Step3Menu.fxml")));

    }


    /*
    @FXML Button annullabutton;
    @FXML Button confermabutton;
    @FXML TextField nomeIngredienteField;
    @FXML Text alertbox;
    @FXML AnchorPane step2MenuPane;

    private Pane tabellePane;
    private TabellePaneController tabellePaneController;


    //@FXML public TableView<AggiungiAllergieController.IngredienteTemp> intolleranzeTable;
    //@FXML private TableColumn<AggiungiAllergieController.IngredienteTemp, String> ingredientiColumn;
//    @FXML private TableColumn<AggiungiAllergieController.IngredienteTemp, Boolean> selectColumn;


    //private ObservableList<AggiungiAllergieController.IngredienteTemp> intolleranza = FXCollections.observableArrayList();
    //private List<Ingredienti> intolleranzePresenti = new ArrayList<>();

    //private iIngredientiDAO intolleranzaDAO;
    //private Bambino bambino;


    public void initialize() {
        /*intolleranzaDAO = NamingContextManager.getIngredientiController();
        initTables();
        initColumns();
        intolleranzeTable.setEditable(true);
*/
    /*
    }
    private void initTables() {
        //intolleranzeTable.setItems(intolleranza);
        //refreshIntolleranzeTable();
    }
    private void initColumns() {

       ingredientiColumn.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getIngrediente().getNome_i()));
        selectColumn.setCellValueFactory(data->new SimpleBooleanProperty(data.getValue().getBool()));
        selectColumn.setCellValueFactory( new PropertyValueFactory<AggiungiAllergieController.IngredienteTemp,Boolean>( "checkBoxValue" ) );
        selectColumn.setCellFactory( tc -> new CheckBoxTableCell<>());

    }
    public void refreshIntolleranzeTable(){

        List<Ingredienti> ingredientsList = new ArrayList<>();
        try {
            ingredientsList = intolleranzaDAO.getAllIngredienti();
        } catch(RemoteException ex) {
            System.err.println(ex.getMessage());
            ex.printStackTrace();
        } catch(SQLException ex) {
            System.err.println(ex.getMessage());
            ex.printStackTrace();
        }
        intolleranza.clear();
        for(Ingredienti ingredienti : ingredientsList) {
            intolleranza.add(new AggiungiAllergieController.IngredienteTemp(ingredienti));
        }

    }

    @FXML
    public void returnToStep1Menu()throws IOException {
        //((BorderPane)allergiepane.getParent()).setCenter(FXMLLoader.load(getClass().getResource("../view/TabellePane.fxml")));
        /*((BorderPane)allergiepane.getParent()).setCenter(tabellePane);
        tabellePaneController.refreshTabelle();
        */
    /*}
    @FXML
    public void salvaIngredientiPiatto()throws IOException {

    }
    public void aggiungiIngrediente()throws IOException {

    }
    @FXML

    public void inizializza(Bambino bambino, Pane tabellePane, TabellePaneController tabellePaneController) {
       /*
        this.bambino = bambino;
        nomeLabel.setText(bambino.getNome());
        cognomeLabel.setText(bambino.getCognome());
        this.tabellePane = tabellePane;
        this.tabellePaneController = tabellePaneController;
        try {
            intolleranzePresenti.addAll(intolleranzaDAO.getAllIngredienti());
            refreshIntolleranzeTable();
        } catch(RemoteException ex) {
            ex.printStackTrace();
        } catch(SQLException ex) {
            ex.printStackTrace();
        }


    }


    private static class IngredienteTemp {
        private Ingredienti ing;
        private boolean bool;
        public IngredienteTemp (Ingredienti ing) {
            this.ing = ing;
        }
        public Ingredienti getIngrediente() { return ing; }
        public boolean getBool() { return bool; }
        public void setBool(boolean bool) { this.bool = bool; }

    }

    public void selezionaIngrediente() {


    }
    */

}
