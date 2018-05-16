package client.controller;

import client.NamingContextManager;
import common.Interface.iBambinoDAO;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;

import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDate;

public class ModificaFornitoreController {

    @FXML
    AnchorPane modificapane;

    @FXML
    private void returnToTabellePane()throws IOException {

        ((BorderPane)modificapane.getParent()).setCenter((Pane) FXMLLoader.load(getClass().getResource("../view/TabellePane.fxml")));

    }
    @FXML
    private void modificaFornitore() throws IOException, SQLException {



        ((BorderPane)modificapane.getParent()).setCenter((Pane)FXMLLoader.load(getClass().getResource("../view/TabellePane.fxml")));



    }
}
