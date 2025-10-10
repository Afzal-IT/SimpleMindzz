package codewithz;

import codewithz.DBConnection;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.WebServlet;
import java.io.*;
import java.sql.*;

@WebServlet("/HistoryServlet")
public class HistoryServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        HttpSession session = request.getSession(false);
        if(session == null || session.getAttribute("user_id") == null) {
            response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Login required");
            return;
        }

        int userId = (int) session.getAttribute("user_id");
        response.setContentType("application/json");
        PrintWriter out = response.getWriter();

        try (Connection con = DBConnection.getConnection()) {
            PreparedStatement ps = con.prepareStatement(
                    "SELECT user_input, ai_response FROM chat_history WHERE user_id=? ORDER BY id ASC");
            ps.setInt(1, userId);
            ResultSet rs = ps.executeQuery();

            out.print("[");
            boolean first = true;
            while(rs.next()) {
                if(!first) out.print(",");
                out.print("{\"user\":\"" + rs.getString("user_input").replace("\"","\\\"") + "\",");
                out.print("\"ai\":\"" + rs.getString("ai_response").replace("\"","\\\"") + "\"}");
                first = false;
            }
            out.print("]");
        } catch(Exception e) { e.printStackTrace(); out.print("[]"); }
        out.flush();
    }
}
