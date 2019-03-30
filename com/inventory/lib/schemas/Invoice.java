package com.inventory.lib.schemas;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.sql.PreparedStatement;
import java.util.UUID;

public class Invoice {
  private String id;
  private String name;
  private String email;
  private float timestamp;

  public Invoice() {
  }
  
  public Invoice(String id, String name, String email, float timestamp) {
    this.id = id;
    this.name = name;
    this.email = email;
    this.timestamp = timestamp;
  }

  public Invoice (String name, String email, float timestamp) {
    UUID id = UUID.randomUUID();
    this.id = id.toString();
    this.name = name;
    this.email = email;
    this.timestamp = timestamp;
  }

  public String getId() {
    return this.id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public String getName() {
    return this.name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getEmail() {
    return this.email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public float getTimestamp() {
    return this.timestamp;
  }

  public void setTimestamp(float timestamp) {
    this.timestamp = timestamp;
  }

  public void print() {
    System.out.println("ID: " + this.getId());
    System.out.println("Name: " + this.getName());
    System.out.println("Email: " + this.getEmail());
    System.out.println("Timestamp: " + this.getTimestamp());
  }

  public static void createInvoice(Connection conn, Invoice inv) {
    try {
      String query = "INSERT INTO Invoice (id, name, email, timestamp) VALUES (?,?,?,?)";

      PreparedStatement stmt = conn.prepareStatement(query);
      stmt.setString(1, inv.getId());
      stmt.setString(2, inv.getName());
      stmt.setString(3, inv.getEmail());
      stmt.setString(4, inv.getId());
      stmt.executeUpdate();
      System.out.println("Created invoice " + inv.getId());
    } catch (Exception e) {
      System.out.println("Error: Unable to create invoice");
      System.out.println("Reason: " + e.getMessage());
    }
  }
  

  public static Invoice getInvoice(Connection conn, String id) {
    try {
      String query = "SELECT * "
        + "FROM Invoice "
        + "WHERE id = ? ";

      PreparedStatement stmt = conn.prepareStatement(query);
      stmt.setString(1, id);
      ResultSet rs = stmt.executeQuery();
      return new Invoice(rs.getString("id"), rs.getString("name"), rs.getString("email"), rs.getFloat("timestamp"));
    } catch (Exception e) {
      System.out.println("Error: Unable to get invoice");
      System.out.println("Reason: " + e.getMessage());
    }
    return null;
  }

  public static void updateInvoice(Connection conn, Invoice inv) {
    try {
      String query = "UPDATE Invoice "
        + "SET name = ?, email = ? "
        + "WHERE id = ? ";

      PreparedStatement stmt = conn.prepareStatement(query);
      stmt.setString(1, inv.getName());
      stmt.setString(2, inv.getEmail());
      stmt.setString(3, inv.getId());
      stmt.executeUpdate();
      System.out.println("Updated invoice " + inv.getId());
    } catch (Exception e) {
      System.out.println("Error: Unable to update invoice");
      System.out.println("Reason: " + e.getMessage());
    }
  }

  public static void removeInvoice(Connection conn, String id) {
    try {
      String query = "DELETE FROM Invoice WHERE id = ?";

      PreparedStatement stmt = conn.prepareStatement(query);
      stmt.setString(1, id);
      stmt.executeUpdate();
      System.out.println("Removed invoice - " + id);
    } catch (Exception e) {
      System.out.println("Error: Unable to remove invoice");
      System.out.println("Reason: " + e.getMessage());    }
  }

  public static ArrayList<Invoice> getAll(Connection conn) {
    try {
      String query = "SELECT * FROM Invoice";

      Statement stmt = conn.createStatement();
      ResultSet rs = stmt.executeQuery(query);
      ArrayList<Invoice> res = new ArrayList<Invoice>();
      while(rs.next()) {
        res.add(new Invoice(rs.getString("id"), rs.getString("name"), rs.getString("email"), rs.getFloat("timestamp")));
      }
      return res;
    } catch(Exception e) {
      System.out.println("Error: Unable to get invoices");
      System.out.println("Reason: " + e.getMessage());    
    }
    return null;
  }
}