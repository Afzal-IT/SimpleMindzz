package codewithz;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.PrintWriter;

@WebServlet("/pyservlet")
public class pyservlet extends HttpServlet {

    // ✅ Easy Questions
    private final String[][] easy = {
            {"What is the output of print(2 ** 3)?", "6", "8", "9", "5", "B"},
            {"Which of these is not a core data type?", "List", "Dictionary", "Tuples", "Class", "D"},
            {"Which keyword is used for function?", "define", "def", "function", "fun", "B"},
            {"What is the output of print(type([]))?", "list", "<class 'list'>", "tuple", "<list>", "B"},
            {"Which of these is a loop structure?", "for", "if", "def", "break", "A"},
            {"Which one is used to handle exceptions?", "catch", "error", "except", "try", "C"},
            {"What is the correct file extension for Python?", ".pt", ".py", ".pyt", ".p", "B"},
            {"Which function is used to get input from user?", "read()", "scanf()", "input()", "get()", "C"},
            {"Which symbol is used for comments in Python?", "//", "#", "<!--", "/* */", "B"},
            {"What is the output of len('Python')?", "5", "6", "7", "Error", "B"}
    };

    // ✅ Medium Questions
    private final String[][] medium = {
            {"Which of the following is mutable in Python?", "Tuple", "String", "List", "Frozenset", "C"},
            {"What is the output of bool(\"\") in Python?", "True", "False", "None", "Error", "B"},
            {"Which keyword is used for exception handling in Python?", "try", "catch", "exception", "handle", "A"},
            {"What is the output of 2 ** 3 ** 2?", "64", "512", "256", "16", "B"},
            {"Which module in Python is used for regular expressions?", "regex", "re", "pyregex", "match", "B"},
            {"What is the output of print(\"abc\" * 3)?", "abcabcabc", "abc3", "Error", "9", "A"},
            {"Which of the following is a Python dictionary declaration?", "{ \"name\": \"John\", \"age\": 25 }", "[ \"name\", \"John\", \"age\", 25 ]", "( \"name\", \"John\", \"age\", 25 )", "dict[\"name\"=\"John\",\"age\"=25]", "A"},
            {"Which function is used to get user input in Python 3?", "scan()", "cin()", "input()", "read()", "C"},
            {"What is the output of set([1,2,2,3,3,3])?", "[1,2,2,3,3,3]", "{1,2,3}", "(1,2,3)", "Error", "B"},
            {"Which operator is used for floor division in Python?", "/", "//", "%", "div", "B"}
    };

    // ✅ Hard Questions
    private final String[][] hard = {
            {"What is the output of print(sorted({3,1,2}))?", "[1,2,3]", "(1,2,3)", "{1,2,3}", "[3,2,1]", "A"},
            {"Which of these is not a valid way to create a set?", "set()", "{1,2,3}", "{}", "set([1,2,3])", "C"},
            {"What is the output of print(all([True,1,3]))?", "True", "False", "Error", "None", "A"},
            {"Which function is used to get object ID in Python?", "identity()", "objid()", "id()", "getid()", "C"},
            {"What is the output of print(isinstance(5, int))?", "True", "False", "Error", "None", "A"},
            {"Which of these is used for creating an iterator?", "yield", "return", "break", "continue", "A"},
            {"What is the output of print(list(map(lambda x:x*2, [1,2,3])))?", "[1,2,3]", "[2,4,6]", "(2,4,6)", "Error", "B"},
            {"Which module is used for serialization in Python?", "pickle", "json", "marshal", "all of the above", "D"},
            {"What is the output of print({True: 'yes', 1: 'no', 1.0: 'maybe'})?", "{True:'yes',1:'no',1.0:'maybe'}", "{1.0:'maybe'}", "{True:'maybe'}", "{True:'yes'}", "C"},
            {"Which keyword is used in context managers?", "use", "with", "context", "as", "B"}
    };

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String level = req.getParameter("level");
        if (level == null) level = "easy";
        String timer = req.getParameter("timer");
        showQuiz(resp, level, timer);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String level = req.getParameter("level");
        if (level == null) level = "easy";

        String[][] questions = switch (level) {
            case "medium" -> medium;
            case "hard" -> hard;
            default -> easy;
        };

        resp.setContentType("text/html");
        PrintWriter out = resp.getWriter();
        int correct = 0, wrong = 0;

        // ✅ Result Page (Numbers-style)
        out.println("<!DOCTYPE html><html><head><title>Quiz Result</title>");
        out.println("<style>");
        out.println("body { font-family: 'Segoe UI', sans-serif; background: #f4f4f4; }");
        out.println(".container { width: 50%; margin: 50px auto; background: #fff; padding: 30px; border-radius: 10px; box-shadow: 0 4px 12px rgba(0,0,0,0.2); text-align:center; }");
        out.println("h2 { margin-bottom:15px; }");
        out.println("p { font-size:18px; }");
        out.println("</style></head><body>");
        out.println("<div class='container'>");
        out.println("<h2>Concept: Python</h2>");
        out.println("<h3>Results:</h3>");

        for (int i = 0; i < questions.length; i++) {
            String ans = req.getParameter("q" + i);
            String correctOption = questions[i][5];
            int correctIndex = correctOption.charAt(0) - 'A' + 1;
            String correctAnswer = questions[i][correctIndex];

            if (ans != null && ans.equals(correctAnswer)) {
                correct++;
            } else {
                wrong++;
            }
        }

        out.println("<p>✅ Correct Answers: " + correct + "</p>");
        out.println("<p>❌ Wrong Answers: " + wrong + "</p>");
        out.println("<p>📊 Total Score: " + (correct * 10) + " / 100</p>");

        out.println("</div></body></html>");
    }

    private void showQuiz(HttpServletResponse resp, String level, String timer) throws IOException {
        String[][] questions = switch (level) {
            case "medium" -> medium;
            case "hard" -> hard;
            default -> easy;
        };

        resp.setContentType("text/html");
        PrintWriter out = resp.getWriter();

        // ✅ Quiz Page (Numbers-style)
        out.println("<!DOCTYPE html><html><head><title>Python Quiz</title>");
        out.println("<style>");
        out.println("body { font-family: 'Segoe UI', sans-serif; background:#f4f4f4; margin:0; }");
        out.println(".quiz-container { width: 50%; margin: 40px auto; background: #fff; padding: 30px; border-radius: 10px; box-shadow: 0 4px 12px rgba(0,0,0,0.2); }");
        out.println(".question { margin-bottom: 15px; font-size:16px; }");
        out.println(".options { margin-left: 20px; }");
        out.println(".submit-btn { margin-top: 20px; padding: 10px 25px; background: #007bff; color: white; border: none; border-radius: 6px; cursor: pointer; font-size: 15px; }");
        out.println(".submit-btn:hover { background: #0056b3; }");
        out.println("#timer { font-size:18px; font-weight:bold; color:red; float:right; }");
        out.println("</style>");
        out.println("</head><body>");
        out.println("<div class='quiz-container'>");
        out.println("<h2>Concept: Python <span id='timer'></span></h2>");
        out.println("<h3>" + level.toUpperCase() + " Level - 10 Questions</h3>");
        out.println("<form method='post'>");
        out.println("<input type='hidden' name='level' value='" + level + "'>");

        for (int i = 0; i < questions.length; i++) {
            out.println("<div class='question'>");
            out.println((i + 1) + ". " + questions[i][0] + "<br>");
            out.println("<div class='options'>");
            out.println("<input type='radio' name='q" + i + "' value='" + questions[i][1] + "'> A) " + questions[i][1] + "<br>");
            out.println("<input type='radio' name='q" + i + "' value='" + questions[i][2] + "'> B) " + questions[i][2] + "<br>");
            out.println("<input type='radio' name='q" + i + "' value='" + questions[i][3] + "'> C) " + questions[i][3] + "<br>");
            out.println("<input type='radio' name='q" + i + "' value='" + questions[i][4] + "'> D) " + questions[i][4] + "<br>");
            out.println("</div></div>");
        }

        out.println("<button type='submit' class='submit-btn'>Submit</button>");
        out.println("</form></div>");

        // ✅ Timer JS only if "on"
        if ("on".equalsIgnoreCase(timer)) {
            out.println("<script>");
            out.println("let time = 120;");
            out.println("const timerEl = document.getElementById('timer');");
            out.println("const form = document.forms[0];");
            out.println("const countdown = setInterval(() => {");
            out.println("  let min = Math.floor(time/60);");
            out.println("  let sec = time%60;");
            out.println("  timerEl.innerHTML = `⏳ ${min}:${sec<10?'0'+sec:sec}`;");
            out.println("  if(time<=0){ clearInterval(countdown); form.submit(); }");
            out.println("  time--; }, 1000);");
            out.println("</script>");
        }

        out.println("</body></html>");
    }
}
