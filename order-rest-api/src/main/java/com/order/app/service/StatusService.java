package com.order.app.service;

import com.order.app.modal.Status;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;


@Service
public class StatusService {

    @Value("${application.status.details.url}")
    private String statusDetailsEndpoint;

    public Status[] getAllStatusDetails() {
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<Status[] > response = restTemplate.
                getForEntity(statusDetailsEndpoint, Status[] .class);
        return response.getBody();
    }
}
