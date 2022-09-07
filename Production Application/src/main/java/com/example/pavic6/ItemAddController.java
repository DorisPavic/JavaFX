package com.example.pavic6;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import model.Category;
import model.Item;

import java.io.*;
import java.math.BigDecimal;
import java.util.List;

public class ItemAddController {
    @FXML
    private TextField imeNovogArtiklaTextField;
    @FXML
    private TextField sifraNovogArtiklaTextField;
    @FXML
    private ComboBox kategorijaNovogArtiklaComboBox;
    @FXML
    private TextField sirinaNovogArtiklaTextField;
    @FXML
    private TextField visinaNovogArtiklaTextField;
    @FXML
    private TextField duzinaNovogArtiklaTextField;
    @FXML
    private TextField cijenaProizvodnjeNovogArtiklaTextField;
    @FXML
    private TextField osnovnaProdajnaCijenaTextField;
    @FXML
    private TextField popustNovogArtiklaTextField;


    List<Item> listaArtikala = HelloController.listaArtikala;
    List<Category> listaKategorija = HelloController.listaKategorija;

    PrintWriter upisaniArtikliFile;

    @FXML
    public void initialize() {

        for (Category kategorija : listaKategorija) {
            kategorijaNovogArtiklaComboBox.getItems().add(kategorija.getName());
        }

    }

    @FXML
    public void AddItem() {

        StringBuilder errorMessage = new StringBuilder();

        if (kategorijaNovogArtiklaComboBox.getSelectionModel().isEmpty()) {
            String message = "Odabir kategorije artikla je obavezan!";
            errorMessage.append(message + "\n");
        }

        if (imeNovogArtiklaTextField.getText().isEmpty() || sifraNovogArtiklaTextField.getText().isEmpty() || sirinaNovogArtiklaTextField.getText().isEmpty() ||
                visinaNovogArtiklaTextField.getText().isEmpty() || duzinaNovogArtiklaTextField.getText().isEmpty() || cijenaProizvodnjeNovogArtiklaTextField
                .getText().isEmpty() || osnovnaProdajnaCijenaTextField.getText().isEmpty() || popustNovogArtiklaTextField.getText().isEmpty()){
            String message = "Sva polja moraju biti upisana!";
            errorMessage.append(message + "\n");
        }

        if (errorMessage.isEmpty()) {

            Category kategorijaUnesenogNovogArtikla = null;
            for (int i = 0; i < listaKategorija.size(); i++) {
                if (kategorijaNovogArtiklaComboBox.getSelectionModel().isSelected(i)) {
                    kategorijaUnesenogNovogArtikla = listaKategorija.get(i);
                }
            }

            BigDecimal cijenaSPopustomNovogArtikla=BigDecimal.valueOf(Long.parseLong(osnovnaProdajnaCijenaTextField.getText())).multiply(BigDecimal.valueOf(100).subtract(BigDecimal.valueOf(Long.parseLong(popustNovogArtiklaTextField.getText()))).divide(BigDecimal.valueOf(100)));

            Item noviArtikl = new Item(imeNovogArtiklaTextField.getText(), kategorijaUnesenogNovogArtikla,
                    BigDecimal.valueOf(Long.parseLong(sirinaNovogArtiklaTextField.getText())), BigDecimal.valueOf(Long.parseLong(visinaNovogArtiklaTextField.getText())),
                    BigDecimal.valueOf(Long.parseLong(duzinaNovogArtiklaTextField.getText())), BigDecimal.valueOf(Long.parseLong(cijenaProizvodnjeNovogArtiklaTextField.getText())),
                    BigDecimal.valueOf(Long.parseLong(osnovnaProdajnaCijenaTextField.getText())), BigDecimal.valueOf(Long.parseLong(popustNovogArtiklaTextField.getText())),
                    cijenaSPopustomNovogArtikla, sifraNovogArtiklaTextField.getText());
            {
                try {
                    upisaniArtikliFile = new PrintWriter(new FileWriter("datoteke/artikli.txt", true));
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            upisaniArtikliFile.println(imeNovogArtiklaTextField.getText() + "," + kategorijaUnesenogNovogArtikla.getName() + "," +
                    BigDecimal.valueOf(Long.parseLong(sirinaNovogArtiklaTextField.getText())) + "," + BigDecimal.valueOf(Long.parseLong(visinaNovogArtiklaTextField.getText())) + "," +
                    BigDecimal.valueOf(Long.parseLong(duzinaNovogArtiklaTextField.getText())) + "," + BigDecimal.valueOf(Long.parseLong(cijenaProizvodnjeNovogArtiklaTextField.getText())) + "," +
                    BigDecimal.valueOf(Long.parseLong(osnovnaProdajnaCijenaTextField.getText())) + "," + BigDecimal.valueOf(Long.parseLong(popustNovogArtiklaTextField.getText())) + "," +
                    cijenaSPopustomNovogArtikla + "," + sifraNovogArtiklaTextField.getText());
            upisaniArtikliFile.close();

            listaArtikala.add(noviArtikl);

            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Uspješan unos!");
            alert.setHeaderText("Uspješno ste spremili novi artikl : " + noviArtikl.getName().toUpperCase() + "!");
            alert.setContentText("Artikl  " + noviArtikl.getName().toUpperCase() + " - " + noviArtikl.getSifraArtikla().toUpperCase() + ": šifra: "+noviArtikl.getSifraArtikla()+
                    "\n je uspješno spremljen u aplikaciju!");
            alert.showAndWait();
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Artikl nije pohranjen!");
            alert.setHeaderText("Neuspjela akcija!");
            alert.setContentText(errorMessage.toString());
            alert.showAndWait();
        }
    }
}
