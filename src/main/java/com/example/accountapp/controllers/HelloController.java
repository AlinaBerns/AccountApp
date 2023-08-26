package com.example.accountapp.controllers;

import com.example.accountapp.config.MySqlConfiguration;
import com.example.accountapp.model.Account;
import com.example.accountapp.repository.AccountRepository;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;

import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class HelloController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button authSignInButton;

    @FXML
    private TextField emailField;

    @FXML
    private Button loginSignUpButton;

    @FXML
    private PasswordField passwField;

    @FXML
    void initialize() {

        authSignInButton.setOnAction(actionEvent -> {
            String emailText = emailField.getText().trim();
            String loginPassword = passwField.getText().trim();

            if (!emailText.equals("")&&!loginPassword.equals("")){
                loginUser(emailText, loginPassword);
            }else{
                System.out.println("Email or Password is empty");
            }
        });

        loginSignUpButton.setOnAction(actionEvent -> {
            loginSignUpButton.getScene().getWindow().hide();

            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/com/example/accountapp/signup.fxml"));

            try {
                loader.load();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }

            Parent root = loader.getRoot();
            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.showAndWait();
        });


    }

    private void loginUser(String emailText, String loginPassword) {
        //AccountRepository accountRepository = new AccountRepository();
        //Account account = new Account(emailText, loginPassword);
        MySqlConfiguration mySqlConfiguration = new MySqlConfiguration();

        ResultSet resultSet;


        try {
            resultSet = mySqlConfiguration.getResultAccount(new Account(emailText, loginPassword));
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

        int counter = 0;
        while (true) {
            try {
                if (!resultSet.next()) break;
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
            counter++;
        }

        if (counter >= 1) {
            System.out.println("Success");
        }

    }

}


