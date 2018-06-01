package client.controller;

import client.*;
import com.github.sarxos.webcam.WebcamPanel;
import com.sun.security.ntlm.Client;
import common.Classes.Bambino;
import common.Classes.Gita;
import common.Interface.iBambinoDAO;
import common.Interface.iGitaDAO;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.embed.swing.SwingNode;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

import java.io.IOException;
import java.rmi.RemoteException;
import java.sql.SQLException;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

public class PresenzeGitaController implements CheckPointControllerInterface {

    @FXML private Button fineButton;
    @FXML private AnchorPane QRPane;

    public TabelleGiteController tabellePaneController;
    public Pane tabellePane;
    public Gita gita;
    private PresenzeGitaController presenzeGitaController;



    @FXML public TableView<Bambino> adesioniTable;
    @FXML private TableColumn<Bambino, String> cfColumn;
    //@FXML private TableColumn<Bambino, String> nomeColumn;
    //@FXML private TableColumn<Bambino, String> cognomeColumn;


    private ObservableList<Bambino> kids = FXCollections.observableArrayList();
    private iGitaDAO gitaDAO;

    private Stage actual;

    private WebcamPanel webcamPanel;
    private SwingNode node;
    private NewWebcamQRCodeReader newWebcamQRCodeReader;

    public void inizializza(TabelleGiteController tabellePaneController, Pane tabellePane, Gita gita){

        this.gita = gita;
        this.tabellePane = tabellePane;
        this.tabellePaneController = tabellePaneController;
        if(gita == null)
            return;
        this.gita = gita;
        try {
            kids.addAll(gitaDAO.getAllBambiniGita(gita));
        } catch(RemoteException ex) {
            System.err.println(ex.getMessage());
            ex.printStackTrace();
        }
        catch(SQLException ex) {
            System.err.println(ex.getMessage());
            ex.printStackTrace();
        }

    }

    public void initialize() {
        try {
            newWebcamQRCodeReader = new NewWebcamQRCodeReader(this);
        } catch(CameraBusyException ex) {
            ex.printStackTrace();
        }

        gitaDAO = NamingContextManager.getTripsController();

        initTables();
        initColumns();
        Main.primaryStage.setOnCloseRequest(new EventHandler<WindowEvent>() {
            @Override
            public void handle(WindowEvent event) {
                shutDownWebcam();
            }
        });
    }

    private void initColumns() {
        cfColumn.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getCf()));
        //nomeColumn.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getNome()));
        //cognomeColumn.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getCognome()));
    }

    private void initTables() {
        adesioniTable.setItems(kids);
        //refreshKidTable();

    }

    /*public void refreshKidTable() {
        List<Bambino> bambinoList = new ArrayList<>();
        try {
            bambinoList = gitaDAO.getAllBambiniGita(gita);
        } catch(RemoteException ex) {
            System.err.println(ex.getMessage());
            ex.printStackTrace();
        } catch(SQLException ex) {
            System.err.println(ex.getMessage());
            ex.printStackTrace();
        }
        kids.clear();
        kids.addAll(bambinoList);
    }*/

    @FXML
    private void returnToControlloAccessi(ActionEvent event)throws IOException {
        shutDownWebcam();

        Parent root= FXMLLoader.load(getClass().getResource("../view/GiteQR.fxml"));
        actual =(Stage)fineButton.getScene().getWindow();
        actual.setScene(new Scene(root,fineButton.getScene().getWidth(),fineButton.getScene().getHeight()));
        actual.show();
    }

    public void setPane(WebcamPanel webcamPanel) {

        this.webcamPanel = webcamPanel;
        webcamPanel.setMirrored(true);
        QRPane.getChildren().clear();
        node = new SwingNode();
        node.setContent(webcamPanel);
        QRPane.getChildren().add(node);
        anchorChild(QRPane, node);
    }

    public void shutDownWebcam() {
        if(newWebcamQRCodeReader != null) {
            Thread thread = new Thread(new Runnable() {
                @Override
                public void run() {
                    newWebcamQRCodeReader.shutDown();
                    webcamPanel.stop();
                }
            });
            thread.start();
        }
    }

    public void saveCheckPoint(String code) {
        //argomento codice scannerizzato dal qr
        //devo salvare l'accesso

        try{
            kids.remove(gitaDAO.getKidPresente(code));
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    public void anchorChild(AnchorPane anchorPane, Node node) {
        anchorPane.setBottomAnchor(node, 0.0);
        anchorPane.setLeftAnchor(node, 0.0);
        anchorPane.setTopAnchor(node, 0.0);
        anchorPane.setRightAnchor(node, 0.0);
    }
}
