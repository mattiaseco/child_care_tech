package client.controller;

import javafx.fxml.FXML;

import javafx.event.ActionEvent;

import javafx.fxml.FXMLLoader;

import javafx.scene.control.Button;
//import javafx.scene.control.RadioButton;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;

import java.io.IOException;


public class logincontroller{

    @FXML private AnchorPane rootpane;

    @FXML private TextField usernamefield;

    @FXML private PasswordField passwordfield;

    @FXML private Button loginbutton;

    //@FXML private RadioButton rmibutton;

    //@FXML private RadioButton socketbutton;

    @FXML private Text alertbox;



    @FXML
    private void loginaction(ActionEvent event)throws IOException{

        alertbox.setText("");
        if (usernamefield.getText().equals("") || passwordfield.getText().equals("")) {
            alertbox.setText("Warning: Username or Password empty");
        }
        else{
            if (passwordfield.getText().equals("default")) {
                alertbox.setText("Signed in as " + usernamefield.getText() + "!");
                AnchorPane pane = FXMLLoader.load(getClass().getResource("../view/home.fxml"));
                rootpane.getChildren().setAll(pane);
            }
            else
                alertbox.setText("Warning: Username or Password not valid");
        }

    }

}
