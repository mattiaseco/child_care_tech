package client.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;

public class PresenzeGitaController {

    @FXML private Button fineButton;

    private Stage actual;

    @FXML
    private void returnToControlloAccessi(ActionEvent event)throws IOException {

        Parent root= FXMLLoader.load(getClass().getResource("../view/ControlloAccessi.fxml"));
        actual =(Stage)fineButton.getScene().getWindow();
        actual.setScene(new Scene(root,fineButton.getScene().getWidth(),fineButton.getScene().getHeight()));
        actual.show();
    }
}
