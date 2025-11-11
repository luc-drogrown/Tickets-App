package com.example.ticketsapp;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

//run this code only once to set the database

public class MainDB {

    public static void main(String[] args)
    {
        String url = "jdbc:mysql://localhost:3306/javabase";
        String username = "java";
        String password = "Password@12";

        System.out.println("Loading driver ...");

        try {
            Class.forName("com.mysql.cj.jdbc.Driver"); // Use com.mysql.jdbc.Driver if you're not on MySQL 8+ yet.
            System.out.println("Driver loaded!");
        } catch (ClassNotFoundException e) {
            throw new IllegalStateException("Cannot find the driver in the classpath!", e);
        }

        try {
            Connection connection = DriverManager.getConnection(url, username, password);
            System.out.println("Database connected!");

            Statement stmt = connection.createStatement();

            String createUsersTableQuery = "CREATE TABLE users (" +
                    " user_id INT AUTO_INCREMENT PRIMARY KEY," +
                    " username VARCHAR(50) NOT NULL," +
                    " email VARCHAR(100) UNIQUE NOT NULL," +
                    " password_hash VARCHAR(255) NOT NULL" +
                    ");";

            stmt.executeUpdate(createUsersTableQuery);
            System.out.println("Table created or already exists.");

            String createEventsTableQuery = "CREATE TABLE events (" +
                    " event_id INT AUTO_INCREMENT PRIMARY KEY," +
                    " title VARCHAR(100) NOT NULL," +
                    " description TEXT," +
                    " location VARCHAR(100)," +
                    " event_date DATETIME NOT NULL," +
                    " price DECIMAL(10,2) NOT NULL" +
                    ");";

            stmt.executeUpdate(createEventsTableQuery);
            System.out.println("Table created or already exists.");

            String createTicketsTableUpdate = "CREATE TABLE tickets (" +
                    " ticket_id INT AUTO_INCREMENT PRIMARY KEY," +
                    " user_id INT NOT NULL," +
                    " event_id INT NOT NULL," +
                    " purchase_date DATETIME DEFAULT CURRENT_TIMESTAMP," +
                    " seat_number VARCHAR(20)," +
                    " FOREIGN KEY (user_id) REFERENCES users(user_id)" +
                    " ON DELETE CASCADE" +
                    " ON UPDATE CASCADE," +
                    " FOREIGN KEY (event_id) REFERENCES events(event_id)" +
                    " ON DELETE CASCADE" +
                    " ON UPDATE CASCADE" +
                    ");";

            stmt.executeUpdate(createTicketsTableUpdate);
            System.out.println("Table created or already exists.");

            stmt.close();
            connection.close();
        } catch (SQLException e) {
            throw new IllegalStateException("Cannot connect the database!", e);
        }
    }
}
