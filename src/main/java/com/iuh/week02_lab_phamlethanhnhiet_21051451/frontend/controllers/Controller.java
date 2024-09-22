package com.iuh.week02_lab_phamlethanhnhiet_21051451.frontend.controllers;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.iuh.week02_lab_phamlethanhnhiet_21051451.backend.config.GsonConfig;
import com.iuh.week02_lab_phamlethanhnhiet_21051451.backend.entities.Cart;
import com.iuh.week02_lab_phamlethanhnhiet_21051451.backend.entities.CartItem;
import com.iuh.week02_lab_phamlethanhnhiet_21051451.backend.entities.Product;
import com.iuh.week02_lab_phamlethanhnhiet_21051451.backend.entities.ProductPrice;
import com.iuh.week02_lab_phamlethanhnhiet_21051451.backend.enums.ProductStatus;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.apache.hc.client5.http.classic.methods.HttpGet;
import org.apache.hc.client5.http.classic.methods.HttpPost;
import org.apache.hc.client5.http.impl.classic.CloseableHttpClient;
import org.apache.hc.client5.http.impl.classic.CloseableHttpResponse;
import org.apache.hc.client5.http.impl.classic.HttpClients;
import org.apache.hc.core5.http.ContentType;
import org.apache.hc.core5.http.HttpEntity;
import org.apache.hc.core5.http.HttpStatus;
import org.apache.hc.core5.http.ParseException;
import org.apache.hc.core5.http.io.entity.EntityUtils;
import org.apache.hc.core5.http.io.entity.StringEntity;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.URLEncoder;
import java.nio.channels.ScatteringByteChannel;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@WebServlet(name = "Controller", value = "/controller")
public class Controller extends HttpServlet {
    private Cart cart;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        if (action == null) {
            request.getRequestDispatcher("/index.jsp").forward(request, response);
            return;
        }

        switch (action) {
            case "add":
                String name = request.getParameter("name");
                String description = request.getParameter("description");
                String manufacturer = request.getParameter("manufacturer");
                String unit = request.getParameter("unit");
                String status = request.getParameter("status");
                ProductStatus productStatus = ProductStatus.values()[Integer.parseInt(status) - 1];

                // use api products to add product
                Product product = new Product(name, description, manufacturer, productStatus, unit);

                // Chuyển đối tượng sản phẩm thành JSON
                Gson gson = new GsonConfig().getGson();
                TypeToken<Product> token = new TypeToken<Product>() {
                };
                String productJson = gson.toJson(product, token.getType());

                // Gửi dữ liệu đến API để thêm sản phẩm
                HttpPost postRequest = new HttpPost("http://localhost:8080/week02_lab_PhamLeThanhNhiet_21051451-1.0-SNAPSHOT/api/products/save");
                postRequest.setEntity(new StringEntity(productJson, ContentType.APPLICATION_JSON));

                CloseableHttpClient httpClient = HttpClients.createDefault();
                CloseableHttpResponse apiResponse = httpClient.execute(postRequest);
                response.sendRedirect("index.jsp");
                apiResponse.close();
                httpClient.close();
                break;
            case "delete":
                Long id = Long.parseLong(request.getParameter("id"));
                CloseableHttpClient httpClient_del = HttpClients.createDefault();
                HttpPost httpPostProduct_del = new HttpPost("http://localhost:8080/week02_lab_PhamLeThanhNhiet_21051451-1.0-SNAPSHOT/api/products/delete" + id);
                CloseableHttpResponse apiRespProduct_del = httpClient_del.execute(httpPostProduct_del);
                response.sendRedirect("index.jsp");
                apiRespProduct_del.close();
                httpClient_del.close();
                break;
            case "find":
                String nameFind = request.getParameter("nameP");
                String encodedNameFind = nameFind.replace(" ", "%20");
                try (CloseableHttpClient httpClient_find = HttpClients.createDefault()) {
                    HttpGet httpGetProduct = new HttpGet("http://localhost:8080/week02_lab_PhamLeThanhNhiet_21051451-1.0-SNAPSHOT/api/products/find/" + encodedNameFind);
                    HttpGet httpGetProductPrices = new HttpGet("http://localhost:8080/week02_lab_PhamLeThanhNhiet_21051451-1.0-SNAPSHOT/api/product-prices");
                    try (CloseableHttpResponse apiResponseProduct = httpClient_find.execute(httpGetProduct)
                         ; CloseableHttpResponse apiResponseProductP = httpClient_find.execute(httpGetProductPrices)
                    ) {
                        HttpEntity entityProduct = apiResponseProduct.getEntity();
                        HttpEntity entityProductPrices = apiResponseProductP.getEntity();
                        if (entityProduct != null) {
                            String jsonResponse = EntityUtils.toString(entityProduct);
                            Gson gson_find = new GsonConfig().getGson();
                            TypeToken<List<Product>> token_find = new TypeToken<List<Product>>() {
                            };
                            List<Product> products = gson_find.fromJson(jsonResponse, token_find.getType());
                            request.setAttribute("products", products);
                        }
                        if (entityProductPrices != null) {
                            String jsonResponse = EntityUtils.toString(entityProductPrices);
                            Gson gson_find = new GsonConfig().getGson();
                            TypeToken<List<ProductPrice>> token_find = new TypeToken<List<ProductPrice>>() {
                            };
                            List<ProductPrice> productPrices = gson_find.fromJson(jsonResponse, token_find.getType());
                            request.setAttribute("productPrices", productPrices);
                        }
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }
                }
                request.getRequestDispatcher("product.jsp").forward(request, response);
                break;
            case "update":
                String idU = request.getParameter("id");
                String nameU = request.getParameter("name");
                String descriptionU = request.getParameter("description");
                String manufacturerU = request.getParameter("manufacturer");
                String unitU = request.getParameter("unit");
                String statusU = request.getParameter("status");
                ProductStatus productStatusU = ProductStatus.values()[Integer.parseInt(statusU) - 1];
                Product productU = new Product(nameU, descriptionU, manufacturerU, productStatusU, unitU);
                productU.setProductId(Long.parseLong(idU));
                // Chuyển đối tượng sản phẩm thành JSON
                Gson gson_u = new GsonConfig().getGson();
                TypeToken<Product> token_u = new TypeToken<Product>() {
                };
                String productJson_u = gson_u.toJson(productU, token_u.getType());

                // Gửi dữ liệu đến API để thêm sản phẩm
                HttpPost postRequest_u = new HttpPost("http://localhost:8080/week02_lab_PhamLeThanhNhiet_21051451-1.0-SNAPSHOT/api/products/update");
                postRequest_u.setEntity(new StringEntity(productJson_u, ContentType.APPLICATION_JSON));

                CloseableHttpClient httpClient_u = HttpClients.createDefault();
                CloseableHttpResponse apiResponse_u = httpClient_u.execute(postRequest_u);
                response.sendRedirect("index.jsp");
                apiResponse_u.close();
                httpClient_u.close();
                break;
            case "add2Cart":
                Long id_2cart = Long.parseLong(request.getParameter("id"));
                HttpSession session = request.getSession(true);
                Object obj = session.getAttribute("cart");
                if (obj == null) {
                    cart = new Cart();
                } else {
                    cart = (Cart) obj;
                }

                CartItem item = new CartItem(id_2cart, 1);
                cart.addProduct(item);
                session.setAttribute("cart", cart);
                response.sendRedirect("index.jsp");
                break;
            case "viewCart":
                HttpSession session_viewCart = request.getSession(true);
                ArrayList<Product> products = (ArrayList<Product>) session_viewCart.getAttribute("products");

                List<Product> productsInCart = (List<Product>) session_viewCart.getAttribute("productsInCart");
                List<ProductPrice> productPricesInCart = (List<ProductPrice>) session_viewCart.getAttribute("productPricesInCart");

                if (productsInCart != null) {
                    productsInCart.clear(); // Xóa danh sách cũ
                } else {
                    productsInCart = new ArrayList<>();  // Nếu chưa có, tạo mới
                }

                if (productPricesInCart != null) {
                    productPricesInCart.clear(); // Xóa danh sách cũ
                } else {
                    productPricesInCart = new ArrayList<>();  // Nếu chưa có, tạo mới
                }

                Object cart_obj = session_viewCart.getAttribute("cart");
                Cart cart = null;
                PrintWriter out = response.getWriter();

                if (cart_obj == null) {
                    out.println("<h1>Empty Shopping Cart</h1>");
                } else {
//                    String html = "<table border=\"1\" cellpadding=\"0\" cellspacing=\"0\" width=\"90%\">";
//                    html += "<tr><td>ID</td> <td>Name</td> <td>Quanlity</td> <td>Price</td> <td>amount</td></tr>";

                    double sum = 0d;

                    cart = (Cart) cart_obj;
                    for (CartItem cItem : cart.getCart()) {
                        long idI = cItem.getPid();
                        Product product_2cart = null;
                        ProductPrice productPrice_2cart = null;

                        try (CloseableHttpClient httpClient_find = HttpClients.createDefault();
                             CloseableHttpClient httpClient_findProductPrice = HttpClients.createDefault();) {
                            HttpGet httpGetProduct = new HttpGet("http://localhost:8080/week02_lab_PhamLeThanhNhiet_21051451-1.0-SNAPSHOT/api/products/" + idI);
                            HttpGet httpGetProductPrice = new HttpGet("http://localhost:8080/week02_lab_PhamLeThanhNhiet_21051451-1.0-SNAPSHOT/api/product-prices/" + idI);
                            try (CloseableHttpResponse apiResponseProduct = httpClient_find.execute(httpGetProduct);
                                 CloseableHttpResponse apiResponseProductPrice = httpClient_findProductPrice.execute(httpGetProductPrice)) {
                                HttpEntity entityProduct = apiResponseProduct.getEntity();
                                HttpEntity entityProductPrice = apiResponseProductPrice.getEntity();
                                if (entityProduct != null) {
                                    String jsonResponse = EntityUtils.toString(entityProduct);
                                    Gson gson_find = new GsonConfig().getGson();
                                    TypeToken<Product> token_find = new TypeToken<Product>() {
                                    };
                                    product_2cart = gson_find.fromJson(jsonResponse, token_find.getType());
                                    productsInCart.add(product_2cart);
                                }
                                if (entityProductPrice != null) {
                                    String jsonResponse = EntityUtils.toString(entityProductPrice);
                                    Gson gson_find = new GsonConfig().getGson();
                                    TypeToken<ProductPrice> token_find = new TypeToken<ProductPrice>() {
                                    };
                                    productPrice_2cart = gson_find.fromJson(jsonResponse, token_find.getType());
                                    productPricesInCart.add(productPrice_2cart);
                                }
                            } catch (ParseException e) {
                                e.printStackTrace();
                            }
                        }

                        double amount = productPrice_2cart.getPrice() * cItem.getQuantity();
                        sum += amount;

                        //Tao ra doi tuong key value voi key la cItem.getPid() va value la cItem.getQuantity()
                        Map<Long, Long> map = (Map<Long, Long>) session_viewCart.getAttribute("items_map");
                        if (map == null) {
                            map = new HashMap<>();
                        }
                        map.put(cItem.getPid(), cItem.getQuantity());
                        session_viewCart.setAttribute("items_map", map);

//                        html += "<tr>";
//                        html += "<td>" + product_2cart.getProductId() + "</td>";
//                        html += "<td>" + product_2cart.getName() + "</td>";
//                        html += "<td>" + cItem.getQuantity() + "</td>";
//                        html += "<td>" + productPrice_2cart.getPrice() + "</td>";
//                        html += "<td>" + amount + "</td>";
//                        html += "</tr>";
                    }
                    session_viewCart.setAttribute("productsInCart", productsInCart);
                    session_viewCart.setAttribute("productPricesInCart", productPricesInCart);
                    session_viewCart.setAttribute("sum", sum);
                    request.getRequestDispatcher("/view/cart.jsp").forward(request, response);
//                    out.println(html);
//                    out.println("<h2>Total amount: " + sum + "</h2>");
                }
                break;
            case "deleteFromCart":
                // Lấy session và giỏ hàng từ session
                HttpSession session_delete = request.getSession(true);
                Cart cart_delete = (Cart) session_delete.getAttribute("cart");

                if (cart_delete == null) {
                    response.getWriter().println("<h1>Cart is empty!</h1>");
                } else {
                    long productIdToDelete = Long.parseLong(request.getParameter("id")); // Lấy id sản phẩm cần xóa từ request

                    // Xóa sản phẩm khỏi giỏ hàng
                    cart_delete.getCart().removeIf(item1 -> item1.getPid() == productIdToDelete);

                    // Cập nhật lại các danh sách liên quan trong session
                    List<Product> productsInCart_delete = (List<Product>) session_delete.getAttribute("productsInCart");
                    List<ProductPrice> productPricesInCart_delete = (List<ProductPrice>) session_delete.getAttribute("productPricesInCart");
                    Map<Long, Long> items_map_delete = (Map<Long, Long>) session_delete.getAttribute("items_map");

                    // Xóa sản phẩm trong danh sách các sản phẩm hiển thị
                    if (productsInCart_delete != null) {
                        productsInCart_delete.removeIf(p -> p.getProductId() == productIdToDelete);
                    }

                    if (productPricesInCart_delete != null) {
                        productPricesInCart_delete.removeIf(pp -> pp.getProduct().getProductId() == productIdToDelete);
                    }

                    if (items_map_delete != null) {
                        items_map_delete.remove(productIdToDelete);  // Xóa số lượng sản phẩm khỏi items_map
                    }

                    // Cập nhật lại tổng tiền
                    double sum_delete = 0d;
                    for (CartItem cItem : cart_delete.getCart()) {
                        long idI = cItem.getPid();
                        ProductPrice productPrice_delete = null;

                        try (CloseableHttpClient httpClient_findProductPrice = HttpClients.createDefault();) {
                            HttpGet httpGetProductPrice = new HttpGet("http://localhost:8080/week02_lab_PhamLeThanhNhiet_21051451-1.0-SNAPSHOT/api/product-prices/" + idI);
                            try (CloseableHttpResponse apiResponseProductPrice = httpClient_findProductPrice.execute(httpGetProductPrice)) {
                                HttpEntity entityProductPrice = apiResponseProductPrice.getEntity();
                                if (entityProductPrice != null) {
                                    String jsonResponsepp = EntityUtils.toString(entityProductPrice);
                                    Gson gson_find = new GsonConfig().getGson();
                                    TypeToken<ProductPrice> token_find = new TypeToken<ProductPrice>() {
                                    };
                                    productPrice_delete = gson_find.fromJson(jsonResponsepp, token_find.getType());
                                }
                            } catch (ParseException e) {
                                throw new RuntimeException(e);
                            }
                        }

                        // Tính lại tổng tiền
                        if (productPrice_delete != null) {
                            double amount = productPrice_delete.getPrice() * cItem.getQuantity();
                            sum_delete += amount;
                        }
                    }

                    // Lưu các danh sách và tổng tiền vào session
                    session_delete.setAttribute("productsInCart", productsInCart_delete);
                    session_delete.setAttribute("productPricesInCart", productPricesInCart_delete);
                    session_delete.setAttribute("items_map", items_map_delete);
                    session_delete.setAttribute("sum", sum_delete);  // Cập nhật lại tổng tiền

                    // Chuyển về trang giỏ hàng sau khi xóa sản phẩm
                    request.getRequestDispatcher("/view/cart.jsp").forward(request, response);
                }
                break;
            default:
                request.getRequestDispatcher("/index.jsp").forward(request, response);
                break;
        }
    }
}
