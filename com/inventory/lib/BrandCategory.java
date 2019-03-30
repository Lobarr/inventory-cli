package com.inventory.lib;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.sql.PreparedStatement;

public class BrandCategory {
  private String brand_id;
  private String category_id;

  public BrandCategory(String brand_id, String category_id){
    this.brand_id = brand_id;
    this.category_id = category_id;
  }

  public String getBrand_id() {
    return brand_id;
  }

  public void setBrand_id(String brand_id) {
    this.brand_id = brand_id;
  }

  public String getCategory_id() {
    return category_id;
  }

  public void setCategory_id(String category_id) {
    this.category_id = category_id;
  }

  public static void createBrandCategory(Connection conn, BrandCategory bc) {
    try {
      String query = "INSERT INTO BrandCategory (brand_id, category_id) VALUES (?,?)";

      PreparedStatement stmt = conn.prepareStatement(query);
      stmt.setString(1, bc.getBrand_id());
      stmt.setString(2, bc.getCategory_id());
      stmt.executeUpdate();
      System.out.println("Created brand category");
    } catch (Exception e) {
      System.out.println("Error: Unable to create brand category");
      System.out.println("Reason: " + e.getMessage());
    }
  }
  

  public static ArrayList<BrandCategory> getBrandCategoriesByBrand(Connection conn, String brand_id) {
    try {
      String query = "SELECT * "
        + "FROM BrandCategory "
        + "WHERE brand_id = ? ";

      PreparedStatement stmt = conn.prepareStatement(query);
      stmt.setString(1, brand_id);
      ResultSet rs = stmt.executeQuery();
      ArrayList<BrandCategory> res = new ArrayList<>();
      while(rs.next()) {
        res.add(new BrandCategory(rs.getString("brand_id"), rs.getString("category_id")));
      }
    } catch (Exception e) {
      System.out.println("Error: Unable to get brand categories by brand");
      System.out.println("Reason: " + e.getMessage());
    }
    return null;
  }

  public static ArrayList<BrandCategory> getBrandCategoriesByCategory(Connection conn, String category_id) {
    try {
      String query = "SELECT * "
        + "FROM BrandCategory "
        + "WHERE category_id = ? ";

      PreparedStatement stmt = conn.prepareStatement(query);
      stmt.setString(1, category_id);
      ResultSet rs = stmt.executeQuery();
      ArrayList<BrandCategory> res = new ArrayList<>();
      while(rs.next()) {
        res.add(new BrandCategory(rs.getString("brand_id"), rs.getString("category_id")));
      }
    } catch (Exception e) {
      System.out.println("Error: Unable to get brand categories by brand");
      System.out.println("Reason: " + e.getMessage());
    }
    return null;
  }

  public static void removeBrandCategory(Connection conn, BrandCategory bc) {
    try {
      String query = "DELETE FROM BrandCategory WHERE brand_id = ?, category_id = ?";

      PreparedStatement stmt = conn.prepareStatement(query);
      stmt.setString(1, bc.getBrand_id());
      stmt.setString(2, bc.getCategory_id());
      stmt.executeUpdate();
      System.out.println("Deleted brand category");
    } catch (Exception e) {
      System.out.println("Error: Unable to remove brand category");
      System.out.println("Reason: " + e.getMessage());    
    }
  }

  public static ArrayList<BrandCategory> getAll(Connection conn) {
    try {
      String query = "SELECT * FROM BrandCategory";

      Statement stmt = conn.createStatement();
      ResultSet rs = stmt.executeQuery(query);
      ArrayList<BrandCategory> res = new ArrayList<>();
      while(rs.next()) {
        res.add(new BrandCategory(rs.getString("brand_id"), rs.getString("category_id")));
      }
      return res;
    } catch(Exception e) {
      System.out.println("Error: Unable to get all brand categories");
      System.out.println("Reason: " + e.getMessage());    
    }
    return null;
  }
}