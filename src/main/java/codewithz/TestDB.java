package codewithz;

import java.sql.DriverManager;

public class TestDB {
    public static void main(String[] args) {
        DriverManager DBConnection = null;
        Class<?> con = DBConnection.getClass();
        if (con != null) {
            System.out.println("🎉 Connection Working Fine!");
        } else {
            System.out.println("❌ Connection Failed!");
        }
    }
}
