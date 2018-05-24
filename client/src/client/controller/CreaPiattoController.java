package client.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;

import java.io.IOException;

public class CreaPiattoController {

    @FXML RadioButton primobutton;
    @FXML RadioButton secondobutton;
    @FXML RadioButton contornobutton;
    @FXML Button annullabutton;
    @FXML Button confermabutton;
    @FXML ToggleGroup tipoPiatto;

    @FXML AnchorPane piattopane;

    @FXML TextField nomepField;
    @FXML private Text alertbox;

    @FXML
    private void salvaPiatto() throws IOException {
        alertbox.setText("");
    }
    @FXML
    private void returnToTabelleMenu() throws IOException {
        alertbox.setText("");
    }

}
