package client.controller;

import client.NamingContextManager;
import com.sun.javafx.binding.SelectBinding;
import common.Classes.Menu;
import common.Classes.Piatto;
import common.Interface.iMenuDAO;
import common.Interface.iPiattoDAO;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;


import javax.print.attribute.standard.MediaSize;
import java.io.IOException;
import java.rmi.RemoteException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Observable;

public class Step1MenuController extends AnchorPane {


    @FXML
    public TableView<Piatto> primiTable;
    @FXML
    private TableColumn<Piatto, String> primiColumn;
    @FXML
    public TextField numeroMenuField;

    @FXML
    private Text alertbox;

    @FXML
    public AnchorPane step1MenuPane;

    private TabelleMenuController tabelleMenuController;
    private Pane tabelleMenuPane;
    private BorderPane mainpane;
    private ObservableList<Piatto> primi = FXCollections.observableArrayList();

    private iPiattoDAO piattoDAO;

    public void initialize() {

        piattoDAO = NamingContextManager.getPiattoController();

        initTable();
        initColumns();
    }

    private void initTable() {
        primiTable.setItems(primi);
        refreshPrimiTable();

    }

    private void initColumns() {
        primiColumn.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getNome_p()));
    }


    public void refreshPrimiTable() {
        List<Piatto> primiList = new ArrayList<>();
        try {
            primiList = piattoDAO.getAllPrimi();
        } catch (RemoteException ex) {
            System.err.println(ex.getMessage());
            ex.printStackTrace();
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
            ex.printStackTrace();
        }
        primi.clear();
        primi.addAll(primiList);
    }


    public void inizializza(TabelleMenuController tabelleMenuController, Pane tabelleMenuPane, BorderPane mainpane) {

        this.tabelleMenuController = tabelleMenuController;
        this.tabelleMenuPane = tabelleMenuPane;
        this.mainpane = mainpane;


    }

    @FXML
    public void returnToTabelleMenu() throws IOException {
        ((BorderPane) step1MenuPane.getParent()).setCenter(tabelleMenuPane);
        tabelleMenuController.refreshMenuTable();
    }

    @FXML
    public void goToStep2() throws IOException, SQLException {

        alertbox.setText("");
        //int numeroMenu;
        int numeroMenu;
        FXMLLoader loader;
        Pane step2MenuPane;

        numeroMenu = Integer.parseInt(numeroMenuField.getText());
        iMenuDAO menuController = NamingContextManager.getMenuController();

        Piatto piatto = primiTable.getSelectionModel().getSelectedItem();

        if (menuController.getAllNumMenu().contains(numeroMenu)){
            alertbox.setText("ATTENZIONE: numero del menù gia' presente!");
        }
        else if (piatto == null) {
            alertbox.setText("ATTENZIONE: Selezionare un piatto!");
        }
        else if (numeroMenuField.getText().isEmpty()){
            alertbox.setText("ATTENZIONE: Inserire numero Menù!");
        }

        else {
            menuController.inserisciPrimo(numeroMenu,piatto);
            loader = new FXMLLoader(getClass().getResource("../view/Step2Menu.fxml"));
            step2MenuPane = loader.load();
            Step2MenuController controller = loader.getController();
            controller.inizializza(tabelleMenuController,tabelleMenuPane,mainpane,numeroMenu);
            ((BorderPane)step1MenuPane.getParent()).setCenter(step2MenuPane);



        }

    }
}
