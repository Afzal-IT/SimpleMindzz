package codewithz;

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.IOException;
import java.io.PrintWriter;

public class logicservlet extends HttpServlet {

    String[][][] logicQuestions = {
            // 0 - Patterns
            {
                    {"Number Pyramid Challenge:\n1\n2 3\n4 5 6\n7 8 9 10", "Use int num=1 inside nested loops", "Print row number repeatedly", "Use Fibonacci series", "Use while loop only", "1"},
                    {"Hollow Square Puzzle:\n*****\n*   *\n*   *\n*****", "if(i==1 || i==n || j==1 || j==n", "if(i==j)", "Always print *", "Use break", "1"},
                    {"Pascal’s Triangle Game:\n1\n1 1\n1 2 1\n1 3 3 1\n1 4 6 4 1", "n! / (r! * (n-r)!)", "Fibonacci rule", "Multiplication table", "Prime numbers", "1"},
                    {"Checkerboard Puzzle (chess pattern)", "(i+j)%2==0 → * else space", "Print stars in all loops", "Use i%2 only", "Use j%2 only", "1"},
                    {"Diamond Shape Riddle:\n   *\n  ***\n *****\n  ***\n   *", "2 loops → upper + lower pyramid", "Single loop only", "Print square, remove corners", "Not possible", "1"}
            },
            // 1 - Math Logic
            {
                    {"Next in series: 2,4,8,16,?", "32", "24", "30", "40", "1"},
                    {"5x + 3 = 23, x = ?", "4", "5", "6", "3", "1"},
                    {"Train 60 km in 1.5 hr. Speed?", "40 km/h", "45 km/h", "50 km/h", "60 km/h", "1"},
                    {"a + b=10, a-b=2, a=?", "6", "4", "5", "8", "1"},
                    {"Sum of interior angles of triangle?", "180 degrees", "90 degrees", "360 degrees", "270 degrees", "1"}
            },
            // 2 - String Logic
            {
                    {"Reverse 'HELLO'", "OLLEH", "HELLO", "EHLOL", "OLEHL", "1"},
                    {"Length of 'PROGRAM'", "7", "6", "8", "5", "1"},
                    {"Is 'abc' palindrome?", "No", "Yes", "Cannot say", "Sometimes", "1"},
                    {"Convert 'java' to uppercase", "JAVA", "java", "JaVa", "JAVa", "1"},
                    {"First char of 'OpenAI'?", "O", "A", "I", "N", "1"}
            },
            // 3 - Array Logic
            {
                    {"Max of [3,7,2,9,4]?", "9", "7", "4", "3", "1"},
                    {"Sum of [1,2,3,4,5]?", "15", "10", "20", "12", "1"},
                    {"Length of [5,6,7,8]?", "4", "5", "3", "6", "1"},
                    {"First element of [9,8,7]?", "9", "8", "7", "0", "1"},
                    {"Index of 7 in [1,7,3]?", "1", "0", "2", "3", "1"}
            },
            // 4 - Output Tracing
            {
                    {"int x=5; x+=3; print(x)?", "8", "5", "3", "0", "1"},
                    {"for(int i=0;i<3;i++) print(i);?", "0 1 2", "1 2 3", "0 1 2 3", "3 2 1", "1"},
                    {"int a=2,b=3; print(a*b)?", "6", "5", "2", "3", "1"},
                    {"x=10; if(x>5) print(1); else print(0)?", "1", "0", "10", "-1", "1"},
                    {"int y=4; y--; print(y)?", "3", "4", "5", "2", "1"}
            }
    };

    String[] topicNames = {"Pattern Programs", "Math Logic", "String Logic", "Array Logic", "Output Tracing"};

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String topic = request.getParameter("topic");
        int tIndex = 0; // default Patterns
        if(topic != null){
            switch(topic){
                case "patterns": tIndex = 0; break;
                case "math": tIndex = 1; break;
                case "strings": tIndex = 2; break;
                case "arrays": tIndex = 3; break;
                case "tracing": tIndex = 4; break;
            }
        }
        showQuizPage(response, tIndex);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String topic = request.getParameter("topic");
        int tIndex = 0;
        if(topic != null){
            switch(topic){
                case "patterns": tIndex = 0; break;
                case "math": tIndex = 1; break;
                case "strings": tIndex = 2; break;
                case "arrays": tIndex = 3; break;
                case "tracing": tIndex = 4; break;
            }
        }

        int score = 0, wrong = 0;
        for(int i=0; i<logicQuestions[tIndex].length; i++){
            String ans = request.getParameter("q"+i);
            if(ans != null && ans.equals(logicQuestions[tIndex][i][5])) score++;
            else wrong++;
        }
        showResultPage(response, score, wrong, logicQuestions[tIndex].length);
    }

    private void showQuizPage(HttpServletResponse response, int tIndex) throws IOException{
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        out.println("<!DOCTYPE html><html><head><title>"+topicNames[tIndex]+" Quiz</title>");
        out.println("<script src='https://cdnjs.cloudflare.com/ajax/libs/gsap/3.13.0/gsap.min.js'></script>");
        out.println("<style>");
        out.println("body { font-family:'Segoe UI',sans-serif; margin:0; padding:20px; color:#fff; overflow-x:hidden; }");
        out.println(".animated-bg { position:fixed; top:0; left:0; width:100%; height:100%; z-index:-1; }");
        out.println(".container { max-width:800px; margin:auto; background:#fff; color:#000; padding:30px; border-radius:15px; box-shadow:0 6px 15px rgba(0,0,0,0.3); position:relative; z-index:1; }");
        out.println("h1 { text-align:center; margin-bottom:25px; font-size:26px; color:#333; }");
        out.println(".question { margin-bottom:20px; padding:15px; border-radius:10px; background:#f9f9f9; }");
        out.println("label { display:block; padding:10px; margin:6px 0; border:1px solid #ccc; border-radius:8px; cursor:pointer; background:#fff; transition:0.3s; }");
        out.println("label:hover { background:#dfe9f3; }");
        out.println("button { display:block; margin:25px auto; padding:12px 30px; background:#007bff; color:#fff; border:none; border-radius:8px; font-size:16px; cursor:pointer; }");
        out.println("button:hover { background:#0056b3; }");
        out.println("</style></head><body>");

        // Animated background container
        out.println("<canvas class='animated-bg'></canvas>");

        out.println("<div class='container'>");
        out.println("<h1>📝 "+topicNames[tIndex]+" Quiz</h1>");
        out.println("<form method='post'>");
        out.println("<input type='hidden' name='topic' value='"+topicNames[tIndex].toLowerCase().replace(" ","")+"'>");

        for(int i=0; i<logicQuestions[tIndex].length; i++){
            out.println("<div class='question'>");
            out.println("<h3>Q"+(i+1)+". "+logicQuestions[tIndex][i][0].replace("\n","<br>")+"</h3>");
            for(int j=1; j<=4; j++){
                out.println("<label><input type='radio' name='q"+i+"' value='"+j+"'> "+logicQuestions[tIndex][i][j]+"</label>");
            }
            out.println("</div>");
        }

        out.println("<button type='submit'>Submit Quiz</button>");
        out.println("</form></div>");

        // GSAP animated background script
        out.println("<script>");
        out.println("const canvas = document.querySelector('.animated-bg');");
        out.println("const ctx = canvas.getContext('2d');");
        out.println("canvas.width = window.innerWidth;");
        out.println("canvas.height = window.innerHeight;");
        out.println("const colors = ['#667eea','#764ba2','#ff7e5f','#feb47b'];");
        out.println("const particles = [];");
        out.println("for(let i=0;i<50;i++){");
        out.println("  particles.push({ x:Math.random()*canvas.width, y:Math.random()*canvas.height, r:Math.random()*4+1, color: colors[Math.floor(Math.random()*colors.length)], dx:(Math.random()-0.5)*2, dy:(Math.random()-0.5)*2 });");
        out.println("}");
        out.println("function animate(){");
        out.println("  ctx.clearRect(0,0,canvas.width,canvas.height);");
        out.println("  particles.forEach(p=>{");
        out.println("    ctx.beginPath();");
        out.println("    ctx.arc(p.x,p.y,p.r,0,Math.PI*2);");
        out.println("    ctx.fillStyle=p.color;");
        out.println("    ctx.fill();");
        out.println("    p.x += p.dx; p.y += p.dy;");
        out.println("    if(p.x>canvas.width||p.x<0) p.dx*=-1;");
        out.println("    if(p.y>canvas.height||p.y<0) p.dy*=-1;");
        out.println("  });");
        out.println("  requestAnimationFrame(animate);");
        out.println("}");
        out.println("animate();");
        out.println("</script>");

        out.println("</body></html>");
    }

    private void showResultPage(HttpServletResponse response, int score, int wrong, int total) throws IOException{
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        out.println("<!DOCTYPE html><html><head><title>Result</title>");
        out.println("<style>");
        out.println("body { font-family:'Segoe UI', sans-serif; background:#f4f4f4; }");
        out.println(".result-box { max-width:400px; margin:100px auto; background:#fff; padding:30px; border-radius:15px; text-align:center; box-shadow:0 6px 15px rgba(0,0,0,0.2);} ");
        out.println("h2 { margin-bottom:20px; } .score { margin:10px 0; font-size:18px; }");
        out.println("button { margin-top:15px; padding:10px 20px; border:none; border-radius:8px; background:#007bff; color:#fff; cursor:pointer; }");
        out.println("button:hover { background:#0056b3; }");
        out.println("</style></head><body>");
        out.println("<div class='result-box'>");
        out.println("<h2>📊 Your Quiz Result</h2>");
        out.println("<div class='score'>✅ Correct: "+score+"</div>");
        out.println("<div class='score'>❌ Wrong: "+wrong+"</div>");
        out.println("<div class='score'>🏆 Total Score: "+score+" / "+total+"</div>");
        out.println("<form method='get'><button>🔄 Try Again</button></form>");
        out.println("</div></body></html>");
    }
}
