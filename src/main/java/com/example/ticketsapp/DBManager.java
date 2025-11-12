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

    public boolean buyTicket(String email, String eventTitle)
    {
        int eventID = getEventID(eventTitle); int userID = getUserID(email); int maxNoTickets = getMaxTickets(eventTitle);

        String checkUserQuery = "SELECT COUNT(*) FROM tickets WHERE user_id=?";
        String checkAvailability = "SELECT COUNT(*) FROM tickets where eventID=?";
        String insertTicket = "INSERT INTO tickets (user_ID, event_ID) VALUES (?, ?)";

        try (Connection connection = DriverManager.getConnection(DBurl, DBusername, DBpassword))
        {
            try (PreparedStatement preparedStatement = connection.prepareStatement(checkUserQuery))
            {
                preparedStatement.setInt(1, userID);
                ResultSet resultSet = preparedStatement.getResultSet();
                resultSet.next();
                if (resultSet.getInt(1) > 0)
                {
                    System.out.println("User already has a ticket for this event!");
                    return false;
                }
            }

            try (PreparedStatement preparedStatement = connection.prepareStatement(checkAvailability))
            {
                preparedStatement.setInt(1, eventID);
                ResultSet resultSet = preparedStatement.getResultSet();
                resultSet.next();

                if (resultSet.getInt(1) <= maxNoTickets)
                {
                    try (PreparedStatement ps = connection.prepareStatement(insertTicket))
                    {
                        ps.setInt(1, userID);
                        ps.setInt(2, eventID);
                        ps.executeUpdate();
                    }
                    return true;
                }
                else
                {
                    System.out.println("No more tickets available!");
                    return false;
                }
            }
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
        return false;
    }

    public int getUserID(String email)
    {
        String query = "SELECT user_id FROM users WHERE email=?";

        try
        {
            Connection connection = DriverManager.getConnection(DBurl, DBusername, DBpassword);
            PreparedStatement preparedStatement = connection.prepareStatement(query);

            preparedStatement.setString(1, email);
            ResultSet resultSet = preparedStatement.getResultSet();
            resultSet.next();
            if (resultSet.getInt(1) > 0)
            {
                System.out.println("User found!");
                return resultSet.getInt(1);
            }
            else
            {
                System.out.println("User not found!");
                return -1;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return 0;
    }

    public int getEventID(String title)
    {
        String query = "SELECT event_id FROM users WHERE title=?";

        try
        {
            Connection connection = DriverManager.getConnection(DBurl, DBusername, DBpassword);
            PreparedStatement preparedStatement = connection.prepareStatement(query);

            preparedStatement.setString(1, title);
            ResultSet resultSet = preparedStatement.getResultSet();
            resultSet.next();
            if (resultSet.getInt(1) > 0)
            {
                System.out.println("Event found!");
                return resultSet.getInt(1);
            }
            else
            {
                System.out.println("Event not found!");
                return -1;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return 0;
    }

    public int getMaxTickets(String title)
    {
        String query = "SELECT max_tickets FROM users WHERE title=?";

        try
        {
            Connection connection = DriverManager.getConnection(DBurl, DBusername, DBpassword);
            PreparedStatement preparedStatement = connection.prepareStatement(query);

            preparedStatement.setString(1, title);
            ResultSet resultSet = preparedStatement.getResultSet();
            resultSet.next();
            return resultSet.getInt(1);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return 0;
    }
}