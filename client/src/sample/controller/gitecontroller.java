package sample.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;

public class gitecontroller {

    @FXML private Button backhome;

    @FXML private AnchorPane gitepane;

    @FXML
    private void backtohome(ActionEvent event)throws IOException {
        AnchorPane pane = FXMLLoader.load(getClass().getResource("../view/home.fxml"));
        gitepane.getChildren().setAll(pane);
    }
}
