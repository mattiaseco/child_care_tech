package client.controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;

import java.io.IOException;

public class Step3MenuController {
    @FXML
    private Button annullabutton;
    @FXML private Button confermaButton;

    @FXML public AnchorPane step3MenuPane;

    @FXML
    public void returnToTabelleMenu()throws IOException {

    }

    @FXML
    public void salvaMenu()throws IOException {
        ((BorderPane)step3MenuPane.getParent()).setCenter(FXMLLoader.load(getClass().getResource("../view/TabelleMenu.fxml")));

    }
}
