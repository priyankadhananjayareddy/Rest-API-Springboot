package com.order.app.service;

import com.order.app.modal.Payment;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class PaymentService {

    @Value("${application.payment.details.url}")
    private String paymentDetailsEndpoint;

    public Payment[] getAllPaymentDetails() {

        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<Payment[] > response = restTemplate.
                getForEntity(paymentDetailsEndpoint, Payment[] .class);
        return response.getBody();
    }
}
