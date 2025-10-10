<!DOCTYPE html>
<html>
<head>
  <title>Create Account</title>
  <style>
    body {
      font-family: 'Segoe UI', sans-serif;
      background: linear-gradient(to right, #141e30, #243b55);
      color: white;
      display: flex;
      justify-content: center;
      align-items: center;
      height: 100vh;
      margin: 0;
    }
    .register-box {
      background: #fff;
      color: #333;
      padding: 30px;
      border-radius: 12px;
      box-shadow: 0 8px 20px rgba(0,0,0,0.3);
      text-align: center;
      width: 350px;
    }
    input {
      width: 100%;
      padding: 12px;
      margin: 10px 0;
      border: 1px solid #ccc;
      border-radius: 8px;
    }
    button {
      width: 100%;
      padding: 12px;
      background: #243b55;
      color: #fff;
      border: none;
      border-radius: 8px;
      cursor: pointer;
      font-size: 16px;
    }
    button:hover { background: #141e30; }
  </style>
</head>
<body>
  <div class="register-box">
    <h2>Create New Account</h2>
  <form action="register" method="post">
      Email: <input type="text" name="email"><br>
      Password: <input type="password" name="password"><br>
      <input type="submit" value="Register">
  </form>

    <p>Already have an account? <a href="index.jsp">Login here</a></p>
  </div>
</body>
</html>

