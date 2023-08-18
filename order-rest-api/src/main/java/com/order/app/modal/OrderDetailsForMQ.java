package com.order.app.modal;

public class OrderDetailsForMQ {
    private String customerName;

    private String fullAddress;

    private String productName;

    private Double price;

    private String paymentType;

    private Long orderId;

    private Long transactionId;

    private String customerEmailId;

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getFullAddress() {
        return fullAddress;
    }

    public void setFullAddress(String fullAddress) {
        this.fullAddress = fullAddress;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public String getPaymentType() {
        return paymentType;
    }

    public void setPaymentType(String paymentType) {
        this.paymentType = paymentType;
    }

    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    public Long getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(Long transactionId) {
        this.transactionId = transactionId;
    }

    public String getCustomerEmailId() {
        return customerEmailId;
    }

    public void setCustomerEmailId(String customerEmailId) {
        this.customerEmailId = customerEmailId;
    }

    public OrderDetailsForMQ(String customerName, String fullAddress, String productName, Double price, String paymentType, Long orderId, Long transactionId, String customerEmailId) {
        this.customerName = customerName;
        this.fullAddress = fullAddress;
        this.productName = productName;
        this.price = price;
        this.paymentType = paymentType;
        this.orderId = orderId;
        this.transactionId = transactionId;
        this.customerEmailId = customerEmailId;
    }
}
