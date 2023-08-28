package com.example.accountapp.controllers;

import com.example.accountapp.animations.Shake;
import com.example.accountapp.config.MySqlConfiguration;
import com.example.accountapp.model.Account;
import com.example.accountapp.model.User;
import com.example.accountapp.repository.AccountRepository;
import com.example.accountapp.service.LoginService;
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
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class HelloController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private AnchorPane anchorpaneHelloView;

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

        //DEZE METHOD WERKT MAAR MET ACCOUNT

        //AccountRepository accountRepository = new AccountRepository();
        //Account account = new Account(emailText, loginPassword);
        //MySqlConfiguration mySqlConfiguration = new MySqlConfiguration();

        //ResultSet resultSet;


        //try {
            //resultSet = mySqlConfiguration.getResultAccount(new Account(emailText, loginPassword));
        //} catch (ClassNotFoundException e) {
            //throw new RuntimeException(e);
        //}

        //int counter = 0;
        //while (true) {
            //try {
                //if (!resultSet.next()) break;
            //} catch (SQLException e) {
                //throw new RuntimeException(e);
            //}
            //counter++;
        //}

       // if (counter >= 1) {
         //   System.out.println("Success");
       // }

        //METHOD VAN MANUEL

        LoginService loginService = new LoginService();

        Optional<User> userSuccessLogin = loginService.login(emailField.getText(), passwField.getText());

        if(userSuccessLogin.isPresent()) {
            //for comp
            System.out.println("Welcome");

            //for app
            openNewScene("/com/example/accountapp/app.fxml");

        } else {
            //for comp
            System.out.println("Incorrect email or password");

            //foe app
            Text text = new Text("Please input correct password or username");
            text.setFont(Font.font("Verdana", FontWeight.BOLD, 16));
            text.setFill(Color.LIGHTBLUE);
            Shake userLoginAnim = new Shake(emailField);
            Shake userPassAnim = new Shake(passwField);
            userLoginAnim.playAnim();
            userPassAnim.playAnim();
            anchorpaneHelloView.getChildren().add(text);

        }

    }
    public void openNewScene (String window) {
        authSignInButton.getScene().getWindow().hide();

        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource(window));
        try {
            loader.load();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        Parent root = loader.getRoot();
        Stage stage = new Stage();
        stage.setScene(new Scene(root));
        stage.showAndWait();
    }

}


