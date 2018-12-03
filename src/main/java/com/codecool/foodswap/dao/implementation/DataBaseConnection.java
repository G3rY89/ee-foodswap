package com.codecool.foodswap.dao.implementation;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public abstract class DataBaseConnection {

    private final String DATABASE;
    private final String DB_USER;
    private final String DB_PASSWORD;
    private static boolean testing = false;

    public static void setTesting(boolean testing) {
        DataBaseConnection.testing = testing;
    }


    //jdbc:postgresql://localhost:5432/food_swap

    public DataBaseConnection() {

        Properties prop = new Properties();

        try (InputStream input = new FileInputStream("/src/main/resources/META-INF/connection.properties")) {

            prop.load(input);

        } catch (IOException ex) {
            ex.printStackTrace();
        }

        DATABASE = "jdbc:postgresql://" + prop.getProperty("url") + "/" + prop.getProperty(testing ? "database_test" : "database");
        DB_USER = prop.getProperty("database_user");
        DB_PASSWORD = prop.getProperty("database_password");
    }


    Connection getConnection() throws SQLException {
        return DriverManager.getConnection(
                DATABASE,
                DB_USER,
                DB_PASSWORD);

    }


}