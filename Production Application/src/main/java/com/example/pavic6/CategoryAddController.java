package com.example.pavic6;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import model.Category;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

public class CategoryAddController {
    @FXML
    private TextField imeNoveKategorijeTextField;
    @FXML
    private TextField opisNoveKategorijeTextField;


    List<Category> listaKategorija = HelloController.listaKategorija;

    PrintWriter upisaneKategorijeFile;


    @FXML
    public void AddCategory() {

        StringBuilder errorMessage = new StringBuilder();

        if (imeNoveKategorijeTextField.getText().isEmpty()) {
            String message = "Naziv kategorije je obavezan!";
            errorMessage.append(message + "\n");
        }
        if (opisNoveKategorijeTextField.getText().isEmpty()) {
            String message = "Opis kategorije je obavezan!";
            errorMessage.append(message + "\n");
        }

        if (errorMessage.isEmpty()) {


            Category novaKategorija = new Category(imeNoveKategorijeTextField.getText(), opisNoveKategorijeTextField.getText());
            {
                try {
                    upisaneKategorijeFile = new PrintWriter(new FileWriter("datoteke/kategorije.txt", true));
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            upisaneKategorijeFile.println(imeNoveKategorijeTextField.getText() + "," + opisNoveKategorijeTextField.getText());
            upisaneKategorijeFile.close();

            listaKategorija.add(novaKategorija);

            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Uspješan unos!");
            alert.setHeaderText("Uspješno ste spremili novu kategoriju : " + novaKategorija.getName().toUpperCase() + "!");
            alert.setContentText("Kategorija  " + novaKategorija.getName().toUpperCase() + " - " + novaKategorija.getDescription().toUpperCase() +
                    "\n je uspješno spremljena u aplikaciju!");
            alert.showAndWait();
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Kategorija nije pohranjena!");
            alert.setHeaderText("Neuspjela akcija!");
            alert.setContentText(errorMessage.toString());
            alert.showAndWait();
        }

    }
}
