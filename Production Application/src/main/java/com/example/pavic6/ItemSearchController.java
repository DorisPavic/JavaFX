package com.example.pavic6;

import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.TextFieldTableCell;
import model.Category;
import model.Item;


import java.io.File;
import java.io.FileNotFoundException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class ItemSearchController {


    @FXML
    private TextField imeArtiklaTextField;
    @FXML
    private ComboBox kategorijaArtiklaComboBox;
    @FXML
    private TextField sifraArtiklaTextField;

    @FXML
    private TableView<Item> artikliTable;

    @FXML
    private TableColumn<Item, String> imeArtiklaTableColumn;
    @FXML
    private TableColumn<Item, String> kategorijaArtiklaTableColumn;
    @FXML
    private TableColumn<Item, String> sifraArtiklaTableColumn;
    @FXML
    private TableColumn<Item, String> sirinaArtiklaTableColumn;
    @FXML
    private TableColumn<Item, String> visinaArtiklaTableColumn;
    @FXML
    private TableColumn<Item, String> duzinaArtiklaTableColumn;
    @FXML
    private TableColumn<Item, String> cijenaProizvodnjeArtiklaTableColumn;
    @FXML
    private TableColumn<Item, String> osnovnaProdajnaCijenaArtiklaTableColumn;
    @FXML
    private TableColumn<Item, String> popustArtiklaTableColumn;
    @FXML
    private TableColumn<Item, String> cijenasPopustomArtiklaTableColumn;

    List<Item> listaArtikala = HelloController.listaArtikala;
    List<Category> listaKategorija = HelloController.listaKategorija;


    @FXML
    public void initialize() {

        for (Category kategorija : listaKategorija) {
            kategorijaArtiklaComboBox.getItems().add(kategorija.getName());
        }


        imeArtiklaTableColumn
                .setCellValueFactory(cellData ->
                        new SimpleStringProperty(cellData.getValue().getName()));

        kategorijaArtiklaTableColumn
                .setCellValueFactory(cellData ->
                        new SimpleStringProperty(cellData.getValue().getCategory().getName()));

        sifraArtiklaTableColumn
                .setCellValueFactory(cellData ->
                        new SimpleStringProperty(cellData.getValue().getSifraArtikla()));

        sirinaArtiklaTableColumn
                .setCellValueFactory(cellData ->
                        new SimpleStringProperty(cellData.getValue().getWidth().toString()));

        visinaArtiklaTableColumn
                .setCellValueFactory(cellData ->
                        new SimpleStringProperty(cellData.getValue().getHeight().toString()));

        duzinaArtiklaTableColumn
                .setCellValueFactory(cellData ->
                        new SimpleStringProperty(cellData.getValue().getLength().toString()));

        cijenaProizvodnjeArtiklaTableColumn
                .setCellValueFactory(cellData ->
                        new SimpleStringProperty(cellData.getValue().getProductionCost().toString() + "kn"));

        osnovnaProdajnaCijenaArtiklaTableColumn
                .setCellValueFactory(cellData ->
                        new SimpleStringProperty(cellData.getValue().getSellingPrice().toString() + "kn"));

        popustArtiklaTableColumn
                .setCellValueFactory(cellData ->
                        new SimpleStringProperty(cellData.getValue().getPopust().toString() + "%"));

        cijenasPopustomArtiklaTableColumn
                .setCellValueFactory(cellData ->
                        new SimpleStringProperty(cellData.getValue().getCijenaSPopustom().toString() + "kn"));

    }

    List<Item> filtriranaListaArtikala = new ArrayList<>();

    public void SearchItems() {

        filtriranaListaArtikala.addAll(listaArtikala);

        if (!imeArtiklaTextField.getText().isBlank()) {
            filtriranaListaArtikala = filtriranaListaArtikala
                    .stream()
                    .filter(a -> a.getName().equalsIgnoreCase(imeArtiklaTextField.getText()))
                    .collect(Collectors.toList());
        }

        if (!kategorijaArtiklaComboBox.getSelectionModel().isEmpty()) {
            for (int i = 0; i < listaKategorija.size(); i++) {
                if (kategorijaArtiklaComboBox.getSelectionModel().isSelected(i)) {
                    filtriranaListaArtikala = filtriranaListaArtikala
                            .stream()
                            .filter(a -> a.getCategory().getName().equalsIgnoreCase(kategorijaArtiklaComboBox.getValue().toString()))
                            .collect(Collectors.toList());
                }
            }
        }
        if (!sifraArtiklaTextField.getText().isBlank()) {
            filtriranaListaArtikala = filtriranaListaArtikala
                    .stream()
                    .filter(a -> a.getSifraArtikla().equals(sifraArtiklaTextField.getText()))
                    .collect(Collectors.toList());
        }

        artikliTable.setItems(FXCollections.observableList(filtriranaListaArtikala));
    }

    public void DeleteItem() {

        if (!artikliTable.getSelectionModel().isEmpty()) {
            listaArtikala.remove(artikliTable.getSelectionModel().getSelectedItem());
            artikliTable.getItems().remove(artikliTable.getSelectionModel().getSelectedItem());


            String imeOdabranogArtikla = artikliTable.getSelectionModel().getSelectedItem().getName();

            Scanner skeniraniArtikliFile = null;
            String imeArtikla;

            {
                try {
                    skeniraniArtikliFile = new Scanner((new File("datoteke/artikli.txt")));
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }
            }
            while (skeniraniArtikliFile.hasNext()) {
                String line = skeniraniArtikliFile.nextLine();
                Scanner scLine = new Scanner(line).useDelimiter(",");
                imeArtikla = scLine.next();
                if (imeArtikla.equalsIgnoreCase(imeOdabranogArtikla)) {
                    //izbriši taj red
                }

            }
        }
    }


    public void EditItem() {
        if (!artikliTable.getSelectionModel().isEmpty()) {
            for (int i = 0; i < listaArtikala.size(); i++) {
                if (artikliTable.getSelectionModel().isSelected(i)) {
                    filtriranaListaArtikala = filtriranaListaArtikala
                            .stream()
                            .filter(a -> a.getName().equalsIgnoreCase(artikliTable.getSelectionModel().getSelectedItem().getName()))
                            .collect(Collectors.toList());
                }
            }

            artikliTable.setItems(FXCollections.observableList(filtriranaListaArtikala));
            artikliTable.setEditable(true);
            imeArtiklaTableColumn
                    .setCellFactory(TextFieldTableCell.forTableColumn());

            kategorijaArtiklaTableColumn
                    .setCellFactory(TextFieldTableCell.forTableColumn());

            sifraArtiklaTableColumn
                    .setCellFactory(TextFieldTableCell.forTableColumn());

            sirinaArtiklaTableColumn
                    .setCellFactory(TextFieldTableCell.forTableColumn());

            visinaArtiklaTableColumn
                    .setCellFactory(TextFieldTableCell.forTableColumn());

            duzinaArtiklaTableColumn
                    .setCellFactory(TextFieldTableCell.forTableColumn());

            cijenaProizvodnjeArtiklaTableColumn
                    .setCellFactory(TextFieldTableCell.forTableColumn());

            osnovnaProdajnaCijenaArtiklaTableColumn
                    .setCellFactory(TextFieldTableCell.forTableColumn());



        }
    }
}


