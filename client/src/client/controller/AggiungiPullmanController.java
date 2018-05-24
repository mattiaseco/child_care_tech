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

import java.io.IOException;
import java.sql.SQLException;


public class AggiungiPullmanController {

     @FXML private BorderPane mainpane;
     @FXML private FXMLLoader loader;
     @FXML private AnchorPane aggiungipane;
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

        loader = new FXMLLoader(getClass().getResource("../view/Step3AggiungiGita.fxml"));
        aggiungipane = loader.load();
        Step3AggiungiGitaController controller = loader.getController();
        controller.inizializza(tabelleGitaPane,tabelleGiteController,mainpane);
        mainpane.setCenter(aggiungipane);
    }

    public void aggiungiPullman() throws IOException, SQLException{

        iPullmanDAO pullmanController = NamingContextManager.getPullmanController();
        Pane gitepane3;
        FXMLLoader loader;

        String targa;
        Integer capienza;

        targa = targaField.getText();
        capienza = Integer.parseInt(capienzaField.getText());

        if (targa.isEmpty() || capienza == null){

            //TODO errore "riempire campi obbligatori"

        }
        else if ( capienza > 50 ){

            //TODO pullman max 50 posti
        }

        else if( targa.length() < 7 ){

            //TODO targa troppo corta
        }
        else if( targa.length() > 7){
            //TODO targa troppo lunga
        }
        else{

            pullmanController.inserisciPullman(targa,capienza);


            loader = new FXMLLoader(getClass().getResource("../view/Step3AggiungiGita.fxml"));
            gitepane3= loader.load();
            mainpane.setCenter(gitepane3);

        }

    }


}
