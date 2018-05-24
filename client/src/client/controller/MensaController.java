package client.controller;


import client.NamingContextManager;
import common.Classes.*;
import common.Interface.*;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.SQLException;

public class MensaController {


    @FXML
    private ImageView backhome;
    private Stage actual;


    @FXML public BorderPane mainpane;

    @FXML public Button ingredientibutton;
    @FXML public Button piattibutton;
    @FXML public Button creabutton;
    @FXML public Button cancellabutton;
    @FXML public Button adesionibutton;

    @FXML private Text alertbox;
    @FXML private Text alertboxerror;


    private TabelleMenuController tabelleMenuController;
    public static Pane tabelleMenuPane;

    //private VisualizzaBambinoController visualizzaBambinoController;
    //private ModificaBambinoController modificaBambinoController;
    //private AggiungiBambinoController aggiungiBambinoController;


    @FXML
    private void backtohome() throws IOException {

        Parent root = FXMLLoader.load(getClass().getResource("../view/Home.fxml"));
        actual = (Stage) backhome.getScene().getWindow();
        actual.setScene(new Scene(root, backhome.getScene().getWidth(), backhome.getScene().getHeight()));
        actual.show();
    }

    @FXML
    public void initialize() throws IOException, SQLException {


        FXMLLoader loader = new FXMLLoader(getClass().getResource("../view/TabelleMenu.fxml"));
        tabelleMenuPane = loader.load();
        tabelleMenuController = loader.getController();
        mainpane.setCenter(tabelleMenuPane);

       /*try{
           / tabelleinstance.initBambini(NamingContextManager.getKidController().getAllBambini());

        }catch(SQLException e) {
            System.err.println("sql exception");
            e.printStackTrace();
        }
        mainpane.setCenter((Pane)FXMLLoader.load(getClass().getResource("../view/TabellePane.fxml")));
*/
    }


    @FXML
    private void goToIngredienti() throws IOException {
        alertbox.setText("");
        alertboxerror.setText("");
        mainpane.setCenter(FXMLLoader.load(getClass().getResource("../view/AggiungiIngredienti.fxml")));

    }
    @FXML
    private void goToPiatti() throws IOException {
        alertbox.setText("");
        alertboxerror.setText("");
        mainpane.setCenter(FXMLLoader.load(getClass().getResource("../view/CreaPiatto.fxml")));
    }
    @FXML
    private void goToCreaMenu() throws IOException {
        alertbox.setText("");
        alertboxerror.setText("");

        FXMLLoader loader;
        Pane step1MenuPane;
        loader = new FXMLLoader(getClass().getResource("../view/step1Menu.fxml"));
        step1MenuPane = loader.load();
        mainpane.setCenter(step1MenuPane);
        Step1MenuController controller = loader.getController();
        Menu menu = tabelleMenuController.mensaTable.getSelectionModel().getSelectedItem();
        //controller.inizializza(mainpane, tabellePane, tabellePaneController);
    }

    @FXML
    private void cancellaMenu() throws IOException, SQLException {
        alertbox.setText("");
        alertboxerror.setText("");
        int numero;
        FXMLLoader loader;
        Pane tabelleMenuPane;
        iMenuDAO menuController = NamingContextManager.getMenuController();
        Menu menu = tabelleMenuController.mensaTable.getSelectionModel().getSelectedItem();
        if (menu == null){
            alertbox.setText("");
            alertboxerror.setText("Attenzione:\nselezionare\nuna riga !\n");

        }
        else {
            numero = menu.getNumero();
            menuController.cancellaMenu(numero);
            tabelleMenuController.refreshMenuTable();
            alertboxerror.setText("");
            alertbox.setText("Cancellazione\neffettuata !");



            //loader = new FXMLLoader(getClass().getResource("../view/AggiungiIngredienti.fxml"));
            //tabelleMenuPane = loader.load();
            //mainpane.setCenter(tabelleMenuPane);
            //AggiungiIngredientiController controller = loader.getController();
            //controller.inizializza(menu, tabelleMenuPane, tabelleMenuController);
        }

    }

    @FXML
    private void goToAdesioni() throws IOException{

        FXMLLoader loader;
        Pane adesionipane;

            Menu menu = tabelleMenuController.mensaTable.getSelectionModel().getSelectedItem();
            if (menu == null) {
                alertbox.setText("");
                alertboxerror.setText("Attenzione:\nselezionare\nuna riga !\n");

            } else {
                loader = new FXMLLoader(getClass().getResource("../view/AggiungiAllergie.fxml"));
                adesionipane = loader.load();
                mainpane.setCenter(adesionipane);
                AggiungiAllergieController controller = loader.getController();
                //controller.inizializza(menu, tabelleMenuPane, tabelleMenuController);
            }

    }
}
