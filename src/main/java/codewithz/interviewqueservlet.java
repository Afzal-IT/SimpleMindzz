package codewithz;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/interviewque")
public class interviewqueservlet extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String companyParam = request.getParameter("company");
        if(companyParam == null) companyParam = "default";

        Company company = getCompanyDetails(companyParam.toLowerCase());

        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        out.println("<!DOCTYPE html>");
        out.println("<html><head><title>"+company.name+" Interview Details</title>");
        out.println("<meta name='viewport' content='width=device-width, initial-scale=1.0'>");
        out.println("<style>");
        // Body
        out.println("body { font-family:'Segoe UI',sans-serif; background: linear-gradient(135deg,#141e30,#243b55); margin:0; color:#fff; padding:20px; }");
        out.println("h1 { text-align:center; font-size:30px; margin-bottom:25px; color:#ffd700; text-shadow:2px 2px 6px rgba(0,0,0,0.6);} ");
        // Container
        out.println(".container { max-width:1200px; margin:auto; display:grid; grid-template-columns: repeat(auto-fit,minmax(320px,1fr)); gap:25px; }");
        // Cards
        out.println(".card { background: rgba(255,255,255,0.08); backdrop-filter: blur(10px); padding:25px 20px; border-radius:16px; box-shadow: 0 6px 20px rgba(0,0,0,0.5); transition: transform .3s, box-shadow .3s; }");
        out.println(".card:hover { transform: translateY(-6px); box-shadow:0 12px 25px rgba(0,0,0,0.7);} ");
        // Headings
        out.println(".card h3 { font-size:20px; margin-bottom:12px; color:#00e6e6; border-bottom:1px solid rgba(255,255,255,0.2); padding-bottom:6px; }");
        out.println(".card p, .card li { font-size:15px; color:#e6e6e6; line-height:1.6; }");
        out.println(".card ul, .card ol { margin-left:20px; }");
        // Links
        out.println("a { color:#ffda79; text-decoration:none; } a:hover { text-decoration:underline; }");
        // Responsive
        out.println("@media(max-width:768px){ .container{ grid-template-columns:1fr; } h1{ font-size:24px; } }");
        out.println("</style>");
        out.println("</head><body>");

        out.println("<h1>💼 "+company.name+" - Interview Preparation Guide</h1>");
        out.println("<div class='container'>");

        // Company Info
        out.println("<div class='card'><h3>Company Details</h3>");
        out.println("<p><strong>Name:</strong> "+company.name+"</p>");
        out.println("<p><strong>Headquarters:</strong> "+company.headquarter+"</p>");
        out.println("<p><strong>Founded:</strong> "+company.founded+"</p>");
        out.println("<p><strong>Contact:</strong> "+company.contact+"</p>");
        out.println("<p><strong>Website:</strong> <a href='"+company.website+"' target='_blank'>"+company.website+"</a></p></div>");

        // About
        out.println("<div class='card'><h3>About the Company</h3><p>"+company.about+"</p></div>");

        // Interview Process
        out.println("<div class='card'><h3>Interview Process</h3><ol>");
        for(String round: company.interviewRounds){ out.println("<li>"+round+"</li>"); }
        out.println("</ol></div>");

        // Tips
        out.println("<div class='card'><h3>Preparation Guidance</h3><ul>");
        for(String tip: company.preparationTips){ out.println("<li>"+tip+"</li>"); }
        out.println("</ul></div>");

        // Previous Questions
        out.println("<div class='card'><h3>Previous Year Questions</h3><ol>");
        for(String q: company.previousQuestions){ out.println("<li>"+q+"</li>"); }
        out.println("</ol></div>");

        // Job Openings
        out.println("<div class='card'><h3>Current Job Openings & Internships</h3><ul>");
        for(String job: company.jobs){ out.println("<li>"+job+"</li>"); }
        out.println("</ul></div>");

        out.println("</div></body></html>");
    }

    private Company getCompanyDetails(String company){
        switch(company){
            case "tcs": return new Company(
                    "Tata Consultancy Services (TCS)","Mumbai, India","1968",
                    "hr@tcs.com | +91 22 6778 9999","https://www.tcs.com",
                    "TCS is one of the largest IT services and consulting companies worldwide.",
                    new String[]{"Aptitude Test","Technical Interview","HR/Managerial Round"},
                    new String[]{"Revise Java, OOP, DBMS","Solve aptitude & reasoning","Practice coding on arrays/strings"},
                    new String[]{"Explain OOP principles","What are joins in SQL?","Write Java palindrome program","Difference between ArrayList & LinkedList"},
                    new String[]{"Openings: Java Developer","Openings: Data Analyst","Internships: Summer Internship Program"}
            );
            case "infosys": return new Company(
                    "Infosys Limited","Bengaluru, India","1981",
                    "hr@infosys.com | +91 80 2852 0261","https://www.infosys.com",
                    "Infosys is a global leader in digital services and consulting.",
                    new String[]{"Online Written Test","Technical Round","HR Round"},
                    new String[]{"Revise DSA & SQL","Brush up Java/C basics","Prepare HR Q&A"},
                    new String[]{"What is inheritance?","Explain MVC","HashMap working","What is recursion?"},
                    new String[]{"Openings: Full Stack Developer","Openings: Cloud Engineer","Internships: Infosys InStep"}
            );
            case "wipro": return new Company(
                    "Wipro Limited","Bengaluru, India","1945",
                    "careers@wipro.com | +91 80 2844 0011","https://www.wipro.com",
                    "Wipro is a leading IT services & consulting company.",
                    new String[]{"Online Test","Technical Interview","HR Interview"},
                    new String[]{"Revise OOP, OS basics","Practice coding","Prepare aptitude"},
                    new String[]{"Explain OOPs","Difference: process vs thread","Write factorial code","SQL normalization"},
                    new String[]{"Openings: Cybersecurity Engineer","Openings: AI Engineer","Internships: Wipro Elite NLTH"}
            );
            case "hcl": return new Company(
                    "HCL Technologies","Noida, India","1976",
                    "careers@hcl.com | +91 120 430 6000","https://www.hcltech.com",
                    "HCL is a global technology company providing IT and consulting services.",
                    new String[]{"Online Test","Technical Round","HR Round"},
                    new String[]{"Revise Java, C++, Networking","Prepare DBMS and OS","Work on problem-solving"},
                    new String[]{"Difference between C and C++","SQL queries","Explain polymorphism","Networking basics"},
                    new String[]{"Openings: Software Engineer","Openings: System Analyst","Internships: HCL Early Careers"}
            );
            case "cognizant": return new Company(
                    "Cognizant Technology Solutions","New Jersey, USA","1994",
                    "careers@cognizant.com | +1 201-801-0233","https://www.cognizant.com",
                    "Cognizant is a multinational IT services and consulting company.",
                    new String[]{"Aptitude Test","Coding Test","Technical Interview","HR Interview"},
                    new String[]{"Revise Java, Python, DBMS","Practice SQL joins","Work on DSA"},
                    new String[]{"Write Fibonacci program","Explain abstraction","What is SDLC?","SQL joins"},
                    new String[]{"Openings: Data Engineer","Openings: QA Engineer","Internships: GenC Next Program"}
            );
            case "capgemini": return new Company(
                    "Capgemini","Paris, France","1967",
                    "careers@capgemini.com | +33 1 47 54 50 00","https://www.capgemini.com",
                    "Capgemini is a global leader in consulting, technology, and outsourcing.",
                    new String[]{"Written Test","Group Discussion","Technical Round","HR Round"},
                    new String[]{"Revise OOP, SQL","Practice group discussion","Work on behavioral questions"},
                    new String[]{"Explain OOPs","SQL primary vs foreign key","Write factorial program","Agile methodology"},
                    new String[]{"Openings: Cloud Developer","Openings: Java Engineer","Internships: Capgemini Exceller"}
            );
            case "tech mahindra": return new Company(
                    "Tech Mahindra","Pune, India","1986",
                    "careers@techmahindra.com | +91 20 6601 8100","https://www.techmahindra.com",
                    "Tech Mahindra is a leading provider of digital transformation and consulting services.",
                    new String[]{"Aptitude Test","Coding Test","Technical Interview","HR Round"},
                    new String[]{"Revise OS, CN basics","Work on Java/Python","Practice aptitude"},
                    new String[]{"Explain OOP concepts","SQL queries","Coding string reversal","Cloud computing basics"},
                    new String[]{"Openings: Frontend Developer","Openings: DevOps Engineer","Internships: Young Engineer Program"}
            );
            case "mindtree": return new Company(
                    "Mindtree (LTIMindtree)","Bengaluru, India","1999",
                    "careers@mindtree.com | +91 80 6706 4000","https://www.ltimindtree.com",
                    "Mindtree is an IT services company specializing in digital transformation.",
                    new String[]{"Online Assessment","Coding Test","Technical Interview","HR Round"},
                    new String[]{"Revise DBMS, OOP, DSA","Solve aptitude questions","Practice coding problems"},
                    new String[]{"SQL joins","Explain encapsulation","Write prime number program","Cloud basics"},
                    new String[]{"Openings: Automation Engineer","Openings: Java Developer","Internships: Mindtree Campus Program"}
            );
            case "zoho": return new Company(
                    "Zoho Corporation","Chennai, India","1996",
                    "careers@zohocorp.com | +91 44 7181 7070","https://www.zoho.com",
                    "Zoho builds software solutions for businesses including CRM, HR, and finance tools.",
                    new String[]{"Aptitude Test","Programming Test","Advanced Coding","HR Round"},
                    new String[]{"Practice problem-solving","Revise DSA and algorithms","Work on C, Java basics"},
                    new String[]{"Write palindrome program","Explain inheritance","What is normalization?","Puzzle questions"},
                    new String[]{"Openings: Product Developer","Openings: UI Engineer","Internships: Zoho Campus Drive"}
            );
            case "accenture": return new Company(
                    "Accenture","Dublin, Ireland","1989",
                    "careers@accenture.com | +353 1 646 2000","https://www.accenture.com",
                    "Accenture is a multinational professional services company specializing in IT and consulting.",
                    new String[]{"Cognitive Assessment","Coding Round","Technical Interview","HR Interview"},
                    new String[]{"Revise Java, SQL, CN","Practice communication skills","Brush up DSA"},
                    new String[]{"Explain polymorphism","SQL normalization","Coding Fibonacci","What is SDLC?"},
                    new String[]{"Openings: AI Engineer","Openings: Cloud Architect","Internships: Accenture Internship Program"}
            );
            case "ibm": return new Company(
                    "IBM (International Business Machines)","Armonk, USA","1911",
                    "careers@ibm.com | +1 914-499-1900","https://www.ibm.com",
                    "IBM is a global leader in hybrid cloud, AI, and enterprise IT solutions.",
                    new String[]{"Online Test","Coding Assessment","Technical Interview","HR Round"},
                    new String[]{"Revise Java, Python, SQL","Practice puzzles","Brush up OS and CN"},
                    new String[]{"Explain OOP principles","SQL query examples","Explain inheritance","Puzzle: 2 eggs problem"},
                    new String[]{"Openings: Research Engineer","Openings: Cloud Specialist","Internships: Extreme Blue"}
            );
            case "oracle": return new Company(
                    "Oracle Corporation","Austin, USA","1977",
                    "careers@oracle.com | +1 737-867-1000","https://www.oracle.com",
                    "Oracle is known worldwide for its database software, cloud services, and enterprise solutions.",
                    new String[]{"Aptitude Test","Technical Interview","Coding Round","HR Interview"},
                    new String[]{"Revise SQL and DBMS","Brush up Java basics","Practice DSA"},
                    new String[]{"What is normalization?","Difference between SQL and PL/SQL","Coding: Reverse string","Explain triggers"},
                    new String[]{"Openings: Database Engineer","Openings: Cloud Engineer","Internships: Oracle Internship Program"}
            );
            case "l&t": return new Company(
                    "L&T Infotech (LTIMindtree)","Mumbai, India","1997",
                    "careers@lntinfotech.com | +91 22 6776 6776","https://www.lntinfotech.com",
                    "L&T Infotech provides IT services, consulting, and digital solutions worldwide.",
                    new String[]{"Aptitude Test","Technical Interview","Coding Round","HR Interview"},
                    new String[]{"Revise Java, DBMS","Work on Aptitude","Practice SQL queries"},
                    new String[]{"Write Fibonacci program","Explain abstraction","SQL joins","OOP vs POP"},
                    new String[]{"Openings: Analyst Programmer","Openings: SAP Consultant","Internships: LTI Campus Program"}
            );
            case "hexaware": return new Company(
                    "Hexaware Technologies","Navi Mumbai, India","1990",
                    "careers@hexaware.com | +91 22 6791 9000","https://www.hexaware.com",
                    "Hexaware is an IT and BPO service provider focusing on automation and cloud services.",
                    new String[]{"Online Test","Coding Assessment","Technical Round","HR Interview"},
                    new String[]{"Revise SQL and OOP","Work on puzzles","Brush up DBMS"},
                    new String[]{"Explain encapsulation","SQL primary vs foreign key","Write factorial program","Puzzle-based Qs"},
                    new String[]{"Openings: Software Engineer","Openings: Data Analyst","Internships: Hexaware Campus Drive"}
            );
            case "virtusa": return new Company(
                    "Virtusa Corporation","Massachusetts, USA","1996",
                    "careers@virtusa.com | +1 508-389-7300","https://www.virtusa.com",
                    "Virtusa provides digital engineering and IT outsourcing services worldwide.",
                    new String[]{"Online Assessment","Coding Test","Technical Round","HR Round"},
                    new String[]{"Revise Java, Spring, SQL","Brush up problem-solving","Prepare HR questions"},
                    new String[]{"Explain polymorphism","SQL query practice","Coding palindrome","Agile basics"},
                    new String[]{"Openings: Java Engineer","Openings: QA Engineer","Internships: Virtusa Campus Drive"}
            );
            default: return new Company(
                    "Unknown Company","-","-","-","#",
                    "Information not available.",
                    new String[]{"-"}, new String[]{"-"}, new String[]{"-"}, new String[]{"-"}
            );
        }
    }

    class Company {
        String name, headquarter, founded, contact, website, about;
        String[] interviewRounds, preparationTips, previousQuestions, jobs;

        public Company(String name,String headquarter,String founded,String contact,String website,
                       String about,String[] interviewRounds,String[] preparationTips,
                       String[] previousQuestions,String[] jobs){
            this.name=name; this.headquarter=headquarter; this.founded=founded;
            this.contact=contact; this.website=website; this.about=about;
            this.interviewRounds=interviewRounds; this.preparationTips=preparationTips;
            this.previousQuestions=previousQuestions; this.jobs=jobs;
        }
    }
}
