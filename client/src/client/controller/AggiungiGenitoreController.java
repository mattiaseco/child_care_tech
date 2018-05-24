package client.controller;

import client.NamingContextManager;
import common.Interface.iGenitoreDAO;
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

public class AggiungiGenitoreController extends AnchorPane {

    @FXML AnchorPane aggiungipane;

    @FXML private Button annullabutton;
    @FXML private Button confermabutton;

    @FXML private TextField nomeField;
    @FXML private TextField cognField;
    @FXML private TextField cfField;
    @FXML private TextField telField;
    @FXML private TextField indField;
    @FXML private DatePicker dataField;
    private TabellePaneController tabellePaneController;
    private Pane tabellePane;

    @FXML private Text alertbox;

    public void inizializza(Pane tabellePane, TabellePaneController tabellePaneController){
        this.tabellePane = tabellePane;
        this.tabellePaneController = tabellePaneController;
    }

    @FXML
    public void returnToTabellePane() throws IOException {

        ((BorderPane)aggiungipane.getParent()).setCenter(tabellePane);
        tabellePaneController.refreshTabelle();
    }
    @FXML
    private void aggiungiGenitore() throws IOException, SQLException {
        alertbox.setText("");

        iGenitoreDAO parentsControll = NamingContextManager.getParentsController();

        String nome, cognome, indirizzo, cf, telefono;
        LocalDate data;

        nome = nomeField.getText();
        cognome = cognField.getText();
        data = dataField.getValue();
        cf = cfField.getText();
        indirizzo = indField.getText();
        telefono = telField.getText();

        if(cf.isEmpty() || nome.isEmpty() || cognome.isEmpty() || indirizzo.isEmpty() || data == null || telefono.isEmpty()){

            alertbox.setText("Attenzione: inserire campi obbligatori (*)");

        }
        else if( cf.length() < 16) {
            alertbox.setText("Attenzione: Codice Fiscale troppo corto !");
        }
        else if ( cf.length() > 16){
            alertbox.setText("Attenzione: Codice Fiscale troppo lungo !");
        }
        else if ( telefono.length() > 10){
            alertbox.setText("Attenzione: numero di telefono troppo lungo !");
        }
        else {

            parentsControll.inserisciGenitore(cf,nome,cognome,data,indirizzo,telefono);

            ((BorderPane)aggiungipane.getParent()).setCenter(tabellePane);
            tabellePaneController.refreshTabelle();
        }
    }
}
