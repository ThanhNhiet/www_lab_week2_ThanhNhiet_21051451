<%@ page import="com.iuh.week02_lab_phamlethanhnhiet_21051451.backend.entities.Product" %>
<%@ page import="java.util.List" %>
<%@ page import="com.iuh.week02_lab_phamlethanhnhiet_21051451.backend.entities.ProductPrice" %>
<%@ page import="java.util.Optional" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Product List</title>
    <style type="text/css">
        body {
            font-family: Arial, sans-serif;
            margin: 0;
        }

        h1 {
            text-align: center;
            margin-top: 20px;
        }

        #Table {
            width: 80%;
            margin: 20px auto;
            border-collapse: collapse;
        }

        #Table th, #Table td {
            padding: 10px;
            text-align: center;
            border: 1px solid #ccc;
        }

        #Table th {
            background-color: #f4f4f4;
        }

        button {
            display: flex;
            padding: 5px 10px;
            margin-right: 5px;
            cursor: pointer;
        }

        #addBtn {
            display: block;
            margin: 20px auto;
            padding: 10px 20px;
            background-color: #4CAF50;
            color: white;
            border: none;
            cursor: pointer;
        }

        #addBtn:hover, button:hover {
            background-color: #45a049;
        }


        /* Overlay để tạo hiệu ứng mờ nền khi form xuất hiện */
        #modalOverlay {
            display: none; /* Ban đầu ẩn */
            position: fixed;
            top: 0;
            left: 0;
            width: 100%;
            height: 100%;
            background-color: rgba(0, 0, 0, 0.5); /* Nền mờ */
            z-index: 999; /* Đảm bảo nó luôn trên cùng */
        }

        #findP {
            position: absolute;
            top: 50px;
            left: 195px;
        }
        #viewcart{
            position: absolute;
            top: 40px;
            right: 195px;
        }

        /* Form chung cho AddForm, updateForm, grantAccessForm */
        .form-container {
            width: 400px;
            margin: 0 auto;
            padding: 20px;
            border: 1px solid #ccc;
            border-radius: 10px;
            background-color: #fff;
            box-shadow: 0px 4px 8px rgba(0, 0, 0, 0.1);
            position: fixed;
            top: 50%;
            left: 50%;
            transform: translate(-50%, -50%);
            display: none; /* Ban đầu ẩn form */
            z-index: 1000; /* Form sẽ xuất hiện trên overlay */
        }

        /* Style cho tiêu đề của form */
        .form-container h2 {
            text-align: center;
            margin-bottom: 20px;
            font-size: 1.4em;
        }

        /* Style cho các input và select */
        .form-container input[type="text"], .form-container input[type="password"],
        .form-container input[type="email"], .form-container input[type="submit"],
        .form-container select {
            width: 100%;
            padding: 10px;
            margin-bottom: 15px;
            border: 1px solid #ccc;
            border-radius: 5px;
            box-sizing: border-box;
        }

        /* Style cho nút submit */
        .form-container input[type="submit"] {
            background-color: #4CAF50; /* Màu xanh cho nút submit */
            cursor: pointer;
        }

        input[type="submit"]:hover {
            color: white;
            background-color: #45a049;
        }

        /* Nút đóng form */
        .form-container button[type="button"] {
            width: 100%;
            padding: 10px;
            font-size: 1em;
            background-color: #f44336; /* Màu đỏ cho nút Cancel */
            color: white;
            border: none;
            border-radius: 5px;
            cursor: pointer;
        }

        .cancelContainer {
            text-align: center; /* Căn giữa nút */
        }

        #UpdateCloseFormContainer button, #addCloseFormContainer button {
            width: 100%;
            display: inline-block;
        }

        .form-container button[type="button"]:hover {
            opacity: 0.9;
        }
    </style>
</head>
<body>
<h1>Product List</h1>

<div id="modalOverlay"></div>

<form id="findP" action="controller">
    <input type="hidden" name="action" value="find">
    <input type="text" name="nameP" required placeholder="name product">
    <input type="submit" value="Find">
</form>

<form id="viewcart" method="post" action="controller" style="display:inline;">
    <input type="hidden" name="action" value="viewCart">
    <button type="submit">View cart</button>
</form>

<div id="AddForm" class="form-container">
    <h2>Add new product</h2>
    <form method="post" action="controller">
        <input type="hidden" name="action" value="add">

        <label for="name">Name:</label>
        <input type="text" id="name" name="name" required>

        <label for="description">Description:</label>
        <input type="text" id="description" name="description" required>

        <label for="manufacturer">Manufacturer:</label>
        <input type="text" id="manufacturer" name="manufacturer" required>

        <label for="unit">Unit:</label>
        <input type="text" id="unit" name="unit" required><br>

        <label for="status">Status:</label>
        <select id="status" name="status">
            <option value="1">Active</option>
            <option value="2">Deactive</option>
            <option value="3">Terminated</option>
        </select>

        <input type="submit" value="Add new product">

        <div id="addCloseFormContainer" class="cancelContainer">
            <button id="closeForm" type="button"
                    onclick="document.getElementById('AddForm').style.display = 'none';">
                Cancel
            </button>
        </div>
    </form>
</div>

<div id="updateForm" class="form-container" style="display:none;">
    <h2>Update Account</h2>
    <form method="post" action="controller">
        <input type="hidden" name="action" value="update">
        <label for="uid">ID:</label>
        <input type="text" id="uid" name="id" readonly>

        <label for="uname">Name:</label>
        <input type="text" id="uname" name="name" required>

        <label for="description">Description:</label>
        <input type="text" id="udescription" name="description" required>

        <label for="manufacturer">Manufacturer:</label>
        <input type="text" id="umanufacturer" name="manufacturer" required>

        <label for="unit">Unit:</label>
        <input type="text" id="uunit" name="unit" required><br>

        <label for="status">Status:</label>
        <select id="ustatus" name="status">
            <option value="1">Active</option>
            <option value="2">Deactive</option>
            <option value="3">Terminated</option>
        </select>

        <input type="submit" value="Update">

        <div id="UpdateCloseFormContainer" class="cancelContainer">
            <button id="closeFormUpdate" type="button"
                    onclick="closeUpdateForm()">
                Cancel
            </button>
        </div>
    </form>
</div>

<!-- Table of Accounts -->

<table id="Table">
    <thead>
    <tr>
        <th>ID</th>
        <th>Name</th>
        <th>Description</th>
        <th>Manufacturer</th>
        <th>Status</th>
        <th>Unit</th>
        <th>Price</th>
        <th>Actions</th>
    </tr>
    </thead>
    <tbody>
    <%
        // Lấy danh sách sản phẩm từ request
        List<Product> products = (List<Product>) request.getAttribute("products");
        List<ProductPrice> productPrices = (List<ProductPrice>) request.getAttribute("productPrices");
        if (products != null && !products.isEmpty()) {
            for (Product product : products) {
    %>
    <tr id="row-<%=product.getProductId()%>">
        <td><%= product.getProductId() %>
        </td>
        <td><%= product.getName() %>
        </td>
        <td><%= product.getDescription() %>
        </td>
        <td><%= product.getManufacturer() %>
        </td>
        <td><%= product.getStatus() %>
        </td>
        <td><%= product.getUnit() %>
        </td>
        <%
            boolean priceFound = false; // Biến cờ để kiểm tra nếu tìm thấy giá

            for (ProductPrice productPrice : productPrices) {
                if (productPrice.getProduct().getProductId() == product.getProductId()) {
                    priceFound = true;
        %>
        <td><%= productPrice.getPrice() + "d" %></td>
        <%
                    break; // Thoát vòng lặp khi đã tìm thấy giá
                }
            }
            // Nếu không tìm thấy giá, hiển thị giá trị mặc định
            if (!priceFound) {
        %>
        <td>N/A</td>
        <%
            }
        %>

        <td>
            <button type="button"
                    onclick="updateAccount('<%=product.getProductId()%>', '<%=product.getName()%>',
                            '<%=product.getDescription()%>',
                            '<%=product.getManufacturer()%>', '<%=product.getStatus()%>',
                            '<%=product.getUnit()%>')">
                Cập nhật
            </button>

            <form method="post" action="controller" style="display:inline;">
                <input type="hidden" name="action" value="delete">
                <input type="hidden" name="id" value="<%=product.getProductId()%>">
                <button type="submit">Delete</button>
            </form>

            <%
                // Nếu sản phẩm có giá, hiển thị nút thêm vào giỏ hàng
                if (priceFound) {
            %>
            <form method="post" action="controller" style="display:inline;">
                <input type="hidden" name="action" value="add2Cart">
                <input type="hidden" name="id" value="<%=product.getProductId()%>">
                <button type="submit">Add to cart</button>
            </form>
            <%
                }
            %>
        </td>
    </tr>
    <%
        }
        }
        else
        {
    %>
    <tr>
        <td colspan="8">No products found.</td>
    </tr>
    <%
        }
    %>
    </tbody>
</table>

<!-- Add Button -->
<button id="addBtn" onclick="addProduct()">Add new product</button>

<script>
    function addProduct() {
        document.getElementById('AddForm').style.display = 'block';
    }

    function updateAccount(id, name, description, manufacturer, status, unit) {
        // Hiển thị form
        document.getElementById('updateForm').style.display = 'block';

        // Điền thông tin vào form
        document.getElementById('uid').value = id;
        document.getElementById('uname').value = name;
        document.getElementById('udescription').value = description;
        document.getElementById('umanufacturer').value = manufacturer;
        document.getElementById('ustatus').value = status;
        document.getElementById('uunit').value = unit;
    }

    function closeUpdateForm() {
        document.getElementById('updateForm').style.display = 'none';
    }

</script>
</body>
</html>