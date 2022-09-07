package com.example.pavic6;

import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import model.*;

import java.util.*;
import java.util.stream.Collectors;

public class StoreSearchController {
    @FXML
    private TextField imeTrgovineTextField;
    @FXML
    private TextField webAdresaTrgovineTextField;
    @FXML
    private ComboBox artikliTrgovineComboBox;
    @FXML
    private TableView<Store> trgovineTable;

    @FXML
    private TableColumn<Store, String> imeTrgovineTableColumn;
    @FXML
    private TableColumn<Store, String> webAdresaTrgovineTableColumn;
    @FXML
    private TableColumn<Store, String> artikliTrgovineTableColumn;

    List<Item> listaArtikala = HelloController.listaArtikala;
    List<Store> listaTrgovina = HelloController.listaTrgovina;

    @FXML
    public void initialize() {

        for (Item artikl : listaArtikala) {
            artikliTrgovineComboBox.getItems().add(artikl.getName());
        }

        imeTrgovineTableColumn
                .setCellValueFactory(cellData ->
                        new SimpleStringProperty(cellData.getValue().getName()));

        webAdresaTrgovineTableColumn
                .setCellValueFactory(cellData ->
                        new SimpleStringProperty(cellData.getValue().getWebAddress()));

        artikliTrgovineTableColumn
                .setCellValueFactory(cellData ->
                        new SimpleStringProperty(cellData.getValue().getItems().stream().map(NamedEntity::getName).collect(Collectors.joining(", "))));

    }

    public void SearchStore() {
        List<Store> filtriranaListaTrgovina = new ArrayList<>();
        filtriranaListaTrgovina.addAll(listaTrgovina);

        if (!imeTrgovineTextField.getText().isBlank()) {
            filtriranaListaTrgovina = filtriranaListaTrgovina
                    .stream()
                    .filter(a -> a.getName().equalsIgnoreCase(imeTrgovineTextField.getText()))
                    .collect(Collectors.toList());
        }
        if (!webAdresaTrgovineTextField.getText().isBlank()) {
            filtriranaListaTrgovina = filtriranaListaTrgovina
                    .stream()
                    .filter(a -> a.getWebAddress().equalsIgnoreCase(webAdresaTrgovineTextField.getText()))
                    .collect(Collectors.toList());
        }
        for(int i=0; i<listaArtikala.size();i++){
            if(artikliTrgovineComboBox.getSelectionModel().isSelected(i)){
                filtriranaListaTrgovina=filtriranaListaTrgovina
                        .stream()
                        .filter(a->a.getItems().stream().anyMatch(item->item.getName().equals(artikliTrgovineComboBox.getSelectionModel().getSelectedItem())))
                        .collect(Collectors.toList());
            }
        }

        trgovineTable.setItems(FXCollections.observableList(filtriranaListaTrgovina));
    }
}

