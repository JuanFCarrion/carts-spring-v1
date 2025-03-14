package com.example.CartProject.DAO;



import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class Cart {


    private UUID id;

    private List<Product>products;

    private LocalDateTime creationTime;


    public Cart() {
        this.id = UUID.randomUUID();
        this.products =new ArrayList<>();
        this.creationTime = LocalDateTime.now();
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }

    public LocalDateTime getCreationTime() {
        return creationTime;
    }

    public void setCreationTime(LocalDateTime creationTime) {
        this.creationTime = creationTime;
    }

    public void addProduct(Product product) {
        this.creationTime = LocalDateTime.now();
        this.products.add(product);
    }


    @Override
    public String toString() {
        return "Cart{" +
                "id=" + id +
                ", products=" + products +
                '}';
    }



}
