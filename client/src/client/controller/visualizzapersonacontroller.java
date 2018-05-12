package client.controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;

import java.io.IOException;


public class visualizzapersonacontroller extends AnchorPane {

    @FXML
    Button annullabutton;

    @FXML AnchorPane visualizzapane;



    @FXML
    private void returntotabellepane()throws IOException {

        ((BorderPane)visualizzapane.getParent()).setCenter((Pane)FXMLLoader.load(getClass().getResource("../view/tabellepane.fxml")));

    }

}
