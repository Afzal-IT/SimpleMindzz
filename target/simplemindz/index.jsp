<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0"/>
    <title>Simple Minds - Login</title>

    <!-- GSAP CDN -->
    <script src="https://cdnjs.cloudflare.com/ajax/libs/gsap/3.12.2/gsap.min.js"></script>

    <style>
        * { box-sizing: border-box; }
        body {
            font-family: 'Segoe UI', sans-serif;
            margin: 0;
            padding: 0;
            display: flex;
            height: 100vh;
        }
        .container { display: flex; width: 100%; }
        .left {
            flex: 1;
            background: linear-gradient(to bottom right, #004d40, #80cbc4);
            color: white;
            display: flex;
            flex-direction: column;
            justify-content: center;
            align-items: center;
            padding: 40px;
        }
        .left h1 {
            font-size: 2.8rem;
            margin-bottom: 15px;
            border-right: 2px solid white;
            padding-right: 10px;
            white-space: nowrap;
            overflow: hidden;
        }
        .left p { font-size: 1.1rem; text-align: center; max-width: 400px; line-height: 1.6; }
        .right {
            flex: 1;
            background-color: #f2f2f2;
            display: flex;
            justify-content: center;
            align-items: center;
        }
        .form-container {
            background: white;
            padding: 40px;
            border-radius: 10px;
            box-shadow: 0 5px 15px rgba(0,0,0,0.1);
            width: 100%;
            max-width: 400px;
        }
        .form-container h2 { margin-bottom: 20px; color: #00695c; text-align: center; }
        label { display: block; margin-bottom: 5px; font-weight: bold; }
        input[type="text"], input[type="email"], input[type="password"] {
            width: 100%; padding: 10px; margin-bottom: 15px; border: 1px solid #ccc; border-radius: 5px;
        }
        button {
            width: 100%; background-color: #00897b; color: white; padding: 12px;
            border: none; border-radius: 25px; cursor: pointer; font-size: 1rem; transition: 0.3s;
        }
        button:hover { background-color: #4db6ac; }
        @media (max-width: 768px) {
            .container { flex-direction: column; }
            .left, .right { flex: none; width: 100%; }
            .left { padding: 30px 20px; }
            .form-container { padding: 30px 20px; }
        }
        #errorMsg { color: red; text-align: center; margin-top: 10px; }
    </style>
</head>
<body>
<div class="container">
    <!-- Left Side -->
    <div class="left">
        <h1 id="logoText"></h1>
        <p>
            Welcome to Simple Minds Quiz Platform. Learn, practice, and crack your dream job with confidence.
        </p>
    </div>

    <!-- Right Side Form -->
    <div class="right">
        <div class="form-container">
            <h2>User Login</h2>
            <!-- ✅ Changed: removed action, use JS instead -->
       <form action="LoginServlet"" method="post">

            <label>Name</label>
            <input type="text" name="name" required>
            <label>Email</label>
            <input type="email" name="email" required>
            <label>Password</label>
            <input type="password" name="password" required>
            <button type="submit">Login</button>
        </form>


        </div>
    </div>
</div>

<!-- GSAP Reverse Typing Animation -->
<script>
    const fullText = "Simple Minds";
    const logo = document.getElementById("logoText");
    let index = fullText.length;

    function typeReverse() {
        if (index >= 0) {
            logo.innerText = fullText.substring(index);
            index--;
            setTimeout(typeReverse, 150);
        }
    }
    typeReverse();

    // ✅ Login validation
    function loginCheck() {
        const email = document.getElementById("email").value;
        const password = document.getElementById("password").value;

        if (email === "test@gmail.com" && password === "12345") {
            window.location.href = "dash.html"; // open dashboard
            return false; // prevent default form submit
        } else {
            document.getElementById("errorMsg").innerText = "Invalid Login!";
            return false; // stop reload
        }
    }

      window.onload = () => {
        const saved = JSON.parse(localStorage.getItem("settings")) || {};

        // Apply dark mode
        if (saved.darkMode) {
          document.body.classList.add("dark");
        }

        // Apply font size
        if (saved.fontSize) {
          if (saved.fontSize === "small") document.body.style.fontSize = "14px";
          if (saved.fontSize === "medium") document.body.style.fontSize = "16px";
          if (saved.fontSize === "large") document.body.style.fontSize = "18px";
        }

        // Example Language
        if (saved.lang === "ta") {
          document.getElementById("welcome").innerText = "வணக்கம், SimpleMindz!";
        } else if (saved.lang === "hi") {
          document.getElementById("welcome").innerText = "नमस्ते, SimpleMindz!";
        } else {
          document.getElementById("welcome").innerText = "Welcome to SimpleMindz!";
        }
      };



        window.onload = () => {
          const saved = JSON.parse(localStorage.getItem("settings")) || {};

          // Apply dark mode
          if (saved.darkMode) {
            document.body.classList.add("dark");
          }

          // Apply font size
          if (saved.fontSize) {
            if (saved.fontSize === "small") document.body.style.fontSize = "14px";
            if (saved.fontSize === "medium") document.body.style.fontSize = "16px";
            if (saved.fontSize === "large") document.body.style.fontSize = "18px";
          }

          // Example Language
          if (saved.lang === "ta") {
            document.getElementById("welcome").innerText = "வணக்கம், SimpleMindz!";
          } else if (saved.lang === "hi") {
            document.getElementById("welcome").innerText = "नमस्ते, SimpleMindz!";
          } else {
            document.getElementById("welcome").innerText = "Welcome to SimpleMindz!";
          }
        };
</script>
</body>
</html>
