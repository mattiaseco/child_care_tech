package client.controller;

import client.NamingContextManager;
import common.Classes.Gita;
import common.Interface.iGitaDAO;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.SQLException;

public class GiteQRController {

     @FXML private Text alertboxerror;

     @FXML private TabelleGiteController tabelleGitaController;
     @FXML public static Pane tabelleGita;

     @FXML private ImageView backhome;

     @FXML private BorderPane mainpane;

     @FXML private FXMLLoader loader;

     @FXML private AnchorPane giteQrPpane;
     @FXML private Button partenzaButton;
     @FXML private Button TappeButton;

     private Stage actual;

     @FXML
     public void initialize() throws IOException, SQLException {
         alertboxerror.setText("");

         FXMLLoader loader = new FXMLLoader(getClass().getResource("../view/TabelleGite.fxml"));
         tabelleGita = loader.load();
         tabelleGitaController = loader.getController();
         mainpane.setCenter(tabelleGita);

     }
     @FXML
     private void backtohome()throws IOException {

         Parent root= FXMLLoader.load(getClass().getResource("../view/ControlloAccessi.fxml"));
         actual =(Stage)backhome.getScene().getWindow();
         actual.setScene(new Scene(root,backhome.getScene().getWidth(),backhome.getScene().getHeight()));
         actual.show();
     }

     @FXML
     private void goToPresenzeIniziali(ActionEvent event) throws IOException{

         loader = new FXMLLoader(getClass().getResource("../view/PresenzeGita.fxml"));
         giteQrPpane = loader.load();

         //Step1AggiungiGitaController controller = loader.getController();
         //controller.inizializza(tabelleGita, tabelleGitaController, mainpane);

         mainpane.setCenter(giteQrPpane);
     }

     @FXML
     private void goToPresenzeTappa(ActionEvent event) throws IOException{

         loader = new FXMLLoader(getClass().getResource("../view/PresenzeGita.fxml"));
         giteQrPpane = loader.load();

         //Step1AggiungiGitaController controller = loader.getController();
         //controller.inizializza(tabelleGita, tabelleGitaController, mainpane);

         mainpane.setCenter(giteQrPpane);
     }

}