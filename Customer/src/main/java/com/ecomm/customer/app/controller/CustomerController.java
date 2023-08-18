package com.ecomm.customer.app.controller;

import com.ecomm.customer.app.entity.Customer;
import com.ecomm.customer.app.entity.CustomerDetailsForOrder;
import com.ecomm.customer.app.model.CustomerInfo;
import com.ecomm.customer.app.repository.CustomerDetailsForOrderRepository;
import com.ecomm.customer.app.repository.CustomerRepository;
import com.ecomm.customer.app.response.RestServiceResponse;
import com.ecomm.customer.app.service.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/customer")
public class CustomerController {

    @Autowired
    CustomerRepository customerRepository;

    @Autowired
    CustomerDetailsForOrderRepository customerDetailsForOrderRepository;

    @Autowired
    EmailService emailService;
    @PostMapping("/signUpCustomer")
    public RestServiceResponse signUpCustomer(@RequestBody CustomerInfo customerInfo)  {
        Customer newCustomer = new Customer();
        newCustomer.setCustomerName(customerInfo.getCustomerName());
        newCustomer.setCustomerEmail(customerInfo.getCustomerEmail());
        Date dateTime = new Date();
        newCustomer.setCreatedDate(dateTime);
        newCustomer.setUpdatedDate(dateTime);
        newCustomer.setPrimeUser(false);
        customerRepository.save(newCustomer);
        emailService.sendEmail(customerInfo);
        RestServiceResponse restServiceResponse = new RestServiceResponse();
        restServiceResponse.setStatusCode(200);
        restServiceResponse.setStatusMessage("Customer " + customerInfo.getCustomerName() + " Created Successfully.");
        return restServiceResponse;
    }

    @GetMapping("/getCustomerByName/{customerName}")
    public List<CustomerDetailsForOrder> getCustomerByName(@PathVariable("customerName") String customerName) {
        List<CustomerDetailsForOrder> customerList = customerDetailsForOrderRepository.getCustomerDetailsByName(customerName);
        return customerList;
    }
}
