package client.controller;

import client.NamingContextManager;
import common.Classes.Bambino;
import common.Classes.Ingredienti;
import common.Classes.Menu;
import common.Classes.Piatto;
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
    private TableView<Bambino> bambiniPTable;
    @FXML
    private TableView<Bambino> bambiniDTable;
    @FXML
    private TableColumn<Bambino, String> nameColumnP;
    @FXML
    private TableColumn<Bambino, String> nameColumnD;


    @FXML private Button addButton;
    @FXML private Button removeButton;

    @FXML Button annullabutton;
    @FXML Button confermabutton;

    @FXML AnchorPane adesionipane;

    @FXML Label numeroMenuLabel;

    @FXML private Text alertbox;

    private Pane tabellePane;
    private TabelleMenuController tabellePaneController;


    private Menu menu;
    private Bambino bambino;
    private iBambinoDAO bambinoDAO;
    private iMenuDAO menuDAO;
    private ObservableList<Bambino> bambiniDisponibili = FXCollections.observableArrayList();
    private ObservableList<Bambino> bambiniPresenti = FXCollections.observableArrayList();



    @FXML
    public void initialize() {
        bambinoDAO = NamingContextManager.getKidController();
        menuDAO = NamingContextManager.getMenuController();
        initMenu();
        initTable();

    }

    public void addButtonAction(ActionEvent event ) {
        Bambino selected = bambiniDTable.getSelectionModel().getSelectedItem();
        if(selected == null) return;
        bambiniPresenti.add(selected);
        bambiniDisponibili.remove(selected);
    }
    @FXML
    public void removeButtonAction(ActionEvent event ) {
        Bambino selected = bambiniPTable.getSelectionModel().getSelectedItem();
        if(selected == null) return;
        bambiniDisponibili.add(selected);
        bambiniPresenti.remove(selected);
    }

    private void initMenu() {
        removeButton.setDisable(true);
        addButton.setDisable(true);

        bambiniPTable.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
            if (newSelection != null) {
                removeButton.setDisable(false);
                addButton.setDisable(true);
            }
            else {
                removeButton.setDisable(true);
                addButton.setDisable(true);
            }
        });
        bambiniDTable.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
            if (newSelection != null) {
                removeButton.setDisable(true);
                addButton.setDisable(false);
            }
            else {
                removeButton.setDisable(true);
                addButton.setDisable(true);
            }
        });
    }

    private void initTable() {
        List<Bambino> tempBambini = new ArrayList<>();

        nameColumnP.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getCf()));
        nameColumnD.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getCf()));
        bambiniPTable.setItems(bambiniPresenti);
        bambiniDTable.setItems(bambiniDisponibili);

        try {
            tempBambini = bambinoDAO.getAllBambini();
        } catch(RemoteException ex) {
            System.err.println(ex.getMessage());
            ex.printStackTrace();
        } catch(SQLException ex) {
            System.err.println(ex.getMessage());
            ex.printStackTrace();
        }
        bambiniDisponibili.clear();
        bambiniPresenti.clear();
        bambiniDisponibili.addAll(tempBambini);
    }

    @FXML
    private void aggiornaAdesioni(ActionEvent event)throws RemoteException, SQLException, IOException {

        alertbox.setText("");
        int numero=menu.getNumero();
        common.Classes.Menu menu=new common.Classes.Menu(numero,menuDAO.getPiatto1(numero),menuDAO.getPiatto2(numero),menuDAO.getPiatto3(numero));

            List<Bambino> newbambini = new ArrayList<>();
            List<Bambino> oldbambini = new ArrayList<>(menuDAO.getAllBambiniMenu(menu));

            newbambini.addAll( bambiniPresenti);


            try {

                for (Bambino newBambino : newbambini) {
                    if (!oldbambini.contains(newBambino))
                        menuDAO.inserisciBambinoMangia(menu, newBambino);

                }
                for (Bambino oldBambino : oldbambini) {
                    if (!newbambini.contains(oldBambino))
                        menuDAO.cancellaBambinoMangia(menu, oldBambino);
                }

                ((BorderPane) adesionipane.getParent()).setCenter(tabellePane);
                tabellePaneController.refreshMenuTable();


            } catch (RemoteException ex) {
                System.err.println(ex.getMessage());
                ex.printStackTrace();
            }

        }

    public void addButtonAction() {
        addButtonAction();
    }

    @FXML
    private void returnToTabelleMenu() throws IOException {
        alertbox.setText("");
        ((BorderPane) adesionipane.getParent()).setCenter(tabellePane);
        tabellePaneController.refreshMenuTable();


    }
    public void inizializza(Menu menu, Pane tabellePane, TabelleMenuController tabellePaneController) {

        this.tabellePane = tabellePane;
        this.tabellePaneController = tabellePaneController;

        if(menu == null)
            return;
        this.menu = menu;
        try {
            bambiniPresenti.addAll(menuDAO.getAllBambiniMenu(menu));
        } catch(RemoteException ex) {
            System.err.println(ex.getMessage());
            ex.printStackTrace();
        }
        catch(SQLException ex) {
            System.err.println(ex.getMessage());
            ex.printStackTrace();
        }

        bambiniDisponibili.removeAll(bambiniPresenti);

        numeroMenuLabel.setText(String.valueOf(menu.getNumero()));




    }

}

