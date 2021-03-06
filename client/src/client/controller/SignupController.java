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
import javafx.scene.control.ToggleGroup;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import javax.print.attribute.standard.MediaSize;
import java.io.IOException;
import java.sql.SQLException;

public class SignupController {


    @FXML
    private TextField usernamefield;

    @FXML
    private PasswordField passwordfield;

    @FXML
    private PasswordField confirmpasswordfield;

    @FXML
    private Button signupbutton2;
    @FXML
    private Button annullabutton;

    @FXML
    private AnchorPane signuppane;
    @FXML
    private ToggleGroup choice;

    @FXML
    private Text alertbox;

    private Stage actual;
    private boolean network;

    public void inizializza(boolean network) {
        this.network = network;
    }

    @FXML
    public void initialize() {
        signuppane.addEventHandler(KeyEvent.KEY_PRESSED, ev -> {
            if (ev.getCode() == KeyCode.ENTER) {
                try {
                    signupaction2(null);
                } catch (IOException | SQLException ex) {
                    ex.printStackTrace();
                }
                ev.consume();
            }
        });
    }

    @FXML
    private void signupaction2(ActionEvent event) throws IOException, SQLException {

        iRegisterController registerController = NamingContextManager.getRegisterController();
        String username = usernamefield.getText();
        String password = passwordfield.getText();
        alertbox.setText("");

        if (username.equals("") || password.equals(""))
            alertbox.setText("Attenzione: Username o Password vuoti");

            if (passwordfield.getText().equals(confirmpasswordfield.getText())) {

                if (registerController != null) {
                    registerController.register(username, password);
                    alertbox.setText("Signin: " + usernamefield.getText() + "!");

                    Parent root = FXMLLoader.load(getClass().getResource("../view/Login.fxml"));
                    actual = (Stage) signupbutton2.getScene().getWindow();
                    actual.setScene(new Scene(root, signupbutton2.getScene().getWidth(), signupbutton2.getScene().getHeight()));
                    actual.show();


            } else return;
        } else alertbox.setText("Attenzione: Password diverse");
    }


    @FXML private void returnToLogin(ActionEvent event) throws  IOException{
        Parent root = FXMLLoader.load(getClass().getResource("../view/Login.fxml"));
        actual = (Stage) annullabutton.getScene().getWindow();
        actual.setScene(new Scene(root, annullabutton.getScene().getWidth(), annullabutton.getScene().getHeight()));
        actual.show();
    }

}