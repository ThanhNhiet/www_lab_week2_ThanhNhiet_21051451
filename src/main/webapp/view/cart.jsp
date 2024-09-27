<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.List" %>
<%@ page import="com.iuh.week02_lab_phamlethanhnhiet_21051451.backend.entities.Product" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="com.iuh.week02_lab_phamlethanhnhiet_21051451.backend.entities.ProductPrice" %>
<%@ page import="java.util.Map" %>

<!DOCTYPE html>
<html>
<head>
    <title>Cart</title>
</head>
<body>
<h2>Your Cart</h2>
<table border="1" cellpadding="0" cellspacing="0" width="90%">
    <tr>
        <td>ID</td>
        <td>Name</td>
        <td>Quanlity</td>
        <td>Price</td>
        <td>amount</td>
        <td style="align-items: center">Action</td>
    </tr>
    <%
        List<Product> pInC = (List<Product>) session.getAttribute("productsInCart");
        List<ProductPrice> ppInC = (List<ProductPrice>) session.getAttribute("productPricesInCart");
        Map<Long, Long> items_map = (Map<Long, Long>) session.getAttribute("items_map");
        Double total = (Double) session.getAttribute("sum");
//        double total = 0;
        for (Product p : pInC) {
//            total += p.getPrice() * p.getQuantity();
    %>
    <tr>
        <td><%= p.getProductId() %>
        </td>
        <td><%= p.getName() %>
        </td>
        <td><%= items_map.get(p.getProductId()).longValue() %>
        </td>
        <%
            for (ProductPrice pp : ppInC) {
                if (pp.getProduct().getProductId() == p.getProductId()) {
        %>
        <td><%= pp.getPrice() %>
        </td>
        <td><%= pp.getPrice() * items_map.get(p.getProductId()).longValue() %>
        </td>
        <%
                }
            }
        %>
        <%--Xoa san pham--%>
        <td style="text-align: center">
            <form action="controller" method="post">
                <input type="hidden" name="action" value="deleteFromCart">
                <input type="hidden" name="id" value="<%= p.getProductId() %>">
                <input type="submit" value="Delete">
            </form>
        </td>
    </tr>
    <%
        }
    %>
</table>
<h3>Total: <%= total %></h3>
<br>

<div>
    <h2>Payment: </h2>
    <form action="controller" method="post">
        <input type="hidden" name="action" value="pay">
        <label>ID Customer:</label> <br>
        <input type="text" name="idCus" required> <br>
        <label>ID Employee:</label> <br>
        <input type="text" name="idEmp" required> <br>
        <label>Note:</label> <br>
        <input type="text" name="note"> <br>
        <label>Money:</label> <br>
        <input type="number" name="money" required> <br>
        <input type="submit" value="Pay">
    </form>
</div>
</body>
</html>
