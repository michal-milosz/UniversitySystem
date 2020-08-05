package dbconnection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DbConnection {
    private static final String USER = "root";
    private static final String PASSWORD = "OwocowyDrift";
    private static final String URL = "jdbc:mysql://localhost/universitysystem?serverTimezone=UTC";

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }
}
