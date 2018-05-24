package client.controller;


import client.NamingContextManager;
import com.sun.xml.internal.ws.policy.privateutil.PolicyUtils;
import common.Classes.Gita;
import common.Interface.iGitaDAO;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.IOException;
import java.rmi.RemoteException;
import java.sql.SQLException;


public class GiteController {

    @FXML
    private TabelleGiteController tabelleGitaController;
    @FXML
    public static Pane tabellePane;
    @FXML
    private ImageView backhome;
    @FXML
    private BorderPane mainpane;
    @FXML
    private FXMLLoader loader;
    @FXML
    private AnchorPane gitepane;

    private Stage actual;

    @FXML
    private void backtohome()throws IOException {

        Parent root= FXMLLoader.load(getClass().getResource("../view/Home.fxml"));
        actual =(Stage)backhome.getScene().getWindow();
        actual.setScene(new Scene(root,backhome.getScene().getWidth(),backhome.getScene().getHeight()));
        actual.show();
    }

    @FXML
    private void goToAggiungiGita(ActionEvent event) throws IOException{

        loader = new FXMLLoader(getClass().getResource("../view/Step1AggiungiGita.fxml"));
        gitepane = loader.load();
        Step1AggiungiGitaController controller = loader.getController();
        controller.inizializza(tabellePane, tabelleGitaController, mainpane);
        mainpane.setCenter(gitepane);
    }

    @FXML
    private void gotoVisualizzaGita(ActionEvent event) throws IOException{


    }

    @FXML
    private void cancellaGita(ActionEvent event) throws IOException, SQLException {

        int cod_g;
        iGitaDAO gitaController = NamingContextManager.getTripsController();
        Gita gita = tabelleGitaController.giteTable.getSelectionModel().getSelectedItem();


        cod_g = gita.getCodice_g();
        gitaController.cancellaGita(cod_g);


    }

}
