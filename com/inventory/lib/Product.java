package com.inventory.lib;

import java.util.UUID;

public class Product {
  private String id;
  private String name;
  private int stock;
  private float price;

  public Product(){}

  public Product(String id, String name, int stock, float price) {
    this.id = id;
    this.name = name;
    this.stock = stock;
    this.price = price;
  }

  public Product(String name, int stock, float price) {
    UUID id = UUID.randomUUID();
    this.id = id.toString();
    this.name = name;
    this.stock = stock;
    this.price = price;
  }

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public int getStock() {
    return stock;
  }

  public void setStock(int stock) {
    this.stock = stock;
  }

  public float getPrice() {
    return price;
  }

  public void setPrice(float price) {
    this.price = price;
  }
  
}