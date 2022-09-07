package com.example.pavic6;


import javafx.fxml.FXML;
import model.*;

import java.io.*;
import java.math.BigDecimal;
import java.util.*;


public class HelloController {

    public static List<Category> listaKategorija = new ArrayList<>();
    public static List<Item> listaArtikala = new ArrayList<>();
    public static List<Factory> listaTvornica = new ArrayList<>();
    public static List<Store> listaTrgovina = new ArrayList<>();

    @FXML
    public void initialize() {

        Scanner skeniraneKategorijeFile = null;
        String imeKategorije;
        String opisKategorije;
        Category novaKategorija;

        {
            try {
                skeniraneKategorijeFile = new Scanner((new File("datoteke/kategorije.txt")));
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }

        while (skeniraneKategorijeFile.hasNext()) {
            String line = skeniraneKategorijeFile.nextLine();
            Scanner scLine = new Scanner(line).useDelimiter(",");
            imeKategorije = scLine.next();
            opisKategorije = scLine.next();
            novaKategorija = new Category(imeKategorije, opisKategorije);
            listaKategorija.add(novaKategorija);
            scLine.close();
        }
        skeniraneKategorijeFile.close();

        Scanner skeniraniArtikliFile = null;
        String imeArtikla, kategorijaArtiklaString, sifraArtikla;
        BigDecimal width, height, length, productionCost, sellingPrice, popust, cijenaSPopustom;
        Item noviArtikl;
        Category kategorijaArtikla = null;

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
            kategorijaArtiklaString = scLine.next();
            width = scLine.nextBigDecimal();
            height = scLine.nextBigDecimal();
            length = scLine.nextBigDecimal();
            productionCost = scLine.nextBigDecimal();
            sellingPrice = scLine.nextBigDecimal();
            popust = scLine.nextBigDecimal();
            cijenaSPopustom = scLine.nextBigDecimal();
            sifraArtikla = scLine.next();

            for (int i = 0; i < listaKategorija.size(); i++) {
                if (kategorijaArtiklaString.equalsIgnoreCase(listaKategorija.get(i).getName())) {
                    kategorijaArtikla = listaKategorija.get(i);
                }
            }

            noviArtikl = new Item(imeArtikla, kategorijaArtikla, width, height, length, productionCost, sellingPrice, popust, cijenaSPopustom, sifraArtikla);
            listaArtikala.add(noviArtikl);
            scLine.close();
        }

        skeniraniArtikliFile.close();


        Scanner skeniraneTvorniceFile = null;
        String imeTvornice, ulicaTvornice, kbrTvornice, gradTvornice;
        Factory novaTvornica;
        String artikliTvorniceNazivi;
        City gradNoveTvornice = null;

        List<String> artikliTvorniceListaNaziva = new ArrayList<>();


        {
            try {
                skeniraneTvorniceFile = new Scanner((new File("datoteke/tvornice.txt")));
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }
        while (skeniraneTvorniceFile.hasNextLine()) {
            artikliTvorniceListaNaziva.clear();
            String line = skeniraneTvorniceFile.nextLine();
            Scanner scLine = new Scanner(line).useDelimiter(";");
            imeTvornice = scLine.next();
            ulicaTvornice = scLine.next();
            kbrTvornice = scLine.next();
            gradTvornice=scLine.next();
            artikliTvorniceNazivi = scLine.next();
            scLine.close();

            for (City grad : City.values()) {
                if (gradTvornice.equalsIgnoreCase(grad.getCityName())) {
                    gradNoveTvornice = grad;

                }
            }

            artikliTvorniceListaNaziva.add(gradNoveTvornice.getCityName());
            Address adresaNoveTvornice = new Address.Builder(gradNoveTvornice)
                    .street(ulicaTvornice)
                    .houseNumber(kbrTvornice)
                    .build();


            Scanner scItems = new Scanner(artikliTvorniceNazivi).useDelimiter(",");

            while (scItems.hasNext()) {
                String artiklString = scItems.next();
                artikliTvorniceListaNaziva.add(artiklString);
            }

            scItems.close();

            Set<Item> artikliTvorniceSet = new HashSet<>();

            for (Item a : listaArtikala) {
                for (int i = 0; i < artikliTvorniceListaNaziva.size(); i++) {
                    if (a.getName().equalsIgnoreCase(artikliTvorniceListaNaziva.get(i))) {
                        artikliTvorniceSet.add(a);
                    }
                }
            }
            novaTvornica = new Factory(imeTvornice, adresaNoveTvornice, artikliTvorniceSet, gradNoveTvornice);
            listaTvornica.add(novaTvornica);
        }
        skeniraneTvorniceFile.close();




        Scanner skeniraneTrgovineFile = null;
        String imeTrgovine;
        String webAdresaTrgovine;
        Store novaTrgovina;
        String artikliTrgovineNazivi;

        List<String> artikliListaNaziva = new ArrayList<>();


        {
            try {
                skeniraneTrgovineFile = new Scanner((new File("datoteke/trgovine.txt")));
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }
        while (skeniraneTrgovineFile.hasNextLine()) {
            artikliListaNaziva.clear();
            String line = skeniraneTrgovineFile.nextLine();
            Scanner scLine = new Scanner(line).useDelimiter(";");
            imeTrgovine = scLine.next();
            webAdresaTrgovine = scLine.next();
            artikliTrgovineNazivi = scLine.next();
            scLine.close();

            Scanner scItems = new Scanner(artikliTrgovineNazivi).useDelimiter(",");

            while (scItems.hasNext()) {
                String artiklString = scItems.next();
                artikliListaNaziva.add(artiklString);
            }

            scItems.close();

            Set<Item> artikliTrgovineSet = new HashSet<>();

            for (Item a : listaArtikala) {
                for (int i = 0; i < artikliListaNaziva.size(); i++) {
                    if (a.getName().equalsIgnoreCase(artikliListaNaziva.get(i))) {
                        artikliTrgovineSet.add(a);
                    }
                }
            }
            novaTrgovina = new Store(imeTrgovine, webAdresaTrgovine, artikliTrgovineSet);
            listaTrgovina.add(novaTrgovina);
        }
        skeniraneTrgovineFile.close();
    }
}