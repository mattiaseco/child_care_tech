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
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;
import java.rmi.RemoteException;
import java.sql.SQLException;


public class GiteController {


    @FXML private Text alertbox;
    @FXML private Text alertboxerror;
    @FXML
    private TabelleGiteController tabelleGitaController;
    @FXML
    public static Pane tabelleGita;
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
    public void initialize() throws IOException, SQLException {
        alertbox.setText("");
        alertboxerror.setText("");

        FXMLLoader loader = new FXMLLoader(getClass().getResource("../view/TabelleGite.fxml"));
        tabelleGita = loader.load();
        tabelleGitaController = loader.getController();
        mainpane.setCenter(tabelleGita);

    }

    @FXML
    private void backtohome()throws IOException {

        Parent root= FXMLLoader.load(getClass().getResource("../view/Home.fxml"));
        actual =(Stage)backhome.getScene().getWindow();
        actual.setScene(new Scene(root,backhome.getScene().getWidth(),backhome.getScene().getHeight()));
        actual.show();
    }

    @FXML
    private void goToAggiungiGita(ActionEvent event) throws IOException{
        alertbox.setText("");
        loader = new FXMLLoader(getClass().getResource("../view/Step1AggiungiGita.fxml"));
        gitepane = loader.load();
        Step1AggiungiGitaController controller = loader.getController();
        controller.inizializza(tabelleGita, tabelleGitaController, mainpane);
        mainpane.setCenter(gitepane);
    }

    @FXML
    private void gotoVisualizzaGita(ActionEvent event) throws IOException{

        alertboxerror.setText("");
        alertboxerror.setText("");

        FXMLLoader loader;
        Pane visualizzaGite;

        Gita gita = tabelleGitaController.giteTable.getSelectionModel().getSelectedItem();

        if(gita == null){
            alertboxerror.setText("ATTENZIONE:\nSelezionere\nuna riga!");
        }
        else {

            loader = new FXMLLoader(getClass().getResource("../view/VisualizzaGita.fxml"));
            visualizzaGite = loader.load();
            mainpane.setCenter(visualizzaGite);
            VisualizzaGitaController controller = loader.getController();
            controller.inizializza(tabelleGitaController, tabelleGita, gita);

        }


    }

    @FXML
    private void cancellaGita(ActionEvent event) throws IOException, SQLException {


        alertbox.setText("");
        alertboxerror.setText("");

        int cod_g;

        iGitaDAO gitaController = NamingContextManager.getTripsController();
        Gita gita = tabelleGitaController.giteTable.getSelectionModel().getSelectedItem();

        if (gita == null){
            alertbox.setText("");
            alertboxerror.setText("ATTENZIONE:\nSelezionare\nuna riga!");
        }
        else {
            cod_g = gita.getCodice_g();
            gitaController.cancellaGita(cod_g);
            tabelleGitaController.refreshGiteTables();

            alertboxerror.setText("");
            alertbox.setText("Cancellazione\neffettuata");
        }


    }

}
