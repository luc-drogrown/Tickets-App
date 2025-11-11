package com.example.ticketsapp;

import java.sql.*;

public class DBManager
{
    //attributes
    private String DBurl = "jdbc:mysql://localhost:3306/javabase";
    private String DBusername = "java";
    private String DBpassword = "Password@12";

    //methods
    public boolean addUser(String email, String password)
    {
        String checkQuery = "SELECT COUNT(*) FROM users WHERE email=?";
        String insertQuery = "INSERT INTO users (email, password) VALUES (?, ?)";

        try (Connection connection = DriverManager.getConnection(DBurl, DBusername, DBpassword))
        {
            try (PreparedStatement preparedStatement = connection.prepareStatement(checkQuery))
            {
                preparedStatement.setString(1, email);
                ResultSet resultSet = preparedStatement.executeQuery();
                resultSet.next();
                if (resultSet.getInt(1) > 0) {
                    System.out.println("User already exists!");
                    return false;
                }
                connection.close();
            }

            try (PreparedStatement ps = connection.prepareStatement(insertQuery))
            {
                ps.setString(1, email);
                ps.setString(2, password);

                ps.executeUpdate();
                connection.close();
            }
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }

        System.out.println("User added successfully!");
        return true;
    }

    public boolean addEvent(String title, String description, String location, Date eventDate, float price, int maxTickets)
    {
        String checkQuery = "SELECT COUNT(*) FROM events WHERE title=?";
        String insertQuery = "INSERT INTO events (title, description, location, event_date, price, max_tickets) VALUES (?, ?, ?, ?, ?, ?)";

        try (Connection connection = DriverManager.getConnection(DBurl, DBusername, DBpassword))
        {
            try (PreparedStatement preparedStatement = connection.prepareStatement(checkQuery))
            {
                preparedStatement.setString(1, title);
                ResultSet resultSet = preparedStatement.executeQuery();
                resultSet.next();
                if (resultSet.getInt(1) > 0) {
                    System.out.println("Event already exists!");
                    return false;
                }
                connection.close();
            }

            try (PreparedStatement preparedStatement = connection.prepareStatement(insertQuery))
            {
                preparedStatement.setString(1, title);
                preparedStatement.setString(2, description);
                preparedStatement.setString(3, location);
                preparedStatement.setDate(4, eventDate);
                preparedStatement.setFloat(5, price);
                preparedStatement.setInt(6, maxTickets);

                preparedStatement.executeUpdate();
                connection.close();
            }
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }

        System.out.println("Event added successfully!");
        return true;
    }
}

