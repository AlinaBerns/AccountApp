module com.example.accountapp {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;


    opens com.example.accountapp to javafx.fxml;
    exports com.example.accountapp;
    exports com.example.accountapp.controllers;
    opens com.example.accountapp.controllers to javafx.fxml;
}