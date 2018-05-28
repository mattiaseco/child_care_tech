package client.controller;

import client.NamingContextManager;
import common.Classes.Ingredienti;
import common.Classes.Piatto;
import common.Interface.iIngredientiDAO;
import common.Interface.iPiattoDAO;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;

import java.io.IOException;
import java.rmi.RemoteException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class PiattiController {

    @FXML public BorderPane mainpane;
    @FXML public AnchorPane piattiPane;
    @FXML public Button eliminaButton;
    @FXML public Button aggiungiButton;
    @FXML public Button saveButton;
    @FXML public Text alertbox;
    @FXML public Text alertboxok;

    private TabelleMenuController tabelleMenuController;
    public static Pane tabelleMenuPane;

    @FXML public TableView<Piatto> piattiTable;
    @FXML private TableColumn<Piatto, String> nomepColumn;

    private ObservableList<Piatto> piattos = FXCollections.observableArrayList();
    private iPiattoDAO piattoDAO;

    public void inizializza( Pane tabellePane, TabelleMenuController tabellePaneController) {
        this.tabelleMenuPane = tabellePane;
        this.tabelleMenuController = tabellePaneController;

    }

    public void initialize() {
        piattoDAO = NamingContextManager.getPiattoController();
        initTables();
        initColumns();
    }
    private void initTables() {
        piattiTable.setItems(piattos);
        refreshPiattiTable();

    }
    private void initColumns() {
        nomepColumn.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getNome_p()));

    }
    public void refreshPiattiTable() {
        List<Piatto> piattoList = new ArrayList<>();
        try {
            piattoList = piattoDAO.getAllPiatti();
        } catch(RemoteException ex) {
            System.err.println(ex.getMessage());
            ex.printStackTrace();
        } catch(SQLException ex) {
            System.err.println(ex.getMessage());
            ex.printStackTrace();
        }
        piattos.clear();
        piattos.addAll(piattoList);
    }

    @FXML
    public void eliminaPiatto()throws IOException {
        alertbox.setText("");
        alertboxok.setText("");

        String piatto;
        iPiattoDAO piattiController = NamingContextManager.getPiattoController();
        Piatto piatto1= piattiTable.getSelectionModel().getSelectedItem();
        if (piatto1 == null) {
            alertbox.setText("Attenzione: selezionare una riga !");
            alertboxok.setText("");
        }
        else {
            piatto = piatto1.getNome_p();
            try {
                piattiController.cancellaPiatti(piatto);
                refreshPiattiTable();
            } catch(RemoteException ex) {
                ex.printStackTrace();
            } catch(SQLException ex) {
                ex.printStackTrace();
            }
            alertbox.setText("");
            alertboxok.setText("Cancellazione effettuata !");
        }

    }
    @FXML
    public void aggiungiPiatto() throws IOException, SQLException {
        alertbox.setText("");
        alertboxok.setText("");

        /*FXMLLoader loader;
        Pane piattoPane;
        loader = new FXMLLoader(getClass().getResource("../view/CreaPiatto.fxml"));
        piattoPane = loader.load();
        mainpane.setCenter(piattoPane);
        CreaPiattoController controller = loader.getController();
        controller.inizializza(tabelleMenuPane,tabelleMenuController);*/
        ((BorderPane)piattiPane.getParent()).setCenter(FXMLLoader.load(getClass().getResource("../view/CreaPiatto.fxml")));





        /*alertbox.setText("");
        alertboxok.setText("");

        iIngredientiDAO ingredientiController = NamingContextManager.getIngredientiController();
        String ingrediente;
        ingrediente = ingredienteField.getText();
        if(ingrediente.isEmpty() ){

            alertbox.setText("Attenzione: inserire ingrediente !");
        }
        else if( ingrediente.length() > 15) {
            alertbox.setText("Attenzione: nome troppo lungo !");
        }
        else {
            if(piattiTable.getItems().contains(new Ingredienti(ingrediente, 0)))
                alertbox.setText("Attenzione: Ingrediente già presente!");
            else {
                ingredientiController.inserisciIngrediente(ingrediente);
                refreshPiattiTable();
                ingredienteField.clear();
                alertbox.setText("");

            }
        }
*/
    }

    @FXML
    public void aggiornaPiatti()throws IOException {

        ((BorderPane) piattiPane.getParent()).setCenter(FXMLLoader.load(getClass().getResource("../view/TabelleMenu.fxml")));


    }

}
