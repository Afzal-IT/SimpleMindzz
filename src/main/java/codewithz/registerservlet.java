package codewithz;

import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.sql.*;

public class registerservlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        String email = request.getParameter("email");
        String password = request.getParameter("password");

        try {
            DatabaseMetaData DBConnection = null;
            Connection con = DBConnection.getConnection();
            PreparedStatement ps = con.prepareStatement("INSERT INTO users(email,password) VALUES(?,?)");
            ps.setString(1, email);
            ps.setString(2, password);

            int i = ps.executeUpdate();
            if (i > 0) {
                out.println("<h3 style='color:green'>Registration Successful!</h3>");
            } else {
                out.println("<h3 style='color:red'>Registration Failed!</h3>");
            }
        } catch (Exception e) {
            e.printStackTrace(out);
        }
    }
}
