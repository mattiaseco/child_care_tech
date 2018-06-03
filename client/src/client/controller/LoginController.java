package client.controller;


import client.NamingContextManager;
import common.Classes.Bambino;
import common.Classes.Genitore;
import common.Interface.iLoginController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;

public class LoginController {


    @FXML private TextField usernamefield;

    @FXML private PasswordField passwordfield;

    @FXML private Button loginbutton;

    @FXML private Button signupbutton;

    @FXML private AnchorPane rootpane;

    @FXML private RadioButton rmibutton;
    @FXML private RadioButton socketbutton;
    @FXML private ToggleGroup choice;

    @FXML private Text alertbox;

    private Stage actual;

    @FXML
    public void initialize() {
        rootpane.addEventHandler(KeyEvent.KEY_PRESSED, ev -> {
            if(ev.getCode() == KeyCode.ENTER) {
                try {
                    loginaction(null);
                } catch(IOException ex){
                    ex.printStackTrace();
                }
                ev.consume();
            }
        });
    }

    @FXML
    private void loginaction(ActionEvent event)throws IOException {
        iLoginController loginController = NamingContextManager.getLoginController();
        String userName = usernamefield.getText();
        String password = passwordfield.getText();
        alertbox.setText("");
        if (userName.equals("") || password.equals("")) {
            alertbox.setText("Attenzione: Username o Password vuoti");
        }
        else if(choice.getSelectedToggle() == null) {
            alertbox.setText("Attenzione: selezionare un tipo di collegamento !");
            return;
        }
        else if (choice.getSelectedToggle().equals(rmibutton)) {
            if (loginController != null && loginController.login(userName, password)) {
                alertbox.setText("Signin: " + usernamefield.getText() + "!");

                Parent root= FXMLLoader.load(getClass().getResource("../view/Home.fxml"));
                actual =(Stage)loginbutton.getScene().getWindow();
                actual.setScene(new Scene(root,loginbutton.getScene().getWidth(),loginbutton.getScene().getHeight()));
                actual.show();
            }
            else
                alertbox.setText("Attenzione: Username o Password non validi");
        }
        else if (choice.getSelectedToggle().equals(socketbutton)){
            alertbox.setText("COLLEGAMENTO IN SOCKET NON ATTIVO");
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