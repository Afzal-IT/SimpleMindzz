<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Simple Minds - Login</title>

<!-- GSAP CDN -->
<script src="https://cdnjs.cloudflare.com/ajax/libs/gsap/3.12.2/gsap.min.js"></script>

<style>
* { box-sizing: border-box; margin:0; padding:0; }
body {
    font-family: 'Segoe UI', sans-serif;
    display: flex;
    min-height: 100vh;
    background: #e0f2f1;
}
.container {
    display: flex;
    flex: 1;
    width: 100%;
}

/* Left Hero Section */
.left {
    flex: 1;
    background: linear-gradient(to bottom right, #004d40, #80cbc4);
    color: white;
    display: flex;
    flex-direction: column;
    justify-content: center;
    align-items: center;
    padding: 40px;
    min-height: 100vh;
}
.left h1 {
    font-size: 3rem;
    margin-bottom: 20px;
    border-right: 2px solid white;
    padding-right: 10px;
    white-space: nowrap;
    overflow: hidden;
    text-align: center;
}
.left p {
    font-size: 1.1rem;
    text-align: center;
    max-width: 400px;
    line-height: 1.6;
}

/* Right Form Section */
.right {
    flex: 1;
    background-color: #f2f2f2;
    display: flex;
    justify-content: center;
    align-items: center;
    padding: 20px;
}
.form-container {
    background: white;
    padding: 40px;
    border-radius: 15px;
    box-shadow: 0 5px 20px rgba(0,0,0,0.1);
    width: 100%;
    max-width: 400px;
}
.form-container h2 {
    margin-bottom: 20px;
    color: #00695c;
    text-align: center;
}
label {
    display: block;
    margin-bottom: 5px;
    font-weight: bold;
}
input[type="text"], input[type="email"], input[type="password"] {
    width: 100%;
    padding: 12px;
    margin-bottom: 15px;
    border: 1px solid #ccc;
    border-radius: 8px;
    font-size: 1rem;
}
button {
    width: 100%;
    background-color: #00897b;
    color: white;
    padding: 12px;
    border: none;
    border-radius: 25px;
    cursor: pointer;
    font-size: 1rem;
    transition: 0.3s;
}
button:hover { background-color: #4db6ac; }

.login-link {
    text-align: center;
    margin-top: 15px;
    font-size: 0.95rem;
    color: #00695c;
}
.login-link a {
    text-decoration: none;
    color: #00897b;
    font-weight: bold;
}
.login-link a:hover { color: #4db6ac; }

/* Modal Styles */
.modal {
    display: none;
    position: fixed;
    z-index: 1000;
    left:0;
    top:0;
    width:100%;
    height:100%;
    background: rgba(0,0,0,0.5);
    justify-content: center;
    align-items: center;
}
.modal-content {
    background:white;
    padding:30px;
    border-radius:12px;
    width:90%;
    max-width:400px;
    position: relative;
    box-shadow:0 5px 15px rgba(0,0,0,0.3);
}
.modal-content h3 {
    text-align:center;
    margin-bottom:20px;
    color:#00695c;
}
.close-btn {
    position:absolute;
    top:10px;
    right:15px;
    font-size:1.5rem;
    font-weight:bold;
    cursor:pointer;
    color:#666;
}
.close-btn:hover { color:#000; }

/* Responsive */
@media (max-width: 768px){
    body { flex-direction: column; }
    .container { flex-direction: column; width:100%; }
    .left, .right { flex:none; width:100%; padding:30px 20px; }
    .left h1 { font-size:2.2rem; border-right:none; padding:0; margin-bottom:15px; }
    .left p { font-size:1rem; max-width:100%; }
    .form-container { padding:30px 20px; width:100%; border-radius:12px; }
}
@media(max-width:480px){
    .left h1{ font-size:1.8rem; }
    .left p{ font-size:0.95rem; }
    input[type="text"], input[type="email"], input[type="password"]{ padding:10px; }
    button{ padding:10px; font-size:0.95rem; }
}
</style>
</head>
<body>
<div class="container">
    <!-- Left Hero Section -->
    <div class="left">
        <h1 id="logoText"></h1>
        <p>
            Welcome to Simple Minds Quiz Platform. Learn, practice, and crack your dream job with confidence.
        </p>
    </div>

    <!-- Right Login Form -->
    <div class="right">
        <div class="form-container">
            <h2>User Login</h2>
            <form action="LoginServlet" method="post">
                <label>Name</label>
                <input type="text" name="name" required>
                <label>Email</label>
                <input type="email" name="email" required>
                <label>Password</label>
                <input type="password" name="password" required>
                <button type="submit">Login</button>
            </form>

            <div class="login-link">
                Already have an account? <a id="openModal">Login here</a>
            </div>
            <div id="errorMsg"></div>
        </div>
    </div>
</div>

<!-- Modal -->
<div class="modal" id="loginModal">
    <div class="modal-content">
        <span class="close-btn" id="closeModal">&times;</span>
        <h3>Login</h3>
        <form id="modalLoginForm" action="LoginServlet" method="post">
            <label>Email</label>
            <input type="email" name="email" required>
            <label>Password</label>
            <input type="password" name="password" required>
            <button type="submit">Login</button>
        </form>
        <div id="modalError" style="color:red; text-align:center; margin-top:10px;"></div>
    </div>
</div>

<script>
// GSAP Reverse Typing
const fullText = "Simple Minds";
const logo = document.getElementById("logoText");
let index = fullText.length;
function typeReverse() {
    if(index>=0){
        logo.innerText = fullText.substring(index);
        index--;
        setTimeout(typeReverse,150);
    }
}
typeReverse();

// Modal functionality
const modal = document.getElementById("loginModal");
const openModal = document.getElementById("openModal");
const closeModal = document.getElementById("closeModal");

openModal.onclick = () => modal.style.display = "flex";
closeModal.onclick = () => modal.style.display = "none";
window.onclick = (e) => { if(e.target==modal) modal.style.display="none"; }
</script>
</body>
</html>
