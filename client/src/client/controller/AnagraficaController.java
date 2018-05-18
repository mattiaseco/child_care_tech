package client.controller;



import client.NamingContextManager;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.SQLException;

public class AnagraficaController {

    @FXML
    public BorderPane mainpane;

    @FXML private Button visualizzabutton;
    @FXML private Button modificabutton;
    @FXML private Button aggiungibutton;
    @FXML private Button cancellabutton;

    @FXML
    private ImageView backhome;

    private Stage actual;

    private TabellePaneController tabellePaneController;
    public static Pane tabellePane;

    private VisualizzaBambinoController visualizzaBambinoController;
    private ModificaBambinoController modificaBambinoController;
    private AggiungiBambinoController aggiungiBambinoController;


    @FXML
    private void backtohome()throws IOException {
        Parent root= FXMLLoader.load(getClass().getResource("../view/Home.fxml"));
        actual =(Stage)backhome.getScene().getWindow();
        actual.setScene(new Scene(root,backhome.getScene().getWidth(),backhome.getScene().getHeight()));
        actual.show();
    }

    @FXML
    public void initialize() throws IOException,SQLException {
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
    private void gotovisualizzapersona()throws IOException {
        mainpane.setCenter((Pane)FXMLLoader.load(getClass().getResource("../view/VisualizzaBambino.fxml")));
    }

    @FXML
    private void modificaAction()throws IOException {
        if(tabellePaneController.tabellaAttiva.equals("kid"))
            mainpane.setCenter((Pane)FXMLLoader.load(getClass().getResource("../view/ModificaBambino.fxml")));
        else if(tabellePaneController.tabellaAttiva.equals("genitore"))
            mainpane.setCenter((Pane)FXMLLoader.load(getClass().getResource("../view/ModificaGenitore.fxml")));
        else if(tabellePaneController.tabellaAttiva.equals("personale"))
            mainpane.setCenter((Pane)FXMLLoader.load(getClass().getResource("../view/ModificaPersonale.fxml")));
        else if(tabellePaneController.tabellaAttiva.equals("pediatra"))
            mainpane.setCenter((Pane)FXMLLoader.load(getClass().getResource("../view/ModificaPediatra.fxml")));
        else if(tabellePaneController.tabellaAttiva.equals("fornitore"))
            mainpane.setCenter((Pane)FXMLLoader.load(getClass().getResource("../view/ModificaFornitore.fxml")));

    }

    @FXML
    private void gotoaggiungipersona()throws IOException {

        mainpane.setCenter((Pane)FXMLLoader.load(getClass().getResource("../view/ScegliPersona.fxml")));

    }

    //@FXML
    //private void cancellapersona()throws IOException { }
}