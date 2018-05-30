package client.controller;

import client.NamingContextManager;
import com.sun.xml.internal.ws.policy.privateutil.PolicyUtils;
import common.Interface.iPullmanDAO;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;

import java.io.IOException;
import java.sql.SQLException;


public class AggiungiPullmanController {

     @FXML private Text alertbox;
     @FXML private BorderPane mainpane;
     @FXML private FXMLLoader loader;
     @FXML private AnchorPane gitepane;
     @FXML private Pane tabelleGitaPane;
     @FXML private TabelleGiteController tabelleGiteController;

     @FXML private TextField targaField;
     @FXML private TextField capienzaField;

    public void inizializza(Pane tabelleGitaPane, TabelleGiteController tabelleGiteController, BorderPane mainpane){

        this.mainpane = mainpane;
        this.tabelleGitaPane = tabelleGitaPane;
        this.tabelleGiteController = tabelleGiteController;
    }


    public void returnToTabellePane() throws IOException{

        ((BorderPane)gitepane.getParent()).setCenter(tabelleGitaPane);
    }

    public void aggiungiPullman() throws IOException, SQLException{

        alertbox.setText("");

        iPullmanDAO pullmanController = NamingContextManager.getPullmanController();
        Pane gitepane3;
        FXMLLoader loader;

        String targa;
        Integer capienza;

        targa = targaField.getText();
        capienza = Integer.parseInt(capienzaField.getText());


        /*if (pullmanController.getAllTarghe().contains(targa)){
            alertbox.setText("Attenzione: targa  già presente");
        }else */if (targa.isEmpty() || capienza == null){

            alertbox.setText("ATTENZIONE:Inserire i campi obbligatori!");

        }
        else if ( capienza > 50 ){

            alertbox.setText("ATTENZIONE: Capienza Massima 50!");
        }

        else if( targa.length() < 7 ){

            alertbox.setText("ATTENZIONE:Targa troppo corta!");
        }
        else if( targa.length() > 7){
            alertbox.setText("ATTENZIONE:Targa troppo lunga!");
        }
        else{

            pullmanController.inserisciPullman(targa,capienza);
            loader = new FXMLLoader(getClass().getResource("../view/Step3AggiungiGita.fxml"));
            gitepane3= loader.load();
            mainpane.setCenter(gitepane3);

        }

    }


}
