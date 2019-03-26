package com.inventory.lib;

import com.inventory.lib.Supplier;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.sql.PreparedStatement;


public class SupplierActions {
  public static void createSupplier(Connection conn, Supplier su) {
    try {
      String query = "INSERT INTO Supplier (id, name, email) VALUES (?,?)";

      PreparedStatement stmt = conn.prepareStatement(query);
      stmt.setString(1, br.getId());
      stmt.setString(2, br.getName());
      stmt.executeUpdate();
      System.out.println("Created brand " + br.getId());
    } catch (Exception e) {
      System.out.println("Error: Unable to create brand");
      System.out.println("Reason: " + e.getMessage());
    }
  }
  

  public static Brand getBrand(Connection conn, String id) {
    try {
      String query = "SELECT * "
        + "FROM Brand "
        + "WHERE id = ? ";

      PreparedStatement stmt = conn.prepareStatement(query);
      stmt.setString(1, id);
      ResultSet rs = stmt.executeQuery();
      return new Brand(rs.getString("id"), rs.getString("name"));
    } catch (Exception e) {
      System.out.println("Error: Unable to get brand");
      System.out.println("Reason: " + e.getMessage());
    }
    return null;
  }

  public static void updateBrand(Connection conn, Brand br) {
    try {
      String query = "UPDATE Brand "
        + "SET name = ? "
        + "WHERE id = ? ";

      PreparedStatement stmt = conn.prepareStatement(query);
      stmt.setString(1, br.getName());
      stmt.setString(2, br.getId());
      stmt.executeUpdate();
      System.out.println("Updated brand " + br.getId());
    } catch (Exception e) {
      System.out.println("Error: Unable to update brand");
      System.out.println("Reason: " + e.getMessage());
    }
  }

  public static void removeBrand(Connection conn, String id) {
    try {
      String query = "DELETE FROM Brand WHERE id = ?";

      PreparedStatement stmt = conn.prepareStatement(query);
      stmt.setString(1, id);
      stmt.executeUpdate();
      System.out.println("Deleted brand - " + id);
    } catch (Exception e) {
      System.out.println("Error: Unable to remove brand");
      System.out.println("Reason: " + e.getMessage());    }
  }

  public static ArrayList<Brand> getAll(Connection conn) {
    try {
      String query = "SELECT * FROM Brand";

      Statement stmt = conn.createStatement();
      ResultSet rs = stmt.executeQuery(query);
      ArrayList<Brand> res = new ArrayList<Brand>();
      while(rs.next()) {
        res.add(new Brand(rs.getString("id"), rs.getString("name")));
      }
      return res;
    } catch(Exception e) {
      System.out.println("Error: Unable to get brands");
      System.out.println("Reason: " + e.getMessage());    
    }
    return null;
  }
}