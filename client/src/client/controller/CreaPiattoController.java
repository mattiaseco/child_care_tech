package client.controller;

import client.NamingContextManager;
import common.Classes.*;
import common.Interface.iBambinoDAO;
import common.Interface.iIngredientiDAO;
import common.Interface.iPiattoDAO;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;

import java.io.IOException;
import java.rmi.RemoteException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CreaPiattoController {

    @FXML
    private TableView<Ingredienti> ingredientipTable;
    @FXML
    private TableView<Ingredienti> ingredientidTable;
    @FXML
    private TableColumn<Ingredienti, String> nameColumnP;
    @FXML
    private TableColumn<Ingredienti, String> nameColumnD;

    @FXML RadioButton primobutton;
    @FXML RadioButton secondobutton;
    @FXML RadioButton contornobutton;
    @FXML ToggleGroup tipoPiatto;

    @FXML private Button addButton;
    @FXML private Button removeButton;

    @FXML Button annullabutton;
    @FXML Button confermabutton;

    @FXML AnchorPane piattoPane;

    @FXML TextField nomepField;

    @FXML private Text alertbox;

    private Pane tabellePane;
    private TabellePaneController tabellePaneController;


    private Piatto piatto;
    private iPiattoDAO piattoDAO;
    private iIngredientiDAO ingredientiDAO;
    private ObservableList<Ingredienti> ingredientiDisponibili = FXCollections.observableArrayList();
    private ObservableList<Ingredienti> ingredientiPiatto = FXCollections.observableArrayList();


    @FXML
    public void initialize() {
        ingredientiDAO = NamingContextManager.getIngredientiController();
        piattoDAO = NamingContextManager.getPiattoController();
        initMenu();
        initTable();

    }


    public void addButtonAction() {
        addButtonAction();
    }


    public void addButtonAction(ActionEvent event ) {
        Ingredienti selected = ingredientidTable.getSelectionModel().getSelectedItem();
        if(selected == null) return;
        ingredientiPiatto.add(selected);
        ingredientiDisponibili.remove(selected);
    }
    @FXML
    public void removeButtonAction(ActionEvent event ) {
        Ingredienti selected = ingredientipTable.getSelectionModel().getSelectedItem();
        if(selected == null) return;
        ingredientiDisponibili.add(selected);
        ingredientiPiatto.remove(selected);
    }

    private void initMenu() {
        removeButton.setDisable(true);
        addButton.setDisable(true);

        ingredientipTable.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
            if (newSelection != null) {
                removeButton.setDisable(false);
                addButton.setDisable(true);
            }
            else {
                removeButton.setDisable(true);
                addButton.setDisable(true);
            }
        });
        ingredientidTable.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
            if (newSelection != null) {
                removeButton.setDisable(true);
                addButton.setDisable(false);
            }
            else {
                removeButton.setDisable(true);
                addButton.setDisable(true);
            }
        });
    }
    private void initTable() {
        List<Ingredienti> tempFoods = new ArrayList<>();

        nameColumnP.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getNome_i()));
        nameColumnD.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getNome_i()));
        ingredientipTable.setItems(ingredientiPiatto);
        ingredientidTable.setItems(ingredientiDisponibili);

        try {
            tempFoods = ingredientiDAO.getAllIngredienti();
        } catch(RemoteException ex) {
            System.err.println(ex.getMessage());
            ex.printStackTrace();
        } catch(SQLException ex) {
            System.err.println(ex.getMessage());
            ex.printStackTrace();
        }
        ingredientiDisponibili.clear();
        ingredientiPiatto.clear();
        ingredientiDisponibili.addAll(tempFoods);
    }
        /*@FXML
    public void saveButtonAction(ActionEvent event)throws RemoteException, SQLException{
        List<Ingredienti> newAllergies = new ArrayList<>();
        List<Ingredienti> oldAllergies = new ArrayList<>(bambinoDAO.getAllAllergie(bambino));

        Bambino bambino1;
        //PersonDTO newPerson;
        newAllergies.addAll(allergies);

        try{

            for(Ingredienti newAllergy : newAllergies) {
                if(!oldAllergies.contains(newAllergy))
                    bambinoDAO.inserisciAllergia(bambino,newAllergy);
            }
            for(Ingredienti oldAllergy : oldAllergies) {
                if(!newAllergies.contains(oldAllergy))
                    bambinoDAO.cancellaAllergia(bambino,oldAllergy);
            }
            ((BorderPane)allergiePane.getParent()).setCenter(tabellePane);

            tabellePaneController.refreshTabelle();

        }catch (RemoteException ex){
            System.err.println(ex.getMessage());
            ex.printStackTrace();}



    } */



    @FXML
    private void salvaPiatto(ActionEvent event)throws RemoteException, SQLException, IOException {

        alertbox.setText("");
        iPiattoDAO piattoController = NamingContextManager.getPiattoController();
        String nome_p;
        nome_p = nomepField.getText();
        if(nome_p.isEmpty() ){

            alertbox.setText("Attenzione: inserire nome del piatto !");
        }
        else if( nome_p.length() > 15) {
            alertbox.setText("Attenzione: nome troppo lungo !");
        }
        if(tipoPiatto.getSelectedToggle() == null) {
            alertbox.setText("Attenzione: selezionare un tipo di piatto !");
            return;
        }
        if (tipoPiatto.getSelectedToggle().equals(primobutton)) {
            piattoController.inserisciPiatto(nome_p,"Primo");

        }
        else if (tipoPiatto.getSelectedToggle().equals(secondobutton)){
            piattoController.inserisciPiatto(nome_p,"Secondo");
        }
        else if (tipoPiatto.getSelectedToggle().equals(contornobutton)){
            piattoController.inserisciPiatto(nome_p,"Contorno");
        }
        



    }


    @FXML
    private void returnToTabelleMenu() throws IOException {
        alertbox.setText("");
        ((BorderPane)piattoPane.getParent()).setCenter(tabellePane);
        tabellePaneController.refreshTabelle();


    }
    public void inizializza( Pane pane, TabellePaneController tabellePaneController) {
        this.tabellePane = pane;
        this.tabellePaneController = tabellePaneController;

    }

}
