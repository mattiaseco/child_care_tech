package client.controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;

public class ControlloAccessiController {

    @FXML private ImageView backhome;
    @FXML public Button QRgitabutton;
    @FXML public Button QRmensabutton;
    @FXML public Button QRscuolabutton;

    @FXML private Text alertbox;
    @FXML private Text alertboxerror;
    private Stage actual;


    @FXML
    private void backtohome()throws IOException {
        Parent root= FXMLLoader.load(getClass().getResource("../view/Home.fxml"));
        actual =(Stage)backhome.getScene().getWindow();
        actual.setScene(new Scene(root,backhome.getScene().getWidth(),backhome.getScene().getHeight()));
        actual.show();
    }
    @FXML
    private void goToPresenzeGita()throws IOException {
        Parent root=FXMLLoader.load(getClass().getResource("../view/GiteQR.fxml"));
        actual =(Stage)QRgitabutton.getScene().getWindow();
        actual.setScene(new Scene(root,QRgitabutton.getScene().getWidth(),QRgitabutton.getScene().getHeight()));
        actual.show();
    }
    @FXML
    private void goToPresenzeMensa()throws IOException {
        Parent root= FXMLLoader.load(getClass().getResource("../view/PresenzeMensa.fxml"));
        actual =(Stage)QRmensabutton.getScene().getWindow();
        actual.setScene(new Scene(root,QRmensabutton.getScene().getWidth(),QRmensabutton.getScene().getHeight()));
        actual.show();
    }
    @FXML
    private void goToPresenzeScuola()throws IOException {
        Parent root= FXMLLoader.load(getClass().getResource("../view/PresenzeScuola.fxml"));
        actual =(Stage)QRscuolabutton.getScene().getWindow();
        actual.setScene(new Scene(root,QRscuolabutton.getScene().getWidth(),QRscuolabutton.getScene().getHeight()));
        actual.show();
    }



}
