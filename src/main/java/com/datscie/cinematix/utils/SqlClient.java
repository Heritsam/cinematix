package com.datscie.cinematix.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.*;

public class SqlClient {
   private static Connection connection;

    public static Connection getConnection() throws SQLException {
        if (connection == null) {
            try {
                Class.forName("com.mysql.jdbc.Driver");
                connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/cinematix", "root", "kecilsemua");
            } catch (SQLException | ClassNotFoundException ex) {
                Logger.getLogger(SqlClient.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return connection;
    }
}
