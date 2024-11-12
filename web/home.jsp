<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
  <style>
    body {
      display: flex;
      justify-content: center;
      align-items: center;
      height: 100vh;
      margin: 0;
      font-family: Arial, sans-serif;
      background-image: url('/image');
      background-size: cover;
      background-position: center;
      text-align: center;
      flex-direction: column;
      color: #333;
    }
    .logo {
      font-size: 3em;
      color: #e60000; /* Olcha rang */
      font-weight: bold;
      margin-bottom: 20px;
      text-shadow: 2px 2px 4px rgba(0, 0, 0, 0.3);
    }
    h2 {
      margin-bottom: 20px;
      font-size: 2em;
    }
    .button-container {
      display: flex;
      gap: 20px;
      margin-top: 15px;
    }
    .button-container button {
      padding: 12px 25px;
      background-color: #007BFF;
      color: white;
      border: none;
      border-radius: 8px;
      cursor: pointer;
      font-size: 1.2em;
      transition: background-color 0.3s, transform 0.2s;
    }
    .button-container button:hover {
      background-color: #0056b3;
      transform: scale(1.05);
    }
  </style>
</head>
<body>
<div class="logo">Olcha</div>
<h2>Welcome to Our Website</h2>
<div class="button-container">
  <form method="get" action="<%= request.getContextPath() %>/register">
    <button type="submit">Register</button>
  </form>
  <form method="get" action="<%= request.getContextPath() %>/login">
    <button type="submit">Login</button>
  </form>
</div>
</body>
</html>
