package com.hbs.hotell;

/*
    nimi parool jne. teha läbi properties faili
 */


import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class DatabaseConnectionManager {

    public Connection databaseLink;

    private final String url;
    private final Properties properties;
    
    // tekitab ühenduse andmebaasiga
    public DatabaseConnectionManager() throws Exception {

        String host, databaseName, username, password;

        this.properties = new Properties();
        FileInputStream fileInputStream = new FileInputStream("Hotell/src/main/resources/database.properties");
        properties.load (fileInputStream);

        host = (String) properties.get("dphost");
        databaseName = (String) properties.get("dpname");
        username = (String) properties.get("dpuser") ;
        password = (String) properties.get("dppassword");

        this.url = "jdbc:postgresql://" + host + "/" + databaseName;

        this.properties.setProperty("user", username);
        this.properties.setProperty("password", password);

    }

    public Connection getConnection() throws SQLException {
        return DriverManager.getConnection(this.url, this.properties);
    }

}
