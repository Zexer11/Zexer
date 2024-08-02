package com.example.leez;

public class Product {
    private String barcode;
    private ProductInfo productInfo;

    public Product(String barcode, ProductInfo productInfo) {
        this.barcode = barcode;
        this.productInfo = productInfo;
    }

    public String getBarcode() {
        return barcode;
    }

    public ProductInfo getProductInfo() {
        return productInfo;
    }
}
