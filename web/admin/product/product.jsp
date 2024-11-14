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

        .table td, .table th {
            white-space: nowrap;
            vertical-align: middle;
        }

        .scrollable-description {
            max-width: 150px;
            white-space: nowrap;
            overflow-x: auto;
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
            <th>Active</th>
            <th>Created Date</th>
            <th>Updated Date</th>
            <th>Actions</th>
            <th>Info</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach var="product" items="${products}">
            <tr>
                <td>${product.id}</td>
                <td>${product.name}</td>
                <td>${product.price}</td>
                <td>${product.active ? 'Yes' : 'No'}</td>
                <td>${product.createdDate}</td>
                <td>${product.updatedDate}</td>
                <td>
                    <form action="<%= request.getContextPath() %>/admin/product" method="post" style="display:inline;">
                        <input type="hidden" name="action" value="delete"/>
                        <input type="hidden" name="id" value="${product.id}"/>
                        <button type="submit" class="btn btn-danger btn-sm">Delete</button>
                    </form>
                    <button class="btn btn-success btn-sm updateProductBtn"
                            data-id="${product.id}"
                            data-name="${product.name}"
                            data-price="${product.price}"
                            data-active="${product.active}"
                            data-description="${product.description}">Update
                    </button>
                </td>
                <td>
                    <button class="btn btn-info btn-sm" onclick="showProductDetails('${product.id}')">...</button>
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
            <form action="/admin/product" method="post">
                <input type="hidden" name="action" value="add"/>
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
                            <input type="text" class="form-control" name="descriptionName[]" placeholder="Name"
                                   required>
                            <input type="text" class="form-control" name="descriptionType[]" placeholder="Type"
                                   required>
                        </div>
                    </div>
                    <button type="button" id="addDescriptionField" class="btn btn-secondary">Add Description</button>
                    <div class="form-group" id="image-fields">
                        <label>Images</label>
                        <div class="input-group mb-2">
                            <input type="file" class="form-control" name="image" placeholder="Image URL"
                                   accept="image/*">
                        </div>
                    </div>
                    <button type="button" id="addImageField" class="btn btn-secondary">Add Image</button>

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
            <form action="<%= request.getContextPath() %>/admin/product" method="post">
                <input type="hidden" name="action" value="update"/>
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
                    <div class="form-group" id="update-description-fields">
                        <label>Description</label>
                    </div>
                    <button type="button" id="addUpdateDescriptionField" class="btn btn-secondary">Add Description
                    </button>
                </div>
                <div class="modal-footer">
                    <button type="submit" class="btn btn-success">Update Product</button>
                    <button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>
                </div>
            </form>
        </div>
    </div>
</div>

<div class="modal" id="productDetailsModal" tabindex="-1" role="dialog">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title">Product Details</h5>
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span>
                </button>
            </div>
            <div class="modal-body">
                <h6>Description:</h6>
                <p id="productDescription"></p>
                <h6>Images:</h6>
                <div id="productImages"></div>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>
            </div>
        </div>
    </div>
</div>


<script src="https://code.jquery.com/jquery-3.5.1.min.js"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.bundle.min.js"></script>
<script>
    $(document).ready(function () {
        function showProductDetails(productId) {
            const product = document.querySelector(`[data-id='${productId}']`);
            document.getElementById('productDescription').textContent = product.getAttribute('data-description');

            $('#productDetailsModal').modal('show');
        }

        $('#addImageField').on('click', function () {
            $('#image-fields').append(
                '<div class="input-group mb-2">' +
                '<input type="file" class="form-control" name="image" accept="image/*">' +
                '</div>'
            );
        });
        // Show Add Product Modal
        $('#addProductBtn').on('click', function () {
            $('#addProductModal').modal('show');
        });


        $('.updateProductBtn').on('click', function () {
            var productId = $(this).data('id');
            var productName = $(this).data('name');
            var productPrice = $(this).data('price');
            var productActive = $(this).data('active');
            var productDescription = $(this).data('description');

            // Set modal fields
            $('#updateId').val(productId);
            $('#updateName').val(productName);
            $('#updatePrice').val(productPrice);
            $('#updateActive').val(productActive);

            // Clear previous description fields
            $('#update-description-fields').html('');

            // Check if productDescription is valid JSON, otherwise use empty array
            var descriptions = [];
            try {
                descriptions = productDescription ? JSON.parse(productDescription) : [];
            } catch (e) {
                console.error("Invalid JSON format for description", e);
            }

            // Loop through descriptions and add them to the modal
            descriptions.forEach(function (desc) {
                $('#update-description-fields').append(
                    '<div class="input-group mb-2">' +
                    '<input type="text" class="form-control" name="descriptionName[]" placeholder="Name" value="' + desc.name + '" required>' +
                    '<input type="text" class="form-control" name="descriptionType[]" placeholder="Type" value="' + desc.type + '" required>' +
                    '</div>'
                );
            });


            // Show the modal
            $('#updateProductModal').modal('show');
        });


        // Add new description field
        $('#addDescriptionField').on('click', function () {
            $('#description-fields').append(
                '<div class="input-group mb-2">' +
                '<input type="text" class="form-control" name="descriptionName[]" placeholder="Name" required>' +
                '<input type="text" class="form-control" name="descriptionType[]" placeholder="Type" required>' +
                '</div>'
            );
        });


        $('#addUpdateDescriptionField').on('click', function () {
            $('#update-description-fields').append(
                '<div class="input-group mb-2">' +
                '<input type="text" class="form-control" name="descriptionName[]" placeholder="Name" required>' +
                '<input type="text" class="form-control" name="descriptionType[]" placeholder="Type" required>' +
                '</div>'
            );
        });
    });
</script>
</body>
</html>
