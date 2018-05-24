package client.controller;

import client.NamingContextManager;
import common.Classes.Bambino;
import common.Classes.Menu;
import common.Interface.iBambinoDAO;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;

import java.io.IOException;

public class Step1MenuController extends AnchorPane{

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


    }
    @FXML
    public void goToIngredientiSecondo()throws IOException {


    }
    @FXML
    public void goToIngredientiContorno()throws IOException {


    }
    @FXML
    public void salvaMenu()throws IOException {


    }

}
