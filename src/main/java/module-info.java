module com.example.accountapp {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.accountapp to javafx.fxml;
    exports com.example.accountapp;
}