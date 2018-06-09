package client.controller;

import client.NamingContextManager;
import common.Classes.*;
import common.Classes.Menu;
import common.Interface.iBambinoDAO;
import common.Interface.iMenuDAO;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;

import java.io.IOException;
import java.rmi.RemoteException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AdesioniController {

    @FXML
    private TableView<Intolleranze> bambiniPTable;
    @FXML
    private TableColumn<Intolleranze, String> nameColumnP;
    @FXML
    private TableColumn<Intolleranze, String> nameColumnP1;

    @FXML
    Button confermabutton;
    @FXML
    Button annullabutton;
    @FXML
    AnchorPane adesionipane;
    @FXML
    Label numeroMenuLabel;
    @FXML
    private Text alertbox;

    //private Pane tabellePane;
    //private TabelleMenuController tabellePaneController;

    private TabelleMenuController tabelleMenuController;
    public static Pane tabelleMenuPane;


    private Menu menu;
    private iBambinoDAO bambinoDAO;
    private iMenuDAO menuDAO;
    //int numero=1;
    private ObservableList<Intolleranze> bambiniPresenti = FXCollections.observableArrayList();

    public void initialize()throws RemoteException,SQLException {
        bambinoDAO = NamingContextManager.getKidController();
        menuDAO = NamingContextManager.getMenuController();
        initTables();
        initColumns();
    }

    private void initColumns() {
        nameColumnP.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getCf().getCf()));
        nameColumnP1.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getNomeIngr().getNome_i()));

    }

    private void initTables() throws RemoteException,SQLException {
        bambiniPTable.setItems(bambiniPresenti);

        refreshKidTable();

    }



    public void refreshKidTable()throws RemoteException,SQLException {
        List<Intolleranze> intolleranzeList = new ArrayList<>();
        //Menu menu4=menuDAO.getMenuNumero(numero);
        try {

            for (Bambino newBambino : bambinoDAO.getAllPresenti()) {
                for (Ingredienti ingrediente : menuDAO.getAllIngredientiMenu(menu)) {
                    if (bambinoDAO.getAllAllergie(newBambino).contains(ingrediente)) {
                        Intolleranze bambino_allergico= new Intolleranze(newBambino,ingrediente);
                        intolleranzeList.add(bambino_allergico);
                    }
                }
            }
        } catch (RemoteException ex) {
            System.err.println(ex.getMessage());
            ex.printStackTrace();
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
            ex.printStackTrace();
        }
        bambiniPresenti.clear();
        bambiniPresenti.addAll(intolleranzeList);
    }

    public void inizializza(Menu menu, Pane tabellePane, TabelleMenuController tabellePaneController) {

        this.tabelleMenuPane = tabellePane;
        this.tabelleMenuController = tabellePaneController;
        this.menu=menu;
        numeroMenuLabel.setText(String.valueOf(menu.getNumero()));
    }


    @FXML
    private void aggiornaAdesioni(ActionEvent event)throws RemoteException, SQLException, IOException {
        alertbox.setText("");
        ((BorderPane) adesionipane.getParent()).setCenter(tabelleMenuPane);
        tabelleMenuController.refreshMenuTable();
    }

    @FXML
    private void returnToTabelleMenu() throws IOException {
        alertbox.setText("");
        ((BorderPane) adesionipane.getParent()).setCenter(tabelleMenuPane);
        tabelleMenuController.refreshMenuTable();
    }
}

