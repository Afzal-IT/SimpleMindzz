package codewithz;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/javamcq")
public class javamcqservlet extends HttpServlet {

    // ✅ Java Easy Questions
    private final String[][] easyQuestions = {
            {"What is the default value of an instance variable of type int in Java?", "0", "null", "undefined", "1", "A"},
            {"Which keyword is used to create a subclass in Java?", "implement", "inherit", "extends", "uses", "C"},
            {"Which method signature is the correct entry point of a Java program?", "public void main(String[] args)", "public static void main(String[] args)", "static void main(String[] args)", "public main(String[] args)", "B"},
            {"Which package contains the ArrayList class?", "java.io", "java.util", "java.lang", "java.net", "B"},
            {"What is the output of System.out.println(5 + \"5\"); ?", "10", "55", "5 5", "Error", "B"},
            {"Which of these is NOT a valid access modifier in Java?", "public", "protected", "package", "private", "C"},
            {"Which keyword is used to stop inheritance of a class?", "sealed", "static", "final", "strictfp", "C"},
            {"Which loop will always execute its body at least once?", "for", "while", "do-while", "foreach", "C"},
            {"What does JVM stand for?", "Java Variable Machine", "Java Virtual Machine", "Java Visual Machine", "Java Verified Machine", "B"},
            {"Which interface must be implemented to create a thread (without extending Thread)?", "Runnable", "Callable", "Serializable", "Comparable", "A"}
    };

    // ✅ Java Medium Questions
    private final String[][] mediumQuestions = {
            {"Which collection class allows dynamic array and provides indexed access but is not synchronized?", "HashMap", "Vector", "ArrayList", "LinkedList", "C"},
            {"Which keyword defines a block that executes regardless of exception?", "catch", "finally", "throw", "finalize", "B"},
            {"What is the default capacity of a HashMap in Java?", "8", "16", "32", "64", "B"},
            {"Which of these does NOT implement the Collection interface?", "List", "Set", "Map", "Queue", "C"},
            {"Which method is used to start a thread in Java?", "run()", "start()", "execute()", "init()", "B"},
            {"Which of these is NOT part of OOPS in Java?", "Encapsulation", "Polymorphism", "Compilation", "Inheritance", "C"},
            {"Which operator performs bitwise AND in Java?", "&&", "&", "|", "^", "B"},
            {"Which can hold heterogeneous objects?", "Array", "ArrayList", "HashMap", "Both B and C", "D"},
            {"If two objects have same hashCode but not equal(), what happens?", "Compile error", "Runtime exception", "Both stored in HashMap", "One replaces other", "C"},
            {"Which class cannot be instantiated in Java?", "Abstract class", "Concrete class", "Inner class", "Subclass", "A"}
    };

    // ✅ Java Hard Questions
    private final String[][] hardQuestions = {
            {"Which of the following best describes the use of the volatile keyword in Java?", "It prevents method overriding", "It ensures visibility of changes to variables across threads", "It makes a variable constant", "It synchronizes method execution", "B"},
            {"In Java memory management, which area is used for storing metadata about classes and methods?", "Stack", "Heap", "Metaspace", "Native Method Area", "C"},
            {"Which of these is true about the finalize() method?", "It is called automatically before an object is garbage collected", "It guarantees that an object will be collected", "It must be manually invoked by the programmer", "It prevents an object from being garbage collected", "A"},
            {"What will happen if two interfaces have a method with the same default implementation and a class implements both?", "Compiler error due to ambiguity", "The class inherits both implementations", "The JVM chooses one randomly", "The class automatically overrides the method", "A"},
            {"Which of the following collections guarantees O(1) average time complexity for insertion and lookup?", "ArrayList", "LinkedList", "HashMap", "TreeMap", "C"},
            {"What is the output of System.out.println(10 + 20 + \"Java\" + 10 + 20); ?", "30Java30", "30Java1020", "Java1020", "1020Java30", "B"},
            {"Which of the following is NOT a valid feature of Java Streams API?", "Parallel processing of collections", "Lazy evaluation", "Modifying source collection directly", "Functional-style operations", "C"},
            {"In Java, what will happen if you declare a constructor as final?", "Compiler error", "Constructor cannot be inherited", "Constructor becomes immutable", "No effect", "A"},
            {"Which of the following is true about Java’s ClassLoader?", "It loads only primitive data types", "It is responsible for dynamically loading classes at runtime", "It is used only in Android apps", "It is not part of JVM architecture", "B"},
            {"Which design pattern is implemented in Java’s Runtime.getRuntime() method?", "Factory Pattern", "Singleton Pattern", "Observer Pattern", "Builder Pattern", "B"}
    };

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String level = request.getParameter("level");
        String submitted = request.getParameter("submitted");
        String timer = request.getParameter("timer"); // "on" or null

        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        out.println("<html><head><title>Java Quiz</title>");
        out.println("<style>");
        out.println("body { font-family: Arial, sans-serif; background: #f0f0f0; margin:0; padding:30px; }");
        out.println("#quiz-box { max-width: 700px; margin: auto; background: white; border-radius: 12px; padding: 30px; box-shadow: 0 4px 12px rgba(0,0,0,0.1); }");
        out.println("h2, h3 { text-align: center; color: #333; }");
        out.println("p { font-size: 16px; }");
        out.println("input[type=radio] { margin: 5px; }");
        out.println("input[type=submit] { background: #007bff; color: white; border: none; border-radius: 5px; padding: 10px 20px; cursor: pointer; }");
        out.println("input[type=submit]:hover { background: #0056b3; }");
        out.println("#timer { position: fixed; top: 15px; left: 50%; transform: translateX(-50%); font-size: 20px; font-weight: bold; background: #333; color: #fff; padding: 5px 15px; border-radius: 6px; }");
        out.println("</style>");
        out.println("</head><body>");

        // Timer visible only if "timer=on"
        if ("on".equals(timer) && !"true".equals(submitted)) {
            out.println("<div id='timer'>02:00</div>");
            out.println("<script>");
            out.println("let time=120;");
            out.println("let timerEl=document.getElementById('timer');");
            out.println("let interval=setInterval(function(){");
            out.println("  let min=Math.floor(time/60); let sec=time%60;");
            out.println("  timerEl.textContent=(min<10?'0':'')+min+':'+(sec<10?'0':'')+sec;");
            out.println("  if(time<=0){ clearInterval(interval); document.getElementById('quizForm').submit(); }");
            out.println("  time--; },1000);");
            out.println("</script>");
        }

        out.println("<div id='quiz-box'>");
        out.println("<h2>Concept: Java</h2>");

        if ("true".equals(submitted)) {
            // ✅ Show results
            String[][] questions;
            if ("medium".equals(level)) questions = mediumQuestions;
            else if ("hard".equals(level)) questions = hardQuestions;
            else questions = easyQuestions;

            int correct = 0, wrong = 0;
            for (int i = 0; i < questions.length; i++) {
                String ans = request.getParameter("q" + (i + 1));
                if (ans != null && ans.equals(questions[i][5])) correct++;
                else wrong++;
            }
            int score = correct * 10;

            out.println("<h3>Results (" + capitalize(level) + " Level):</h3>");
            out.println("<p>✅ Correct Answers: " + correct + "</p>");
            out.println("<p>❌ Wrong Answers: " + wrong + "</p>");
            out.println("<p>🏆 Total Score: " + score + " / 100</p>");

        } else if (level != null) {
            // 📝 Show quiz
            String[][] questions;
            if ("medium".equals(level)) questions = mediumQuestions;
            else if ("hard".equals(level)) questions = hardQuestions;
            else questions = easyQuestions;

            out.println("<h3>" + capitalize(level) + " Level - 10 Questions</h3>");
            out.println("<form method='get' action='javamcq' id='quizForm'>");
            for (int i = 0; i < questions.length; i++) {
                out.println("<p><b>" + (i + 1) + ". " + questions[i][0] + "</b></p>");
                out.println("<label><input type='radio' name='q" + (i + 1) + "' value='A'> A) " + questions[i][1] + "</label><br>");
                out.println("<label><input type='radio' name='q" + (i + 1) + "' value='B'> B) " + questions[i][2] + "</label><br>");
                out.println("<label><input type='radio' name='q" + (i + 1) + "' value='C'> C) " + questions[i][3] + "</label><br>");
                out.println("<label><input type='radio' name='q" + (i + 1) + "' value='D'> D) " + questions[i][4] + "</label><br><br>");
            }
            out.println("<input type='hidden' name='level' value='" + level + "'>");
            out.println("<input type='hidden' name='submitted' value='true'>");
            if ("on".equals(timer)) out.println("<input type='hidden' name='timer' value='on'>");
            out.println("<input type='submit' value='Submit'>");
            out.println("</form>");
        } else {
            // 🔘 Level selection
            out.println("<h3>Select Difficulty Level:</h3>");
            out.println("<form method='get' action='javamcq' style='text-align: center;'>");
            out.println("<button type='submit' name='level' value='easy'>Easy</button>");
            out.println("<button type='submit' name='level' value='medium'>Medium</button>");
            out.println("<button type='submit' name='level' value='hard'>Hard</button>");
            out.println("</form>");
        }

        out.println("</div></body></html>");
    }

    private String capitalize(String str) {
        if (str == null || str.isEmpty()) return "";
        return str.substring(0, 1).toUpperCase() + str.substring(1);
    }
}
