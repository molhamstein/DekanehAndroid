package com.socket.dekaneh.network.model;

import java.util.List;

public class ProductsSection {

    private String header;
    private List<Product> products;

    public ProductsSection(String header, List<Product> products) {
        this.header = header;
        this.products = products;
    }

    public String getHeader() {
        return header;
    }

    public void setHeader(String header) {
        this.header = header;
    }

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }
}
