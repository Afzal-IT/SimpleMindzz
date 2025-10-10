package codewithz;//package codewithz;
//
//import javax.servlet.*;
//import javax.servlet.http.*;
//import java.io.*;
//
//public class cpracticeservlet extends HttpServlet {
//
//    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
//        response.setContentType("text/html");
//        PrintWriter out = response.getWriter();
//
//        String userCode = request.getParameter("code");
//
//        // Temporary directory
//        File tempDir = new File(System.getProperty("java.io.tmpdir"), "usercode_c");
//        if (!tempDir.exists()) tempDir.mkdirs();
//
//        File sourceFile = new File(tempDir, "UserCode.c");
//
//        try (FileWriter fw = new FileWriter(sourceFile)) {
//            fw.write(userCode);
//        }
//
//        // Compile using gcc
//        StringBuilder compileErrors = new StringBuilder();
//        File exeFile = new File(tempDir, "UserCode.exe");
//        ProcessBuilder compilePb = new ProcessBuilder("gcc", sourceFile.getAbsolutePath(), "-o", exeFile.getAbsolutePath());
//        compilePb.redirectErrorStream(true);
//        Process compileProcess = compilePb.start();
//
//        try (BufferedReader reader = new BufferedReader(new InputStreamReader(compileProcess.getInputStream()))) {
//            String line;
//            while ((line = reader.readLine()) != null) {
//                compileErrors.append(line).append("\n");
//            }
//        }
//        try { compileProcess.waitFor(); } catch (InterruptedException e) { throw new RuntimeException(e); }
//
//        if (compileErrors.length() > 0) {
//            out.print("Compilation Failed:\n" + compileErrors.toString());
//            return;
//        }
//
//        // Run compiled program
//        StringBuilder runOutput = new StringBuilder();
//        ProcessBuilder runPb = new ProcessBuilder(exeFile.getAbsolutePath());
//        runPb.redirectErrorStream(true);
//        Process runProcess = runPb.start();
//
//        try (BufferedReader reader = new BufferedReader(new InputStreamReader(runProcess.getInputStream()))) {
//            String line;
//            while ((line = reader.readLine()) != null) {
//                runOutput.append(line).append("\n");
//            }
//        }
//        try { runProcess.waitFor(); } catch (InterruptedException e) { throw new RuntimeException(e); }
//
//        out.print(runOutput.toString().trim());
//    }
//}
//








import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.WebServlet;
import java.io.*;

@WebServlet("/CPracticeServlet")   // servlet mapping
public class cpracticeservlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        // If user hits servlet directly with GET, handle same as POST
        doPost(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        String userCode = request.getParameter("code");

        // Temporary directory
        File tempDir = new File(System.getProperty("java.io.tmpdir"), "cusercode");
        if (!tempDir.exists()) tempDir.mkdirs();

        File sourceFile = new File(tempDir, "usercode.c");

        try (FileWriter fw = new FileWriter(sourceFile)) {
            fw.write(userCode);
        }

        // -------- Compile C code --------
        StringBuilder compileErrors = new StringBuilder();
        File exeFile = new File(tempDir, "usercode.exe"); // Windows (for Linux use "usercode")
        ProcessBuilder compilePb = new ProcessBuilder(
                "gcc", sourceFile.getAbsolutePath(), "-o", exeFile.getAbsolutePath()
        );
        compilePb.redirectErrorStream(true);
        Process compileProcess = compilePb.start();

        try (BufferedReader reader = new BufferedReader(new InputStreamReader(compileProcess.getInputStream()))) {
            String line;
            while ((line = reader.readLine()) != null) {
                compileErrors.append(line).append("\n");
            }
        }
        try { compileProcess.waitFor(); } catch (InterruptedException e) { throw new RuntimeException(e); }

        if (compileErrors.length() > 0) {
            out.print("Compilation Failed:\n" + compileErrors.toString());
            return;
        }

        // -------- Run program --------
        StringBuilder runOutput = new StringBuilder();
        ProcessBuilder runPb = new ProcessBuilder(exeFile.getAbsolutePath());
        runPb.redirectErrorStream(true);
        Process runProcess = runPb.start();

        try (BufferedReader reader = new BufferedReader(new InputStreamReader(runProcess.getInputStream()))) {
            String line;
            while ((line = reader.readLine()) != null) {
                runOutput.append(line).append("\n");
            }
        }
        try { runProcess.waitFor(); } catch (InterruptedException e) { throw new RuntimeException(e); }

        out.print(runOutput.toString().trim());
    }
}
