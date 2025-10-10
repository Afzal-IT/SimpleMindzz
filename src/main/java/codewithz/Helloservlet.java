package codewithz;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/hello")
public class Helloservlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        response.setContentType("text/html");

        PrintWriter out = response.getWriter();
        out.println("<html>");
        out.println("<head>");
        out.println("<title>Servlet Helloservlet</title>");  // Fixed spelling mistake: <title> not <tittle>
        out.println("</head>");
        out.println("<body>");
        out.println("<h1>Servlet Helloservlet Working!</h1>");
        out.println("</body>");
        out.println("</html>");

        out.close();
    }
}
