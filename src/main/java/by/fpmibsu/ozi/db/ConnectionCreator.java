package by.fpmibsu.ozi.db;

import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

class ConnectionCreator
{
    public static final Properties PROPERTIES = new Properties();
    private static final String DATABASE_URL;

    static
    {
        try
        {
            PROPERTIES.setProperty("db.url", "jdbc:mysql://localhost:3306/mydb");
            PROPERTIES.setProperty("password", "MarvelWeiandover69");
            PROPERTIES.setProperty("user", "root");
            PROPERTIES.setProperty("db.driver", "com.mysql.cj.jdbc.Driver");
            String driverName = (String) PROPERTIES.get("db.driver");
            Class.forName(driverName);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        DATABASE_URL = (String) PROPERTIES.get("db.url");
    }
    private ConnectionCreator() {}
    public static Connection createConnection() throws SQLException
    {
        return DriverManager.getConnection(DATABASE_URL, PROPERTIES);
    }
}
