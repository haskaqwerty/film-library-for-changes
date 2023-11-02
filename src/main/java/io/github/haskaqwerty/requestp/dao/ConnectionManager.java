package io.github.haskaqwerty.requestp.dao;

import java.sql.Connection;
import java.sql.SQLException;

public interface ConnectionManager {
    static Connection getConnection() throws SQLException {
        return null;
    }
}
