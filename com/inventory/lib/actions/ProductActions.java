package com.inventory.lib.actions;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import com.inventory.lib.schemas.Product;

public class ProductActions {
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
      System.out.println("Deleted product - " + id);
    } catch (Exception e) {
      System.out.println("Error: Unable to delete product");
      System.out.println("Reason: " + e.getMessage());    
    }
  }

  public static ArrayList<Product> getAll(Connection conn) {
    try {
      String query = "SELECT * FROM Invoice";

      Statement stmt = conn.createStatement();
      ResultSet rs = stmt.executeQuery(query);
      ArrayList<Product> res = new ArrayList<Product>();
      while(rs.next()) {
        res.add(new Product(rs.getString("id"), rs.getString("name"), rs.getInt("stock"), rs.getFloat("price")));
      }
      return res;
    } catch(Exception e) {
      System.out.println("Error: Unable to get invoices");
      System.out.println("Reason: " + e.getMessage());    
    }
    return null;
  }
}