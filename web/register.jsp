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
      background-image: url('https://static.tildacdn.com/tild6130-6662-4138-a438-383638616131/Vishnya_s_kost.jpg');
      background-size: cover;
      background-position: center;
      background-attachment: fixed;
      background-repeat: no-repeat;
      text-align: center;
    }
    form {
      background-color: rgba(255, 255, 255, 0.8);
      padding: 20px;
      border-radius: 10px;
      box-shadow: 0 4px 6px rgba(0, 0, 0, 0.1);
      width: 300px;
      text-align: center;
    }
    input {
      width: 100%;
      padding: 10px;
      margin: 10px 0;
      border: 1px solid #ccc;
      border-radius: 5px;
    }
    button {
      padding: 10px 20px;
      background-color: #007BFF;
      color: white;
      border: none;
      border-radius: 5px;
      cursor: pointer;
    }
    button:hover {
      background-color: #0056b3;
    }
    select {
      width: 100%;
      padding: 10px;
      margin: 10px 0;
      border: 1px solid #ccc;
      border-radius: 5px;
      font-size: 1em;
    }
  </style>
</head>
<body>
<form method="post" action="<%= request.getContextPath() %>/register">
  <input name="name" placeholder="Enter your full name ..." required />
  <input name="username" placeholder="Enter username ..." required />
  <input name="email" placeholder="Enter email ..." type="email" required />
  <input name="password" placeholder="Enter password ..." type="password" required />

  <!-- Role selection with dropdown -->
  <label for="role">Select Role:</label>
  <select name="role" id="role" required>
    <option value="USER">User</option>
    <option value="ADMIN">Admin</option>
  </select>

  <button type="submit">Register</button>
</form>
</body>
</html>
