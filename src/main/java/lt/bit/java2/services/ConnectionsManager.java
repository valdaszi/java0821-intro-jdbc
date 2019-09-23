package lt.bit.java2.services;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class ConnectionsManager {

//    private static String URL = "jdbc:mysql://localhost:3306/employees?serverTimezone=Europe/Vilnius";
//    private static String USER = "root";
//    private static String PASSWORD = "mysql123";

    private HikariDataSource ds;
    private Properties properties;

    private Properties getProperties() {
        if (properties == null) {
            properties = new Properties();
            //uzkrauname duomenis i properties is app.properties failo
            try {
                InputStream is = ConnectionsManager.class.getClassLoader().getResourceAsStream("app.properties");
                properties.load(is);
            } catch (IOException | NullPointerException e) {
                System.err.println("Nepavyko uzkrauti app.properties");
            }
        }
        return properties;
    }

    public ConnectionsManager() {
        HikariConfig config = new HikariConfig();

        config.setJdbcUrl(getProperties().getProperty("jdbc.url"));
        config.setUsername(getProperties().getProperty("jdbc.user"));
        config.setPassword(getProperties().getProperty("jdbc.password"));

        config.addDataSourceProperty("cachePrepStmts", "true");
        config.addDataSourceProperty("prepStmtCacheSize", "250");
        config.addDataSourceProperty("prepStmtCacheSqlLimit", "2048");

        ds = new HikariDataSource(config);
    }

    public Connection getConnection(boolean fromPool) throws SQLException {
        return fromPool ? ds.getConnection() : DriverManager.getConnection(
                getProperties().getProperty("jdbc.url"),
                getProperties().getProperty("jdbc.user"),
                getProperties().getProperty("jdbc.password"));
    }
}