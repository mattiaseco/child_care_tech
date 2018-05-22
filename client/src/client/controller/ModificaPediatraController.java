package client.controller;

import client.NamingContextManager;
import common.Classes.Genitore;
import common.Classes.Pediatra;
import common.Interface.iPediatraDAO;
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

public class ModificaPediatraController extends AnchorPane{

    @FXML private TextField nomeField;
    @FXML private TextField cognField;
    @FXML private TextField cfField;
    @FXML private TextField indField;
    @FXML private TextField telField;
    @FXML private DatePicker dataField;

    @FXML AnchorPane modificapane;
    @FXML private Text alertbox;
    private Pane tabellePane;
    private TabellePaneController tabellePaneController;


    public void inizializza(Pediatra pediatra, Pane tabellePane,TabellePaneController tabellePaneController){
        nomeField.setText(pediatra.getNome());
        cognField.setText(pediatra.getCognome());
        dataField.setValue(pediatra.getData());
        cfField.setText(pediatra.getCf());
        indField.setText(pediatra.getIndirizzo());
        telField.setText(pediatra.getTelefono());
        this.tabellePane = tabellePane;
        this.tabellePaneController = tabellePaneController;


    }

    @FXML
    public void returnToTabellePane()throws IOException {
        ((BorderPane)modificapane.getParent()).setCenter(tabellePane);
        tabellePaneController.refreshTabelle();

    }
    @FXML
    private void modificaPediatra() throws IOException, SQLException {
        alertbox.setText("");


        iPediatraDAO pediatraController = NamingContextManager.getPediatraController();

        String nome, cognome, cf, indirizzo, telefono;
        LocalDate data;

        nome = nomeField.getText();
        cognome = cognField.getText();
        data = dataField.getValue();
        cf = cfField.getText();
        indirizzo = indField.getText();
        telefono = telField.getText();

        if(cf.isEmpty() || nome.isEmpty()|| cognome.isEmpty() || indirizzo.isEmpty() || data == null || telefono.isEmpty()){

            alertbox.setText("Attenzione: inserire campi obbligatori (*)");

        }
        else if( cf.length() < 16) {
            alertbox.setText("Attenzione: Codice Fiscale troppo corto !");
        }
        else if ( cf.length() > 17){
            alertbox.setText("Attenzione: Codice Fiscale troppo lungo !");
        }
        else {

            pediatraController.modificaPediatra(cf, nome, cognome, data, indirizzo, telefono);
            ((BorderPane)modificapane.getParent()).setCenter(tabellePane);
            tabellePaneController.refreshTabelle();
        }



    }
}
