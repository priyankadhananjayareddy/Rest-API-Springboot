package com.order.app.service;

import com.order.app.modal.Product;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class ProductService {
    @Value("${application.product.details.url}")
    private String productDetailsEndpoint;

    public Product[] getProductDetailsByName(String productName) {

        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<Product[] > response = restTemplate.
                getForEntity(productDetailsEndpoint+productName, Product[] .class);
        return response.getBody();
    }
}
