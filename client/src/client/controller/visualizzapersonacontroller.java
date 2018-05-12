package client.controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;


public class visualizzapersonacontroller extends AnchorPane {

    @FXML
    Button annullabutton;

    @FXML AnchorPane visualizzapane;

    private Stage actual;


    @FXML
    private void returntotabellepane()throws IOException {

        Parent root= FXMLLoader.load(getClass().getResource("../view/anagrafica.fxml"));
        actual =(Stage)annullabutton.getScene().getWindow();
        actual.setScene(new Scene(root,annullabutton.getScene().getWidth(),annullabutton.getScene().getHeight()));
        actual.show();    }
}
