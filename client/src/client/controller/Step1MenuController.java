package client.controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;

import java.io.IOException;

public class Step1MenuController extends AnchorPane{

    @FXML private Button annullabutton;
    @FXML private Button proseguibutton;

    @FXML public AnchorPane step1MenuPane;

    @FXML
    public void returnToTabelleMenu()throws IOException {

    }

    @FXML
    public void goToStep2()throws IOException {
        ((BorderPane)step1MenuPane.getParent()).setCenter(FXMLLoader.load(getClass().getResource("../view/Step2Menu.fxml")));

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
