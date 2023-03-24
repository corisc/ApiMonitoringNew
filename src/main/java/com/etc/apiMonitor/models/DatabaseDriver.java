package com.etc.apiMonitor.models;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.util.ArrayList;

public class DatabaseDriver {

    private Connection conn;

    public DatabaseDriver() {
        try {
            this.conn = DriverManager.getConnection("jdbc:sqlite:./mazebank.db");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    /*
    Client Section
     */

    public ResultSet getUserData(String eMailAddress, String password) throws SQLException {
        Statement statement;
        ResultSet resultSet = null;
        try {
            statement = this.conn.createStatement();
            String query = "SELECT * FROM client WHERE email_address='"+eMailAddress+ "' AND Password='" +password+ "';";
            System.out.println(query);
            resultSet = statement.executeQuery(query);
            System.out.println(resultSet);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return resultSet;
    }

    public boolean insertNewUser(String firstName, String lastName, String email, String password, LocalDate date) {
        Statement statement;
        try {
            statement = this.conn.createStatement();
            String query = "INSERT INTO client (first_name, last_name, email_address, password, date_created) VALUES ('"+firstName+"', '"+lastName+"', '"+email+"', '"+password+"', '"+date.toString()+"');";
            statement.executeUpdate(query);
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    public ResultSet getAllClientsData() {
        Statement statement;
        ResultSet resultSet = null;
        try {
            statement = this.conn.createStatement();
            resultSet = statement.executeQuery("SELECT * FROM client;");
        }catch (SQLException e){
            e.printStackTrace();
        }
        return resultSet;
    }



    /*
    Admin Section
     */

    /*
    Utility Methods
     */
}
