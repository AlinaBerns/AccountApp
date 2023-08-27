package com.example.accountapp.repository;

import com.example.accountapp.config.Const;
import com.example.accountapp.config.MySqlConfiguration;
import com.example.accountapp.model.Account;
import com.example.accountapp.model.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Optional;

import static com.example.accountapp.config.Const.USER_FIRSTNAME;


public class UserRepository {
    public Optional<User> createUser(User user) {

        //send account to database
        String query = "INSERT INTO " +Const.USER_TABLE + "(id, fname, lname, accemail) VALUES(?,?,?,?)";


        //Try-with-resourses
        try (Connection connection = MySqlConfiguration.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)){

            statement.setLong(1, 0);
            statement.setString(2, user.getFname());
            statement.setString(3, user.getLname());
            statement.setString(4, user.getAccount().getEmail());

            //INSERT with executeUpdate;
            statement.executeUpdate();


        } catch (SQLException e) {
            System.err.println("ERROR: COULD NOT CREATE USER");
            throw new RuntimeException(e);

        }

        return Optional.empty();
    }
}
