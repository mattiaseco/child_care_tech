package client.controller;

import client.NamingContextManager;
import common.Interface.iFornitoreDAO;
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
import java.sql.SQLException;
import java.time.LocalDate;

public class AggiungiFornitoreController extends AnchorPane {

    @FXML private Button annullabutton;
    @FXML private Button confermabutton;

    @FXML private TextField cfField;
    @FXML private TextField nomeField;
    @FXML private TextField cognField;
    @FXML private TextField indField;
    @FXML private TextField telField;
    @FXML private TextField partivaField;
    @FXML private DatePicker dataField;

    @FXML
    AnchorPane aggiungipane;

    @FXML private Text alertbox;


    @FXML
    public void returnToTabellePane() throws IOException {

        ((BorderPane) aggiungipane.getParent()).setCenter((Pane) FXMLLoader.load(getClass().getResource("../view/TabellePane.fxml")));

    }
    @FXML
    private void aggiungiFornitore() throws IOException, SQLException {

        alertbox.setText("");

        iFornitoreDAO providersController = NamingContextManager.getProvidersController();
        String nome, cognome, indirizzo, telefono, partitaIVA, cf;
        LocalDate data;

        nome = nomeField.getText();
        cognome = cognField.getText();
        cf = cfField.getText();
        data = dataField.getValue();
        indirizzo = indField.getText();
        partitaIVA = partivaField.getText();
        telefono = telField.getText();

        if(cf == "" || nome == "" || cognome == "" || indirizzo == "" ||  data == null || partitaIVA == "" || telefono == ""){

            alertbox.setText("Attenzione: inserire campi obbligatori (*)");

        }
        else if( cf.length() < 16) {
            alertbox.setText("Attenzione: Codice Fiscale troppo corto !");
        }
        else if ( cf.length() > 17){
            alertbox.setText("Attenzione: Codice Fiscale troppo lungo !");
        }
        else if( partitaIVA.length() < 11) {
            alertbox.setText("Attenzione: Partita IVA troppo corta !");
        }
        else if ( partitaIVA.length() > 12) {
            alertbox.setText("Attenzione: Partita IVA troppo lunga !");
        }
        else {

            providersController.inserisciFornitore(partitaIVA,cf,nome,cognome,data,indirizzo,telefono);
            ((BorderPane) aggiungipane.getParent()).setCenter((Pane) FXMLLoader.load(getClass().getResource("../view/TabellePane.fxml")));
        }



    }



}