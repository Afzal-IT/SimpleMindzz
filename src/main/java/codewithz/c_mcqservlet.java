package codewithz;

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.IOException;
import java.io.PrintWriter;

public class c_mcqservlet extends HttpServlet {

    // ✅ Easy Level Questions
    private final String[][] easyQuestions = {
            {"Which of the following is the correct comment in C?", "/* Comment */", "// Comment //", "# Comment", "<!-- Comment -->", "A"},
            {"Which of the following is a valid C data type?", "integer", "number", "float", "decimal", "C"},
            {"What is the correct format specifier to print an integer in C?", "%d", "%i", "%f", "both a and b", "D"},
            {"Which function is used to output text in C?", "scanf()", "printf()", "gets()", "puts()", "B"},
            {"C language was developed by:", "Bjarne Stroustrup", "James Gosling", "Dennis Ritchie", "Guido van Rossum", "C"},
            {"Which of the following is the correct way to declare a character variable?", "char ch;", "character ch;", "string ch;", "text ch;", "A"},
            {"What will be the size of int in most 32-bit C compilers?", "2 bytes", "4 bytes", "8 bytes", "depends on OS", "B"},
            {"Which operator is used to find remainder in C?", "/", "%", "*", "#", "B"},
            {"What is the default return type of a C function if not specified?", "void", "int", "float", "char", "B"},
            {"Which header file is required to use printf() function?", "stdlib.h", "stdio.h", "math.h", "string.h", "B"}
    };

    // ✅ Medium Level Questions
    private final String[][] mediumQuestions = {
            {"Which of the following storage classes in C is used to retain value between function calls?", "auto", "register", "static", "extern", "C"},
            {"What will be the output of: int x=10; printf(\"%d %d\", x, x++);", "10 11", "11 10", "Undefined behavior", "10 10", "C"},
            {"Which operator has the highest precedence in C?", "++ (increment)", "* (multiplication)", "= (assignment)", "&& (logical AND)", "A"},
            {"Size of pointer variable in 64-bit system (commonly)?", "2 bytes", "4 bytes", "8 bytes", "Depends on compiler", "C"},
            {"Which is the correct way to declare a pointer to a function in C?", "int *f();", "int (*f)();", "int f*();", "(*int f)();", "B"},
            {"Which is a valid way to dynamically allocate memory for 10 integers?", "int *arr = malloc(10 * sizeof(int));", "int arr = malloc(10 * sizeof(int));", "int *arr = alloc(10);", "int arr = malloc(int, 10);", "A"},
            {"What is the output of: char str[]=\"Hello\"; printf(\"%c\", 5[str]);", "H", "e", "o", "\\0", "C"},
            {"Which keyword prevents a variable from being modified?", "const", "static", "volatile", "extern", "A"},
            {"What will sizeof('A') return in C?", "1", "2", "4", "Depends on compiler", "C"},
            {"Which function is used to compare two strings in C?", "strcmp()", "strcomp()", "compare()", "strcompare()", "A"}
    };

    // ✅ Hard Level Questions
    private final String[][] hardQuestions = {
            {"Which of the following is not a valid storage class in C?", "auto", "dynamic", "static", "register", "B"},
            {"What is the output of: int a=5; printf(\"%d %d %d\", a, a<<2, a>>1);", "5 20 2", "5 10 2", "5 2 20", "5 20 10", "A"},
            {"Which function is used to position the file pointer to a specific location in C?", "fseek()", "ftell()", "rewind()", "fopen()", "A"},
            {"What will happen if you declare a function but do not define it in C?", "Compiler error", "Linker error", "Runtime error", "It runs successfully with no error", "B"},
            {"What is the maximum number of dimensions an array in C can have?", "2", "3", "32", "Implementation dependent", "D"},
            {"What is the output of: char str[] = \"ABC\"; printf(\"%d\", sizeof(str));", "2", "3", "4", "Undefined", "C"},
            {"Which of the following cannot be a pointer type in C?", "Pointer to int", "Pointer to void", "Pointer to function", "Pointer to constant literal", "D"},
            {"Which operator is used to allocate memory dynamically in C?", "malloc()", "new", "calloc()", "Both malloc() and calloc()", "D"},
            {"Which of the following statements about recursion in C is true?", "Recursive functions must have a base condition", "Recursion always uses less memory", "Recursion is faster than iteration", "Recursion is supported only in C++", "A"},
            {"What is the output of: int x=10; if(x=0) printf(\"True\"); else printf(\"False\");", "True", "False", "Compile-time error", "Undefined", "B"}
    };

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        showQuiz(req, resp, null, null, null);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String level = req.getParameter("level");

        String[][] questions;
        if ("medium".equalsIgnoreCase(level)) {
            questions = mediumQuestions;
        } else if ("hard".equalsIgnoreCase(level)) {
            questions = hardQuestions;
        } else {
            questions = easyQuestions;
        }

        int correct = 0, wrong = 0;
        for (int i = 0; i < questions.length; i++) {
            String ans = req.getParameter("q" + i);
            if (ans != null && ans.equalsIgnoreCase(questions[i][5])) {
                correct++;
            } else {
                wrong++;
            }
        }
        int totalScore = correct * 10;
        showQuiz(req, resp, correct, wrong, totalScore);
    }

    private void showQuiz(HttpServletRequest req, HttpServletResponse resp,
                          Integer correct, Integer wrong, Integer totalScore) throws IOException {
        resp.setContentType("text/html");
        PrintWriter out = resp.getWriter();

        String level = req.getParameter("level");

        String[][] questions;
        if ("medium".equalsIgnoreCase(level)) {
            questions = mediumQuestions;
        } else if ("hard".equalsIgnoreCase(level)) {
            questions = hardQuestions;
        } else {
            questions = easyQuestions;
        }

        String timerChoice = req.getParameter("timer");

        out.println("<!DOCTYPE html><html lang='en'><head>");
        out.println("<meta charset='UTF-8'><meta name='viewport' content='width=device-width, initial-scale=1.0'>");
        out.println("<title>C Language Quiz</title>");
        out.println("<style>");
        out.println("body {font-family:'Segoe UI',sans-serif; background:#ffffff; margin:0; padding:30px;}");
        out.println(".container {max-width:850px; margin:auto; background:#ffffff; padding:30px; border-radius:15px; box-shadow:0 8px 30px rgba(0,0,0,0.2);}");
        out.println("h1 {text-align:center; color:#222; margin-bottom:20px;}");
        out.println(".question {background:#f9f9f9; margin:18px 0; padding:20px; border-radius:12px; box-shadow:0 4px 10px rgba(0,0,0,0.1);}");
        out.println(".question h2 {margin:0 0 12px 0; font-size:18px; color:#222;}");
        out.println("label {display:block; margin:8px 0; font-size:15px; cursor:pointer;}");
        out.println("input[type=radio]{margin-right:8px; transform:scale(1.2);}");
        out.println("button {display:block; margin:25px auto 0; padding:12px 30px; font-size:16px; border:none; border-radius:6px; background:#007bff; color:white; cursor:pointer; transition:0.3s;}");
        out.println("button:hover{background:#0056b3;}");
        out.println(".result-box {margin-top:25px; text-align:center; background:#f1f1f1; padding:20px; border-radius:12px; box-shadow:0 4px 15px rgba(0,0,0,0.1);}");
        out.println(".result-box p {font-size:18px; margin:10px 0;}");
        out.println("#timer {position:fixed; top:20px; right:25px; font-size:18px; font-weight:bold; color:#e74c3c; background:#ffffff; padding:8px 15px; border-radius:10px; box-shadow:0 3px 8px rgba(0,0,0,0.2);} ");
        out.println("</style>");

        if ("on".equalsIgnoreCase(timerChoice)) {
            out.println("<script>");
            out.println("let timeLeft=120;");
            out.println("function startTimer(){const t=document.getElementById('timer');");
            out.println("const interval=setInterval(()=>{if(timeLeft<=0){clearInterval(interval);alert('⏰ Time up! Submitting...');document.getElementById('quizForm').submit();}");
            out.println("else{let m=Math.floor(timeLeft/60);let s=timeLeft%60;t.innerHTML='⏳ '+m+'m '+(s<10?'0'+s:s)+'s';timeLeft--;}} ,1000);}");
            out.println("window.onload=startTimer;");
            out.println("</script>");
        }

        out.println("</head><body><div class='container'>");

        if (correct == null) {
            out.println("<h1>C Programming Quiz (" +
                    ("medium".equalsIgnoreCase(level) ? "Medium" :
                            "hard".equalsIgnoreCase(level) ? "Hard" : "Easy") +
                    " Level)</h1>");
            if ("on".equalsIgnoreCase(timerChoice)) {
                out.println("<div id='timer'></div>");
            }
            out.println("<form method='post' id='quizForm'>");
            out.println("<input type='hidden' name='level' value='" + level + "'>");
            for (int i = 0; i < questions.length; i++) {
                out.println("<div class='question'>");
                out.println("<h2>Q" + (i + 1) + ". " + questions[i][0] + "</h2>");
                out.println("<label><input type='radio' name='q" + i + "' value='A'> " + questions[i][1] + "</label>");
                out.println("<label><input type='radio' name='q" + i + "' value='B'> " + questions[i][2] + "</label>");
                out.println("<label><input type='radio' name='q" + i + "' value='C'> " + questions[i][3] + "</label>");
                out.println("<label><input type='radio' name='q" + i + "' value='D'> " + questions[i][4] + "</label>");
                out.println("</div>");
            }
            out.println("<button type='submit'>Submit</button>");
            out.println("</form>");
        } else {
            out.println("<h1>Results</h1>");
            out.println("<div class='result-box'>");
            out.println("<p>✅ Correct Answers: " + correct + "</p>");
            out.println("<p>❌ Wrong Answers: " + wrong + "</p>");
            out.println("<p>🏆 Total Score: " + totalScore + " / 100</p>");
            out.println("</div>");
        }

        out.println("</div></body></html>");
    }
}
