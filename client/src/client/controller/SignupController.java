package client.controller;


import client.NamingContextManager;
import common.Interface.iRegisterController;
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
import java.sql.SQLException;

public class SignupController {


    @FXML private TextField usernamefield;

    @FXML private PasswordField passwordfield;

    @FXML private PasswordField confirmpasswordfield;

    @FXML private Button signupbutton2;

    @FXML private Text alertbox;

    private Stage actual;



    @FXML
    private void signupaction2(ActionEvent event) throws IOException, SQLException {

        iRegisterController registerController = NamingContextManager.getRegisterController();
        String username = usernamefield.getText();
        String password = passwordfield.getText();
        alertbox.setText("");
        if (username.equals("") || password.equals("")) {
            alertbox.setText("Warning: Username or Password empty");
        }
        else{
            if (passwordfield.getText().equals(confirmpasswordfield.getText())) {

                if(registerController != null) {
                    registerController.register(username, password);
                    alertbox.setText("Signed in as " + usernamefield.getText() + "!");

                    Parent root = FXMLLoader.load(getClass().getResource("../view/Login.fxml"));
                    actual = (Stage) signupbutton2.getScene().getWindow();
                    actual.setScene(new Scene(root, signupbutton2.getScene().getWidth(), signupbutton2.getScene().getHeight()));
                    actual.show();
                }
                else return;

            }
            else
                alertbox.setText("Warning: Password not match");
        }

    }

}