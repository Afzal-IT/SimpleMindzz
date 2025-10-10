package codewithz;

import java.sql.*;

public class DBConnection {
    private static final String URL = "jdbc:mysql://localhost:3306/simplemindz";
    private static final String USER = "root";      // MySQL username
    private static final String PASS = "Nithu@afzal123";      // MySQL password

    public static Connection getConnection() throws SQLException, ClassNotFoundException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        return DriverManager.getConnection(URL, USER, PASS);
    }
}

