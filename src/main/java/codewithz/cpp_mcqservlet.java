package codewithz;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/cpp_mcq")
public class cpp_mcqservlet extends HttpServlet {

    // ✅ Easy Level Questions for C++ (10 questions, 4 options each)
    private final String[][] easy = {
            {"Which of the following is the correct way to start the main function in C++?", "void main()", "int main()", "main()", "start()", "B"},
            {"Which symbol is used to end a statement in C++?", ":", ";", ".", "/", "B"},
            {"Which of these is a C++ comment?", "/* comment */", "// comment", "Both A and B", "<!-- comment -->", "C"},
            {"Which is correct to declare an integer variable in C++?", "int num;", "num int;", "integer num;", "num = int;", "A"},
            {"Which operator is used for addition in C++?", "+", "-", "*", "/", "A"},
            {"Which header file is required to use cout?", "<iostream>", "<stdio.h>", "<conio.h>", "<fstream>", "A"},
            {"Which keyword is used to create a constant variable?", "const", "constant", "let", "final", "A"},
            {"Which is used to take input from the user in C++?", "cin", "cout", "scanf", "print", "A"},
            {"Which of these is a loop in C++?", "for", "while", "do-while", "All of the above", "D"},
            {"Which is the correct syntax to create a class in C++?", "class MyClass { };", "MyClass class { };", "class = MyClass { };", "MyClass { class };", "A"}
    };

    // Medium Level Questions for C++ (10 questions)
    private final String[][] medium = {
            {"Which of the following is used to allocate memory dynamically in C++?", "malloc()", "new", "alloc()", "create()", "B"},
            {"Which operator is used to access members of a class through a pointer?", ".", "->", "*", "&", "B"},
            {"What is the default access specifier for members of a class in C++?", "private", "protected", "public", "none", "A"},
            {"Which of the following is correct syntax for defining a constructor?", "ClassName()", "~ClassName()", "void ClassName()", "ClassName(void)", "A"},
            {"Which keyword is used to inherit a class in C++?", "inherits", "extends", "base", ":", "D"},
            {"Which of these is a valid virtual function declaration?", "virtual void func();", "void virtual func();", "void func(virtual);", "virtual func void();", "A"},
            {"Which operator is used for object assignment?", "=", "==", ":", "+=", "A"},
            {"Which STL container allows fast insertion and deletion at both ends?", "vector", "list", "deque", "set", "C"},
            {"Which function is used to release memory allocated by new?", "delete", "free", "release", "dispose", "A"},
            {"Which of the following supports function overloading in C++?", "C", "C++", "Java", "Python", "B"}
    };


    // Hard Level Questions for C++ (10 questions)
    private final String[][] hard = {
            {"Which of the following is used for compile-time polymorphism in C++?", "Virtual functions", "Function overloading", "Operator overriding", "Dynamic binding", "B"},
            {"Which of these is true about a pure virtual function?", "Can have a body", "Must be defined outside the class", "Declared with =0", "Cannot be inherited", "C"},
            {"Which of the following is not a type of constructor in C++?", "Default constructor", "Parameterized constructor", "Copy constructor", "Destructor constructor", "D"},
            {"Which operator is overloaded by default for all objects?", "Assignment operator", "Arithmetic operator", "Comparison operator", "Address-of operator", "A"},
            {"Which of the following is a smart pointer in C++?", "auto_ptr", "shared_ptr", "unique_ptr", "All of these", "D"},
            {"Which C++ feature is used to prevent multiple inclusions of a header file?", "#include guard", "#pragma once", "Both A and B", "namespace", "C"},
            {"Which of these is used for exception handling in C++?", "try-catch", "throw", "Both try-catch and throw", "finally", "C"},
            {"Which of the following is true about virtual inheritance?", "Prevents multiple copies of base class in diamond problem", "Increases object size", "Only works with private base classes", "Is default inheritance type", "A"},
            {"Which C++ container stores elements in sorted order and does not allow duplicates?", "vector", "list", "set", "map", "C"},
            {"Which of the following is true about multiple inheritance in C++?", "A class can inherit from more than one class", "C++ does not support multiple inheritance", "It is done using interfaces only", "Only one base class can have methods", "A"}
    };


    // doGet and doPost remain unchanged
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");
        PrintWriter out = resp.getWriter();

        String level = req.getParameter("level");
        String timer = req.getParameter("timer"); // timer on/off from HTML

        String[][] questions;
        if ("medium".equalsIgnoreCase(level)) {
            questions = medium;
        } else if ("hard".equalsIgnoreCase(level)) {
            questions = hard;
        } else {
            questions = easy; // default easy
        }

        out.println("<!DOCTYPE html><html><head><title>Quiz - C++</title>");
        out.println("<style>");
        out.println("body { font-family: 'Segoe UI', sans-serif; background: #f4f4f9; text-align: center; }");
        out.println(".quiz-container { width: 45%; margin: 30px auto; background: #fff; padding: 25px; border-radius: 12px; box-shadow: 0 4px 12px rgba(0,0,0,0.3); text-align: left; }");
        out.println("h2 { margin-bottom: 20px; text-align:center; }");
        out.println(".question { margin-bottom: 15px; }");
        out.println(".options { margin-left: 20px; }");
        out.println(".submit-btn { margin-top: 20px; padding: 10px 25px; background: #007bff; color: white; border: none; border-radius: 6px; cursor: pointer; font-size: 15px; display:inline-block; }");
        out.println(".submit-btn:hover { background: #0056b3; }");
        out.println("#timer { position: fixed; top: 20px; right: 20px; background: #333; color: #fff; padding: 8px 12px; border-radius: 6px; font-weight: bold; }");
        out.println("</style>");

        // Timer script
        if ("on".equalsIgnoreCase(timer)) {
            out.println("<script>");
            out.println("let timeLeft = 120;");
            out.println("function startTimer(){");
            out.println("  let timerDiv = document.getElementById('timer');");
            out.println("  let countdown = setInterval(function(){");
            out.println("    let min = Math.floor(timeLeft/60); let sec = timeLeft % 60;");
            out.println("    timerDiv.textContent = 'Time Left: ' + min + ':' + (sec<10?'0':'') + sec;");
            out.println("    timeLeft--;");
            out.println("    if(timeLeft < 0){ clearInterval(countdown); alert('Time is up!'); document.forms[0].submit(); }");
            out.println("  },1000);");
            out.println("}");
            out.println("window.onload = startTimer;");
            out.println("</script>");
        }

        out.println("</head><body>");

        if ("on".equalsIgnoreCase(timer)) {
            out.println("<div id='timer'>Time Left: 2:00</div>");
        }

        out.println("<div class='quiz-container'>");
        out.println("<h2>Concept: C++<br><small>" + level.toUpperCase() + " Level - 10 Questions</small></h2>");
        out.println("<form method='post'>");
        out.println("<input type='hidden' name='level' value='" + level + "'/>");

        for (int i = 0; i < questions.length; i++) {
            out.println("<div class='question'>");
            out.println((i + 1) + ". " + questions[i][0] + "<br>");
            out.println("<div class='options'>");
            out.println("<input type='radio' name='q" + i + "' value='A'> A) " + questions[i][1] + "<br>");
            out.println("<input type='radio' name='q" + i + "' value='B'> B) " + questions[i][2] + "<br>");
            out.println("<input type='radio' name='q" + i + "' value='C'> C) " + questions[i][3] + "<br>");
            out.println("<input type='radio' name='q" + i + "' value='D'> D) " + questions[i][4] + "<br>");
            out.println("</div></div>");
        }

        out.println("<button type='submit' class='submit-btn'>Submit</button>");
        out.println("</form></div></body></html>");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");
        PrintWriter out = resp.getWriter();

        String level = req.getParameter("level");
        String[][] questions;

        if ("medium".equalsIgnoreCase(level)) {
            questions = medium;
        } else if ("hard".equalsIgnoreCase(level)) {
            questions = hard;
        } else {
            questions = easy; // default easy
        }

        int score = 0, correct = 0, wrong = 0;

        for (int i = 0; i < questions.length; i++) {
            String ans = req.getParameter("q" + i);
            if (ans != null && ans.equals(questions[i][5])) {
                score++;
                correct++;
            } else {
                wrong++;
            }
        }

        int totalScore = score * 10;

        out.println("<!DOCTYPE html><html><head><title>Result</title>");
        out.println("<style>");
        out.println("body { font-family: 'Segoe UI', sans-serif; background: #f4f4f9; }");
        out.println(".quiz-container { width: 50%; margin: 100px auto; background: #fff; padding: 35px; border-radius: 12px; box-shadow: 0 4px 12px rgba(0,0,0,0.3); text-align: left; }");
        out.println("h2 { margin-bottom: 20px; text-align:center; }");
        out.println(".result { font-size: 18px; line-height: 1.8; margin-left: 40px; }");
        out.println("</style></head><body>");
        out.println("<div class='quiz-container'>");
        out.println("<h2>Concept: C++<br><small>Results:</small></h2>");
        out.println("<div class='result'>Correct Answers: " + correct + "<br>");
        out.println("Wrong Answers: " + wrong + "<br>");
        out.println("Total Score: " + totalScore + " / 100</div>");
        out.println("</div></body></html>");
    }
}
