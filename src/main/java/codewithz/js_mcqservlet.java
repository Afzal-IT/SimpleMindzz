package codewithz;

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.IOException;
import java.io.PrintWriter;

public class js_mcqservlet extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        showQuizPage(request, response, null, null, null);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String level = request.getParameter("level");
        if (level == null) level = "easy";

        String[][] jsQuestions = getQuestions(level);
        int correct = 0, wrong = 0;

        for (int i = 0; i < jsQuestions.length; i++) {
            String ans = request.getParameter("q" + i);
            if (ans != null) {
                if (ans.equals(jsQuestions[i][5])) correct++;
                else wrong++;
            }
        }

        int totalScore = correct * 10;

        // Show quiz again but pass results
        showQuizPage(request, response, correct, wrong, totalScore);
    }

    private void showQuizPage(HttpServletRequest request, HttpServletResponse response,
                              Integer correct, Integer wrong, Integer totalScore) throws IOException {
        String level = request.getParameter("level");
        if (level == null) level = "easy";

        String timerStatus = request.getParameter("timer");
        if (timerStatus == null) timerStatus = "off"; // default OFF

        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        out.println("<!DOCTYPE html>");
        out.println("<html><head><title>Quiz - JavaScript</title>");
        out.println("<meta name='viewport' content='width=device-width, initial-scale=1.0'>");

        // ==== White Card UI CSS ====
        out.println("<style>");
        out.println("body { font-family: Arial, sans-serif; background:#f1f1f1; margin:0; padding:40px; }");
        out.println(".container { max-width:700px; margin:auto; background:#fff; padding:30px; border-radius:12px; box-shadow:0 4px 20px rgba(0,0,0,0.1); }");
        out.println("h2,h1 { text-align:center; color:#333; margin-bottom:20px; }");
        out.println(".question { margin:18px 0; font-size:16px; font-weight:bold; color:#222; }");
        out.println("label { display:block; margin:8px 0; }");
        out.println("input[type=radio] { margin-right:10px; }");
        out.println("button { display:block; margin:25px auto 0; padding:12px 30px; font-size:16px; border:none; border-radius:6px; background:#007bff; color:white; cursor:pointer; }");
        out.println("button:hover { background:#0056b3; }");
        out.println(".result-box { margin-top:30px; padding:20px; border-radius:10px; background:#fafafa; text-align:center; box-shadow:0 2px 10px rgba(0,0,0,0.1);} ");
        out.println(".result-box p { font-size:18px; margin:8px 0; }");
        out.println("#timer { text-align:right; font-weight:bold; color:#d9534f; margin-bottom:15px; font-size:16px;} ");
        out.println("</style>");

        // ==== Timer Script (only if ON) ====
        if (timerStatus.equals("on")) {
            out.println("<script>");
            out.println("let timeLeft=120;"); // 2 minutes
            out.println("function startTimer(){");
            out.println("  const timer=document.getElementById('timer');");
            out.println("  const interval=setInterval(()=>{");
            out.println("    if(timeLeft<=0){ clearInterval(interval); alert('⏰ Time up! Submitting...'); document.getElementById('quizForm').submit(); }");
            out.println("    else { let m=Math.floor(timeLeft/60); let s=timeLeft%60; timer.innerHTML='⏳ '+m+'m '+(s<10?'0'+s:s)+'s'; timeLeft--; }");
            out.println("  },1000);");
            out.println("}");
            out.println("window.onload=startTimer;");
            out.println("</script>");
        }

        out.println("</head><body>");
        out.println("<div class='container'>");

        if (correct == null) {
            out.println("<h1>Concept: JavaScript</h1>");
            out.println("<h2>" + level.substring(0,1).toUpperCase()+level.substring(1) + " Level - 10 Questions</h2>");
            if (timerStatus.equals("on")) {
                out.println("<div id='timer'></div>");
            }
        } else {
            out.println("<h1>Concept: JavaScript</h1>");
            out.println("<h2>Results:</h2>");
        }

        // ==== Quiz Form ====
        if (correct == null) {
            out.println("<form method='post' id='quizForm'>");
            out.println("<input type='hidden' name='level' value='" + level + "'>");
            out.println("<input type='hidden' name='timer' value='" + timerStatus + "'>"); // keep timer state
            String[][] jsQuestions = getQuestions(level);

            for (int i = 0; i < jsQuestions.length; i++) {
                out.println("<div class='question'>" + (i+1) + ". " + jsQuestions[i][0] + "</div>");
                for (int j = 1; j <= 4; j++) {
                    out.println("<label><input type='radio' name='q" + i + "' value='" + j + "'> " + jsQuestions[i][j] + "</label>");
                }
            }

            out.println("<button type='submit'>Submit</button>");
            out.println("</form>");
        }

        // ==== Results ====
        if (correct != null && wrong != null && totalScore != null) {
            out.println("<div class='result-box'>");
            out.println("<p>✅ Correct Answers: " + correct + "</p>");
            out.println("<p>❌ Wrong Answers: " + wrong + "</p>");
            out.println("<p>🏆 Total Score: " + totalScore + " / 100</p>");
            out.println("</div>");
        }

        out.println("</div></body></html>");
    }

    // ==== JS Questions by Level ====
    private String[][] getQuestions(String level) {
        if (level.equals("medium")) {
            return new String[][] {
                    {"Which of the following is not a JavaScript data type?", "Undefined", "Number", "Float", "Boolean", "3"},
                    {"What will typeof NaN return in JavaScript?", "NaN", "Number", "Undefined", "Object", "2"},
                    {"Which method is used to join two or more arrays in JavaScript?", "push()", "concat()", "join()", "append()", "2"},
                    {"Which keyword is used to declare a constant in JavaScript?", "var", "let", "const", "static", "3"},
                    {"What is the default value of an uninitialized variable in JavaScript?", "0", "null", "undefined", "NaN", "3"},
                    {"Which operator is used to check both value and type in JavaScript?", "==", "=", "===", "!=", "3"},
                    {"Which function converts a JSON string into a JavaScript object?", "JSON.parse()", "JSON.stringify()", "JSON.object()", "parse.JSON()", "1"},
                    {"Which loop will execute at least once, even if the condition is false?", "for", "while", "do...while", "forEach", "3"},
                    {"Which method removes the last element from an array in JavaScript?", "shift()", "pop()", "splice()", "delete()", "2"},
                    {"What does the this keyword refer to in JavaScript inside a method?", "The function itself", "The global object", "The object that owns the method", "The parent object", "3"}
            };
        } else if (level.equals("hard")) {
            return new String[][] {
                    {"Which JavaScript feature allows functions to access variables from an outer scope?", "Hoisting", "Closure", "Currying", "Callback", "2"},
                    {"Which symbol is used for optional chaining in JavaScript?", "??", "?.", "::", "=>", "2"},
                    {"What will be the output of typeof Symbol()?", "string", "symbol", "object", "function", "2"},
                    {"Which method is used to define multiple properties on an object?", "Object.assign()", "Object.defineProperties()", "Object.create()", "Object.keys()", "2"},
                    {"Which of the following is NOT a valid JavaScript Promise state?", "fulfilled", "pending", "resolved", "rejected", "3"},
                    {"Which keyword is used with async functions for handling asynchronous code?", "await", "yield", "defer", "sync", "1"},
                    {"Which JavaScript method prevents an object from being extended?", "Object.freeze()", "Object.lock()", "Object.preventExtensions()", "Object.seal()", "3"},
                    {"What is the output of [] == ![] ?", "true", "false", "undefined", "error", "1"},
                    {"Which operator is used for exponentiation in JavaScript?", "^", "**", "exp()", "Math.pow()", "2"},
                    {"What does the new.target property return inside a function?", "The parent object", "The constructor called with new", "undefined always", "The prototype of the object", "2"}
            };
        } else {
            // Default: Easy
            return new String[][] {
                    {"Which keyword declares a variable in JavaScript?", "var", "let", "const", "All of the above", "4"},
                    {"How to write a comment in JavaScript?", "// comment", "# comment", "<!-- comment -->", "/* comment */", "1"},
                    {"Which symbol is used for strict equality?", "==", "===", "=", "!=", "2"},
                    {"What is the output of typeof null?", "'object'", "'null'", "'undefined'", "'number'", "1"},
                    {"Which function converts a string to integer?", "parseInt()", "toInt()", "Number()", "int()", "1"},
                    {"What does isNaN() return if the value is Not a Number?", "true", "false", "0", "NaN", "1"},
                    {"Which event triggers on button click?", "onchange", "onmouseover", "onclick", "onsubmit", "3"},
                    {"Which object is used for timing in JS?", "setTimeout", "Date", "Time", "Timer", "1"},
                    {"How do you declare an array?", "[]", "{}", "<>", "()", "1"},
                    {"What is the result of '2' + 2 in JS?", "'22'", "4", "NaN", "'4'", "1"}
            };
        }
    }
}
