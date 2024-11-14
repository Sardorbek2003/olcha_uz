<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>Order List</title>
  <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
  <style>
    .sidebar {
      width: 200px;
      background-color: #333;
      color: white;
      position: fixed;
      top: 0;
      left: 0;
      height: 100%;
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
    .container {
      margin-left: 220px;
      padding-top: 20px;
    }
    table td {
      white-space: nowrap;
      overflow: hidden;
      text-overflow: ellipsis;
      max-width: 250px;
    }
    table td.scrollable {
      max-width: 300px;
      overflow: auto;
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

<div class="container">
  <h2>Order List</h2>
  <table class="table table-bordered">
    <thead>
    <tr>
      <th>ID</th>
      <th>User ID</th>
      <th>User name</th>
      <th>User email</th>
      <th>Status</th>
      <th>Active</th>
      <th>Created Date</th>
      <th>Updated Date</th>
      <th>Actions</th>
    </tr>
    </thead>
    <tbody>
    <c:forEach var="order" items="${orders}">
      <tr>
        <td>${order.order.id}</td>
        <td>${order.user.id}</td>
        <td>${order.user.name}</td>
        <td class="scrollable">${order.user.email}</td>
        <td>${order.order.status}</td>
        <td>${order.order.activ ? 'Yes' : 'No'}</td>
        <td>${order.order.created_date}</td>
        <td>${order.order.updated_date}</td>
        <td>
          <a href="<%= request.getContextPath() %>/admin/orders?action=delete&id=${order.order.id}" class="btn btn-danger">Delete</a>
          <button class="btn btn-success" data-toggle="modal" data-target="#updateModal${order.order.id}">Update</button>
        </td>
      </tr>
    </c:forEach>
    </tbody>
  </table>

  <button class="btn btn-primary" data-toggle="modal" data-target="#addModal">Add Order</button>

  <!-- Add Order Modal -->
  <div class="modal fade" id="addModal" tabindex="-1" role="dialog" aria-labelledby="addModalLabel" aria-hidden="true">
    <div class="modal-dialog" role="document">
      <div class="modal-content">
        <form action="<%= request.getContextPath() %>/admin/orders" method="post">
          <div class="modal-header">
            <h5 class="modal-title" id="addModalLabel">Add New Order</h5>
            <button type="button" class="close" data-dismiss="modal" aria-label="Close">
              <span aria-hidden="true">&times;</span>
            </button>
          </div>
          <div class="modal-body">
            <input type="hidden" name="action" value="add">
            <div class="form-group">
              <label>User ID:</label>
              <input type="number" class="form-control" name="user_id" required>
            </div>
            <div class="form-group">
              <label>Status:</label>
              <input type="text" class="form-control" name="status" required>
            </div>
            <div class="form-group">
              <label>Active:</label>
              <select class="form-control" name="activ" required>
                <option value="true">Yes</option>
                <option value="false">No</option>
              </select>
            </div>
          </div>
          <div class="modal-footer">
            <button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>
            <button type="submit" class="btn btn-primary">Save Order</button>
          </div>
        </form>
      </div>
    </div>
  </div>

  <!-- Update Order Modals -->
  <c:forEach var="order" items="${orders}">
    <div class="modal fade" id="updateModal${order.order.id}" tabindex="-1" role="dialog" aria-labelledby="updateModalLabel" aria-hidden="true">
      <div class="modal-dialog" role="document">
        <div class="modal-content">
          <form action="<%= request.getContextPath() %>/admin/orders" method="post">
            <div class="modal-header">
              <h5 class="modal-title" id="updateModalLabel">Update Order</h5>
              <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                <span aria-hidden="true">&times;</span>
              </button>
            </div>
            <div class="modal-body">
              <input type="hidden" name="action" value="update">
              <input type="hidden" name="id" value="${order.order.id}">
              <div class="form-group">
                <label>User ID:</label>
                <input type="number" class="form-control" name="user_id" value="${order.user.id}" required>
              </div>
              <div class="form-group">
                <label>Status:</label>
                <input type="text" class="form-control" name="status" value="${order.order.status}" required>
              </div>
              <div class="form-group">
                <label>Active:</label>
                <select class="form-control" name="activ" required>
                  <option value="true" ${order.order.activ ? 'selected' : ''}>Yes</option>
                  <option value="false" ${!order.order.activ ? 'selected' : ''}>No</option>
                </select>
              </div>
            </div>
            <div class="modal-footer">
              <button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>
              <button type="submit" class="btn btn-primary">Update Order</button>
            </div>
          </form>
        </div>
      </div>
    </div>
  </c:forEach>
</div>

<!-- JavaScript Libraries for Bootstrap Modals -->
<script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.5.3/dist/umd/popper.min.js"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
</body>
</html>
