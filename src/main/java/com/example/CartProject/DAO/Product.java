package com.example.CartProject.DAO;



public class Product {
    private static int counter = 1;
    private int id;
    private String description;
    private int amount;


    public Product() {
        this.id =  counter++;
    }

    public Product( String description, int amount) {
        this.id =  counter++;
        this.description = description;
        this.amount = amount;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", Description='" + description + '\'' +
                ", amount=" + amount +
                '}';
    }
}
