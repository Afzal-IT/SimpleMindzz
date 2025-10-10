//package codewithz;
//
//import javax.servlet.ServletException;
//import javax.servlet.annotation.WebServlet;
//import javax.servlet.http.*;
//import java.io.IOException;
//import java.io.PrintWriter;
//
//@WebServlet("/aptitudes")
//public class aptitudesservlet extends HttpServlet {
//
//    @Override
//    protected void doGet(HttpServletRequest request, HttpServletResponse response)
//            throws ServletException, IOException {
//
//        String concept = request.getParameter("concept");
//        String level = request.getParameter("level");
//
//        response.setContentType("text/html");
//        PrintWriter out = response.getWriter();
//
//        out.println("<html><head><title>Quiz - " + concept + "</title>");
//        out.println("<style>");
//        out.println("body { font-family: Arial; background-color: #f0f0f0; padding: 50px; }");
//        out.println("#quiz-box { max-width: 700px; margin: auto; background: white; border-radius: 12px; padding: 30px; box-shadow: 0 4px 12px rgba(0,0,0,0.1); }");
//        out.println("h2, h3 { text-align: center; color: #333; }");
//        out.println("button { padding: 10px 20px; margin: 10px 10px; font-size: 16px; border-radius: 6px; border: none; cursor: pointer; }");
//        out.println(".easy { background-color: #d4edda; color: #155724; }");
//        out.println(".medium { background-color: #fff3cd; color: #856404; }");
//        out.println(".hard { background-color: #f8d7da; color: #721c24; }");
//        out.println("input[type=submit] { background: #007bff; color: white; border: none; border-radius: 5px; padding: 10px 20px; cursor: pointer; }");
//        out.println("input[type=submit]:hover { background: #0056b3; }");
//        out.println("#timer { position: fixed; top: 20px; right: 30px; font-weight: bold; color: red; font-size: 18px; }");
//        out.println("</style>");
//
//        out.println("<script>");
//        out.println("let time = 120;");
//        out.println("function startTimer() {");
//        out.println("let t = setInterval(function() {");
//        out.println("let minutes = Math.floor(time / 60);");
//        out.println("let seconds = time % 60;");
//        out.println("document.getElementById('timer').innerHTML = minutes + 'm ' + seconds + 's';");
//        out.println("time--; if (time < 0) { clearInterval(t); alert('Time Over!'); document.getElementById('quizForm').submit(); } }, 1000); }");
//        out.println("</script>");
//
//        out.println("</head><body onload='" + (level != null ? "startTimer()" : "") + "'>");
//        out.println("<div id='quiz-box'>");
//
//        out.println("<h2>Concept: " + capitalize(concept) + "</h2>");
//
//        if (level == null) {
//            out.println("<h3>Select Difficulty Level:</h3>");
//            out.println("<form method='get' action='aptitudes' style='text-align: center;'>");
//            out.println("<input type='hidden' name='concept' value='" + concept + "'>");
//            out.println("<button type='submit' class='easy' name='level' value='easy'>Easy</button>");
//            out.println("<button type='submit' class='medium' name='level' value='medium'>Medium</button>");
//            out.println("<button type='submit' class='hard' name='level' value='hard'>Hard</button>");
//            out.println("</form>");
//        } else {
//            out.println("<div id='timer'></div>");
//            out.println("<h3>" + capitalize(level) + " Level - 10 Questions</h3>");
//            out.println("<form id='quizForm'>");
//
//            for (int i = 1; i <= 10; i++) {
//                out.println("<p><b>" + i + ". " + getQuestion(concept, level, i) + "</b></p>");
//                out.println("<input type='radio' name='q" + i + "'> A<br>");
//                out.println("<input type='radio' name='q" + i + "'> B<br>");
//                out.println("<input type='radio' name='q" + i + "'> C<br>");
//                out.println("<input type='radio' name='q" + i + "'> D<br><br>");
//            }
//
//            out.println("<input type='submit' value='Submit'>");
//            out.println("</form>");
//        }
//
//        out.println("</div></body></html>");
//    }
//
//    private String capitalize(String str) {
//        if (str == null || str.isEmpty()) return "";
//        return str.substring(0, 1).toUpperCase() + str.substring(1);
//    }
//
//    private String getQuestion(String concept, String level, int number) {
//        if ("numbers".equalsIgnoreCase(concept)) {
//            switch (level.toLowerCase()) {
//                case "easy": return "What is " + number + " + " + number + "?";
//                case "medium": return "Find LCM of " + number * 2 + " and " + number * 3 + ".";
//                case "hard": return "What is the remainder when " + (number * 5 + 1) + " ÷ " + (number + 3) + "?";
//            }
//        }
//        return "Question " + number + " for " + level + " level";
//    }
//}
package codewithz;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/aptitudes")
public class aptitudesservlet extends HttpServlet {

    // Numbers – Easy Level Questions
    private final String[][] easyQuestions_numbers = {
            {"What is the smallest prime number?", "0", "1", "2", "3", "C"},
            {"Which of the following numbers is divisible by 9?", "234", "342", "729", "541", "C"},
            {"What is the sum of the first 10 natural numbers?", "45", "50", "55", "60", "C"},
            {"What is the HCF of 12 and 18?", "2", "3", "6", "12", "C"},
            {"Which of the following is a composite number?", "7", "11", "13", "15", "D"},
            {"A number is divisible by 5 if it ends with?", "1 or 6", "2 or 7", "0 or 5", "3 or 8", "C"},
            {"The least common multiple (LCM) of 4 and 6 is?", "10", "12", "18", "24", "B"},
            {"Which of the following numbers is even?", "37", "51", "82", "95", "C"},
            {"Which number is called a perfect square?", "18", "25", "30", "45", "B"},
            {"Which of the following numbers is divisible by 11?", "209", "231", "315", "420", "B"}
    };

    // Averages – Easy Level Questions
    private final String[][] easyQuestions_averages = {
            {"The average of 5 numbers is 20. If the sum of four of them is 80, what is the fifth number?", "15", "20", "25", "30", "C"},
            {"The average of 10 numbers is 12. If each number is increased by 2, the new average will be:", "12", "14", "15", "20", "B"},
            {"The average marks obtained by 30 students is 40. If the marks of one student were wrongly entered as 50 instead of 40, then the correct average is:", "39.67", "39.33", "40", "41", "B"},
            {"The average age of 12 men is 40 years. If one more man is included, the average becomes 41 years. What is the age of the new man?", "40", "52", "53", "54", "C"},
            {"The average of 7 consecutive odd numbers is 33. The largest of these numbers is:", "35", "37", "39", "41", "C"},
            {"The average monthly income of P and Q is ₹5050. The average monthly income of Q and R is ₹6250, and the average monthly income of P and R is ₹5200. The monthly income of Q is:", "₹4000", "₹4200", "₹6000", "₹6400", "C"},
            {"The average temperature for Mon, Tue and Wed was 40°C. The average for Tue, Wed and Thu was 41°C. If Thu was 42°C, what was Mon?", "39°C", "38°C", "37°C", "36°C", "B"},
            {"The average of 25 numbers is 36. If the first 12 numbers have an average of 32 and the last 12 have an average of 40, then the 13th number is:", "36", "37", "38", "39", "A"},
            {"The average weight of 15 students is 50 kg. When the teacher is included, the average increases by 1 kg. Find the weight of the teacher.", "60 kg", "65 kg", "66 kg", "70 kg", "C"},
            {"The average score of a class of 80 students is 40. If the average score of 60 students is 45, then the average score of the remaining 20 students is:", "20", "15", "25", "30", "C"}
    };

    // ✅ Averages – Medium Level Questions
    private final String[][] mediumQuestions_averages = {
            {"The average of 11 numbers is 50. If the average of the first 6 numbers is 49 and that of the last 6 numbers is 52, find the 6th number.", "56", "55", "54", "53", "B"},
            {"The average age of 8 men is increased by 2 years when one of them, aged 30, is replaced by a new man. Find the age of the new man.", "46", "44", "48", "50", "C"},
            {"The average marks of 9 students is 63. If the marks of the 10th student are added, the average increases by 2. Find the marks of the 10th student.", "85", "83", "82", "81", "A"},
            {"The average salary of 20 workers in a factory is ₹8500. If the salary of the manager is added, the average increases to ₹9000. Find the salary of the manager.", "₹18000", "₹19000", "₹20000", "₹21000", "C"},
            {"The average of 15 numbers is 25. If each number is multiplied by 4, then the new average is:", "50", "75", "100", "125", "C"},
            {"The average of 40 numbers is 38. If two numbers 45 and 55 are removed, the average of the remaining numbers is:", "36.5", "37", "37.5", "38", "C"},
            {"The average runs scored by a batsman in 10 innings is 40. How many runs must he score in the next innings to increase his average by 2?", "60", "62", "64", "66", "C"},
            {"The average weight of 16 boys in a group is 50 kg. If one more boy is added and the average weight becomes 51 kg, then the weight of the new boy is:", "66 kg", "67 kg", "68 kg", "69 kg", "C"},
            {"A man’s average expenses for the first 4 months is ₹275. For the next 5 months, the average expenses is ₹294. If he saves ₹500 in that year, his average monthly income is:", "₹285", "₹285.50", "₹290", "₹295", "C"},
            {"The average marks of 50 students is 72. The average marks of boys is 70 and that of girls is 75. Find the number of boys in the class.", "25", "30", "35", "40", "C"}
    };

    // ✅ Averages – Hard Level Questions
    private final String[][] hardQuestions_averages = {
            {"The average age of 8 men is increased by 2 years when one of them, aged 30, is replaced by a new man. Find the age of the new man.", "44", "46", "48", "50", "C"},
            {"The average of 10 numbers is 70. If the first 5 numbers have an average of 65 and the last 5 have an average of 75, what is the difference between the 5th and 6th number?", "5", "10", "15", "20", "C"},
            {"The average weight of 30 students in a class is 50 kg. If the teacher’s weight is included, the average increases by 1 kg. What is the teacher’s weight?", "75 kg", "80 kg", "81 kg", "82 kg", "C"},
            {"The average marks of 50 students in a class is 72. If the average marks of boys is 70 and that of girls is 75, find the number of boys in the class.", "20", "25", "30", "35", "C"},
            {"The average of 5 consecutive odd numbers is 61. What is the largest of these numbers?", "63", "65", "67", "69", "C"},
            {"The average runs scored by 10 players is 40. If the highest score is excluded, the average reduces by 2. Find the highest score.", "58", "60", "62", "64", "C"},
            {"The average salary of 12 employees is ₹20,000. If the manager’s salary is added, the average increases by ₹1,000. Find the manager’s salary.", "₹32,000", "₹33,000", "₹34,000", "₹35,000", "C"},
            {"The average marks of 25 students is 48. If the marks of one student were wrongly entered as 72 instead of 42, find the correct average.", "46.8", "47", "47.2", "47.5", "B"},
            {"The average age of a family of 5 members is 21 years. If a baby is born, the average is reduced by 3 years. Find the age of the baby.", "1 year", "2 years", "3 years", "4 years", "A"},
            {"The average expenditure of a man for the first 5 months is ₹120, for the next 7 months is ₹130. If he saves ₹290 in a year, find his annual income.", "₹1500", "₹1600", "₹1650", "₹1700", "C"}
    };

    // Percentages – Easy Level Questions
    private final String[][] easyQuestions_percentages = {
            {"What is 25% of 200?", "25", "40", "50", "60", "C"},
            {"A student scored 60 marks out of 120. What is the percentage?", "40%", "45%", "50%", "55%", "C"},
            {"If 20% of a number is 30, what is the number?", "100", "120", "140", "150", "B"},
            {"What percent of 50 is 5?", "5%", "8%", "10%", "15%", "C"},
            {"The price of a book is ₹500. If it is reduced by 10%, what is the new price?", "₹400", "₹450", "₹475", "₹480", "B"},
            {"80 is what percent of 200?", "30%", "35%", "40%", "45%", "C"},
            {"A person got 90 marks out of 150. What is his percentage?", "55%", "58%", "60%", "65%", "C"},
            {"What is 15% of 600?", "60", "75", "85", "90", "D"},
            {"A number increases from 200 to 250. What is the percentage increase?", "20%", "22%", "25%", "30%", "C"},
            {"The salary of a man is ₹5000. If it increases by 20%, what is his new salary?", "₹5500", "₹5800", "₹6000", "₹6200", "C"}
    };

    // Percentages – Medium Level Questions
    private final String[][] mediumQuestions_percentages = {
            {"A student scored 540 marks out of 750. What is his percentage?", "70%", "72%", "74%", "76%", "C"},
            {"The price of a commodity rises from ₹600 to ₹750. What is the percentage increase?", "20%", "22%", "25%", "30%", "C"},
            {"If 40% of a number is 360, then what is the number?", "800", "850", "900", "1000", "C"},
            {"A shopkeeper buys a toy for ₹500 and sells it at a profit of 20%. What is the selling price?", "₹580", "₹600", "₹620", "₹650", "B"},
            {"A student scores 35% in an exam and fails by 40 marks. If the passing marks are 240, what are the total marks?", "600", "620", "640", "680", "A"},
            {"A candidate got 38% of the votes and lost by 720 votes. Find the total number of votes.", "2000", "3000", "3600", "4000", "C"},
            {"A man spends 85% of his income. If he saves ₹900, what is his income?", "₹4500", "₹5000", "₹5200", "₹6000", "B"},
            {"In an examination, 65% of students passed in English, 50% passed in Maths, and 30% failed in both. What percentage passed in both?", "15%", "25%", "35%", "45%", "B"},
            {"A man spends 40% of his salary on rent, 25% on food, 15% on travel, and saves the rest. If his savings are ₹2400, what is his salary?", "₹6000", "₹8000", "₹10000", "₹12000", "C"},
            {"The population of a town increases by 10% in the first year and 20% in the second year. If the present population is 6600, what was it 2 years ago?", "5000", "5100", "5200", "5300", "A"}
    };

    // Percentages – Hard Level Questions
    private final String[][] hardQuestions_percentages = {
            {"A man spends 80% of his income. If his income increases by 20% and his expenditure increases by 10%, what is the percentage increase in his savings?", "40%", "50%", "60%", "70%", "C"},
            {"In an election, 60% of the voters voted and 2% of the votes were declared invalid. A candidate got 18000 valid votes, which were 75% of the total valid votes. Find the total number of voters.", "35000", "40000", "45000", "50000", "B"},
            {"A's salary is 25% more than B's. How much percent is B's salary less than A's?", "15%", "18%", "20%", "25%", "C"},
            {"A number is increased by 20% and then decreased by 20%. The net change is:", "0%", "2% decrease", "4% decrease", "4% increase", "C"},
            {"In a class of 60 students, 40% are boys. 30% of the boys and 20% of the girls failed. Find the percentage of students who passed.", "60%", "62%", "65%", "68%", "B"},
            {"The population of a town increases by 10% in the first year, decreases by 10% in the second year, and again increases by 10% in the third year. If the present population is 100000, what was the population 3 years ago?", "99000", "100000", "101000", "102000", "A"},
            {"A fruit seller had some apples. He sells 40% of them and still has 420 apples left. Find the original number of apples.", "500", "600", "700", "750", "C"},
            {"A student got 60% marks in 5 subjects of 100 marks each and 70% in 3 subjects of 50 marks each. What is the overall percentage?", "62.5%", "64%", "65%", "66%", "A"},
            {"A shopkeeper gives 20% discount on the marked price and still gains 20%. If the cost price is ₹240, what is the marked price?", "₹300", "₹350", "₹360", "₹375", "C"},
            {"A candidate scores 30% marks in a test of 200 marks and fails by 30 marks. What is the pass percentage?", "35%", "40%", "45%", "50%", "B"}
    };

    // Numbers – Medium Level Questions
    private final String[][] mediumQuestions_numbers = {
            {"What is the LCM of 24 and 36?", "48", "72", "84", "96", "B"},
            {"Find the HCF of 210 and 315.", "15", "30", "45", "105", "D"},
            {"If 3 consecutive even numbers have a sum of 84, what is the largest number?", "26", "28", "30", "32", "C"},
            {"What is the remainder when 452 is divided by 9?", "0", "1", "2", "4", "B"},
            {"Which of the following is a prime number?", "91", "97", "143", "119", "B"},
            {"The product of two numbers is 2028 and their HCF is 13. What is their LCM?", "144", "156", "169", "178", "B"},
            {"Find the smallest 4-digit number divisible by 18.", "1008", "1012", "1020", "1035", "A"},
            {"What is the least number which when divided by 12, 15, and 18 leaves the same remainder 5?", "185", "365", "545", "725", "C"},
            {"Find the sum of digits of the least number divisible by 45, 72, and 108.", "18", "27", "36", "45", "C"},
            {"If the sum of two numbers is 216 and their HCF is 18, then what is their LCM?", "648", "720", "864", "1080", "C"}
    };

    // Numbers – Hard Level Questions
    private final String[][] hardQuestions_numbers = {
            {"The greatest 5-digit number exactly divisible by 91 is:", "99918", "99991", "99909", "99981", "A"},
            {"What least number should be added to 1056 to make it perfectly divisible by 23?", "2", "3", "18", "21", "B"},
            {"Find the smallest number which when divided by 35, 56, and 91 leaves the same remainder 7.", "1015", "1057", "1127", "1175", "B"},
            {"A 3-digit number is divisible by 7, 11, and 13. The number is:", "572", "693", "728", "1001", "D"},
            {"The largest 4-digit number exactly divisible by 12, 15, 18, and 27 is:", "9720", "9900", "9960", "9990", "C"},
            {"Find the least number of 5 digits which is exactly divisible by 12, 15, 18, and 27.", "10080", "10125", "10260", "10350", "A"},
            {"A number when divided by 5, 7, and 9 leaves remainder 1 in each case. The number is:", "316", "3161", "631", "6316", "B"},
            {"If the difference between a number and its three-fifth is 50, then the number is:", "75", "100", "125", "150", "D"},
            {"Find the remainder when 7^100 is divided by 13.", "7", "8", "9", "11", "A"},
            {"What is the unit digit of (2357)^1234?", "1", "3", "7", "9", "C"}
    };

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String concept = request.getParameter("concept");
        String level = request.getParameter("level");
        String submit = request.getParameter("submit");
        String timer = request.getParameter("timer");

        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        out.println("<html><head><title>Quiz - " + concept + "</title>");
        out.println("<style>");
        out.println("body { font-family: Arial; background-color: #f0f0f0; padding: 50px; }");
        out.println("#quiz-box { max-width: 700px; margin: auto; background: white; border-radius: 12px; padding: 30px; box-shadow: 0 4px 12px rgba(0,0,0,0.1); }");
        out.println("h2, h3 { text-align: center; color: #333; }");
        out.println("button, input[type=submit] { padding: 10px 20px; margin: 10px; font-size: 16px; border-radius: 6px; border: none; cursor: pointer; }");
        out.println(".easy { background-color: #d4edda; color: #155724; }");
        out.println(".medium { background-color: #fff3cd; color: #856404; }");
        out.println(".hard { background-color: #f8d7da; color: #721c24; }");
        out.println("input[type=submit] { background: #007bff; color: white; }");
        out.println("input[type=submit]:hover { background: #0056b3; }");
        out.println("#timer { position: fixed; top: 20px; right: 30px; font-weight: bold; color: red; font-size: 18px; }");
        out.println("</style>");

        // Timer Script
        out.println("<script>");
        out.println("let time = 120;");
        out.println("function startTimer() {");
        out.println("let t = setInterval(function() {");
        out.println("let minutes = Math.floor(time / 60);");
        out.println("let seconds = time % 60;");
        out.println("document.getElementById('timer').innerHTML = minutes + 'm ' + seconds + 's';");
        out.println("time--; if (time < 0) { clearInterval(t); alert('Time Over!'); document.getElementById('quizForm').submit(); } }, 1000); }");
        out.println("</script>");
        out.println("</head><body " + ((level != null && "on".equals(timer)) ? "onload='startTimer()'" : "") + ">");

        out.println("<div id='quiz-box'>");
        out.println("<h2>Concept: " + capitalize(concept) + "</h2>");

        // Step 1: Choose level
        if (level == null && submit == null) {
            out.println("<h3>Select Difficulty Level:</h3>");
            out.println("<form method='get' action='aptitudes' style='text-align: center;'>");
            out.println("<input type='hidden' name='concept' value='" + concept + "'>");
            out.println("Timer: <select name='timer'><option value='off'>Off</option><option value='on'>On</option></select><br><br>");
            out.println("<button type='submit' class='easy' name='level' value='easy'>Easy</button>");
            out.println("<button type='submit' class='medium' name='level' value='medium'>Medium</button>");
            out.println("<button type='submit' class='hard' name='level' value='hard'>Hard</button>");
            out.println("</form>");
        }

        // Step 2: Show Questions
        else if (submit == null) {
            if ("on".equals(timer)) {
                out.println("<div id='timer'></div>");
            }
            out.println("<h3>" + capitalize(level) + " Level - 10 Questions</h3>");
            out.println("<form id='quizForm' method='get' action='aptitudes'>");
            out.println("<input type='hidden' name='concept' value='" + concept + "'>");
            out.println("<input type='hidden' name='level' value='" + level + "'>");
            out.println("<input type='hidden' name='timer' value='" + timer + "'>");
            out.println("<input type='hidden' name='submit' value='true'>");

            if ("numbers".equalsIgnoreCase(concept)) {
                if ("easy".equalsIgnoreCase(level)) {
                    printQuestions(out, easyQuestions_numbers);
                } else if ("medium".equalsIgnoreCase(level)) {
                    printQuestions(out, mediumQuestions_numbers);
                } else if ("hard".equalsIgnoreCase(level)) {
                    printQuestions(out, hardQuestions_numbers);
                }
            } else if ("percentages".equalsIgnoreCase(concept)) {
                if ("easy".equalsIgnoreCase(level)) {
                    printQuestions(out, easyQuestions_percentages);
                } else if ("medium".equalsIgnoreCase(level)) {
                    printQuestions(out, mediumQuestions_percentages);
                } else if ("hard".equalsIgnoreCase(level)) {
                    printQuestions(out, hardQuestions_percentages);
                }
            } else if ("averages".equalsIgnoreCase(concept)) {
                if ("easy".equalsIgnoreCase(level)) {
                    printQuestions(out, easyQuestions_averages);
                } else if ("medium".equalsIgnoreCase(level)) {
                    printQuestions(out, mediumQuestions_averages);
                } else if ("hard".equalsIgnoreCase(level)) {
                    printQuestions(out, hardQuestions_averages);
                }
            }

            out.println("<input type='submit' value='Submit'>");
            out.println("</form>");
        }

        // Step 3: Evaluate Answers
        else {
            int correct = 0, wrong = 0;
            String[][] questions = null;

            if ("numbers".equalsIgnoreCase(concept)) {
                if ("easy".equalsIgnoreCase(level)) {
                    questions = easyQuestions_numbers;
                } else if ("medium".equalsIgnoreCase(level)) {
                    questions = mediumQuestions_numbers;
                } else if ("hard".equalsIgnoreCase(level)) {
                    questions = hardQuestions_numbers;
                }
            } else if ("percentages".equalsIgnoreCase(concept)) {
                if ("easy".equalsIgnoreCase(level)) {
                    questions = easyQuestions_percentages;
                } else if ("medium".equalsIgnoreCase(level)) {
                    questions = mediumQuestions_percentages;
                } else if ("hard".equalsIgnoreCase(level)) {
                    questions = hardQuestions_percentages;
                }
            } else if ("averages".equalsIgnoreCase(concept)) {
                if ("easy".equalsIgnoreCase(level)) {
                    questions = easyQuestions_averages;
                } else if ("medium".equalsIgnoreCase(level)) {
                    questions = mediumQuestions_averages;
                } else if ("hard".equalsIgnoreCase(level)) {
                    questions = hardQuestions_averages;
                }
            }

            if (questions != null) {
                for (int i = 0; i < questions.length; i++) {
                    String ans = request.getParameter("q" + i);
                    if (ans != null) {
                        if (ans.equals(questions[i][5])) correct++;
                        else wrong++;
                    }
                }
            }

            out.println("<h3>Results:</h3>");
            out.println("<p>Correct Answers: " + correct + "</p>");
            out.println("<p>Wrong Answers: " + wrong + "</p>");
            out.println("<p>Total Score: " + (correct * 10) + " / 100</p>");
        }

        out.println("</div></body></html>");
    }

    private void printQuestions(PrintWriter out, String[][] questions) {
        for (int i = 0; i < questions.length; i++) {
            out.println("<p><b>" + (i + 1) + ". " + questions[i][0] + "</b></p>");
            out.println("<input type='radio' name='q" + i + "' value='A'> A) " + questions[i][1] + "<br>");
            out.println("<input type='radio' name='q" + i + "' value='B'> B) " + questions[i][2] + "<br>");
            out.println("<input type='radio' name='q" + i + "' value='C'> C) " + questions[i][3] + "<br>");
            out.println("<input type='radio' name='q" + i + "' value='D'> D) " + questions[i][4] + "<br><br>");
        }
    }

    private String capitalize(String str) {
        if (str == null || str.length() == 0) return str;
        return str.substring(0, 1).toUpperCase() + str.substring(1).toLowerCase();
    }
}
