package com.inventory.lib;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.sql.PreparedStatement;
import java.util.UUID;

public class Shipping {
  private String id;
  private String address;

  public Shipping() {}

  public Shipping(String id, String address) {
    this.id = id;
    this.address = address;
  }

  public Shipping(String address) {
    UUID id = UUID.randomUUID();
    this.id = id.toString();
    this.address = address;
  }

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public String getAddress() {
    return address;
  }

  public void setAddress(String address) {
    this.address = address;
  }

  public static void createShipping(Connection conn, Shipping sh) {
    try {
      String query = "INSERT INTO Shipping (id, address) VALUES (?,?)";

      PreparedStatement stmt = conn.prepareStatement(query);
      stmt.setString(1, sh.getId());
      stmt.setString(2, sh.getAddress());
      stmt.executeUpdate();
      System.out.println("Created shipping " + sh.getId());
    } catch (Exception e) {
      System.out.println("Error: Unable to create shipping");
      System.out.println("Reason: " + e.getMessage());
    }
  }

  public static Shipping getShipping(Connection conn, String id) {
    try {
      String query = "SELECT * "
        + "FROM Shipping "
        + "WHERE id = ? ";

      PreparedStatement stmt = conn.prepareStatement(query);
      stmt.setString(1, id);
      ResultSet rs = stmt.executeQuery();
      return new Shipping(rs.getString("id"), rs.getString("address"));
    } catch (Exception e) {
      System.out.println("Error: Unable to get shipping");
      System.out.println("Reason: " + e.getMessage());
    }
    return null;
  }

  public static void updateShipping(Connection conn, Shipping sh) {
    try {
      String query = "UPDATE Shipping "
        + "SET address = ? "
        + "WHERE id = ? ";

      PreparedStatement stmt = conn.prepareStatement(query);
      stmt.setString(1, sh.getAddress());
      stmt.setString(2, sh.getId());
      stmt.executeUpdate();
      System.out.println("Updated shipping " + sh.getId());
    } catch (Exception e) {
      System.out.println("Error: Unable to update shipping");
      System.out.println("Reason: " + e.getMessage());
    }
  }

  public static void removeShipping(Connection conn, String id) {
    try {
      String query = "DELETE FROM Shipping WHERE id = ?";

      PreparedStatement stmt = conn.prepareStatement(query);
      stmt.setString(1, id);
      stmt.executeUpdate();
      System.out.println("Removed shipping - " + id);
    } catch (Exception e) {
      System.out.println("Error: Unable to remove shipping");
      System.out.println("Reason: " + e.getMessage());    
    }
  }

  public static ArrayList<Shipping> getAll(Connection conn) {
    try {
      String query = "SELECT * FROM Shipping";

      Statement stmt = conn.createStatement();
      ResultSet rs = stmt.executeQuery(query);
      ArrayList<Shipping> res = new ArrayList<Shipping>();
      while(rs.next()) {
        res.add(new Shipping(rs.getString("id"), rs.getString("address")));
      }
      return res;
    } catch(Exception e) {
      System.out.println("Error: Unable to get shippings");
      System.out.println("Reason: " + e.getMessage());    
    }
    return null;
  }
}