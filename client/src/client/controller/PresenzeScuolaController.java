package client.controller;

import client.CameraBusyException;
import client.CheckPointControllerInterface;
import client.NamingContextManager;
import client.NewWebcamQRCodeReader;
import com.github.sarxos.webcam.WebcamPanel;
import common.Classes.Bambino;
import common.Interface.iBambinoDAO;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.embed.swing.SwingNode;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.rmi.RemoteException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PresenzeScuolaController implements CheckPointControllerInterface{

    @FXML private Button fineButton;
    @FXML private AnchorPane QRPane;

    @FXML public TableView<Bambino> presenzeTable;
    @FXML private TableColumn<Bambino, String> cfColumn;
    @FXML private TableColumn<Bambino, String> nomeColumn;
    @FXML private TableColumn<Bambino, String> cognomeColumn;


    private ObservableList<Bambino> kids = FXCollections.observableArrayList();
    private iBambinoDAO kidDAO;
    private Stage actual;

    private WebcamPanel webcamPanel;
    private SwingNode node;
    private NewWebcamQRCodeReader newWebcamQRCodeReader;

    @FXML
    private void returnToControlloAccessi(ActionEvent event)throws IOException {

        shutDownWebcam();

        Parent root= FXMLLoader.load(getClass().getResource("../view/ControlloAccessi.fxml"));
        actual =(Stage)fineButton.getScene().getWindow();
        actual.setScene(new Scene(root,fineButton.getScene().getWidth(),fineButton.getScene().getHeight()));
        actual.show();
    }

    public void initialize() {
        try {
            newWebcamQRCodeReader = new NewWebcamQRCodeReader(this);
        } catch(CameraBusyException ex) {
            ex.printStackTrace();
        }

        kidDAO = NamingContextManager.getKidController();

        initTables();
        initColumns();
    }

    private void initColumns() {
        cfColumn.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getCf()));
        nomeColumn.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getNome()));
        cognomeColumn.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getCognome()));
    }

    private void initTables() {
        presenzeTable.setItems(kids);
        refreshKidTable();

    }

    public void refreshKidTable() {
        List<Bambino> kidsList = new ArrayList<>();
        try {
            kidsList = kidDAO.getAllBambini();
        } catch(RemoteException ex) {
            System.err.println(ex.getMessage());
            ex.printStackTrace();
        } catch(SQLException ex) {
            System.err.println(ex.getMessage());
            ex.printStackTrace();
        }
        kids.clear();
        kids.addAll(kidsList);
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

    public void saveCheckPoint(String code){
        //argomento codice scannerizzato dal qr
        //devo salvare l'accesso

       try{
           kids.remove(kidDAO.getKid(code));
           //Bambino bimbo=kidDAO.getKid(code);
           kidDAO.inserisciBambinoPresente(code);



           /* if(kidDAO.getAllCf().contains(code)) {
                System.out.println("codice fiscal non presente");

                /*Bambino selected = presenzeTable.getSelectionModel().getSelectedItem();
                kids.remove(kidDAO.getKid(code));
                /*Client.getSessionService().getSession().saveCheckpoint(code, currentEvent, time);
                scannedCodes.add(code);
                logArea.appendText(code + " Registrato correttamente alle " + time + "\n");
                refreshTable();}
            }*/
        } catch (Exception e){
            e.printStackTrace();
        }
        System.out.println(code);
    }
    public void anchorChild(AnchorPane anchorPane, Node node) {
        anchorPane.setBottomAnchor(node, 0.0);
        anchorPane.setLeftAnchor(node, 0.0);
        anchorPane.setTopAnchor(node, 0.0);
        anchorPane.setRightAnchor(node, 0.0);
    }
}
