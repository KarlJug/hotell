package local.hotelli_susteem;

import java.sql.Connection;
import java.sql.DriverManager;

import java.io.File;

public class DatabaseConnection{

    public Connection databaseLink;


    public Connection getDatabaseLink() {
        String databaseName = "test_info";
        String databaseUser = "root";
        String databasePassword = "Passw0rd";
        String url = "jdbc:mysql://localhost/" + databaseName;

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            databaseLink = DriverManager.getConnection(url, databaseUser, databasePassword);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return databaseLink;
    }

}
