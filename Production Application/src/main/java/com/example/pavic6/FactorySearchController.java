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

public class FactorySearchController {
    @FXML
    private TextField imeTvorniceTextField;
    @FXML
    private TextField adresaTvorniceTextField;
    @FXML
    private TextField gradTvorniceTextField;
    @FXML
    private ComboBox artikliTvorniceComboBox;
    @FXML
    private TableView<Factory> tvorniceTable;

    @FXML
    private TableColumn<Factory, String> imeTvorniceTableColumn;
    @FXML
    private TableColumn<Factory, String> adresaTvorniceTableColumn;
    @FXML
    private TableColumn<Factory, String> gradTvorniceTableColumn;
    @FXML
    private TableColumn<Factory, String> artikliTvorniceTableColumn;

    List<Item> listaArtikala = HelloController.listaArtikala;
    List<Factory> listaTvornica = HelloController.listaTvornica;

    @FXML
    public void initialize() {

        for (Item artikl : listaArtikala) {
            artikliTvorniceComboBox.getItems().add(artikl.getName());
        }

        imeTvorniceTableColumn
                .setCellValueFactory(cellData ->
                        new SimpleStringProperty(cellData.getValue().getName()));

        adresaTvorniceTableColumn
                .setCellValueFactory(cellData ->
                        new SimpleStringProperty(cellData.getValue().getAddress().getStreet() + " " + cellData.getValue().getAddress().getHouseNumber()));

        gradTvorniceTableColumn
                .setCellValueFactory(cellData ->
                        new SimpleStringProperty(cellData.getValue().getGrad().getPostalCode() + " " + cellData.getValue().getGrad().getCityName()));

        artikliTvorniceTableColumn
                .setCellValueFactory(cellData ->
                        new SimpleStringProperty(cellData.getValue().getItems().stream().map(NamedEntity::getName).collect(Collectors.joining(", "))));

    }

    public void SearchFactory() {
        List<Factory> filtriranaListaTvornica = new ArrayList<>();
        filtriranaListaTvornica.addAll(listaTvornica);

        if (!imeTvorniceTextField.getText().isBlank()) {
            filtriranaListaTvornica = filtriranaListaTvornica
                    .stream()
                    .filter(a -> a.getName().equalsIgnoreCase(imeTvorniceTextField.getText()))
                    .collect(Collectors.toList());
        }
        if (!adresaTvorniceTextField.getText().isBlank()) {
            filtriranaListaTvornica = filtriranaListaTvornica
                    .stream()
                    .filter(a -> (a.getAddress().getStreet() + " " + a.getAddress().getHouseNumber()).equals(adresaTvorniceTextField.getText()))
                    .collect(Collectors.toList());
        }
        if (!gradTvorniceTextField.getText().isBlank()) {
            filtriranaListaTvornica = filtriranaListaTvornica
                    .stream()
                    .filter(a -> a.getGrad().getCityName().equals(gradTvorniceTextField.getText()))
                    .collect(Collectors.toList());
        }
        for (int i = 0; i < listaArtikala.size(); i++) {
            if (artikliTvorniceComboBox.getSelectionModel().isSelected(i)) {
                filtriranaListaTvornica = filtriranaListaTvornica
                        .stream()
                        .filter(a -> a.getItems().stream().anyMatch(item -> item.getName().equals(artikliTvorniceComboBox.getSelectionModel().getSelectedItem())))
                        .collect(Collectors.toList());
            }
        }

        tvorniceTable.setItems(FXCollections.observableList(filtriranaListaTvornica));
    }
}

