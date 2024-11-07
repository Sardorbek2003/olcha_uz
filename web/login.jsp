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
            position: relative;
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
        /* Register button styling */
        .register-link {
            position: absolute;
            top: 10px;
            left: 10px;
            text-decoration: none;
            color: #007BFF;
            font-size: 1.1rem;
            font-weight: bold;
        }
        .register-link:hover {
            color: #0056b3;
        }
    </style>
</head>
<body>
<!-- Register Link at the top-left corner -->
<a href="<%= request.getContextPath() %>/register" class="register-link">Register</a>

<form method="post" action="<%= request.getContextPath() %>/login">
    <input name="username" placeholder="Enter username ..." required />
    <input name="password" placeholder="Enter password ..." type="password" required />
    <button type="submit">Login</button>
</form>
</body>
</html>
