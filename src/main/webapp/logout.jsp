<!DOCTYPE html>
<html>
<head>
    <title>Logout - SimpleMindz</title>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <style>
        body {
            font-family: 'Segoe UI', sans-serif;
            background: linear-gradient(to right, #141e30, #243b55);
            color: white;
            display: flex;
            flex-direction: column;
            justify-content: center;
            align-items: center;
            height: 100vh;
            margin: 0;
            padding: 0 20px; /* Ensures padding on small screens */
            text-align: center;
        }
        h2 {
            margin-bottom: 20px;
            font-size: 28px;
        }
        a {
            display: inline-block;
            margin: 10px;
            padding: 12px 20px;
            background: #fff;
            color: #243b55;
            text-decoration: none;
            border-radius: 8px;
            font-weight: bold;
            font-size: 16px;
            transition: 0.3s;
        }
        a:hover {
            background: #f1f1f1;
        }

        /* Responsive Adjustments */
        @media(max-width:768px){
            h2 { font-size: 24px; }
            a { padding: 10px 18px; font-size: 15px; }
        }
        @media(max-width:480px){
            h2 { font-size: 20px; }
            a { padding: 8px 16px; font-size: 14px; }
        }
    </style>
</head>
<body>
    <h2>You have logged out successfully</h2>
    <a href="index.jsp">Login Again</a>
</body>
</html>
