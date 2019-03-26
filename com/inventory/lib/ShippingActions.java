package com.inventory.lib;

import com.inventory.lib.Shipping;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.sql.PreparedStatement;


public class ShippingActions {
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
      System.out.println("Deleted shipping - " + id);
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