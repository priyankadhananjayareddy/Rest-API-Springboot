package com.order.app.service;

import com.order.app.modal.CustomerDetailsForOrder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class CustomerService {

    @Value("${application.customer.details.url}")
    private String customerDetailsEndpoint;

    public CustomerDetailsForOrder[] getCustomerDetailsByName(String customerName) {

        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<CustomerDetailsForOrder[]> response = restTemplate.
                getForEntity(customerDetailsEndpoint+customerName, CustomerDetailsForOrder[].class);
        return response.getBody();
    }
}
