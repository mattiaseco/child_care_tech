package client.controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;

import java.io.IOException;


public class modificapersonacontroller extends  AnchorPane{

    @FXML
    Button annullabutton;

    @FXML
    Button confermabutton;

    @FXML AnchorPane modificapane;

    @FXML
    private void returntotabellepane()throws IOException {

        ((BorderPane)modificapane.getParent()).setCenter((Pane)FXMLLoader.load(getClass().getResource("../view/tabellepane.fxml")));

    }
    @FXML
    private void modificapersona()throws IOException {

        ((BorderPane)modificapane.getParent()).setCenter((Pane)FXMLLoader.load(getClass().getResource("../view/tabellepane.fxml")));



        //qui dovresti chiamare il metodo



    }

}
