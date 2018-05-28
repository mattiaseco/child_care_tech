package client.controller;

import client.NamingContextManager;
import common.Classes.Bambino;
import common.Classes.Ingredienti;
import common.Interface.iBambinoDAO;
import common.Interface.iIngredientiDAO;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;

import java.rmi.RemoteException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class AggiungiAllergieController {
        @FXML
        private TableView<Ingredienti> allergiesTable;
        @FXML
        private TableView<Ingredienti> foodsTable;
        @FXML
        private TableColumn<Ingredienti, String> nameColumnA;
        @FXML
        private TableColumn<Ingredienti, String> nameColumnF;
        @FXML
        private Button addButton;
        @FXML
        private Button removeButton;
        @FXML
        private Button saveButton;
        @FXML private Text nomebambino;

        @FXML private AnchorPane allergiePane;

        private Bambino bambino;
        private iBambinoDAO bambinoDAO;
        private iIngredientiDAO ingredientiDAO;
        private ObservableList<Ingredienti> allergies = FXCollections.observableArrayList();
        private ObservableList<Ingredienti> foods = FXCollections.observableArrayList();

        private Pane tabellePane;
        private TabellePaneController tabellePaneController;

        @FXML
        public void initialize() {
            ingredientiDAO = NamingContextManager.getIngredientiController();
            bambinoDAO = NamingContextManager.getKidController();
            initMenu();
            initTable();

        }

    @FXML
        public void saveButtonAction(ActionEvent event)throws RemoteException, SQLException{
            List<Ingredienti> newAllergies = new ArrayList<>();
            List<Ingredienti> oldAllergies = new ArrayList<>(bambinoDAO.getAllAllergie(bambino));

            Bambino bambino1;
            newAllergies.addAll(allergies);

            try{

                for(Ingredienti newAllergy : newAllergies) {
                    if(!oldAllergies.contains(newAllergy))
                        bambinoDAO.inserisciAllergia(bambino,newAllergy);
                }
                for(Ingredienti oldAllergy : oldAllergies) {
                    if(!newAllergies.contains(oldAllergy))
                        bambinoDAO.cancellaAllergia(bambino,oldAllergy);
                }
                ((BorderPane)allergiePane.getParent()).setCenter(tabellePane);

                tabellePaneController.refreshTabelle();

            }catch (RemoteException ex){
                System.err.println(ex.getMessage());
                ex.printStackTrace();}



        }

        @FXML
        public void addButtonAction(ActionEvent event ) {
            Ingredienti selected = foodsTable.getSelectionModel().getSelectedItem();
            if(selected == null) return;
            allergies.add(selected);
            foods.remove(selected);
        }
        @FXML
        public void removeButtonAction(ActionEvent event ) {
            Ingredienti selected = allergiesTable.getSelectionModel().getSelectedItem();
            if(selected == null) return;
            foods.add(selected);
            allergies.remove(selected);
        }

        private void initMenu() {
            removeButton.setDisable(true);
            addButton.setDisable(true);

            allergiesTable.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
                if (newSelection != null) {
                    removeButton.setDisable(false);
                    addButton.setDisable(true);
                }
                else {
                    removeButton.setDisable(true);
                    addButton.setDisable(true);
                }
            });
            foodsTable.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
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
            List<Ingredienti> tempFoods = new ArrayList<>();

            nameColumnA.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getNome_i()));
            nameColumnF.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getNome_i()));
            allergiesTable.setItems(allergies);
            foodsTable.setItems(foods);

            try {
                tempFoods = ingredientiDAO.getAllIngredienti();
            } catch(RemoteException ex) {
                System.err.println(ex.getMessage());
                ex.printStackTrace();
            } catch(SQLException ex) {
                System.err.println(ex.getMessage());
                ex.printStackTrace();
            }
            foods.clear();
            allergies.clear();
            foods.addAll(tempFoods);
        }

        public void inizializza(Bambino bambino, Pane pane, TabellePaneController tabellePaneController) {
            this.tabellePane = pane;
            this.tabellePaneController = tabellePaneController;
            if(bambino == null)
                return;
            this.bambino = bambino;
            try {
                allergies.addAll(bambinoDAO.getAllAllergie(bambino));
            } catch(RemoteException ex) {
                System.err.println(ex.getMessage());
                ex.printStackTrace();
            }
            catch(SQLException ex) {
                System.err.println(ex.getMessage());
                ex.printStackTrace();
            }
            foods.removeAll(allergies);
            nomebambino.setText(bambino.getCognome()+" "+bambino.getNome());

        }

}
