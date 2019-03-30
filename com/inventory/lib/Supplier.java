package com.inventory.lib;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.sql.PreparedStatement;
import java.util.UUID;

public class Supplier {
  private String id;
  private String name;
  private String email;

  public Supplier(){}

  public Supplier(String id, String name, String email) {
    this.id = id;
    this.name = name;
    this.email = email;
  }

  public Supplier(String name, String email) {
    UUID id = UUID.randomUUID();
    this.id = id.toString();
    this.name = name;
    this.email = email;
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

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public static void createSupplier(Connection conn, Supplier su) {
    try {
      String query = "INSERT INTO Supplier (id, name, email) VALUES (?,?, ?)";

      PreparedStatement stmt = conn.prepareStatement(query);
      stmt.setString(1, su.getId());
      stmt.setString(2, su.getName());
      stmt.setString(3, su.getEmail());
      stmt.executeUpdate();
      System.out.println("Created supplier " + su.getId());
    } catch (Exception e) {
      System.out.println("Error: Unable to create supplier");
      System.out.println("Reason: " + e.getMessage());
    }
  }
  

  public static Supplier getSupplier(Connection conn, String id) {
    try {
      String query = "SELECT * "
        + "FROM Supplier "
        + "WHERE id = ? ";

      PreparedStatement stmt = conn.prepareStatement(query);
      stmt.setString(1, id);
      ResultSet rs = stmt.executeQuery();
      return new Supplier(rs.getString("id"), rs.getString("name"), rs.getString("email"));
    } catch (Exception e) {
      System.out.println("Error: Unable to get supplier");
      System.out.println("Reason: " + e.getMessage());
    }
    return null;
  }

  public static void updateSupplier(Connection conn, Supplier su) {
    try {
      String query = "UPDATE Supplier "
        + "SET name = ?, email = ? "
        + "WHERE id = ? ";

      PreparedStatement stmt = conn.prepareStatement(query);
      stmt.setString(1, su.getName());
      stmt.setString(2, su.getEmail());
      stmt.setString(3, su.getId());
      stmt.executeUpdate();
      System.out.println("Updated supplier " + su.getId());
    } catch (Exception e) {
      System.out.println("Error: Unable to update supplier");
      System.out.println("Reason: " + e.getMessage());
    }
  }

  public static void removeSupplier(Connection conn, String id) {
    try {
      String query = "DELETE FROM Supplier WHERE id = ?";

      PreparedStatement stmt = conn.prepareStatement(query);
      stmt.setString(1, id);
      stmt.executeUpdate();
      System.out.println("Removedr supplier - " + id);
    } catch (Exception e) {
      System.out.println("Error: Unable to remove supplier");
      System.out.println("Reason: " + e.getMessage());    
    }
  }

  public static ArrayList<Supplier> getAll(Connection conn) {
    try {
      String query = "SELECT * FROM Supplier";

      Statement stmt = conn.createStatement();
      ResultSet rs = stmt.executeQuery(query);
      ArrayList<Supplier> res = new ArrayList<Supplier>();
      while(rs.next()) {
        res.add(new Supplier(rs.getString("id"), rs.getString("name"), rs.getString("email")));
      }
      return res;
    } catch(Exception e) {
      System.out.println("Error: Unable to get suppliers");
      System.out.println("Reason: " + e.getMessage());    
    }
    return null;
  }
}