package com.inventory.lib.schemas;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
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
  
  public static void createProduct(Connection conn, Product prod) {
    try {
      String query = "INSERT INTO Product (id, name, stock, price) VALUES (?,?,?,?)";

      PreparedStatement stmt = conn.prepareStatement(query);
      stmt.setString(1, prod.getId());
      stmt.setString(2, prod.getName());
      stmt.setInt(3, prod.getStock());
      stmt.setFloat(4, prod.getPrice());
      stmt.executeUpdate();
      System.out.println("Created product " + prod.getId());
    } catch (Exception e) {
      System.out.println("Error: Unable to create product");
      System.out.println("Reason: " + e.getMessage());
    }
  }

  public static Product getProduct(Connection conn, String id) {
    try {
      String query = "SELECT * "
        + "FROM Product "
        + "WHERE id = ?";
      
        PreparedStatement stmt = conn.prepareStatement(query);
        stmt.setString(1, id);
        ResultSet rs = stmt.executeQuery();
        return new Product(rs.getString("id"), rs.getString("name"), rs.getInt("stock"), rs.getFloat("price"));
    } catch(Exception e) {
      System.out.println("Error: Unable to get product");
      System.out.println("Reason: " + e.getMessage());
    }
    return null;
  }

  public static void updateProduct(Connection conn, Product prod) {
    try {
      String query = "UPDATE Product "
        + "SET name = ?, stock = ?, price = ? "
        + "WHERE id = ? ";

      PreparedStatement stmt = conn.prepareStatement(query);
      stmt.setString(1, prod.getName());
      stmt.setInt(2, prod.getStock());
      stmt.setFloat(3, prod.getPrice());
      stmt.setString(4, prod.getId());
      System.out.println("Updated product - " + prod.getId());
    } catch (Exception e) {
      System.out.println("Error: Unable to update product");
      System.out.println("Reason: " + e.getMessage());
    }
  }
  
  public static void removeProduct(Connection conn, String id) {
    try {
      String query = "DELETE FROM Product WHERE id = ?";

      PreparedStatement stmt = conn.prepareStatement(query);
      stmt.setString(1, id);
      stmt.executeUpdate();
      System.out.println("Remove product - " + id);
    } catch (Exception e) {
      System.out.println("Error: Unable to remove product");
      System.out.println("Reason: " + e.getMessage());    
    }
  }

  public static ArrayList<Product> getAll(Connection conn) {
    try {
      String query = "SELECT * FROM Product";

      Statement stmt = conn.createStatement();
      ResultSet rs = stmt.executeQuery(query);
      ArrayList<Product> res = new ArrayList<Product>();
      while(rs.next()) {
        res.add(new Product(rs.getString("id"), rs.getString("name"), rs.getInt("stock"), rs.getFloat("price")));
      }
      return res;
    } catch(Exception e) {
      System.out.println("Error: Unable to get products");
      System.out.println("Reason: " + e.getMessage());    
    }
    return null;
  }
}