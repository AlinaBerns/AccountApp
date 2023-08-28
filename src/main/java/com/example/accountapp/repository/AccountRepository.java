package com.example.accountapp.repository;

import com.example.accountapp.config.Const;
import com.example.accountapp.config.MySqlConfiguration;
import com.example.accountapp.model.Account;

import java.sql.*;
import java.util.List;
import java.util.Optional;

public class AccountRepository {
    public boolean createAccount(Account account) {
        //send account to database
        String query = "INSERT INTO " + Const.ACCOUNT_TABLE + "("
                + Const.EMAIL + "," + Const.USER_PASSWORD + ") "
                + "VALUES(?,?);";

        //INSERT with executeUpdate;
        try (Connection connection = MySqlConfiguration.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(query);

            statement.setString(1, account.getEmail());
            statement.setString(2, account.getPassw());
            statement.executeUpdate();

        } catch (SQLException e) {
            System.err.println("ERROR: COULD NOT CREATE ACCOUNT");
            throw new RuntimeException(e);

        }
        return true;
    }

    public Optional<Account> getAccount(String email) {

        //elke methode in repository moet Connection bevatten

        String query = String.format(
                "Select * FROM " +Const.ACCOUNT_TABLE+" WHERE "+Const.EMAIL+ " like '%s' ", email);

        String prQuery = "Select * FROM "+ Const.ACCOUNT_TABLE+" WHERE email like ? ";

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

    public void createManyAccounts(List<Account> accountList) {
        String query = "INSERT INTO "+Const.ACCOUNT_TABLE+" VALUES (?, ?); ";

        try (Connection connection = MySqlConfiguration.getConnection()) {

            PreparedStatement statement = connection.prepareStatement(query);

            for (Account account : accountList) {
                statement.setString(1, account.getEmail());
                statement.setString(2, account.getPassw());
                statement.addBatch();
            }

            statement.executeBatch();

        } catch (SQLException e) {
            System.err.println("IT FAILED");
            e.printStackTrace();
        }
    }
}
