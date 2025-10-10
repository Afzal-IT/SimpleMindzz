//package codewithz;
//
//import java.io.*;
//import javax.servlet.*;
//import javax.servlet.http.*;
//import javax.servlet.annotation.*;
//import java.sql.*;
//
//@WebServlet("/LoginServlet")
//public class LoginServlet extends HttpServlet {
//
//    private static final String DB_URL = "jdbc:mysql://localhost:3306/simplemindz";
//    private static final String DB_USER = "root"; // DB username
//    private static final String DB_PASS = "Nithu@afzal123"; // DB password
//
//    protected void doPost(HttpServletRequest request, HttpServletResponse response)
//            throws ServletException, IOException {
//
//        String name = request.getParameter("name");
//        String email = request.getParameter("email");
//        String password = request.getParameter("password");
//
//        try {
//            Class.forName("com.mysql.cj.jdbc.Driver"); // Load driver
//            Connection con = DriverManager.getConnection(DB_URL, DB_USER, DB_PASS);
//
//            String sql = "INSERT INTO users (name,email,password) VALUES (?,?,?)";
//            PreparedStatement ps = con.prepareStatement(sql);
//            ps.setString(1, name);
//            ps.setString(2, email);
//            ps.setString(3, password);
//
//            ps.executeUpdate(); // Insert into DB
//
//            con.close();
//
//            // Redirect to dashboard directly
//            response.sendRedirect("dash.html");
//
//        } catch(Exception e){
//            e.printStackTrace();
//            response.getWriter().println("Error: " + e.getMessage());
//        }
//    }
//}


package codewithz;

import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.sql.*;

@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {

    private static final String DB_URL = "jdbc:mysql://localhost:3306/simplemindz";
    private static final String DB_USER = "root"; // DB username
    private static final String DB_PASS = "Nithu@afzal123"; // DB password

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String name = request.getParameter("name");
        String email = request.getParameter("email");
        String password = request.getParameter("password");

        try {
            Class.forName("com.mysql.cj.jdbc.Driver"); // Load driver
            Connection con = DriverManager.getConnection(DB_URL, DB_USER, DB_PASS);

            // First, check if email already exists
            String checkSql = "SELECT * FROM users WHERE email=? AND password=?";
            PreparedStatement checkStmt = con.prepareStatement(checkSql);
            checkStmt.setString(1, email);
            checkStmt.setString(2, password);
            ResultSet rs = checkStmt.executeQuery();

            if (rs.next()) {
                // Email and password exist -> login
                con.close();
                response.sendRedirect("dash.html");
            } else {
                // Insert new user
                String sql = "INSERT INTO users (name,email,password) VALUES (?,?,?)";
                PreparedStatement ps = con.prepareStatement(sql);
                ps.setString(1, name);
                ps.setString(2, email);
                ps.setString(3, password);

                ps.executeUpdate(); // Insert into DB
                con.close();

                // Redirect to dashboard after registration
                response.sendRedirect("dash.html");
            }

        } catch(Exception e){
            e.printStackTrace();
            response.getWriter().println("Error: " + e.getMessage());
        }
    }
}
