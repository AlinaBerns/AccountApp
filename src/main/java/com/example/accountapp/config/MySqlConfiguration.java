package com.example.accountapp.config;

import com.example.accountapp.model.Account;

import java.sql.*;
import java.util.Properties;


public class MySqlConfiguration {

    //create method for the connection with DB
    public static Connection getConnection() {
        String user = "root";
        String password = "Bburns08121994.";

        //waar ziet mijn db
        String url = "localhost";

        //default port
        String port ="3306";

        //welke db will wij gebruiken
        String database = "accountapp";

        String connectionString = String.format("jdbc:mysql://%s:%s/%s",
                url, port, database);

        Properties connectionProperties = new Properties();
        connectionProperties.put("user", user);
        connectionProperties.put("password", password);

        Connection connection;

        try {
            connection = DriverManager.getConnection(connectionString, connectionProperties);

        } catch (SQLException e) {
            System.err.println("ERROR: COULD NOT CONNECT TO DATABASE.");
            throw new RuntimeException(e);
        }


        return connection;
    }

    public ResultSet getResultAccount(Account account) throws ClassNotFoundException {
        ResultSet resultSet = null;

        String select = "SELECT * FROM " + Const.ACCOUNT_TABLE + " WHERE "
                + Const.EMAIL + "=? AND " + Const.USER_PASSWORD + "=?;";

        try{
            Connection connection = MySqlConfiguration.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(select);
            preparedStatement.setString(1, account.getEmail());
            preparedStatement.setString(2, account.getPassw());


            resultSet = preparedStatement.executeQuery();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return resultSet;
    }



}
