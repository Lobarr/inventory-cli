package com.inventory.lib.schemas;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.sql.PreparedStatement;

public class ProductOrder {
  private String product_id;
  private String supplier_id;
  private int stock;

  public ProductOrder(String product_id, String supplier_id, int stock) {
    this.product_id = product_id;
    this.supplier_id = supplier_id;
    this.stock = stock;
  }

  public String getProduct_id() {
    return product_id;
  }

  public void setProduct_id(String product_id) {
    this.product_id = product_id;
  }

  public String getSupplier_id() {
    return supplier_id;
  }

  public void setSupplier_id(String supplier_id) {
    this.supplier_id = supplier_id;
  }

  public int getStock() {
    return stock;
  }

  public void setStock(int stock) {
    this.stock = stock;
  }

  public static void createProductOrder(Connection conn, ProductOrder po) {
    try {
      String query = "INSERT INTO ProductOrder (product_id, supplier_id, stock) VALUES (?,?,?)";

      PreparedStatement stmt = conn.prepareStatement(query);
      stmt.setString(1, po.getProduct_id());
      stmt.setString(2, po.getSupplier_id());
      stmt.setInt(3, po.getStock());
      stmt.executeUpdate();
      System.out.println("Created product order");
    } catch (Exception e) {
      System.out.println("Error: Unable to create product order");
      System.out.println("Reason: " + e.getMessage());
    }
  }
  

  public static ArrayList<ProductOrder> getProductOrdersByProduct(Connection conn, String product_id) {
    try {
      String query = "SELECT * "
        + "FROM ProductOrder "
        + "WHERE ProductOrder.product_id = ? ";

      PreparedStatement stmt = conn.prepareStatement(query);
      stmt.setString(1, product_id);
      ResultSet rs = stmt.executeQuery();
      ArrayList<ProductOrder> res = new ArrayList<>();
      while(rs.next()) {
        res.add(new ProductOrder(rs.getString("product_id"), rs.getString("supplier_id"), rs.getInt("stock")));
      }
      return res;
    } catch (Exception e) {
      System.out.println("Error: Unable to get product orders");
      System.out.println("Reason: " + e.getMessage());
    }
    return null;
  }

  public static ArrayList<ProductOrder> getProductOrdersBySupplier(Connection conn, String supplier_id) {
    try {
      String query = "SELECT * "
        + "FROM ProductOrder "
        + "WHERE ProductOrder.supplier_id = ? ";

      PreparedStatement stmt = conn.prepareStatement(query);
      stmt.setString(1, supplier_id);
      ResultSet rs = stmt.executeQuery();
      ArrayList<ProductOrder> res = new ArrayList<>();
      while(rs.next()) {
        res.add(new ProductOrder(rs.getString("product_id"), rs.getString("supplier_id"), rs.getInt("stock")));
      }
      return res;
    } catch (Exception e) {
      System.out.println("Error: Unable to get product orders");
      System.out.println("Reason: " + e.getMessage());
    }
    return null;
  }

  public static void removeProductOrder(Connection conn, ProductOrder po) {
    try {
      String query = "DELETE FROM ProductOrder WHERE product_id = ?, supplier_id = ?";

      PreparedStatement stmt = conn.prepareStatement(query);
      stmt.setString(1, po.getProduct_id());
      stmt.setString(2, po.getSupplier_id());
      stmt.executeUpdate();
      System.out.println("Removed product order");
    } catch (Exception e) {
      System.out.println("Error: Unable to remove product order");
      System.out.println("Reason: " + e.getMessage());
    }
  }

  public static ArrayList<ProductOrder> getAll(Connection conn) {
    try {
      String query = "SELECT * FROM ProductOrder";

      Statement stmt = conn.createStatement();
      ResultSet rs = stmt.executeQuery(query);
      ArrayList<ProductOrder> res = new ArrayList<>();
      while(rs.next()) {
        res.add(new ProductOrder(rs.getString("product_id"), rs.getString("supplier_id"), rs.getInt("stock")));
      }
      return res;
    } catch(Exception e) {
      System.out.println("Error: Unable to get product orders");
      System.out.println("Reason: " + e.getMessage());    
    }
    return null;
  }
}