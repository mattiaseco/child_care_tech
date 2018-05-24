package client.controller;

import javafx.fxml.FXML;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;

import java.io.IOException;

public class Step3AggiungiGitaController {

    @FXML
    AnchorPane gitepane;

    private Pane tabelleGitaPene;
    private TabelleGiteController tabelleGiteController;
    private BorderPane mainpane;

    public void inizializza(Pane tabelleGitaPene, TabelleGiteController tabelleGitaController, BorderPane mainpane){

        this.tabelleGitaPene = tabelleGitaPene;
        this.tabelleGiteController = tabelleGitaController;
        this.mainpane = mainpane;
    }


    @FXML
    private void returnToGitePane() throws IOException {

        ((BorderPane)gitepane.getParent()).setCenter(tabelleGitaPene);
        // tabelleGiteController.refreshGiteTables();

    }
    public void goToAggiungiPullman(){

    }

    public void goToGitePane(){


    }

}