package client.controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;

import java.io.IOException;


public class aggiungipersonacontroller extends AnchorPane {

    @FXML
    Button annullabutton;

    @FXML
    Button confermabutton;

    @FXML
    AnchorPane aggiungipane;

    @FXML
    private void returntotabellepane()throws IOException {

        ((BorderPane)aggiungipane.getParent()).setCenter((Pane)FXMLLoader.load(getClass().getResource("../view/tabellepane.fxml")));

        //Parent root= FXMLLoader.load(getClass().getResource("../view/anagrafica.fxml"));
        //actual =(Stage)annullabutton.getScene().getWindow();
        //actual.setScene(new Scene(root,annullabutton.getScene().getWidth(),annullabutton.getScene().getHeight()));
        //actual.show();
    }

    @FXML
    private void aggiungipersona()throws IOException {

        ((BorderPane)aggiungipane.getParent()).setCenter((Pane)FXMLLoader.load(getClass().getResource("../view/tabellepane.fxml")));



        //qui dovresti chiamare il metodo



    }
}
