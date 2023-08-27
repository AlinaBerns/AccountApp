package com.example.accountapp.controllers;

import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;
import java.util.ResourceBundle;

import com.example.accountapp.config.MySqlConfiguration;
import com.example.accountapp.model.Account;
import com.example.accountapp.model.User;
import com.example.accountapp.repository.AccountRepository;
import com.example.accountapp.repository.UserRepository;
import com.example.accountapp.service.LoginService;
import com.example.accountapp.service.UserService;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

public class SignUpController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button signUpButton;

    @FXML
    private TextField signUpEmailField;

    @FXML
    private TextField signUpFirstNameField;

    @FXML
    private TextField signUpLastnameField;

    @FXML
    private PasswordField signUpPasswField;

    @FXML
    void initialize() {

        signUpButton.setOnAction(actionEvent -> {
            AccountRepository accountRepository = new AccountRepository();
            UserService userService = new UserService();
            MySqlConfiguration mySqlConfiguration = new MySqlConfiguration();
            String email = signUpEmailField.getText();
            String passw = signUpPasswField.getText();
            String fname = signUpFirstNameField.getText();
            String lname = signUpLastnameField.getText();

            userService.createUser(new User(fname,lname, Optional.of(new Account(email, passw))));

        });

    }
}
