package com.order.app.validation;

import com.order.app.modal.*;
import com.order.app.service.CustomerService;
import com.order.app.service.PaymentService;
import com.order.app.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ValidateData {


    @Autowired
    CustomerService customerService;

    @Autowired
    ProductService productService;

    @Autowired
    PaymentService paymentService;

    public CustomerValidationResponse validateCustomerInfo(String customerName, String fullAddress) {
        CustomerValidationResponse customerValidationResponse = validateCustomerNameAndFullAddress(customerName, fullAddress);
        return customerValidationResponse;
    }

    public ProductValidationResponse validateProductInfo(String productName, Double price) {
        ProductValidationResponse productValidationResponse = validateProductNameAndPrice(productName, price);
        return productValidationResponse;
    }

    public PaymentValidationResponse validatePaymentType(String paymentType) {
        StringBuilder stringBuilder = new StringBuilder();
        Payment[] paymentArray = paymentService.getAllPaymentDetails();
        boolean isPaymentTypeMatch = false;
        Long paymentId = null;
        for (Payment payment : paymentArray) {
            if(payment.getPaymentType().equals(paymentType)) {
                isPaymentTypeMatch = true;
                paymentId = payment.getPaymentId();
                break;
            }
        }
        if(!isPaymentTypeMatch) {
            stringBuilder.append("Payment Type is InValid.<br/>");
        }
        PaymentValidationResponse paymentValidationResponse = new PaymentValidationResponse();
        if(stringBuilder.toString().isEmpty()) {
            paymentValidationResponse.setPaymentId(paymentId);
        } else {
            paymentValidationResponse.setErrorMessage(stringBuilder.toString());

        }

        return paymentValidationResponse;
    }

    private ProductValidationResponse validateProductNameAndPrice(String productName, Double price) {
        StringBuilder stringBuilder = new StringBuilder();
        Product[] productDetailsArray = productService.getProductDetailsByName(productName);
        Long productId = null;
        if(productDetailsArray.length == 0) {
            stringBuilder.append("Product Name is InValid.<br/>");
        } else {
            boolean isPriceMatch = false;
            for (Product product : productDetailsArray) {
                if(product.getPrice().equals(price)) {

                  isPriceMatch = true;
                  productId = product.getProductId();
                  break;
                }
            }
            if(!isPriceMatch) {
                stringBuilder.append("Product Price is InValid.<br/>");
            }
        }
        ProductValidationResponse productValidationResponse = new ProductValidationResponse();
        if(stringBuilder.toString().isEmpty()) {
            productValidationResponse.setProductId(productId);
        } else {
            productValidationResponse.setErrorMessage(stringBuilder.toString());

        }
        return productValidationResponse;
    }
    private CustomerValidationResponse validateCustomerNameAndFullAddress(String customerName, String fullAddress) {
        StringBuilder stringBuilder = new StringBuilder();

        CustomerDetailsForOrder[] customerData = customerService.getCustomerDetailsByName(customerName);

        Long addressId = null;
        Long customerId = null;
        String emailId = null;

        if(customerData.length == 0) {
            stringBuilder.append("Customer Name is InValid.<br/>");
        } else {
            boolean isAddressMatch = false;

            for (CustomerDetailsForOrder customer : customerData) {
                if(customer.getFullAddress().equals(fullAddress)) {
                    isAddressMatch = true;
                    addressId = customer.getAddressId();
                    customerId = customer.getCustomerId();
                    emailId = customer.getCustomerEmail();
                    break;
                }
            }
            if(!isAddressMatch) {
                stringBuilder.append("Customer Address is InValid.<br/>");
            }
        }
        CustomerValidationResponse customerValidationResponse = new CustomerValidationResponse();
        if(stringBuilder.toString().isEmpty()) {
            customerValidationResponse.setAddressId(addressId);
            customerValidationResponse.setCustomerId(customerId);
            customerValidationResponse.setEmailId(emailId);
        } else {
            customerValidationResponse.setErrorMessage(stringBuilder.toString());
        }
        return customerValidationResponse;
    }
}
