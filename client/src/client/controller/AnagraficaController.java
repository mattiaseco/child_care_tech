package client.controller;



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

    private TabellePaneController tabelleinstance = new TabellePaneController();
    private visualizzapersonacontroller visualizzainstance = new visualizzapersonacontroller();
    private ModificaBambinoController modivicainstance = new ModificaBambinoController();
    private AggiungiBambinoController aggiungiinstance = new AggiungiBambinoController();


    @FXML
    private void backtohome()throws IOException {
        Parent root= FXMLLoader.load(getClass().getResource("../view/Home.fxml"));
        actual =(Stage)backhome.getScene().getWindow();
        actual.setScene(new Scene(root,backhome.getScene().getWidth(),backhome.getScene().getHeight()));
        actual.show();
    }

    @FXML
    public void initialize() throws IOException{
        FXMLLoader loader = new FXMLLoader(AnagraficaController.class.getResource("../view/TabellePane.fxml"));
        tabelleinstance = loader.getController();
        mainpane.setCenter((Pane)loader.load());

        //mainpane.setCenter((Pane)FXMLLoader.load(getClass().getResource("../view/TabellePane.fxml")));

    }

    @FXML
    private void gotovisualizzapersona()throws IOException {

        mainpane.setCenter((Pane)FXMLLoader.load(getClass().getResource("../view/VisualizzaBambino.fxml")));
    }

    @FXML
    private void gotomodificapersona()throws IOException {

        mainpane.setCenter((Pane)FXMLLoader.load(getClass().getResource("../view/ModificaBambino.fxml")));

    }

    @FXML
    private void gotoaggiungipersona()throws IOException {

        mainpane.setCenter((Pane)FXMLLoader.load(getClass().getResource("../view/AggiungiBambino.fxml")));

    }

    //@FXML
    //private void cancellapersona()throws IOException { }
}