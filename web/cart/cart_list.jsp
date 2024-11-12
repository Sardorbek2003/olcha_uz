<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>Cart List</title>
  <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
  <script src="${pageContext.request.contextPath}/scripts/data_format.js"></script>
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
  <h2>Cart List</h2>
  <table class="table table-bordered">
    <thead>
    <tr>
      <th>ID</th>
      <th>User ID</th>
      <th>Product ID</th>
      <th>Quantity</th>
      <th>Active</th>
      <th>Created Date</th>
      <th>Updated Date</th>
      <th>Actions</th>
    </tr>
    </thead>
    <tbody>
    <c:forEach var="cart" items="${carts}">
      <tr>
        <td>${cart.id}</td>
        <td>${cart.user_id}</td>
        <td>${cart.product_id}</td>
        <td>${cart.quantity}</td>
        <td>${cart.activ ? 'Yes' : 'No'}</td>
        <td class = "date-field">${cart.created_date}</td>
        <td class = "date-field">${cart.updated_date}</td>
        <td>
          <a href="#" class="btn btn-danger" data-toggle="modal" data-target="#deleteModal" data-id="${cart.id}">Delete</a>
          <a href="#" class="btn btn-success" data-toggle="modal" data-target="#updateModal"
             data-id="${cart.id}" data-userid="${cart.user_id}" data-productid="${cart.product_id}" data-quantity="${cart.quantity}"
             data-activ="${cart.activ}" data-createddate="${cart.created_date}" data-updateddate="${cart.updated_date}">Update</a>
        </td>
      </tr>
    </c:forEach>
    </tbody>
  </table>
  <button class="btn btn-primary" data-toggle="modal" data-target="#addModal">Add Cart</button>
</div>

<!-- Add Modal -->
<div class="modal fade" id="addModal" tabindex="-1" role="dialog">
  <div class="modal-dialog">
    <div class="modal-content">
      <form action="<%= request.getContextPath() %>/cart" method="post">
        <div class="modal-header">
          <h5 class="modal-title">Add Cart</h5>
          <button type="button" class="close" data-dismiss="modal">&times;</button>
        </div>
        <div class="modal-body">
          <input type="hidden" name="action" value="add">
          <div class="form-group">
            <label>User ID</label>
            <input type="number" name="user_id" class="form-control" required>
          </div>
          <div class="form-group">
            <label>Product ID</label>
            <input type="number" name="product_id" class="form-control" required>
          </div>
          <div class="form-group">
            <label> Quantity</label>
            <input type="number" name="quantity" class="form-control" required>
          </div>
          <div class="form-group">
            <label>Active</label>
            <select name="activ" class="form-control">
              <option value="true" selected>True</option>
              <option value="false">False</option>
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

<!-- Update Modal -->
<div class="modal fade" id="updateModal" tabindex="-1" role="dialog">
  <div class="modal-dialog">
    <div class="modal-content">
      <form action="<%= request.getContextPath() %>/cart" method="post">
        <div class="modal-header">
          <h5 class="modal-title">Update Cart</h5>
          <button type="button" class="close" data-dismiss="modal">&times;</button>
        </div>
        <div class="modal-body">
          <input type="hidden" name="action" value="update">
          <input type="hidden" name="id" id="update-cart-id">
          <div class="form-group">
            <label>User ID</label>
            <input type="number" name="user_id" id="update-user-id" class="form-control" required>
          </div>
          <div class="form-group">
            <label>Product ID</label>
            <input type="number" name="product_id" id="update-product-id" class="form-control" required>
          </div>
          <div class="form-group">
            <label>Quantity</label>
            <input type="number" name="quantity" id="update-quantity" class="form-control" required>
          </div>
          <div class="form-group">
            <label>Active</label>
            <select name="activ" class="form-control">
              <option value="true" selected>True</option>
              <option value="false">False</option>
            </select>
          </div>

        </div>
        <div class="modal-footer">
          <button type="submit" class="btn btn-primary">Update</button>
          <button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>
        </div>
      </form>
    </div>
  </div>
</div>

<!-- Delete Modal (Optional) -->
<div class="modal fade" id="deleteModal" tabindex="-1" role="dialog">
  <div class="modal-dialog">
    <div class="modal-content">
      <form action="<%= request.getContextPath() %>/cart" method="get">
        <div class="modal-header">
          <h5 class="modal-title">Delete Cart</h5>
          <button type="button" class="close" data-dismiss="modal">&times;</button>
        </div>
        <div class="modal-body">
          <input type="hidden" name="action" value="delete">
          <input type="hidden" name="id" id="delete-cart-id">
          <p>Are you sure you want to delete this cart?</p>
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
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
<script>
  $('#updateModal').on('show.bs.modal', function (event) {
    var button = $(event.relatedTarget);
    var id = button.data('id');
    var userId = button.data('userid');
    var productId = button.data('productid');
    var activ = button.data('activ');
    var quantity = button.data('quantity');

    var modal = $(this);
    modal.find('#update-cart-id').val(id);
    modal.find('#update-user-id').val(userId);
    modal.find('#update-product-id').val(productId);
    modal.find('#update-quantity').val(quantity);
    modal.find('#update-activ').prop('checked', activ);
  });

  $('#deleteModal').on('show.bs.modal', function (event) {
    var button = $(event.relatedTarget);
    var id = button.data('id');

    var modal = $(this);
    modal.find('#delete-cart-id').val(id);
  });
</script>

</body>
</html>
