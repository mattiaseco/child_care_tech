package client.controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;

import java.io.IOException;

public class ScegliPersonaController {

    @FXML
    RadioButton BambinoButton;
    @FXML
    RadioButton GenitoreButton;
    @FXML
    RadioButton PersonaleButton;
    @FXML
    RadioButton PediatraButton;
    @FXML
    RadioButton FornitoreButton;

    @FXML
    Button confermabutton;

    @FXML
    AnchorPane scegliPersonaPane;

    @FXML
    private void goToBambino()throws IOException {

        ((BorderPane)scegliPersonaPane.getParent()).setCenter((Pane)FXMLLoader.load(getClass().getResource("../view/AggingiBambino.fxml")));

    }
    @FXML
    private void goToGenitore()throws IOException {

        ((BorderPane)scegliPersonaPane.getParent()).setCenter((Pane)FXMLLoader.load(getClass().getResource("../view/AggingiGenitore.fxml")));

    }
    @FXML
    private void goToPersonale()throws IOException {

        ((BorderPane)scegliPersonaPane.getParent()).setCenter((Pane)FXMLLoader.load(getClass().getResource("../view/AggingiPersonale.fxml")));

    }
    @FXML
    private void goToPediatra()throws IOException {

        ((BorderPane)scegliPersonaPane.getParent()).setCenter((Pane)FXMLLoader.load(getClass().getResource("../view/AggingiPediatra.fxml")));

    }
    @FXML
    private void goToFornitore()throws IOException {

        ((BorderPane)scegliPersonaPane.getParent()).setCenter((Pane)FXMLLoader.load(getClass().getResource("../view/AggingiFornitore.fxml")));

    }

}
