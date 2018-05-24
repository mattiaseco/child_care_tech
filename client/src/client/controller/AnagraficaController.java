package client.controller;


import client.controller.TabellePaneController;
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

public class AnagraficaController {

    @FXML
    public BorderPane mainpane;

    @FXML
    private Button visualizzabutton;
    @FXML
    private Button modificabutton;
    @FXML
    private Button aggiungibutton;
    @FXML
    private Button cancellabutton;
    @FXML
    private Button allergiebutton;

    @FXML
    private Text alertbox;
    @FXML
    private Text alertboxerror;
    @FXML
    private ImageView backhome;

    private Stage actual;

    private TabellePaneController tabellePaneController;
    public static Pane tabellePane;

    private VisualizzaBambinoController visualizzaBambinoController;
    private ModificaBambinoController modificaBambinoController;
    private AggiungiBambinoController aggiungiBambinoController;


    @FXML
    private void backtohome() throws IOException {

        Parent root = FXMLLoader.load(getClass().getResource("../view/Home.fxml"));
        actual = (Stage) backhome.getScene().getWindow();
        actual.setScene(new Scene(root, backhome.getScene().getWidth(), backhome.getScene().getHeight()));
        actual.show();
    }

    @FXML
    public void initialize() throws IOException, SQLException {

        //FXMLLoader loader = new FXMLLoader(AnagraficaController.class.getResource("../view/TabellePane.fxml"));
        //tabelleinstance = loader.getController();
        //mainpane.setCenter((Pane)loader.load());

        FXMLLoader loader = new FXMLLoader(getClass().getResource("../view/TabellePane.fxml"));
        tabellePane = loader.load();
        tabellePaneController = loader.getController();
        mainpane.setCenter(tabellePane);

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
    private void gotovisualizzapersona() throws IOException {
        alertbox.setText("");
        alertboxerror.setText("");

        FXMLLoader loader;
        Pane visualizzaPane;

        if (tabellePaneController.tabellaAttiva.equals("kid")) {

            Bambino bambino = tabellePaneController.bambinoTable.getSelectionModel().getSelectedItem();
            if (bambino == null){
                alertbox.setText("");
                alertboxerror.setText("Attenzione:\nselezionare\nuna riga !\n");

            }
            else {
                loader = new FXMLLoader(getClass().getResource("../view/VisualizzaBambino.fxml"));
                visualizzaPane = loader.load();
                mainpane.setCenter(visualizzaPane);
                VisualizzaBambinoController controller = loader.getController();
                controller.inizializza(bambino, tabellePane, tabellePaneController);
            }
        } else if (tabellePaneController.tabellaAttiva.equals("genitore")) {

            Genitore genitore = tabellePaneController.genitoreTable.getSelectionModel().getSelectedItem();
            if (genitore == null){
                alertbox.setText("");
                alertboxerror.setText("Attenzione:\nselezionare\nuna riga !\n");

            }
            else {
                loader = new FXMLLoader(getClass().getResource("../view/VisualizzaGenitore.fxml"));
                visualizzaPane = loader.load();
                mainpane.setCenter(visualizzaPane);

                VisualizzaGenitoreController controller = loader.getController();
                controller.inizializza(genitore, tabellePane, tabellePaneController);

            }
        } else if (tabellePaneController.tabellaAttiva.equals("personale")) {

            Personale personale = tabellePaneController.personaleTable.getSelectionModel().getSelectedItem();
            if (personale == null){
                alertbox.setText("");
                alertboxerror.setText("Attenzione:\nselezionare\nuna riga !\n");

            }
            else {
                loader = new FXMLLoader(getClass().getResource("../view/VisualizzaPersonale.fxml"));
                visualizzaPane = loader.load();
                mainpane.setCenter(visualizzaPane);

                VisualizzaPersonaleController controller = loader.getController();
                controller.inizializza(personale, tabellePane, tabellePaneController);

            }

        } else if (tabellePaneController.tabellaAttiva.equals("pediatra")) {

            Pediatra pediatra = tabellePaneController.pediatraTable.getSelectionModel().getSelectedItem();
            if (pediatra == null){
                alertbox.setText("");
                alertboxerror.setText("Attenzione:\nselezionare\nuna riga !\n");

            }
            else {
                loader = new FXMLLoader(getClass().getResource("../view/VisualizzaPediatra.fxml"));
                visualizzaPane = loader.load();
                mainpane.setCenter(visualizzaPane);

                VisualizzaPediatraController controller = loader.getController();
                controller.inizializza(pediatra, tabellePane, tabellePaneController);

            }
        } else if (tabellePaneController.tabellaAttiva.equals("fornitore")) {

            Fornitore fornitore = tabellePaneController.fornitoreTable.getSelectionModel().getSelectedItem();
            if (fornitore == null){
                    alertbox.setText("");
                    alertboxerror.setText("Attenzione:\nselezionare\nuna riga !\n");
            }
            else {
                loader = new FXMLLoader(getClass().getResource("../view/VisualizzaFornitore.fxml"));
                visualizzaPane = loader.load();
                mainpane.setCenter(visualizzaPane);

                VisualizzaFornitoreController controller = loader.getController();
                controller.inizializza(fornitore, tabellePane, tabellePaneController);

            }
        }
    }

    @FXML
    private void modificaAction() throws IOException {
        alertbox.setText("");
        alertboxerror.setText("");

        FXMLLoader loader;
        Pane modificapane;
        if (tabellePaneController.tabellaAttiva.equals("kid")) {
            //loader = new FXMLLoader(getClass().getResource("../view/ModificaBambino.fxml"));
            //modificapane = loader.load();
            //mainpane.setCenter(modificapane);
            //ModificaBambinoController controller = loader.getController();
            Bambino bambino = tabellePaneController.bambinoTable.getSelectionModel().getSelectedItem();
            if (bambino == null){
                alertbox.setText("");
                alertboxerror.setText("Attenzione:\nselezionare\nuna riga !\n");

            }
            else{
                loader = new FXMLLoader(getClass().getResource("../view/ModificaBambino.fxml"));
                modificapane = loader.load();
                mainpane.setCenter(modificapane);
                ModificaBambinoController controller = loader.getController();
                controller.inizializza(bambino, tabellePane, tabellePaneController);
            }
            //controller.inizializza(bambino, tabellePane, tabellePaneController);
        } else if (tabellePaneController.tabellaAttiva.equals("genitore")) {

            Genitore genitore = tabellePaneController.genitoreTable.getSelectionModel().getSelectedItem();
            if (genitore == null){
                alertbox.setText("");
                alertboxerror.setText("Attenzione:\nselezionare\nuna riga !\n");

            }
            else{
                loader = new FXMLLoader(getClass().getResource("../view/ModificaGenitore.fxml"));
                modificapane = loader.load();
                mainpane.setCenter(modificapane);
                ModificaGenitoreController controller = loader.getController();
                controller.inizializza(genitore, tabellePane, tabellePaneController);

            }
        } else if (tabellePaneController.tabellaAttiva.equals("personale")) {

            Personale personale = tabellePaneController.personaleTable.getSelectionModel().getSelectedItem();
            if (personale == null){
                alertbox.setText("");
                alertboxerror.setText("Attenzione:\nselezionare\nuna riga !\n");

            }
            else{
                loader = new FXMLLoader(getClass().getResource("../view/ModificaPersonale.fxml"));
                modificapane = loader.load();
                mainpane.setCenter(modificapane);
                ModificaPersonaleController controller = loader.getController();
                controller.inizializza(personale, tabellePane, tabellePaneController);

            }
        } else if (tabellePaneController.tabellaAttiva.equals("pediatra")) {

            Pediatra pediatra = tabellePaneController.pediatraTable.getSelectionModel().getSelectedItem();
            if (pediatra == null){
                alertbox.setText("");
                alertboxerror.setText("Attenzione:\nselezionare\nuna riga !\n");

            }
            else {
                loader = new FXMLLoader(getClass().getResource("../view/ModificaPediatra.fxml"));
                modificapane = loader.load();
                mainpane.setCenter(modificapane);
                ModificaPediatraController controller = loader.getController();
                controller.inizializza(pediatra, tabellePane, tabellePaneController);
            }
        } else if (tabellePaneController.tabellaAttiva.equals("fornitore")) {

            Fornitore fornitore = tabellePaneController.fornitoreTable.getSelectionModel().getSelectedItem();
            if (fornitore == null){
                alertbox.setText("");
                alertboxerror.setText("Attenzione:\nselezionare\nuna riga !\n");

            }
            else {
                loader = new FXMLLoader(getClass().getResource("../view/ModificaFornitore.fxml"));
                modificapane = loader.load();
                mainpane.setCenter(modificapane);
                ModificaFornitoreController controller = loader.getController();
                controller.inizializza(fornitore, tabellePane, tabellePaneController);

            }
        }
    }

    @FXML
    private void gotoaggiungipersona() throws IOException {
        alertbox.setText("");
        alertboxerror.setText("");

        FXMLLoader loader;
        Pane aggiungipane;
        loader = new FXMLLoader(getClass().getResource("../view/ScegliPersona.fxml"));
        aggiungipane = loader.load();
        mainpane.setCenter(aggiungipane);

        ScegliPersonaController controller = loader.getController();
        Bambino bambino = tabellePaneController.bambinoTable.getSelectionModel().getSelectedItem();

        controller.inizializza(mainpane, tabellePane, tabellePaneController);
    }

    @FXML
    private void cancellapersona() throws IOException, SQLException {

        alertbox.setText("");
        alertboxerror.setText("");

        if (tabellePaneController.tabellaAttiva.equals("kid")) {
            String cf;
            iBambinoDAO kidController = NamingContextManager.getKidController();
            Bambino bambino = tabellePaneController.bambinoTable.getSelectionModel().getSelectedItem();

            if (bambino == null){
                alertbox.setText("");
                alertboxerror.setText("Attenzione:\nselezionare\nuna riga !\n");

            }
            else {

                cf = bambino.getCf();
                kidController.cancellaBambino(cf);
                tabellePaneController.refreshTabelle();
                alertboxerror.setText("");
                alertbox.setText("Cancellazione\neffettuata !");


            }

        } else if (tabellePaneController.tabellaAttiva.equals("genitore")) {
            String cf;
            iGenitoreDAO parentsController = NamingContextManager.getParentsController();
            Genitore genitore = tabellePaneController.genitoreTable.getSelectionModel().getSelectedItem();

            if (genitore== null){
                alertbox.setText("");
                alertboxerror.setText("Attenzione:\nselezionare\nuna riga !\n");


            }
            else {
                cf = genitore.getCf();
                parentsController.cancellaGenitore(cf);
                tabellePaneController.refreshTabelle();
                alertboxerror.setText("");
                alertbox.setText("Cancellazione\neffettuata !");
            }

        } else if (tabellePaneController.tabellaAttiva.equals("personale")) {
            String cf;
            iPersonaleDAO personalController = NamingContextManager.getPersonalController();
            Personale personale = tabellePaneController.personaleTable.getSelectionModel().getSelectedItem();

            if (personale == null){
                alertbox.setText("");
                alertboxerror.setText("Attenzione:\nselezionare\nuna riga !\n");


            }
            else {
                cf = personale.getCf();
                personalController.cancellaPersonale(cf);
                tabellePaneController.refreshTabelle();
                alertboxerror.setText("");
                alertbox.setText("Cancellazione\neffettuata !");

            }

        } else if (tabellePaneController.tabellaAttiva.equals("pediatra")) {
            String cf;
            iPediatraDAO pediatraController = NamingContextManager.getPediatraController();
            Pediatra pediatra = tabellePaneController.pediatraTable.getSelectionModel().getSelectedItem();
            if (pediatra == null) {
                alertbox.setText("");
                alertboxerror.setText("Attenzione:\nselezionare\nuna riga !\n");


            } else {

                cf = pediatra.getCf();
                pediatraController.cancellaPediatra(cf);
                tabellePaneController.refreshTabelle();
                alertboxerror.setText("");
                alertbox.setText("Cancellazione\neffettuata !");
            }


        } else if (tabellePaneController.tabellaAttiva.equals("fornitore")) {
            String cf;
            iFornitoreDAO providersController = NamingContextManager.getProvidersController();
            Fornitore fornitore = tabellePaneController.fornitoreTable.getSelectionModel().getSelectedItem();
            if (fornitore == null) {
                alertbox.setText("");
                alertboxerror.setText("Attenzione:\nselezionare\nuna riga !\n");

            }
            else {
                cf = fornitore.getCf();
                providersController.cancellaFornitore(cf);
                tabellePaneController.refreshTabelle();
                alertboxerror.setText("");
                alertbox.setText("Cancellazione\neffettuata !");
            }
        }
    }

    @FXML
    private void allergieAction() throws IOException{

        FXMLLoader loader;
        Pane allergiepane;
        if (!(tabellePaneController.tabellaAttiva.equals("kid"))) {
            alertbox.setText("");
            alertboxerror.setText("Attenzione:\nfunzione\ndisponibile\nsolo per bambino\n");
        }else{
                Bambino bambino = tabellePaneController.bambinoTable.getSelectionModel().getSelectedItem();
                if (bambino == null) {
                    alertbox.setText("");
                    alertboxerror.setText("Attenzione:\nselezionare\nuna riga !\n");

                } else {
                    alertbox.setText("");
                    alertboxerror.setText("");
                    loader = new FXMLLoader(getClass().getResource("../view/AggiungiAllergie.fxml"));
                    allergiepane = loader.load();
                    mainpane.setCenter(allergiepane);
                    AggiungiAllergieController controller = loader.getController();
                    controller.inizializza(bambino, tabellePane, tabellePaneController);
                }
            }
        }
    }