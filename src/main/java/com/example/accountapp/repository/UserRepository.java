package com.example.accountapp.repository;

import com.example.accountapp.config.Const;
import com.example.accountapp.config.MySqlConfiguration;
import com.example.accountapp.model.Account;
import com.example.accountapp.model.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;



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
    public Optional<User> getUser(Account account) {

        String query = "SELECT * FROM User WHERE accemail like ?";

        try(Connection connection = MySqlConfiguration.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, account.getEmail());

            ResultSet resultSet = preparedStatement.executeQuery();

            if(resultSet.next()) {
                long id = resultSet.getLong("id");
                String fname = resultSet.getString(2);
                String lname = resultSet.getString("lname");

                User user = new User(id, fname, lname, Optional.of(account));

                return Optional.of(user);
            }

            return Optional.empty();

        }catch (SQLException e) {
            System.out.println("ERROR");
            e.printStackTrace();

        }
        return  Optional.empty();
    }

    public void createManyUsers (List<User> usersList) {
        String query = "INSERT INTO user VALUES (?, ?, ?, ?);";

        try (Connection connection = MySqlConfiguration.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(query);

            for (User user: usersList) {
                preparedStatement.setLong(1, 0);
                preparedStatement.setString(2, user.getFname());
                preparedStatement.setString(3, user.getLname());
                preparedStatement.setString(4, user.getAccount().getEmail());
            }

            preparedStatement.executeBatch();
        } catch (SQLException e) {
            System.err.println("IT FAILED");
            e.printStackTrace();
        }
    }
}
