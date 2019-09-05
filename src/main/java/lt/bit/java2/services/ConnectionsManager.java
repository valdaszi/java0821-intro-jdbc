package lt.bit.java2.services;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionsManager {

    private static String URL = "jdbc:mysql://localhost:3306/employees?serverTimezone=Europe/Vilnius";
    private static String USER = "root";
    private static String PASSWORD = "mysql123";

    private HikariDataSource ds;

    public ConnectionsManager() {
        HikariConfig config = new HikariConfig();
        config.setJdbcUrl(URL);
        config.setUsername(USER);
        config.setPassword(PASSWORD);
        config.addDataSourceProperty("cachePrepStmts", "true");
        config.addDataSourceProperty("prepStmtCacheSize", "250");
        config.addDataSourceProperty("prepStmtCacheSqlLimit", "2048");

        ds = new HikariDataSource(config);
    }

    public Connection getConnection(boolean fromPool) throws SQLException {
        return fromPool ? ds.getConnection() : DriverManager.getConnection(URL, USER, PASSWORD);
    }
}