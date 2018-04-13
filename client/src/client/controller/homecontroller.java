package client.controller;


import javafx.event.ActionEvent;
import javafx.fxml.FXML;

import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;

public class homecontroller {

    @FXML private Button anagraficabutton;

    @FXML private Button mensabutton;

    @FXML private Button gitebutton;

    @FXML private Button accessibutton;

    @FXML private AnchorPane homepane;

    @FXML
    private void gotoanagrafica(ActionEvent event)throws IOException {
        AnchorPane pane = FXMLLoader.load(getClass().getResource("../view/anagrafica.fxml"));
        homepane.getChildren().setAll(pane);
    }

    @FXML
    private void gotomensa(ActionEvent event)throws IOException {
        AnchorPane pane = FXMLLoader.load(getClass().getResource("../view/mensa.fxml"));
        homepane.getChildren().setAll(pane);
    }

    @FXML
    private void gotogite(ActionEvent event)throws IOException {
        AnchorPane pane = FXMLLoader.load(getClass().getResource("../view/gite.fxml"));
        homepane.getChildren().setAll(pane);
    }

    @FXML
    private void gotoaccessi(ActionEvent event)throws IOException {
        AnchorPane pane = FXMLLoader.load(getClass().getResource("../view/controlloaccessi.fxml"));
        homepane.getChildren().setAll(pane);
    }




}
