package com.inventory.lib.actions.relationahips;

import com.inventory.lib.schemas.relationships.BrandCategory;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.sql.PreparedStatement;


public class BrandCategoryActions {
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
  

  public static ArrayList<BrandCategory> getBrandCategories(Connection conn, String brand_id) {
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
      System.out.println("Error: Unable to get brand categories");
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
      System.out.println("Error: Unable to delete brand category");
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