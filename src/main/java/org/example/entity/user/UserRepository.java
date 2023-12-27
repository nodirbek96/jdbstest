package org.example.entity.user;

import org.example.entity.DBConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserRepository implements UserTableCallbacks {
    private Connection connection;

    @Override
    public boolean createUserTable() {
        connection = DBConnection.makeConnection();
        boolean status;
        try {
            if (connection.isClosed())
                connection = DBConnection.makeConnection();
            String query =
                    "CREATE TABLE IF NOT EXISTS users " +
                            "(id int(11) NOT NULL AUTO_INCREMENT," +
                            "firstname varchar(255), " +
                            "lastname varchar(255), " +
                            "username varchar(255), " +
                            "password varchar(255), " +
                            "created_date TIMESTAMP default CURRENT_TIMESTAMP NOT NULL," +
                            "PRIMARY KEY(id), " +
                            "UNIQUE (password))";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.execute();
            status = true;
        } catch (SQLException e) {
            status = false;
            System.out.println("Exception: " + e.getLocalizedMessage());
        }
        return status;
    }

    @Override
    public UserDto insertUser(UserDto userDto) {
        connection = DBConnection.makeConnection();
        UserDto insertedUserDto = null;
        try {
            if (connection.isClosed())
                connection = DBConnection.makeConnection();
            String query = "INSERT INTO users(firstname,lastname,username,password) " +
                    "VALUES(?,?,?,?) ";
            PreparedStatement preparedStatement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, userDto.getFirstname());
            preparedStatement.setString(2, userDto.getLastname());
            preparedStatement.setString(3, userDto.getUsername());
            preparedStatement.setString(4, userDto.getPassword());
            preparedStatement.execute();
            ResultSet resultSet = preparedStatement.getGeneratedKeys();
            if (resultSet.next()) {
                int id = resultSet.getInt(1);
                query = "SELECT * FROM users WHERE id='" + id + "'";
                preparedStatement = connection.prepareStatement(query);
                resultSet = preparedStatement.executeQuery();
                insertedUserDto = new UserDto();
                while (resultSet.next()) {
                    insertedUserDto.setId(resultSet.getInt(1));
                    insertedUserDto.setFirstname(resultSet.getString(2));
                    insertedUserDto.setLastname(resultSet.getString(3));
                    insertedUserDto.setUsername(resultSet.getString(4));
                    insertedUserDto.setPassword(resultSet.getString(5));
                    insertedUserDto.setCreatedDateTime(resultSet.getTimestamp(6).toLocalDateTime());
                }
            }
        } catch (SQLException e) {
            System.out.println(e.getLocalizedMessage());
        }
        return insertedUserDto;
    }

    @Override
    public UserDto updateUser(UserDto userDto) {
        UserDto updatedUserDto = null;
        connection = DBConnection.makeConnection();
        try {
            if (connection.isClosed())
                connection = DBConnection.makeConnection();
            String query = "UPDATE users SET firstname='" + userDto.getFirstname() +
                    "',lastname='" + userDto.getLastname() +
                    "',username='" + userDto.getUsername() +
                    "',password='" + userDto.getPassword() +
                    "' WHERE id='" + userDto.getId() + "'";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.execute();
            updatedUserDto = userDto;
        } catch (SQLException e) {
            System.out.println(e.getLocalizedMessage());
        }
        return updatedUserDto;
    }

    @Override
    public boolean deleteUser(Integer id) {
        connection = DBConnection.makeConnection();
        boolean status = false;
        try {
            if (connection.isClosed())
                connection = DBConnection.makeConnection();
            String query = "DELETE FROM users where id='" + id + "'";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            status = preparedStatement.execute();
        } catch (SQLException e) {
            System.out.println(e.getLocalizedMessage());
        }
        return status;
    }

    @Override
    public UserDto getUser(Integer id) {
        UserDto userDto = null;
        connection = DBConnection.makeConnection();
        try {
            if (connection.isClosed())
                connection = DBConnection.makeConnection();
            String query = "SELECT * FROM users where id='" + id + "'";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                userDto = new UserDto(
                        resultSet.getInt(1),
                        resultSet.getString(2),
                        resultSet.getString(3),
                        resultSet.getString(4),
                        resultSet.getString(5),
                        resultSet.getTimestamp(6).toLocalDateTime()
                );
            }
        } catch (SQLException e) {
            System.out.println(e.getLocalizedMessage());
        }
        return userDto;
    }

    @Override
    public List<UserDto> getAllUsers() {
        List<UserDto> list = null;
        connection = DBConnection.makeConnection();
        try {
            if (connection.isClosed())
                connection = DBConnection.makeConnection();
            String query = "SELECT * FROM users";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            ResultSet resultSet = preparedStatement.executeQuery();
            list = new ArrayList<>();
            while (resultSet.next()) {
                UserDto userDto = new UserDto(
                        resultSet.getInt(1),
                        resultSet.getString(2),
                        resultSet.getString(3),
                        resultSet.getString(4),
                        resultSet.getString(5),
                        resultSet.getTimestamp(6).toLocalDateTime());
                list.add(userDto);
            }

        } catch (SQLException e) {
            System.out.println(e.getLocalizedMessage());
        }
        return list;
    }
}
