package com.iuh.week02_lab_phamlethanhnhiet_21051451.frontend.models;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.iuh.week02_lab_phamlethanhnhiet_21051451.backend.config.GsonConfig;
import com.iuh.week02_lab_phamlethanhnhiet_21051451.backend.dtos.ProductDTO;
import org.apache.hc.client5.http.classic.methods.HttpGet;
import org.apache.hc.client5.http.impl.classic.CloseableHttpClient;
import org.apache.hc.client5.http.impl.classic.CloseableHttpResponse;
import org.apache.hc.client5.http.impl.classic.HttpClients;
import org.apache.hc.core5.http.HttpEntity;
import org.apache.hc.core5.http.ParseException;
import org.apache.hc.core5.http.io.entity.EntityUtils;

import java.io.IOException;
import java.util.List;

public class ProductModel {
    private static final String BASED_URI = "http://localhost:8080/week02_lab_PhamLeThanhNhiet_21051451-1.0-SNAPSHOT/api/products";

    public List<ProductDTO> getAllProducts() {
        List<ProductDTO> products = null;
        try (CloseableHttpClient httpClient = HttpClients.createDefault()) {
            HttpGet httpGetProduct = new HttpGet(BASED_URI);
            try (CloseableHttpResponse apiResponseProduct = httpClient.execute(httpGetProduct)) {
                HttpEntity entityProduct = apiResponseProduct.getEntity();
                if (entityProduct != null) {
                    String jsonResponse = EntityUtils.toString(entityProduct);
                    // Chuyển đổi JSON thành danh sách sản phẩm
                    Gson gson = new GsonConfig().getGson();
                    TypeToken<List<ProductDTO>> token = new TypeToken<List<ProductDTO>>() {
                    };
                    products = gson.fromJson(jsonResponse, token.getType());
                }
            } catch (ParseException e) {
                throw new RuntimeException(e);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return products;
    }
}
