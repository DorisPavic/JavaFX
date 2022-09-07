package com.example.pavic6;

import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import model.Category;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class CategorySearchController {
    @FXML
    private TextField imeKategorijeTextField;
    @FXML
    private TextField opisKategorijeTextField;
    @FXML
    private TableView<Category> kategorijaTable;

    @FXML
    private TableColumn<Category, String> imeKategorijeTableColumn;

    @FXML
    private TableColumn<Category, String> opisKategorijeTableColumn;

    List<Category> listaKategorija = HelloController.listaKategorija;


    @FXML
    public void initialize() {


        imeKategorijeTableColumn
                .setCellValueFactory(cellData ->
                        new SimpleStringProperty(cellData.getValue().getName()));

        opisKategorijeTableColumn
                .setCellValueFactory(cellData ->
                        new SimpleStringProperty(cellData.getValue().getDescription()));


    }

    public void SearchCategory() {

        List<Category> filtriranaListaKategorija = new ArrayList<>();
        filtriranaListaKategorija.addAll(listaKategorija);

        if (!imeKategorijeTextField.getText().isBlank()) {
            filtriranaListaKategorija = filtriranaListaKategorija
                    .stream()
                    .filter(a -> a.getName().equalsIgnoreCase(imeKategorijeTextField.getText()))
                    .collect(Collectors.toList());
        }
        if (!opisKategorijeTextField.getText().isBlank()) {
            filtriranaListaKategorija = filtriranaListaKategorija
                    .stream()
                    .filter(a -> a.getDescription().equalsIgnoreCase(opisKategorijeTextField.getText()))
                    .collect(Collectors.toList());
        }

        kategorijaTable.setItems(FXCollections.observableList(filtriranaListaKategorija));
    }
}
