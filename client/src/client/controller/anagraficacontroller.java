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

public class anagraficacontroller {

    @FXML
    public BorderPane mainpane;

    @FXML private Button visualizzabutton;
    @FXML private Button modificabutton;
    @FXML private Button aggiungibutton;
    @FXML private Button cancellabutton;

    @FXML
    private ImageView backhome;

    private Stage actual;

    private tabellepanecontroller tabelleinstance = new tabellepanecontroller();
    private visualizzapersonacontroller visualizzainstance = new visualizzapersonacontroller();
    private modificapersonacontroller modivicainstance = new modificapersonacontroller();
    private aggiungipersonacontroller aggiungiinstance = new aggiungipersonacontroller();


    @FXML
    private void backtohome()throws IOException {
        Parent root= FXMLLoader.load(getClass().getResource("../view/home.fxml"));
        actual =(Stage)backhome.getScene().getWindow();
        actual.setScene(new Scene(root,backhome.getScene().getWidth(),backhome.getScene().getHeight()));
        actual.show();
    }

    @FXML
    public void initialize() throws IOException{
        FXMLLoader loader = new FXMLLoader(anagraficacontroller.class.getResource("../view/tabellepane.fxml"));
        tabelleinstance = loader.getController();
        mainpane.setCenter((Pane)loader.load());

        //mainpane.setCenter((Pane)FXMLLoader.load(getClass().getResource("../view/tabellepane.fxml")));

    }

    @FXML
    private void gotovisualizzapersona()throws IOException {

        mainpane.setCenter((Pane)FXMLLoader.load(getClass().getResource("../view/visualizzapersona.fxml")));
    }

    @FXML
    private void gotomodificapersona()throws IOException {

        mainpane.setCenter((Pane)FXMLLoader.load(getClass().getResource("../view/modificapersona.fxml")));

    }

    @FXML
    private void gotoaggiungipersona()throws IOException {

        mainpane.setCenter((Pane)FXMLLoader.load(getClass().getResource("../view/aggiungipersona.fxml")));

    }

    //@FXML
    //private void cancellapersona()throws IOException { }
}