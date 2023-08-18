package com.order.app.modal;

public class Product {

    private Long productId;

    private String productName;

    private Double price;

    private int discount;

    private boolean isActiveProduct;

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
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

    public int getDiscount() {
        return discount;
    }

    public void setDiscount(int discount) {
        this.discount = discount;
    }

    public boolean isActiveProduct() {
        return isActiveProduct;
    }

    public void setActiveProduct(boolean activeProduct) {
        isActiveProduct = activeProduct;
    }
}
