//package codewithz;
//
//import javax.servlet.http.*;
//import javax.servlet.annotation.WebServlet;
//import java.io.*;
//import java.net.*;
//import org.json.*;
//
//@WebServlet("/ChatServlet")
//public class chatservlet extends HttpServlet {
//
//    private final String GROK_KEY = "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";
//
//    @Override
//    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
//        String userMessage = request.getParameter("question");
//        if (userMessage == null || userMessage.trim().isEmpty()) {
//            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Empty question");
//            return;
//        }
//
//        String aiResponse = getAIResponse(userMessage);
//
//        response.setContentType("application/json");
//        PrintWriter out = response.getWriter();
//        String safeResponse = aiResponse.replace("\\", "\\\\").replace("\"", "\\\"");
//        out.print("{\"answer\":\"" + safeResponse + "\"}");
//        out.flush();
//    }
//
//    private String getAIResponse(String userMessage) {
//        try {
//            // Force TLS 1.2
//            System.setProperty("https.protocols", "TLSv1.2");
//
//            // Use xAI base URL
//            URL url = new URL("https://api.x.ai/v1/chat/completions");
//            HttpURLConnection con = (HttpURLConnection) url.openConnection();
//            con.setRequestMethod("POST");
//            con.setRequestProperty("Authorization", "Bearer " + GROK_KEY);
//            con.setRequestProperty("Content-Type", "application/json");
//            con.setDoOutput(true);
//            con.setConnectTimeout(15000);
//            con.setReadTimeout(15000);
//
//            String jsonInput = "{"
//                    + "\"model\": \"grok-4\","
//                    + "\"messages\": [{\"role\": \"user\", \"content\": \"" + userMessage.replace("\"","\\\"") + "\"}]"
//                    + "}";
//
//            try (OutputStream os = con.getOutputStream()) {
//                os.write(jsonInput.getBytes("utf-8"));
//            }
//
//            int status = con.getResponseCode();
//            InputStream is = (status < 400) ? con.getInputStream() : con.getErrorStream();
//
//            BufferedReader br = new BufferedReader(new InputStreamReader(is, "utf-8"));
//            StringBuilder sb = new StringBuilder();
//            String line;
//            while ((line = br.readLine()) != null) {
//                sb.append(line);
//            }
//
//            System.out.println("Grok API Response: " + sb.toString());  // Debug log
//
//            JSONObject obj = new JSONObject(sb.toString());
//            JSONArray choices = obj.getJSONArray("choices");
//            if (choices.length() > 0) {
//                JSONObject first = choices.getJSONObject(0);
//                if (first.has("message")) {
//                    return first.getJSONObject("message").getString("content");
//                } else if (first.has("text")) {
//                    return first.getString("text");
//                }
//            }
//
//            return "AI response error.";
//
//        } catch (Exception e) {
//            e.printStackTrace();
//            return "Error contacting Grok API.";
//        }
//    }
//}
package codewithz;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.WebServlet;
import java.io.*;
import java.net.*;
import org.json.*;

@WebServlet("/ChatServlet")
public class ChatServlet extends HttpServlet {

    private static final String OPENROUTER_API_KEY = "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";
    private static final String API_URL = "https://openrouter.ai/api/v1/chat/completions";

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("application/json");
        String userMessage = request.getParameter("message");

        try {
            URL url = new URL(API_URL);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("POST");
            conn.setRequestProperty("Authorization", "Bearer " + OPENROUTER_API_KEY);
            conn.setRequestProperty("Content-Type", "application/json");
            conn.setDoOutput(true);

            // JSON request body
            JSONObject jsonBody = new JSONObject();
            jsonBody.put("model", "openai/gpt-4o");
            JSONArray messages = new JSONArray();
            JSONObject userMsg = new JSONObject();
            userMsg.put("role", "user");
            userMsg.put("content", userMessage);
            messages.put(userMsg);
            jsonBody.put("messages", messages);

            // Send request
            OutputStream os = conn.getOutputStream();
            os.write(jsonBody.toString().getBytes("utf-8"));
            os.flush();
            os.close();

            // Read response
            BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream(), "utf-8"));
            StringBuilder sb = new StringBuilder();
            String line;
            while ((line = br.readLine()) != null) {
                sb.append(line);
            }
            br.close();

            // Parse AI response
            JSONObject apiResponse = new JSONObject(sb.toString());
            String chatReply = apiResponse
                    .getJSONArray("choices")
                    .getJSONObject(0)
                    .getJSONObject("message")
                    .getString("content");

            JSONObject jsonResponse = new JSONObject();
            jsonResponse.put("reply", chatReply);
            response.getWriter().write(jsonResponse.toString());

        } catch (Exception e) {
            e.printStackTrace();
            JSONObject error = new JSONObject();
            error.put("reply", "Error contacting AI: " + e.getMessage());
            response.getWriter().write(error.toString());
        }
    }
}
