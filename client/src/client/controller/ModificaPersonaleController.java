package client.controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;

import java.io.IOException;
import java.sql.SQLException;

public class ModificaPersonaleController {
    @FXML
    AnchorPane modificapane;

    @FXML
    private void returnToTabellePane()throws IOException {

        ((BorderPane)modificapane.getParent()).setCenter((Pane) FXMLLoader.load(getClass().getResource("../view/TabellePane.fxml")));

    }
    @FXML
    private void modificaPersonale() throws IOException, SQLException {



        ((BorderPane)modificapane.getParent()).setCenter((Pane)FXMLLoader.load(getClass().getResource("../view/TabellePane.fxml")));



    }
}
