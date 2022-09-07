module com.example.pavic5 {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.pavic6 to javafx.fxml;
    exports com.example.pavic6;
}