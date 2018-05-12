package client.controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;

import java.awt.event.ActionEvent;
import java.io.IOException;


public class modificapersonacontroller extends  AnchorPane{

    @FXML
    Button annullabutton;

    @FXML
    Button confermabutton;

    @FXML AnchorPane modificapane;

    @FXML
    public void returntotabellepane()throws IOException {

        modificapane= FXMLLoader.load(getClass().getResource("../view/anagrafica.fxml"));
    }

}
