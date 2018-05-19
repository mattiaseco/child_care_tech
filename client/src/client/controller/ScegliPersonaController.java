package client.controller;

import common.Classes.Bambino;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.control.Toggle;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;

import java.io.IOException;

public class ScegliPersonaController {

    @FXML
    RadioButton BambinoButton;
    @FXML
    RadioButton GenitoreButton;
    @FXML
    RadioButton PersonaleButton;
    @FXML
    RadioButton PediatraButton;
    @FXML
    RadioButton FornitoreButton;

    @FXML
    Button confermabutton;

    @FXML
    AnchorPane scegliPersonaPane;

    @FXML
    ToggleGroup persontipe;

    private BorderPane mainpane;
    private Pane tabellePane;
    private TabellePaneController tabellePaneController;

    public void inizializza(BorderPane carloBarbieri, Pane tabellePane, TabellePaneController tabellePaneController){
        this.mainpane = carloBarbieri;
        this.tabellePane = tabellePane;
        this.tabellePaneController = tabellePaneController;
    }


    @FXML
    private void confermaAction() throws IOException {
        FXMLLoader loader;
        Pane aggiungipane;
        if (persontipe.getSelectedToggle().equals(BambinoButton)) {
            loader = new FXMLLoader(getClass().getResource("../view/AggiungiBambino.fxml"));
            aggiungipane = loader.load();
            mainpane.setCenter(aggiungipane);

            AggiungiBambinoController controller = loader.getController();
            Bambino bambino = tabellePaneController.bambinoTable.getSelectionModel().getSelectedItem();

            controller.inizializza(tabellePane, tabellePaneController);
        } else {
            if (persontipe.getSelectedToggle().equals(GenitoreButton))
                ((BorderPane) scegliPersonaPane.getParent()).setCenter((Pane) FXMLLoader.load(getClass().getResource("../view/AggiungiGenitore.fxml")));
            else {
                if (persontipe.getSelectedToggle().equals(PersonaleButton))
                    ((BorderPane) scegliPersonaPane.getParent()).setCenter((Pane) FXMLLoader.load(getClass().getResource("../view/AggiungiPersonale.fxml")));
                else {
                    if (persontipe.getSelectedToggle().equals(PediatraButton))
                        ((BorderPane) scegliPersonaPane.getParent()).setCenter((Pane) FXMLLoader.load(getClass().getResource("../view/AggiungiPediatra.fxml")));

                    else
                        ((BorderPane) scegliPersonaPane.getParent()).setCenter((Pane) FXMLLoader.load(getClass().getResource("../view/AggiungiFornitore.fxml")));
                }
            }
        }
    }
}
