package com.iuh.week02_lab_phamlethanhnhiet_21051451.frontend.controllers;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.iuh.week02_lab_phamlethanhnhiet_21051451.backend.config.GsonConfig;
import com.iuh.week02_lab_phamlethanhnhiet_21051451.backend.entities.Product;
import com.iuh.week02_lab_phamlethanhnhiet_21051451.backend.entities.ProductPrice;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.hc.client5.http.classic.methods.HttpGet;
import org.apache.hc.client5.http.impl.classic.CloseableHttpClient;
import org.apache.hc.client5.http.impl.classic.CloseableHttpResponse;
import org.apache.hc.client5.http.impl.classic.HttpClients;
import org.apache.hc.core5.http.HttpEntity;
import org.apache.hc.core5.http.ParseException;
import org.apache.hc.core5.http.io.entity.EntityUtils;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@WebServlet(name = "index", value = "/index")
public class IndexController extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private static final String API_Product_URL = "http://localhost:8080/week02_lab_PhamLeThanhNhiet_21051451-1.0-SNAPSHOT/api/products";
    private static final String API_ProductPrices_URL = "http://localhost:8080/week02_lab_PhamLeThanhNhiet_21051451-1.0-SNAPSHOT/api/product-prices";

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Tạo HttpClient để gọi API
        try (CloseableHttpClient httpClient = HttpClients.createDefault()) {
            HttpGet httpGetProduct = new HttpGet(API_Product_URL);
            HttpGet httpGetProductPrices = new HttpGet(API_ProductPrices_URL);
            try (CloseableHttpResponse apiResponseProduct = httpClient.execute(httpGetProduct)
                 ; CloseableHttpResponse apiResponseProductP = httpClient.execute(httpGetProductPrices)
            ) {
                HttpEntity entityProduct = apiResponseProduct.getEntity();
                HttpEntity entityProductPrices = apiResponseProductP.getEntity();
                if (entityProduct != null) {
                    String jsonResponse = EntityUtils.toString(entityProduct);
                    // Chuyển đổi JSON thành danh sách sản phẩm
                    Gson gson = new GsonConfig().getGson();
                    TypeToken<List<Product>> token = new TypeToken<List<Product>>() {
                    };
                    List<Product> products = gson.fromJson(jsonResponse, token.getType());
                    // Gán danh sách sản phẩm vào thuộc tính request
                    request.setAttribute("products", products);
                }
                if(entityProductPrices != null) {
                    String jsonResponse = EntityUtils.toString(entityProductPrices);
                    // Chuyển đổi JSON thành danh sách giá sản phẩm
                    Gson gson = new GsonConfig().getGson();
                    TypeToken<List<ProductPrice>> token = new TypeToken<List<ProductPrice>>() {
                    };
                    List<ProductPrice> productPrices = gson.fromJson(jsonResponse, token.getType());
                    // Gán danh sách giá sản phẩm vào thuộc tính request
                    request.setAttribute("productPrices", productPrices);
                }
            } catch (ParseException e) {
                // Log lại lỗi hoặc đưa ra thông báo lỗi phù hợp
                e.printStackTrace();
            }
        }

        // Chuyển tiếp đến trang JSP
        request.getRequestDispatcher("product.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
}
