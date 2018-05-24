package client.controller;

import client.NamingContextManager;
import common.Classes.Bambino;
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

import java.awt.*;
import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDate;


public class ModificaBambinoController extends  AnchorPane{

    @FXML private TextField nomeField;
    @FXML private TextField cognField;
    @FXML private DatePicker dataField;
    @FXML private TextField cfField;
    @FXML private TextField indField;
    @FXML private TextField cont1Field;
    @FXML private TextField cont2Field;

    @FXML
    Button annullabutton;

    @FXML
    Button confermabutton;

    @FXML AnchorPane modificapane;

    @FXML private Text alertbox;
    private Pane tabellePane;
    private TabellePaneController tabellePaneController;


    @FXML
    public void returnToTabellePane()throws IOException {
        ((BorderPane)modificapane.getParent()).setCenter(tabellePane);
        tabellePaneController.refreshTabelle();
    }

    public void inizializza(Bambino bambino, Pane tabellePane, TabellePaneController tabellePaneController){
        nomeField.setText(bambino.getNome());
        cognField.setText(bambino.getCognome());
        dataField.setValue(bambino.getData());
        cfField.setText(bambino.getCf());
        indField.setText(bambino.getIndirizzo());
        cont1Field.setText(bambino.getContatto1());
        cont2Field.setText(bambino.getContatto2());
        this.tabellePane = tabellePane;
        this.tabellePaneController = tabellePaneController;
    }

    @FXML
    private void modificaPersona() throws IOException, SQLException {

        alertbox.setText("");

        iBambinoDAO kidController = NamingContextManager.getKidController();

        String nome, cognome, cf, indirizzo, contatto1, contatto2;
        LocalDate data;

        nome= nomeField.getText();
        cognome = cognField.getText();
        cf = cfField.getText();
        indirizzo = indField.getText();
        contatto1 = cont1Field.getText();
        contatto2 = cont2Field.getText();
        data = dataField.getValue();

        if(cf.isEmpty() || nome.isEmpty() || cognome.isEmpty() || indirizzo.isEmpty() || contatto1.isEmpty() || data == null){

            alertbox.setText("Attenzione: inserire campi obbligatori (*)");

        }
        else if( cf.length() < 16) {
            alertbox.setText("Attenzione: Codice Fiscale troppo corto !");
        }
        else if ( cf.length() > 17){
            alertbox.setText("Attenzione: Codice Fiscale troppo lungo !");
        }
        else if ( contatto1.length() > 10){
            alertbox.setText("Attenzione: numero di telefono troppo lungo !");
        }
        else if ( contatto2.length() > 10){
            alertbox.setText("Attenzione: numero di telefono troppo lungo !");
        }
        else {
            kidController.modificaBambino(cf, nome, cognome, data, indirizzo, contatto1, contatto2);
            ((BorderPane)modificapane.getParent()).setCenter(tabellePane);
            tabellePaneController.refreshTabelle();
        }



    }

}
