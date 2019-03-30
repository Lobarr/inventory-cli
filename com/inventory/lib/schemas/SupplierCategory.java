package com.inventory.lib.schemas;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.sql.PreparedStatement;

public class SupplierCategory {
  private String supplier_id;
  private String category_id;

  public SupplierCategory(String supplier_id, String category_id) {
    this.supplier_id = supplier_id;
    this.category_id = category_id;
  }

  public String getSupplier_id() {
    return supplier_id;
  }

  public void setSupplier_id(String supplier_id) {
    this.supplier_id = supplier_id;
  }

  public String getCategory_id() {
    return category_id;
  }

  public void setCategory_id(String category_id) {
    this.category_id = category_id;
  }  

  public static void createSupplierCategory(Connection conn, SupplierCategory sc) {
    try {
      String query = "INSERT INTO SupplierCategory (supplier_id, category_id) VALUES (?,?)";

      PreparedStatement stmt = conn.prepareStatement(query);
      stmt.setString(1, sc.getSupplier_id());
      stmt.setString(2, sc.getCategory_id());
      stmt.executeUpdate();
      System.out.println("Created supplier category");
    } catch (Exception e) {
      System.out.println("Error: Unable to create supplier category");
      System.out.println("Reason: " + e.getMessage());
    }
  }
  

  public static ArrayList<SupplierCategory> getSuppliercCagetoryBySupplier(Connection conn, String supplier_id) {
    try {
      String query = "SELECT * "
        + "FROM SupplierCategory "
        + "WHERE SupplierCategory.supplier_id = ? ";

      PreparedStatement stmt = conn.prepareStatement(query);
      stmt.setString(1, supplier_id);
      ResultSet rs = stmt.executeQuery();
      ArrayList<SupplierCategory> res = new ArrayList<>();
      while(rs.next()) {
        res.add(new SupplierCategory(rs.getString("supplier_id"), rs.getString("category_id")));
      }
      return res;
    } catch (Exception e) {
      System.out.println("Error: Unable to get supplier category by supplier");
      System.out.println("Reason: " + e.getMessage());
    }
    return null;
  }

  public static ArrayList<SupplierCategory> getSuppliercCagetoryByCategory(Connection conn, String category_id) {
    try {
      String query = "SELECT * "
        + "FROM ProductOrder "
        + "WHERE ProductOrder.category_id = ? ";

      PreparedStatement stmt = conn.prepareStatement(query);
      stmt.setString(1, category_id);
      ResultSet rs = stmt.executeQuery();
      ArrayList<SupplierCategory> res = new ArrayList<>();
      while(rs.next()) {
        res.add(new SupplierCategory(rs.getString("supplier_id"), rs.getString("category_id")));
      }
      return res;
    } catch (Exception e) {
      System.out.println("Error: Unable to get supplier category by category");
      System.out.println("Reason: " + e.getMessage());
    }
    return null;
  }

  public static void removeSupplierCategory(Connection conn, SupplierCategory sc) {
    try {
      String query = "DELETE FROM SupplierCategory WHERE category_id = ?, supplier_id = ?";

      PreparedStatement stmt = conn.prepareStatement(query);
      stmt.setString(1, sc.getCategory_id());
      stmt.setString(2, sc.getSupplier_id());
      stmt.executeUpdate();
      System.out.println("Deleted supplier category");
    } catch (Exception e) {
      System.out.println("Error: Unable to remove supplier category");
      System.out.println("Reason: " + e.getMessage());
    }
  }

  public static ArrayList<SupplierCategory> getAll(Connection conn) {
    try {
      String query = "SELECT * FROM SupplierCategory";

      Statement stmt = conn.createStatement();
      ResultSet rs = stmt.executeQuery(query);
      ArrayList<SupplierCategory> res = new ArrayList<>();
      while(rs.next()) {
        res.add(new SupplierCategory(rs.getString("supplier_id"), rs.getString("category_id")));
      }
      return res;
    } catch(Exception e) {
      System.out.println("Error: Unable to get supplier categories");
      System.out.println("Reason: " + e.getMessage());    
    }
    return null;
  }
}