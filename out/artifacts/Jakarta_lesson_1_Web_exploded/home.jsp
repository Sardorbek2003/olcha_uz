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
      background-color: #f4f4f4;
      text-align: center;
      flex-direction: column;
    }
    h2 {
      margin-bottom: 20px;
      font-size: 2em;
      color: #333;
    }
    .button-container {
      display: flex;
      flex-direction: column;
      gap: 15px;
    }
    button {
      padding: 12px 25px;
      background-color: #007BFF;
      color: white;
      border: none;
      border-radius: 8px;
      cursor: pointer;
      font-size: 1.2em;
      transition: background-color 0.3s;
    }
    button:hover {
      background-color: #0056b3;
    }
  </style>
</head>
<body>
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
