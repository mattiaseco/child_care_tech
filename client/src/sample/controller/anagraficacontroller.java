package sample.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;

public class anagraficacontroller {

    @FXML private Button backhome;

    @FXML private AnchorPane anagraficapane;

    @FXML
    private void backtohome(ActionEvent event)throws IOException {
        AnchorPane pane = FXMLLoader.load(getClass().getResource("../view/home.fxml"));
        anagraficapane.getChildren().setAll(pane);
    }


}
