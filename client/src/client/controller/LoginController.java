package client.controller;


import client.NamingContextManager;
import common.Interface.iLoginController;
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

public class LoginController {


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
        iLoginController loginController = NamingContextManager.getLoginController();
        String userName = usernamefield.getText();
        String password = passwordfield.getText();
        alertbox.setText("");
        if (userName.equals("") || password.equals("")) {
            alertbox.setText("Warning: Username or Password empty");
        }
        else{
            if (loginController != null && loginController.login(userName, password)) {
                alertbox.setText("Signed in as " + usernamefield.getText() + "!");

                Parent root= FXMLLoader.load(getClass().getResource("../view/Home.fxml"));
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

        Parent root= FXMLLoader.load(getClass().getResource("../view/Signup.fxml"));
        actual =(Stage)signupbutton.getScene().getWindow();
        actual.setScene(new Scene(root,signupbutton.getScene().getWidth(),signupbutton.getScene().getHeight()));
        actual.show();

    }

}