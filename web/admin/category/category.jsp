<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <title>Category List</title>
  <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
  <script src="${pageContext.request.contextPath}/scripts/data_format.js"></script>
  <style>
    .table td, .table th {
      white-space: nowrap;
      vertical-align: middle;
    }

    .scrollable-description {
      max-width: 150px;
      white-space: nowrap;
      overflow-x: auto;
    }
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
<div class="container mt-5">
  <h2>Category List</h2>
  <table class="table table-bordered">
    <thead>
    <tr>
      <th>ID</th>
      <th>Name</th>
      <th>Parent ID</th>
      <th>Active</th>
      <th>Created by</th>
      <th>Updated by</th>
      <th>Created date</th>
      <th>Updated date</th>
      <th>Actions</th>
    </tr>
    </thead>
    <tbody>
    <c:forEach var="category" items="${categories}">
      <tr>
        <td>${category.id}</td>
        <td>${category.name}</td>
        <td>${category.parentId}</td>
        <td>${category.activ ? 'Yes' : 'No'}</td>
        <td>${category.createdBy}</td>
        <td>${category.updatedBy}</td>
        <td class="date-field">${category.created_date}</td>
        <td class="date-field">${category.updated_date}</td>
        <td>
          <div class="btn-group">
            <button class="btn btn-success updateCategoryBtn" data-id="${category.id}"
                    data-name="${category.name}" data-parent-id="${category.parentId}"
                    data-active="${category.activ}">Update
            </button>
            <button class="btn btn-danger deleteCategoryBtn" data-id="${category.id}">Delete</button>
          </div>
        </td>
      </tr>
    </c:forEach>
    </tbody>
  </table>
  <button id="addCategoryBtn" class="btn btn-primary">Add Category</button>
</div>

<!-- Add Category Modal -->
<div class="modal fade" id="addCategoryModal" tabindex="-1" role="dialog">
  <div class="modal-dialog" role="document">
    <div class="modal-content">
      <form method="post" action="${pageContext.request.contextPath}/admin/category">
        <div class="modal-header">
          <h5 class="modal-title">Add Category</h5>
          <button type="button" class="close" data-dismiss="modal">&times;</button>
        </div>
        <div class="modal-body">
          <input type="hidden" name="action" value="add">
          <div class="form-group">
            <label for="name">Name</label>
            <input type="text" class="form-control" name="name" required>
          </div>
          <div class="form-group">
            <label for="parentId">Parent ID</label>
            <input type="number" class="form-control" name="parentId" required>
          </div>
          <div class="form-group">
            <label for="active">Active</label>
            <select class="form-control" name="active">
              <option value="true">Yes</option>
              <option value="false">No</option>
            </select>
          </div>
        </div>
        <div class="modal-footer">
          <button type="submit" class="btn btn-primary">Save</button>
          <button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>
        </div>
      </form>
    </div>
  </div>
</div>

<!-- Update Category Modal -->
<div class="modal fade" id="updateCategoryModal" tabindex="-1" role="dialog">
  <div class="modal-dialog" role="document">
    <div class="modal-content">
      <form method="post" action="${pageContext.request.contextPath}/admin/category">
        <div class="modal-header">
          <h5 class="modal-title">Update Category</h5>
          <button type="button" class="close" data-dismiss="modal">&times;</button>
        </div>
        <div class="modal-body">
          <input type="hidden" name="action" value="update">
          <input type="hidden" name="id" id="updateId">
          <div class="form-group">
            <label for="name">Name</label>
            <input type="text" class="form-control" name="name" id="updateName" required>
          </div>
          <div class="form-group">
            <label for="parentId">Parent ID</label>
            <input type="number" class="form-control" name="parentId" id="updateParentId" required>
          </div>
          <div class="form-group">
            <label for="active">Active</label>
            <select class="form-control" name="active" id="updateActive">
              <option value="true">Yes</option>
              <option value="false">No</option>
            </select>
          </div>
        </div>
        <div class="modal-footer">
          <button type="submit" class="btn btn-primary">Save changes</button>
          <button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>
        </div>
      </form>
    </div>
  </div>
</div>

<!-- Delete Category Modal -->
<div class="modal fade" id="deleteCategoryModal" tabindex="-1" role="dialog">
  <div class="modal-dialog" role="document">
    <div class="modal-content">
      <form method="post" action="${pageContext.request.contextPath}/admin/category">
        <div class="modal-header">
          <h5 class="modal-title">Delete Category</h5>
          <button type="button" class="close" data-dismiss="modal">&times;</button>
        </div>
        <div class="modal-body">
          <input type="hidden" name="action" value="delete">
          <input type="hidden" name="categoryId" id="deleteCategoryId">
          <p>Are you sure you want to delete this category?</p>
        </div>
        <div class="modal-footer">
          <button type="submit" class="btn btn-danger">Delete</button>
          <button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>
        </div>
      </form>
    </div>
  </div>
</div>

<script src="https://code.jquery.com/jquery-3.5.1.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
<script>
  $(document).ready(function () {
    $('#addCategoryBtn').click(function () {
      $('#addCategoryModal').modal('show');
    });

    $('.updateCategoryBtn').click(function () {
      $('#updateId').val($(this).data('id'));
      $('#updateName').val($(this).data('name'));
      $('#updateParentId').val($(this).data('parent-id'));
      $('#updateActive').val($(this).data('active'));
      $('#updateCategoryModal').modal('show');
    });

    $('.deleteCategoryBtn').click(function () {
      $('#deleteCategoryId').val($(this).data('id'));
      $('#deleteCategoryModal').modal('show');
    });
  });
</script>
</body>
</html>
