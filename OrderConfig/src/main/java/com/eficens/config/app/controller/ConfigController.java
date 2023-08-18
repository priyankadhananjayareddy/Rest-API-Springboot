package com.eficens.config.app.controller;

import com.eficens.config.app.entity.Payment;
import com.eficens.config.app.entity.Status;
import com.eficens.config.app.repository.PaymentRepository;
import com.eficens.config.app.repository.StatusRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/config")
public class ConfigController {
    @Autowired
    StatusRepository statusRepository;

    @Autowired
    PaymentRepository paymentRepository;

    @GetMapping("/values/status")
    public List<Status> getStatusData() {
        return  statusRepository.findAll();
    }

    @GetMapping("/values/payment")
    public List<Payment> getPaymentData() {
        return  paymentRepository.findAll();
    }

}
