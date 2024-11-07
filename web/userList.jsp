<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>User List</title>
  <!-- Bootstrap 4 CSS -->
  <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
  <style>
    /* Custom Styles */
    .welcome-message { text-align: center; font-size: 1.8rem; margin-top: 1rem; }
    .table-container { margin-top: 20px; }
    .table th, .table td { text-align: center; }
    .btn-danger { width: 100px; }
  </style>
</head>
<body>
<div class="container mt-5">
  <!-- Welcome Message Section -->
  <div class="welcome-message">
    <h1>Welcome to the User Management Panel</h1>
  </div>

  <!-- Table Section -->
  <div class="table-container">
    <h2 class="text-center mb-4">User List</h2>
    <table class="table table-bordered table-striped">
      <thead>
      <tr>
        <th>Name</th>
        <th>Username</th>
        <th>Action</th>
      </tr>
      </thead>
      <tbody>
      <!-- JSTL foreach loop to iterate through the list of users -->
      <c:forEach var="user" items="${users}">
        <tr>
          <td>${user.name}</td>
          <td>${user.username}</td>
          <td>
            <!-- Delete User Form -->
            <form method="post" action="${pageContext.request.contextPath}/delete_user">
              <input type="hidden" name="username" value="${user.username}" />
              <button type="submit" class="btn btn-danger">Delete</button>
            </form>
          </td>
        </tr>
      </c:forEach>
      </tbody>
    </table>
  </div>
</div>

<!-- Bootstrap JS & Dependencies -->
<script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.5.3/dist/umd/popper.min.js"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
</body>
</html>
