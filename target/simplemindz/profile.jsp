<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <title>User Profile</title>
    <script src="https://cdn.jsdelivr.net/npm/chart.js"></script>
    <style>
        body {
            margin: 0;
            font-family: 'Segoe UI', sans-serif;
            background: #f7faff;
            color: #333;
        }

        /* Navbar */
        .navbar {
            background: #1e3a8a;
            color: white;
            padding: 15px 30px;
            display: flex;
            justify-content: space-between;
            align-items: center;
        }
        .navbar h2 { margin: 0; font-size: 22px; }

        /* Grid layout */
        .container {
            display: grid;
            grid-template-columns: repeat(3, 1fr); /* 3 cards per row */
            grid-auto-rows: 300px; /* fixed height to avoid scroll */
            gap: 15px;
            padding: 15px;
            height: calc(100vh - 60px); /* full screen minus navbar */
            box-sizing: border-box;
        }

        /* Cards */
        .card {
            background: white;
            border-radius: 12px;
            padding: 20px;
            box-shadow: 0 2px 8px rgba(0,0,0,0.08);
            display: flex;
            flex-direction: column;
            align-items: center;
            justify-content: center;
        }

        /* Avatar */
        .avatar {
            width: 90px;
            height: 90px;
            border-radius: 50%;
            background: url('https://www.w3schools.com/w3images/avatar2.png') center/cover no-repeat;
            margin-bottom: 12px;
        }

        /* User Info */
        .info p {
            margin: 6px 0;
            font-size: 15px;
        }

        /* Charts fill card */
        canvas { width: 100% !important; height: 100% !important; }
    </style>
</head>
<body>

    <!-- Navbar -->
    <div class="navbar">
        <h2>My Profile</h2>
        <a href="dash.html" style="color:white; text-decoration:none; font-size:16px;">⬅ Back to Dashboard</a>
    </div>

    <!-- Grid Container -->
    <div class="container">

        <!-- Profile Card -->
        <div class="card">
            <div class="avatar"></div>
            <div class="info">
                <p><strong>Name:</strong> Afzal</p>
                <p><strong>Email:</strong> afzal@example.com</p>
                <p><strong>Phone:</strong> +91 9876543210</p>
                <p><strong>Gender:</strong> Male</p>
            </div>
        </div>

        <!-- Aptitude Performance -->
        <div class="card">
            <h3>Aptitude</h3>
            <canvas id="aptitudeChart"></canvas>
        </div>

        <!-- Logic Building -->
        <div class="card">
            <h3>Logic Building</h3>
            <canvas id="logicChart"></canvas>
        </div>

        <!-- Java Performance -->
        <div class="card">
            <h3>Java</h3>
            <canvas id="javaChart"></canvas>
        </div>

        <!-- Python Performance -->
        <div class="card">
            <h3>Python</h3>
            <canvas id="pythonChart"></canvas>
        </div>

        <!-- C / C++ / DSA -->
        <div class="card">
            <h3>Other Coding</h3>
            <canvas id="codingChart"></canvas>
        </div>

    </div>

    <!-- ChartJS -->
    <script>
        function createChart(id, type, labels, data, colors) {
            new Chart(document.getElementById(id), {
                type: type,
                data: {
                    labels: labels,
                    datasets: [{
                        data: data,
                        backgroundColor: colors
                    }]
                },
                options: {
                    responsive: true,
                    plugins: { legend: { display: true, position: 'bottom' } }
                }
            });
        }

        createChart("aptitudeChart", "doughnut", ["Easy","Medium","Hard"], [70, 50, 30], ["#2563eb","#38bdf8","#93c5fd"]);
        createChart("logicChart", "bar", ["Puzzles","Reasoning","Series"], [80, 60, 40], ["#2563eb","#38bdf8","#93c5fd"]);
        createChart("javaChart", "radar", ["OOP","Arrays","Strings","Collections"], [65, 75, 55, 40], ["rgba(37,99,235,0.4)"]);
        createChart("pythonChart", "bar", ["Syntax","DS","Libraries"], [70, 50, 60], ["#2563eb","#38bdf8","#93c5fd"]);
        createChart("codingChart", "pie", ["C","C++","DSA"], [50, 40, 70], ["#2563eb","#38bdf8","#93c5fd"]);
    </script>

</body>
</html>
