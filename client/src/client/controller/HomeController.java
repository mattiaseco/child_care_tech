package client.controller;


import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

import java.io.IOException;

public class HomeController {

    @FXML
    private ImageView anagraficabutton;

    @FXML private ImageView mensabutton;

    @FXML private ImageView gitebutton;

    @FXML private ImageView accessibutton;

    @FXML private Button logoutbutton;


    private Stage actual;


    @FXML
    private void gotoanagrafica()throws IOException {

        Parent root= FXMLLoader.load(getClass().getResource("../view/Anagrafica.fxml"));
        actual =(Stage)anagraficabutton.getScene().getWindow();
        actual.setScene(new Scene(root,anagraficabutton.getScene().getWidth(),anagraficabutton.getScene().getHeight()));
        actual.show();



    }

    @FXML
    private void gotomensa()throws IOException {

        Parent root=FXMLLoader.load(getClass().getResource("../view/Mensa.fxml"));
        actual =(Stage)mensabutton.getScene().getWindow();
        actual.setScene(new Scene(root,mensabutton.getScene().getWidth(),mensabutton.getScene().getHeight()));
        actual.show();


    }

    @FXML
    private void gotogite()throws IOException {

        Parent root=FXMLLoader.load(getClass().getResource("../view/Gite.fxml"));
        actual =(Stage)gitebutton.getScene().getWindow();
        actual.setScene(new Scene(root,gitebutton.getScene().getWidth(),gitebutton.getScene().getHeight()));
        actual.show();


    }

    @FXML
    private void gotoaccessi()throws IOException {

        Parent root=FXMLLoader.load(getClass().getResource("../view/ControlloAccessi.fxml"));
        actual =(Stage)accessibutton.getScene().getWindow();
        actual.setScene(new Scene(root,accessibutton.getScene().getWidth(),accessibutton.getScene().getHeight()));
        actual.show();


    }

    @FXML
    private void gotologin()throws IOException {

        Parent root=FXMLLoader.load(getClass().getResource("../view/Login.fxml"));
        actual =(Stage)logoutbutton.getScene().getWindow();
        actual.setScene(new Scene(root,logoutbutton.getScene().getWidth(),logoutbutton.getScene().getHeight()));
        actual.show();


    }




}