package com.example.pavic6;

import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import model.*;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class FactoryAddController {
    @FXML
    private TextField imeNoveTvorniceTextField;
    @FXML
    private TextField ulicaNoveTvorniceTextField;
    @FXML
    private TextField kbrNoveTvorniceTextField;
    @FXML
    private ComboBox gradNoveTvorniceCombobox;

    @FXML
    private TableView artikliTableView;
    @FXML
    private TableColumn<Item, String> imeArtiklaTableColumn;
    @FXML
    private TableColumn<Item, String> kategorijaArtiklaTableColumn;
    @FXML
    private TableColumn<Item, String> sifraArtiklaTableColumn;


    List<Item> listaArtikala = HelloController.listaArtikala;
    List<Factory> listaTvornica = HelloController.listaTvornica;

    PrintWriter upisaneTvorniceFile;

    @FXML
    public void initialize() {

        for (City grad : City.values()) {
            gradNoveTvorniceCombobox.getItems().add(grad.getPostalCode() + " " + grad.getCityName());
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

        artikliTableView.setItems(FXCollections.observableList(listaArtikala));
    }

    @FXML
    public void AddFactory() {

        StringBuilder errorMessage = new StringBuilder();
        Set<Item> artikliNoveTvornice = new HashSet<>();
        City gradNoveTvornice = null;

        for (City grad : City.values()) {
            if (gradNoveTvorniceCombobox.getSelectionModel().equals(grad)) {
                gradNoveTvornice = grad;

            }
        }
        Address adresaTvornice = new Address.Builder(gradNoveTvornice)
                .street(ulicaNoveTvorniceTextField.getText())
                .houseNumber(kbrNoveTvorniceTextField.getText())
                .build();

        artikliTableView.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
        ObservableList<Item> artikliNoveTvorniceObList = artikliTableView.getSelectionModel().getSelectedItems();
        for (Item a : artikliNoveTvorniceObList) {
            artikliNoveTvornice.add(a);
            System.out.println(a.getName());
        }


        if (imeNoveTvorniceTextField.getText().isEmpty()) {
            String message = "Naziv tvornice je obavezan!";
            errorMessage.append(message + "\n");
        }
        if (ulicaNoveTvorniceTextField.getText().isEmpty() || kbrNoveTvorniceTextField.getText().isEmpty()) {
            String message = "Ulica i kućni broj tvornice su obavezni!";
            errorMessage.append(message + "\n");
        }
        if (gradNoveTvorniceCombobox.getSelectionModel().isEmpty()) {
            String message = "Ulica i kućni broj tvornice su obavezni!";
            errorMessage.append(message + "\n");
        }
        if (artikliNoveTvornice.isEmpty()) {
            String message = "Morate odabrati barem jedan artikl!";
            errorMessage.append(message + "\n");
        }

        Factory novaTvornica = new Factory(imeNoveTvorniceTextField.getText(), adresaTvornice, artikliNoveTvornice, gradNoveTvornice);

        if (errorMessage.isEmpty()) {

            {
                try {
                    upisaneTvorniceFile = new PrintWriter(new FileWriter("datoteke/tvornice.txt", true));
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            upisaneTvorniceFile.println(imeNoveTvorniceTextField.getText() + ";" + adresaTvornice.getStreet()+";" +adresaTvornice.getHouseNumber() + ";" + gradNoveTvornice.getCityName()+";" + artikliNoveTvornice.stream().map(NamedEntity::getName).collect(Collectors.joining(",")));
            upisaneTvorniceFile.close();

            listaTvornica.add(novaTvornica);

            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Uspješan unos!");
            alert.setHeaderText("Uspješno ste spremili novu tvornicu: " + novaTvornica.getName().toUpperCase() + "!");
            alert.setContentText("Tvornica  " + novaTvornica.getName().toUpperCase() + " - " + novaTvornica.getAddress()+" ,"
                            +novaTvornica.getGrad().getPostalCode()+" "+novaTvornica.getGrad().getCityName()+
                    "\n je uspješno spremljena u aplikaciju!");
            alert.showAndWait();
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Tvornica nije pohranjena!");
            alert.setHeaderText("Neuspjela akcija!");
            alert.setContentText(errorMessage.toString());
            alert.showAndWait();
        }
    }
}
