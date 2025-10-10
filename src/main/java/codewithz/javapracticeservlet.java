package codewithz;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.WebServlet;
import java.io.*;

@WebServlet("/javapracticeservlet")
public class javapracticeservlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/plain");
        PrintWriter out = resp.getWriter();

        String code = req.getParameter("code");
        String expectedOutput = req.getParameter("expectedOutput");

        if(code == null || code.trim().isEmpty()){
            out.println("⚠️ No code received!");
            return;
        }

        try {
            // Create temp directory
            String tempDir = getServletContext().getRealPath("/") + "temp/";
            File dir = new File(tempDir);
            if(!dir.exists()) dir.mkdirs();

            // Write UserCode.java
            File file = new File(tempDir + "UserCode.java");
            try(FileWriter fw = new FileWriter(file)) {
                fw.write(code);
            }

            // Compile
            Process compile = Runtime.getRuntime().exec("javac -d " + tempDir + " " + file.getAbsolutePath());
            compile.waitFor();

            if(compile.exitValue() != 0){
                BufferedReader err = new BufferedReader(new InputStreamReader(compile.getErrorStream()));
                String line;
                while((line=err.readLine()) != null) out.println(line);
                return;
            }

            // Run
            Process run = Runtime.getRuntime().exec("java -cp " + tempDir + " UserCode");
            BufferedReader reader = new BufferedReader(new InputStreamReader(run.getInputStream()));
            BufferedReader errReader = new BufferedReader(new InputStreamReader(run.getErrorStream()));

            StringBuilder output = new StringBuilder();
            String line;
            while((line = reader.readLine()) != null) output.append(line).append("\n");

            StringBuilder errors = new StringBuilder();
            while((line = errReader.readLine()) != null) errors.append(line).append("\n");

            run.waitFor();

            if(errors.length() > 0){
                // Show only real runtime errors, ignore JDK_JAVA_OPTIONS message
                String errMsg = errors.toString().replaceAll("NOTE: Picked up JDK_JAVA_OPTIONS.*", "").trim();
                if(!errMsg.isEmpty()) {
                    out.println("❌ Runtime Error:\n" + errMsg);
                    return;
                }
            }

            // Clean output to ignore whitespace/newlines
            String outputClean = output.toString().replaceAll("\\s+","").trim();
            String expectedClean = expectedOutput.replaceAll("\\s+","").trim();

            if(outputClean.equals(expectedClean)) {
                out.println(output.toString().trim() + "\n✅ Correct!");
            } else {
                out.println(output.toString().trim() + "\n❌ Incorrect. Expected output: " + expectedOutput);
            }

        } catch(Exception e){
            out.println("⚠️ Exception: " + e.getMessage());
        }
    }
}


//char[] ch = str.toCharArray();
//for(int i = 0, j = ch.length - 1; i < j; i++, j--) {
//char temp = ch[i];
//ch[i] = ch[j];
//ch[j] = temp;
//}
//str = new String(ch);
