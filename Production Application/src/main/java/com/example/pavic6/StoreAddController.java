package com.example.pavic6;

import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import model.Item;
import model.NamedEntity;
import model.Store;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class StoreAddController {
    @FXML
    private TextField imeNoveTrgovineTextField;
    @FXML
    private TextField webAdresaNoveTrgovineTextField;
    @FXML
    private TableView artikliTableView;
    @FXML
    private TableColumn<Item, String> imeArtiklaTableColumn;
    @FXML
    private TableColumn<Item, String> kategorijaArtiklaTableColumn;
    @FXML
    private TableColumn<Item, String> sifraArtiklaTableColumn;


    List<Item> listaArtikala = HelloController.listaArtikala;
    List<Store> listaTrgovina = HelloController.listaTrgovina;

    PrintWriter upisaneTrgovineFile;

    @FXML
    public void initialize() {


        imeArtiklaTableColumn
                .setCellValueFactory(cellData ->
                        new SimpleStringProperty(cellData.getValue().getName()));

        kategorijaArtiklaTableColumn
                .setCellValueFactory(cellData ->
                        new SimpleStringProperty(cellData.getValue().getCategory().getName()));

        sifraArtiklaTableColumn
                .setCellValueFactory(cellData ->
                        new SimpleStringProperty(cellData.getValue().getSifraArtikla()));

        artikliTableView.setItems(FXCollections.observableList(listaArtikala));
    }

    @FXML
    public void AddStore() {

        StringBuilder errorMessage = new StringBuilder();
        Set<Item> artikliNoveTrgovine = new HashSet<>();


        artikliTableView.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
                ObservableList<Item> artikliNoveTrgovineObList = artikliTableView.getSelectionModel().getSelectedItems();
                for (Item a : artikliNoveTrgovineObList) {
                    artikliNoveTrgovine.add(a);
                    System.out.println(a.getName());
                }


        if (imeNoveTrgovineTextField.getText().isEmpty()) {
            String message = "Naziv trgovine je obavezan!";
            errorMessage.append(message + "\n");
        }
        if (webAdresaNoveTrgovineTextField.getText().isEmpty()) {
            String message = "Web adresa trgovine je obavezna!";
            errorMessage.append(message + "\n");
        }
        if (artikliNoveTrgovine.isEmpty()) {
            String message = "Morate odabrati barem jedan artikl!";
            errorMessage.append(message + "\n");
        }

        if (errorMessage.isEmpty()) {

            Store novaTrgovina = new Store(imeNoveTrgovineTextField.getText(), webAdresaNoveTrgovineTextField.getText(), artikliNoveTrgovine);
            {
                try {
                    upisaneTrgovineFile = new PrintWriter(new FileWriter("datoteke/trgovine.txt", true));
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            upisaneTrgovineFile.println(imeNoveTrgovineTextField.getText() + ";" + webAdresaNoveTrgovineTextField.getText() + ";" + artikliNoveTrgovine.stream().map(NamedEntity::getName).collect(Collectors.joining(",")));
            upisaneTrgovineFile.close();

            listaTrgovina.add(novaTrgovina);

            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Uspješan unos!");
            alert.setHeaderText("Uspješno ste spremili novu trgovinu : " + novaTrgovina.getName().toUpperCase() + "!");
            alert.setContentText("Trgovina  " + novaTrgovina.getName().toUpperCase() + " - " + novaTrgovina.getWebAddress() +
                    "\n je uspješno spremljena u aplikaciju!");
            alert.showAndWait();
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Trgovina nije pohranjena!");
            alert.setHeaderText("Neuspjela akcija!");
            alert.setContentText(errorMessage.toString());
            alert.showAndWait();
        }

    }
}
