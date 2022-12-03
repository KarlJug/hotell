package com.hbs.hotell;

/*
    nimi parool jne. teha läbi properties faili
 */


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class DatabaseConnectionManager {

    public Connection databaseLink;

    private final String url;
    private final Properties properties;

    public DatabaseConnectionManager(String host, String databaseName,
                                     String username, String password) {

        this.url = "jdbc:postgresql://" + host + "/" + databaseName;
        this.properties = new Properties();
        this.properties.setProperty("user", username);
        this.properties.setProperty("password", password);

    }

    public Connection getConnection() throws SQLException {
        return DriverManager.getConnection(this.url, this.properties);
    }


    /*
    public Connection getDatabaseLink() {
        String databaseName = "test_info";
        String databaseUser = "root";
        String databasePassword = "Passw0rd";
        String url = "jdbc:mysql://localhost/" + databaseName;



        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            databaseLink = DriverManager.getConnection(url, databaseUser, databasePassword);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return databaseLink;
    }
     */


}
