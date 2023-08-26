package com.example.accountapp.repository;

import com.example.accountapp.config.Const;
import com.example.accountapp.config.MySqlConfiguration;
import com.example.accountapp.model.Account;
import com.example.accountapp.model.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Optional;

public class UserRepository {
    public Optional<User> createUser(User user) {

        //send account to database
        String query = String.format("INSERT INTO " +Const.USER_TABLE+ "(" + Const.USER_ID, Const.USER_FIRSTNAME,
                Const.USER_SURNAME, Const.ACCOUNT_EMAIL + ")" + "VALUES (? , ? , ? , ?);");


        //Try-with-resourses
        try (Connection connection = MySqlConfiguration.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)){

            statement.setLong(1, user.getId());
            statement.setString(2, user.getFname());
            statement.setString(3, user.getLname());
            statement.setString(4, user.getAccount().getEmail());

            //INSERT with executeUpdate;
            statement.executeUpdate(query);


        } catch (SQLException e) {
            System.err.println("ERROR: COULD NOT CREATE USER");
            throw new RuntimeException(e);

        }

        return Optional.empty();
    }
}
