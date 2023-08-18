package com.eficens.email.app.jmslistener;

import com.eficens.email.app.model.EmailTemplate;
import com.eficens.email.app.service.EmailService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;



public class MessageReceiver {
    @Autowired
    EmailService emailService;

    @JmsListener(destination = "new-order-notification")
    public void receive(String message) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode orderInfo = objectMapper.readTree(message);
        JsonNode orderIdJsonNode = orderInfo.get("orderId");
        long orderId = orderIdJsonNode.asLong();
        JsonNode transactionIdJsonNode = orderInfo.get("transactionId");
        long transactionId = transactionIdJsonNode.asLong();
        JsonNode priceJsonNode = orderInfo.get("price");
        double price = priceJsonNode.asDouble();
        JsonNode paymentTypeJsonNode = orderInfo.get("paymentType");
        String paymentType = paymentTypeJsonNode.asText();
        JsonNode productNameJsonNode = orderInfo.get("productName");
        String productName = productNameJsonNode.asText();
        JsonNode fullAddressJsonNode = orderInfo.get("fullAddress");
        String fullAddress = fullAddressJsonNode.asText();
        JsonNode customerNameJsonNode = orderInfo.get("customerName");
        String customerName = customerNameJsonNode.asText();
        JsonNode customerEmailIdJsonNode = orderInfo.get("customerEmailId");
        String customerEmailId = customerEmailIdJsonNode.asText();

        String emailBody = "Hello " + customerName +
                "\n\n"
                + "Thank you for choosing E-Comm App to place your order.\n"
                + "\n"
                + "Here are the order details. \n"
                + "\n"
                + "Product Name :\t" + productName + "\n"
                + "Price :\t" + price + " with Payment Method as :\t" + paymentType
                + "\n"
                + "Your order will be shipped to :\t" + fullAddress + "\n"
                + "\n"
                + "\n"
                + "Please track your order with Order Id :\t" + orderId
                + " or Transaction Id :\t" + transactionId;

        String subject = "Order " + orderId + " has been initiated";

        EmailTemplate emailTemplate = new EmailTemplate();
        emailTemplate.setSubject(subject);
        emailTemplate.setEmailBody(emailBody);
        emailTemplate.setToAddress(customerEmailId);
        emailService.sendEmail(emailTemplate);

        System.err.println("received message :: " + message);
    }
}
