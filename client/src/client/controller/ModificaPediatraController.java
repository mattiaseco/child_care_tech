package client.controller;

import client.NamingContextManager;
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

public class ModificaPediatraController {

    @FXML private TextField nomeField;
    @FXML private TextField cognField;
    @FXML private TextField cfField;
    @FXML private TextField indField;
    @FXML private TextField telField;
    @FXML private DatePicker dataField;

    @FXML AnchorPane modificapane;
    @FXML private Text alertbox;

    @FXML
    private void returnToTabellePane()throws IOException {

        ((BorderPane)modificapane.getParent()).setCenter(AnagraficaController.tabellePane);

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

            pediatraController.modificaPediatra(cf, nome, cognome, data, indirizzo, telefono);
            ((BorderPane) modificapane.getParent()).setCenter((Pane) FXMLLoader.load(getClass().getResource("../view/TabellePane.fxml")));
        }



    }
}
