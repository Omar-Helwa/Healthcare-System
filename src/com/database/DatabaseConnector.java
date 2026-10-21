package com.database;

import com.mysql.cj.jdbc.MysqlDataSource;

import java.sql.Connection;
import java.sql.SQLException;

public class DatabaseConnector {

    private static final String CONN_STRING = getRequiredEnvironmentVariable(
            "HEALTHCARE_DB_URL",
            "jdbc:mysql://localhost:3306/healthcare");
    private static final String USERNAME = getRequiredEnvironmentVariable("HEALTHCARE_DB_USER", null);
    private static final String PASSWORD = getRequiredEnvironmentVariable("HEALTHCARE_DB_PASSWORD", null);
    private static MysqlDataSource dataSource = new MysqlDataSource();

    private static String getRequiredEnvironmentVariable(String name, String defaultValue) {
        String value = System.getenv(name);
        if (value == null || value.isBlank()) {
            if (defaultValue != null) {
                return defaultValue;
            }
            throw new IllegalStateException("Missing required environment variable: " + name);
        }
        return value;
    }

    public static void connect() {
        dataSource.setURL(CONN_STRING);
    }


    public static Connection connection() throws SQLException {
        return dataSource.getConnection(USERNAME, PASSWORD);
    }

}
