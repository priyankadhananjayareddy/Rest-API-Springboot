package com.ecomm.customer.app.service;

import com.ecomm.customer.app.model.CustomerInfo;
import com.ecomm.customer.app.model.EmailTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Date;

@Service
public class EmailService {
    @Value("${application.send.email.url}")
    private String sendEmailEndpoint;
    public String sendEmail(CustomerInfo customerInfo){
        RestTemplate restTemplate = new RestTemplate();
        EmailTemplate emailTemplate = new EmailTemplate();
        String emailBody = "Hello " + customerInfo.getCustomerName() + ", \n"
                + "\n"
                + ""
                + "Welcome to Eficens E-Comm App.\n "
                + "\n"
                + "Enjoy Shopping...\n"
                + "\n"
                + "Thanks and Regards,\n"
                + "E-Comm Customer Support Team";
        String subject = "Welcome...!";

        emailTemplate.setToAddress(customerInfo.getCustomerEmail());
        emailTemplate.setSubject(subject);
        emailTemplate.setEmailBody(emailBody);
        emailTemplate.setAttachmentRequired(false);

        ResponseEntity<String> response = restTemplate.postForEntity(sendEmailEndpoint, emailTemplate, String.class);
        return response.getBody();
    }
}
