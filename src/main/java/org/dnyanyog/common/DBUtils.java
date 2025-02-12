package org.dnyanyog.common;

import java.sql.*;

public class DBUtils {

    private static final String URL = "jdbc:mysql://localhost:3306/user_management";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "shruti9160";

    // Method to establish a database connection
    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USERNAME, PASSWORD);
    }

    // Method to execute a SELECT query
    public static ResultSet executeSelectQuery(String query) throws SQLException {
        Connection con = getConnection();
        Statement stmt = con.createStatement();
        return stmt.executeQuery(query);  // Ensure closing in the calling method
    }

    // Method to execute DML queries (INSERT, UPDATE, DELETE) with PreparedStatement
    public static void executeDMLQuery(String query, Object... params) throws SQLException {
        try (Connection con = getConnection(); PreparedStatement stmt = con.prepareStatement(query)) {
            // Set parameters dynamically
            for (int i = 0; i < params.length; i++) {
                stmt.setObject(i + 1, params[i]);
            }
            stmt.executeUpdate();
        }
    }
}
