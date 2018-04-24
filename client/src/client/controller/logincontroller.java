package client.controller;


import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;

public class logincontroller{


    @FXML private TextField usernamefield;

    @FXML private PasswordField passwordfield;

    @FXML private Button loginbutton;

    @FXML private Button signupbutton;

    //@FXML private RadioButton rmibutton;

    //@FXML private RadioButton socketbutton;

    @FXML private Text alertbox;

    private Stage actual;



    @FXML
    private void loginaction(ActionEvent event)throws IOException {

        alertbox.setText("");
        if (usernamefield.getText().equals("") || passwordfield.getText().equals("")) {
            alertbox.setText("Warning: Username or Password empty");
        }
        else{
            if (passwordfield.getText().equals("default")) {
                alertbox.setText("Signed in as " + usernamefield.getText() + "!");

                Parent root= FXMLLoader.load(getClass().getResource("../view/home.fxml"));
                actual =(Stage)loginbutton.getScene().getWindow();
                actual.setScene(new Scene(root,loginbutton.getScene().getWidth(),loginbutton.getScene().getHeight()));
                actual.show();



            }
            else
                alertbox.setText("Warning: Username or Password not valid");
        }

    }
    @FXML
    private void signupaction(ActionEvent event)throws IOException {

        Parent root= FXMLLoader.load(getClass().getResource("../view/signup.fxml"));
        actual =(Stage)signupbutton.getScene().getWindow();
        actual.setScene(new Scene(root,signupbutton.getScene().getWidth(),signupbutton.getScene().getHeight()));
        actual.show();

    }

}