package client.controller;

import client.NamingContextManager;
import common.Classes.Bambino;
import common.Classes.Ingredienti;
import common.Interface.iBambinoDAO;
import common.Interface.iIngredientiDAO;
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
import javafx.scene.text.Text;

import java.io.IOException;
import java.rmi.RemoteException;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;


public class AggiungiIngredientiController {

    @FXML public BorderPane mainpane;
    @FXML public AnchorPane ingredientiPane;
    @FXML public Button eliminaButton;
    @FXML public Button aggiungiButton;
    @FXML public Button saveButton;
    @FXML public TextField ingredienteField;
    @FXML public Text alertbox;
    @FXML public Text alertboxok;

    @FXML public TableView<Ingredienti> ingredientiTable;
    @FXML private TableColumn<Ingredienti, String> nomeiColumn;

    private ObservableList<Ingredienti> ingredientis = FXCollections.observableArrayList();
    private iIngredientiDAO ingredientiDAO;

    public void initialize() {
        ingredientiDAO = NamingContextManager.getIngredientiController();
        initTables();
        initColumns();
    }
    private void initTables() {
        ingredientiTable.setItems(ingredientis);
        refreshIngredientiTable();

    }
    private void initColumns() {
        nomeiColumn.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getNome_i()));

    }
    public void refreshIngredientiTable() {
        List<Ingredienti> ingredientisList = new ArrayList<>();
        try {
            ingredientisList = ingredientiDAO.getAllIngredienti();
        } catch(RemoteException ex) {
            System.err.println(ex.getMessage());
            ex.printStackTrace();
        } catch(SQLException ex) {
            System.err.println(ex.getMessage());
            ex.printStackTrace();
        }
        ingredientis.clear();
        ingredientis.addAll(ingredientisList);
    }

    @FXML
    public void eliminaIngrediente()throws IOException {
        alertbox.setText("");
        alertboxok.setText("");

        String ingrediente;
        iIngredientiDAO ingredientiController = NamingContextManager.getIngredientiController();
        Ingredienti ingredienti= ingredientiTable.getSelectionModel().getSelectedItem();
        if (ingredienti == null) {
            alertbox.setText("Attenzione: selezionare una riga !");
            alertboxok.setText("");
        }
        else {
            ingrediente = ingredienti.getNome_i();
            try {
                ingredientiController.cancellaIngredienti(ingrediente);
                refreshIngredientiTable();
            } catch(RemoteException ex) {
                ex.printStackTrace();
            } catch(SQLException ex) {
                ex.printStackTrace();
            }
            alertbox.setText("");
            alertboxok.setText("Cancellazione effettuata !");
        }

    }
    @FXML
    public void aggiungiIngrediente() throws IOException, SQLException {

        alertbox.setText("");
        alertboxok.setText("");

        iIngredientiDAO ingredientiController = NamingContextManager.getIngredientiController();
        String ingrediente;
        ingrediente = ingredienteField.getText();
        if(ingrediente.isEmpty() ){

            alertbox.setText("Attenzione: inserire ingrediente !");
        }
        else if( ingrediente.length() > 30) {
            alertbox.setText("Attenzione: nome troppo lungo !");
        }
        else {
            if(ingredientiTable.getItems().contains(new Ingredienti(ingrediente, 0)))
                alertbox.setText("Attenzione: Ingrediente già presente!");
            else {
                ingredientiController.inserisciIngrediente(ingrediente);
                refreshIngredientiTable();
                ingredienteField.clear();
                alertbox.setText("");

            }
        }

    }

    @FXML
    public void aggiornaIngredienti()throws IOException {

        ((BorderPane)ingredientiPane.getParent()).setCenter(FXMLLoader.load(getClass().getResource("../view/TabelleMenu.fxml")));


    }

}
