<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
  <title>Admin Page</title>
  <style>
    body {
      display: flex;
      font-family: Arial, sans-serif;
      margin: 0;
      height: 100vh;
    }
    .sidebar {
      width: 200px;
      height: 100%;
      background-color: #333;
      color: white;
      display: flex;
      flex-direction: column;
      align-items: center;
      padding: 20px;
      gap: 15px;
    }
    .sidebar form {
      width: 100%;
      margin: 0;
    }
    .sidebar button {
      width: 100%;
      padding: 10px;
      border: none;
      background-color: #444;
      color: white;
      font-size: 1em;
      cursor: pointer;
    }
    .sidebar button:hover {
      background-color: #555;
    }
    .content {
      flex-grow: 1;
      padding: 20px;
    }
    .content h2 {
      color: #333;
    }
  </style>
</head>
<body>
<div class="sidebar">
  <form action="<%= request.getContextPath() %>/admin" method="post">
    <input type="hidden" name="action" value="category"/>
    <button type="submit">Category</button>
  </form>
  <form action="<%= request.getContextPath() %>/admin" method="post">
    <input type="hidden" name="action" value="product"/>
    <button type="submit">Product</button>
  </form>
  <form action="<%= request.getContextPath() %>/admin" method="post">
    <input type="hidden" name="action" value="cart"/>
    <button type="submit">Cart</button>
  </form>
  <form action="<%= request.getContextPath() %>/admin" method="post">
    <input type="hidden" name="action" value="orders"/>
    <button type="submit">Orders</button>
  </form>
</div>
<div class="content" id="content">
  <h2>Select an option from the sidebar</h2>
</div>
</body>
</html>
