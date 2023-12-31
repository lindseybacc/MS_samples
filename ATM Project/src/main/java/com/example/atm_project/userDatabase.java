package com.example.atm_project;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Data access object
 *
 * @author Julian Jupiter
 *
 */
class userDatabase {

    protected static final Logger logger = Logger.getLogger(userDatabase.class.getName());

    public boolean userExists(String username) throws SQLException {
        Connection connection = null;
        PreparedStatement statement = null;
        List<User> users = new ArrayList<>();

        try {
            connection = atm_database.getDBConnection();
            connection.setAutoCommit(false);
            String query = "SELECT id, username, last_name, first_name, pin FROM user WHERE username = ?";
            statement = connection.prepareStatement(query);
            int counter = 1;
            statement.setString(counter++, username);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                User user = new User();
                user.setId(resultSet.getInt(1));
                user.setUsername(resultSet.getString(2));
                user.setLastName(resultSet.getString(3));
                user.setFirstName(resultSet.getString(4));
                user.setPin(resultSet.getInt(5));
                users.add(user);
            }

            return users.isEmpty() ? false : true;
        } catch (SQLException exception) {
            logger.log(Level.SEVERE, exception.getMessage());
        } finally {
            if (null != statement) {
                statement.close();
            }

            if (null != connection) {
                connection.close();
            }
        }

        return users.isEmpty() ? false : true;
    }

    public int updateUser(User user) throws SQLException {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;

        try {
            connection = atm_database.getDBConnection();
            connection.setAutoCommit(false);
            String query = "UPDATE user SET first_name = ?, last_name = ?, pin = ? WHERE username = ?;";
            statement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            int counter = 1;
            statement.setString(counter++, user.getFirstName());
            statement.setString(counter++, user.getLastName());
            statement.setInt(counter++, user.getPin());
            statement.setString(counter, user.getUsername());
            statement.executeUpdate();
            connection.commit();
            return 1;
        } catch (SQLException exception) {
            logger.log(Level.SEVERE, exception.getMessage());
            if (null != connection) {
                connection.rollback();
            }
        } finally {
            if (null != resultSet) {
                resultSet.close();
            }
            if (null != statement) {
                statement.close();
            }
            if (null != connection) {
                connection.close();
            }
        }
        return 0;
    }


    public int saveUser(User user) throws SQLException {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            connection = atm_database.getDBConnection();
            connection.setAutoCommit(false);
            String query = "INSERT INTO user(username, last_name, first_name, pin) VALUES(?, ?, ?, ?)";
            statement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            int counter = 1;
            statement.setString(counter++, user.getUsername());
            statement.setString(counter++, user.getLastName());
            statement.setString(counter++, user.getFirstName());
            statement.setInt(counter++, user.getPin());
            statement.executeUpdate();
            connection.commit();
            resultSet = statement.getGeneratedKeys();
            if (resultSet.next()) {
                return resultSet.getInt(1);
            }
        } catch (SQLException exception) {
            logger.log(Level.SEVERE, exception.getMessage());
            if (null != connection) {
                connection.rollback();
            }
        } finally {
            if (null != resultSet) {
                resultSet.close();
            }

            if (null != statement) {
                statement.close();
            }

            if (null != connection) {
                connection.close();
            }
        }
        return 0;
    }
}


