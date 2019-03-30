package com.inventory.lib;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.sql.PreparedStatement;
import java.util.UUID;

public class Category {
  private String id;
  private String name;

  public Category() {}

  public Category(String id, String name) {
    this.id = id;
    this.name = name;
  }

  public Category(String name) {
    UUID id = UUID.randomUUID();
    this.id = id.toString();
    this.name = name;
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

  public static void createCategory(Connection conn, Category cat) {
    try {
      String query = "INSERT INTO Category (id, name) VALUES (?,?)";

      PreparedStatement stmt = conn.prepareStatement(query);
      stmt.setString(1, cat.getId());
      stmt.setString(2, cat.getName());
      stmt.executeUpdate();
      System.out.println("Created category " + cat.getId());
    } catch (Exception e) {
      System.out.println("Error: Unable to create category");
      System.out.println("Reason: " + e.getMessage());
    }
  }
  

  public static Category getCategory(Connection conn, String id) {
    try {
      String query = "SELECT * "
        + "FROM Category "
        + "WHERE id = ? ";

      PreparedStatement stmt = conn.prepareStatement(query);
      stmt.setString(1, id);
      ResultSet rs = stmt.executeQuery();
      return new Category(rs.getString("id"), rs.getString("name"));
    } catch (Exception e) {
      System.out.println("Error: Unable to get category");
      System.out.println("Reason: " + e.getMessage());
    }
    return null;
  }

  public static void updateCategory(Connection conn, Category cat) {
    try {
      String query = "UPDATE Category "
        + "SET name = ? "
        + "WHERE id = ? ";

      PreparedStatement stmt = conn.prepareStatement(query);
      stmt.setString(1, cat.getName());
      stmt.setString(2, cat.getId());
      stmt.executeUpdate();
      System.out.println("Updated category " + cat.getId());
    } catch (Exception e) {
      System.out.println("Error: Unable to update category");
      System.out.println("Reason: " + e.getMessage());
    }
  }

  public static void removeCategory(Connection conn, String id) {
    try {
      String query = "DELETE FROM Category WHERE id = ?";

      PreparedStatement stmt = conn.prepareStatement(query);
      stmt.setString(1, id);
      stmt.executeUpdate();
      System.out.println("Deleted category - " + id);
    } catch (Exception e) {
      System.out.println("Error: Unable to remove category");
      System.out.println("Reason: " + e.getMessage());    }
  }

  public static ArrayList<Category> getAll(Connection conn) {
    try {
      String query = "SELECT * FROM Category";

      Statement stmt = conn.createStatement();
      ResultSet rs = stmt.executeQuery(query);
      ArrayList<Category> res = new ArrayList<Category>();
      while(rs.next()) {
        res.add(new Category(rs.getString("id"), rs.getString("name")));
      }
      return res;
    } catch(Exception e) {
      System.out.println("Error: Unable to get categories");
      System.out.println("Reason: " + e.getMessage());    
    }
    return null;
  }
}