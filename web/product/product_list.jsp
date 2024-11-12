<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Product List</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
    <script src="${pageContext.request.contextPath}/scripts/data_format.js"></script>
    <style>




        .scrollable-description {
            max-width: 250px;
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

        .container {
            margin-left: 220px;
            padding-top: 20px;
        }

        .table td, .table th {
            vertical-align: middle;
            white-space: nowrap;
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
    <h2>Product List</h2>
    <table class="table table-bordered">
        <thead>
        <tr>
            <th>ID</th>
            <th>Name</th>
            <th>Price</th>
            <th>Description</th>
            <th>Active</th>
            <th>Created By</th>
            <th>Updated By</th>
            <th>Created Date</th>
            <th>Updated Date</th>
            <th>Actions</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach var="product" items="${products}">
            <tr>
                <td>${product.id}</td>
                <td>${product.name}</td>
                <td>${product.price}</td>
                <td class="scrollable-description">${product.description}</td>
                <td>${product.active ? 'Yes' : 'No'}</td>
                <td>${product.createdBy}</td>
                <td>${product.updatedBy}</td>
                <td class="date-field">${product.createdDate}</td>
                <td class="date-field">${product.updatedDate}</td>
                <td>
                    <a href="<%= request.getContextPath() %>/delete_product?id=${product.id}"
                       class="btn btn-danger btn-sm">Delete</a>
                    <button class="btn btn-success btn-sm updateProductBtn"
                            data-id="${product.id}"
                            data-name="${product.name}"
                            data-price="${product.price}"
                            data-active="${product.active}"
                            data-description="${product.description}"
                            data-createdBy="${product.createdBy}"
                            data-updatedBy="${product.updatedBy}"
                            data-createdDate="${product.createdDate}"
                            data-updatedDate="${product.updatedDate}">Update
                    </button>
                </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>

    <button id="addProductBtn" class="btn btn-primary">Add Product</button>
</div>

<!-- Add Product Modal -->
<div class="modal" id="addProductModal" tabindex="-1" role="dialog">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title">Add Product</h5>
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span>
                </button>
            </div>
            <form action="/add_product" method="post">
                <div class="modal-body">
                    <div class="form-group">
                        <label for="name">Product Name</label>
                        <input type="text" class="form-control" id="name" name="name" required>
                    </div>
                    <div class="form-group">
                        <label for="price">Price</label>
                        <input type="number" class="form-control" id="price" name="price" required>
                    </div>
                    <div class="form-group">
                        <label for="active">Active</label>
                        <select class="form-control" id="active" name="active">
                            <option value="true">Yes</option>
                            <option value="false">No</option>
                        </select>
                    </div>
                    <div class="form-group" id="description-fields">
                        <label>Description</label>
                        <div class="input-group mb-2">
                            <input type="text" class="form-control" name="descriptionName[]" placeholder="Name" required>
                            <input type="text" class="form-control" name="descriptionType[]" placeholder="Type" required>
                        </div>
                    </div>
                    <button type="button" id="addDescriptionField" class="btn btn-secondary">Add Description</button>
                </div>
                <div class="modal-footer">
                    <button type="submit" class="btn btn-primary">Add Product</button>
                    <button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>
                </div>
            </form>
        </div>
    </div>
</div>

<!-- Update Product Modal -->
<div class="modal" id="updateProductModal" tabindex="-1" role="dialog">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title">Update Product</h5>
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span>
                </button>
            </div>
            <form action="/update_product" method="post">
                <input type="hidden" id="updateId" name="id">
                <div class="modal-body">
                    <div class="form-group">
                        <label for="updateName">Product Name</label>
                        <input type="text" class="form-control" id="updateName" name="name" required>
                    </div>
                    <div class="form-group">
                        <label for="updatePrice">Price</label>
                        <input type="number" class="form-control" id="updatePrice" name="price" required>
                    </div>
                    <div class="form-group">
                        <label for="updateActive">Active</label>
                        <select class="form-control" id="updateActive" name="active">
                            <option value="true">Yes</option>
                            <option value="false">No</option>
                        </select>
                    </div>
                    <div class="form-group">
                        <label for="updateDescription">Description</label>
                        <input type="text" class="form-control" id="updateDescription" name="description" value="">
                    </div>
                </div>
                <div class="modal-footer">
                    <button type="submit" class="btn btn-primary">Update Product</button>
                    <button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>
                </div>
            </form>
        </div>
    </div>
</div>

<script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.9.3/dist/umd/popper.min.js"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>

<script>
    document.querySelectorAll('.updateProductBtn').forEach(button => {
        button.addEventListener('click', (e) => {
            const product = e.target.dataset;
            document.getElementById('updateId').value = product.id;
            document.getElementById('updateName').value = product.name;
            document.getElementById('updatePrice').value = product.price;
            document.getElementById('updateActive').value = product.active;
            document.getElementById('updateDescription').value = product.description;
            $('#updateProductModal').modal('show');
        });
    });

    document.getElementById('addProductBtn').addEventListener('click', () => {
        $('#addProductModal').modal('show');
    });

    document.getElementById('addDescriptionField').addEventListener('click', () => {
        const descriptionFields = document.getElementById('description-fields');
        const newField = document.createElement('div');
        newField.classList.add('input-group', 'mb-2');
        newField.innerHTML = `
            <input type="text" class="form-control" name="descriptionName[]" placeholder="Name" required>
            <input type="text" class="form-control" name="descriptionType[]" placeholder="Type" required>
        `;
        descriptionFields.appendChild(newField);
    });
</script>
</body>
</html>
