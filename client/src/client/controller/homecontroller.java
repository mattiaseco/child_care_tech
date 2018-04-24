package client.controller;


import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

import java.io.IOException;

public class homecontroller {

    @FXML
    private ImageView anagraficabutton;

    @FXML private ImageView mensabutton;

    @FXML private ImageView gitebutton;

    @FXML private ImageView accessibutton;


    private Stage actual;


    @FXML
    private void gotoanagrafica()throws IOException {

        Parent root= FXMLLoader.load(getClass().getResource("../view/anagrafica.fxml"));
        actual =(Stage)anagraficabutton.getScene().getWindow();
        actual.setScene(new Scene(root,anagraficabutton.getScene().getWidth(),anagraficabutton.getScene().getHeight()));
        actual.show();



    }

    @FXML
    private void gotomensa()throws IOException {

        Parent root=FXMLLoader.load(getClass().getResource("../view/mensa.fxml"));
        actual =(Stage)mensabutton.getScene().getWindow();
        actual.setScene(new Scene(root,mensabutton.getScene().getWidth(),mensabutton.getScene().getHeight()));
        actual.show();


    }

    @FXML
    private void gotogite()throws IOException {

        Parent root=FXMLLoader.load(getClass().getResource("../view/gite.fxml"));
        actual =(Stage)gitebutton.getScene().getWindow();
        actual.setScene(new Scene(root,gitebutton.getScene().getWidth(),gitebutton.getScene().getHeight()));
        actual.show();


    }

    @FXML
    private void gotoaccessi()throws IOException {

        Parent root=FXMLLoader.load(getClass().getResource("../view/controlloaccessi.fxml"));
        actual =(Stage)accessibutton.getScene().getWindow();
        actual.setScene(new Scene(root,accessibutton.getScene().getWidth(),accessibutton.getScene().getHeight()));
        actual.show();


    }




}