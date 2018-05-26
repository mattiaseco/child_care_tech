package client.controller;

import client.NamingContextManager;
import common.Classes.Bambino;
import common.Classes.Ingredienti;
import common.Interface.iIngredientiDAO;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;

import java.io.IOException;
import java.rmi.RemoteException;
import java.sql.SQLException;

public class CreaPiattoController {

    @FXML RadioButton primobutton;
    @FXML RadioButton secondobutton;
    @FXML RadioButton contornobutton;
    @FXML ToggleGroup tipoPiatto;

    @FXML Button annullabutton;
    @FXML Button confermabutton;

    @FXML AnchorPane piattoPane;

    @FXML TextField nomepField;

    @FXML private Text alertbox;

    private Pane tabellePane;
    private TabellePaneController tabellePaneController;

    @FXML
    private void salvaPiatto() throws IOException {
        /*alertbox.setText("");
        iIngredientiDAO ingredientiController = NamingContextManager.getIngredientiController();
        String ingrediente;
        ingrediente = ingredienteField.getText();
        if(ingrediente.isEmpty() ){

            alertbox.setText("Attenzione: inserire ingrediente !");
        }
        else if( ingrediente.length() > 15) {
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
        */


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
