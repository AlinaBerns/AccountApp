package com.example.accountapp.repository;

import com.example.accountapp.config.Const;
import com.example.accountapp.config.MySqlConfiguration;
import com.example.accountapp.model.Account;

import java.sql.*;
import java.util.Optional;

public class AccountRepository {
    public boolean createAccount(Account account) {
        //send account to database
        String query = String.format("INSERT INTO "+ Const.ACCOUNT_TABLE+ " VALUES ('%s' , '%s');",
                account.getEmail(), account.getPassw());

        Connection connection = MySqlConfiguration.getConnection();

        //INSERT with executeUpdate;
        try {
            Statement statement = connection.createStatement();
            statement.executeUpdate(query);

            //this is important

            connection.close();

        } catch (SQLException e) {
            System.err.println("ERROR: COULD NOT CREATE ACCOUNT");
            throw new RuntimeException(e);

        }
        return true;
    }

    public Optional<Account> getAccount (String email) {

        //elke methode in repository moet Connection bevatten

        String query = String.format(
                "Select * FROM Account WHERE email like '%s' ", email);

        String prQuery = "Select * FROM Account WHERE email like ? ";

        try (Connection connection = MySqlConfiguration.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(prQuery);
            statement.setString(1, email);

            ResultSet resultSet = statement.executeQuery(query);

            while (resultSet.next()) {
                String dbEmail = resultSet.getString("email");
                String dbPass = resultSet.getString("passw");

                Account account = new Account(dbEmail, dbPass);
                return Optional.of(account);
            }

        } catch (SQLException e) {
            System.err.println("Error: could not find account");
            throw new RuntimeException(e);
        }

        return Optional.empty();
    }
}
