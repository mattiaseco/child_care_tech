package client.controller;

import client.NamingContextManager;
import common.Classes.Pediatra;
import common.Classes.Personale;
import common.Interface.iPersonaleDAO;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;

import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDate;

public class ModificaPersonaleController {
    @FXML private TextField nomeField;
    @FXML private TextField cognField;
    @FXML private TextField cfField;
    @FXML private TextField indField;
    @FXML private TextField telField;
    @FXML private DatePicker dataField;
    @FXML private Text alertbox;

    @FXML
    AnchorPane modificapane;
    private Pane tabellePane;
    private TabellePaneController tabellePaneController;


    public void inizializza(Personale personale,Pane tabellePane,TabellePaneController tabellePaneController){
        nomeField.setText(personale.getNome());
        cognField.setText(personale.getCognome());
        dataField.setValue(personale.getData());
        cfField.setText(personale.getCf());
        indField.setText(personale.getIndirizzo());
        telField.setText(personale.getTelefono());
        this.tabellePane = tabellePane;
        this.tabellePaneController = tabellePaneController;


    }

    @FXML
    public void returnToTabellePane()throws IOException {

        ((BorderPane)modificapane.getParent()).setCenter(tabellePane);
        tabellePaneController.refreshTabelle();
    }
    @FXML
    private void modificaPersonale() throws IOException, SQLException {
        alertbox.setText("");


        iPersonaleDAO personalController = NamingContextManager.getPersonalController();

        String nome, cognome, cf, indirizzo, telefono;
        LocalDate data;

        nome = nomeField.getText();
        cognome = cognField.getText();
        data = dataField.getValue();
        cf = cfField.getText();
        indirizzo = indField.getText();
        telefono = telField.getText();

        if(cf == "" || nome == "" || cognome == "" || indirizzo == "" || data == null || telefono == ""){

            alertbox.setText("Attenzione: inserire campi obbligatori (*)");

        }
        else if( cf.length() < 16) {
            alertbox.setText("Attenzione: Codice Fiscale troppo corto !");
        }
        else if ( cf.length() > 17){
            alertbox.setText("Attenzione: Codice Fiscale troppo lungo !");
        }
        else {
            personalController.modificaPersonale(cf, nome, cognome, data, indirizzo, telefono);
            ((BorderPane)modificapane.getParent()).setCenter(tabellePane);
            tabellePaneController.refreshTabelle();
        }



    }
}
