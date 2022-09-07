package com.example.pavic6;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;

import java.io.IOException;

public class MenuController {
    public void  showHome(){
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("hello-view.fxml"));
        Scene scene = null;
        try {
            scene = new Scene(fxmlLoader.load(), 885, 480);
            scene.getStylesheets().add("style.css");
        } catch (IOException e) {
            e.printStackTrace();
        }
        HelloApplication.getMainStage().setTitle("Production application");
        HelloApplication.getMainStage().setScene(scene);
        HelloApplication.getMainStage().show();
    }
    public void  showItemAddScreen(){
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("itemAdd.fxml"));
        Scene scene = null;
        try {
            scene = new Scene(fxmlLoader.load(), 885, 480);
            scene.getStylesheets().add("style.css");
        } catch (IOException e) {
            e.printStackTrace();
        }
        HelloApplication.getMainStage().setTitle("Add new item");
        HelloApplication.getMainStage().setScene(scene);
        HelloApplication.getMainStage().show();
    }
    public void  showItemSearchScreen(){
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("itemSearch.fxml"));
        Scene scene = null;
        try {
            scene = new Scene(fxmlLoader.load(), 885, 480);
            scene.getStylesheets().add("style.css");
        } catch (IOException e) {
            e.printStackTrace();
        }
        HelloApplication.getMainStage().setTitle("Item search");
        HelloApplication.getMainStage().setScene(scene);
        HelloApplication.getMainStage().show();
    }
    public void  showCategoryAddScreen(){
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("categoryAdd.fxml"));
        Scene scene = null;
        try {
            scene = new Scene(fxmlLoader.load(), 885, 480);
            scene.getStylesheets().add("style.css");
        } catch (IOException e) {
            e.printStackTrace();
        }
        HelloApplication.getMainStage().setTitle("Add new category");
        HelloApplication.getMainStage().setScene(scene);
        HelloApplication.getMainStage().show();
    }
    public void  showCategorySearchScreen(){
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("categorySearch.fxml"));
        Scene scene = null;
        try {
            scene = new Scene(fxmlLoader.load(), 885, 480);
            scene.getStylesheets().add("style.css");
        } catch (IOException e) {
            e.printStackTrace();
        }
        HelloApplication.getMainStage().setTitle("Category search");
        HelloApplication.getMainStage().setScene(scene);
        HelloApplication.getMainStage().show();
    }

    public void  showFactorySearchScreen(){
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("factorySearch.fxml"));
        Scene scene = null;
        try {
            scene = new Scene(fxmlLoader.load(), 885, 480);
            scene.getStylesheets().add("style.css");
        } catch (IOException e) {
            e.printStackTrace();
        }
        HelloApplication.getMainStage().setTitle("Factory search");
        HelloApplication.getMainStage().setScene(scene);
        HelloApplication.getMainStage().show();
    }
    public void  showFactoryAddScreen(){
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("factoryAdd.fxml"));
        Scene scene = null;
        try {
            scene = new Scene(fxmlLoader.load(), 885, 480);
            scene.getStylesheets().add("style.css");
        } catch (IOException e) {
            e.printStackTrace();
        }
        HelloApplication.getMainStage().setTitle("Add new factory");
        HelloApplication.getMainStage().setScene(scene);
        HelloApplication.getMainStage().show();
    }
    public void  showStoreSearchScreen(){
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("storeSearch.fxml"));
        Scene scene = null;
        try {
            scene = new Scene(fxmlLoader.load(), 885, 480);
            scene.getStylesheets().add("style.css");
        } catch (IOException e) {
            e.printStackTrace();
        }
        HelloApplication.getMainStage().setTitle("Store search");
        HelloApplication.getMainStage().setScene(scene);
        HelloApplication.getMainStage().show();
    }
    public void  showStoreAddScreen(){
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("storeAdd.fxml"));
        Scene scene = null;
        try {
            scene = new Scene(fxmlLoader.load(), 885, 480);
            scene.getStylesheets().add("style.css");
        } catch (IOException e) {
            e.printStackTrace();
        }
        HelloApplication.getMainStage().setTitle("Add new store");
        HelloApplication.getMainStage().setScene(scene);
        HelloApplication.getMainStage().show();
    }
}
