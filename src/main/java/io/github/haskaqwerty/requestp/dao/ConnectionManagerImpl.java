package io.github.haskaqwerty.filmlibrary.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionManagerImpl {
    private static String url = "jdbc:postgresql://localhost:5432/postgres";
    private static String username = "postgresuser";
    private static String password = "postgres";

    public static Connection getConnection() throws SQLException {
        Connection connection = DriverManager.getConnection(url, username, password);
        return connection;
    }
}
