package client.controller;

import client.NamingContextManager;
import common.Classes.Bambino;
import common.Classes.Fornitore;
import common.Interface.iBambinoDAO;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;

import java.io.IOException;
import java.rmi.RemoteException;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;

import static client.controller.AnagraficaController.tabellePane;


public class AggiungiBambinoController {

    @FXML private Button annullabutton;
    @FXML private Button confermabutton;

    @FXML private TextField cfField;
    @FXML private TextField nomeField;
    @FXML private TextField cognField;
    @FXML private TextField indField;
    @FXML private TextField cont1Field;
    @FXML private TextField cont2Field;
    @FXML private DatePicker dateField;

    private TabellePaneController tabellePaneController;
    private Pane tabellePane;



    @FXML private Text alertbox;

    @FXML
    AnchorPane aggiungipane;


    public void inizializza(Pane tabellePane, TabellePaneController tabellePaneController){

        this.tabellePane = tabellePane;
        this.tabellePaneController = tabellePaneController;
    }

    @FXML
    public void returnToTabellePane()throws IOException {

        ((BorderPane)aggiungipane.getParent()).setCenter(tabellePane);
        tabellePaneController.refreshTabelle();
        //Parent root= FXMLLoader.load(getClass().getResource("../view/Anagrafica.fxml"));
        //actual =(Stage)annullabutton.getScene().getWindow();
        //actual.setScene(new Scene(root,annullabutton.getScene().getWidth(),annullabutton.getScene().getHeight()));
        //actual.show();
    }

    @FXML
    private void aggiungiPersona() throws IOException, SQLException ,RemoteException {

        alertbox.setText("");

        iBambinoDAO kidController = NamingContextManager.getKidController();
        String cf, nome, cognome, indirizzo, contatto1, contatto2;
        LocalDate data;

        cf = cfField.getText();
        nome = nomeField.getText();
        cognome = cognField.getText();
        data = dateField.getValue();
        indirizzo = indField.getText();
        contatto1 = cont1Field.getText();
        contatto2 = cont2Field.getText();
        //eventualmente cancellare FOR


        if (kidController.getAllCf().contains(cf)) {
            alertbox.setText("Attenzione: codice fiscale già presente");
        }else if(cf.isEmpty() || nome.isEmpty() || cognome.isEmpty() || indirizzo.isEmpty() || contatto1.isEmpty() || data == null){
            alertbox.setText("Attenzione: inserire campi obbligatori (*)");
        }
        else if( cf.length() < 16) {
            alertbox.setText("Attenzione: Codice Fiscale troppo corto !");
        }
        else if ( cf.length() > 16){
            alertbox.setText("Attenzione: Codice Fiscale troppo lungo !");
        }
        else if ( contatto1.length() > 10){
            alertbox.setText("Attenzione: numero di telefono troppo lungo !");
        }
        else if ( contatto2.length() > 10){
            alertbox.setText("Attenzione: numero di telefono troppo lungo !");
        }
        else {
            if(kidController != null) {
                kidController.inserisciBambino(cf, nome, cognome, data, indirizzo, contatto1, contatto2);
                ((BorderPane)aggiungipane.getParent()).setCenter(tabellePane);
                tabellePaneController.refreshTabelle();
            }

        }



    }
}
