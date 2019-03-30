package com.inventory.lib;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Scanner;
import java.sql.PreparedStatement;

// public enum SuppliesOptions {
//   CREATE,
//   GET,
//   REMOVE
// }

// public enum SuppliesGetOptions {
//   ALL,
//   BYSUPPLIER,
//   BYPRODUCT,
// }

public class Supplies {
  private String supplier_id;
  private String product_id;

  public Supplies(String supplier_id, String product_id) {
    this.supplier_id = supplier_id;
    this.product_id = product_id;
  }

  public String getSupplier_id() {
    return supplier_id;
  }

  public void setSupplier_id(String supplier_id) {
    this.supplier_id = supplier_id;
  }

  public String getProduct_id() {
    return product_id;
  }

  public void setProduct_id(String product_id) {
    this.product_id = product_id;
  }

  public static void createSuppies(Connection conn, Supplies s) {
    try {
      String query = "INSERT INTO Supplies (supplier_id, product_id) VALUES (?,?)";

      PreparedStatement stmt = conn.prepareStatement(query);
      stmt.setString(1, s.getSupplier_id());
      stmt.setString(2, s.getProduct_id());
      stmt.executeUpdate();
      System.out.println("Create supplies");
    } catch (Exception e) {
      System.out.println("Error: Unable to create supplies");
      System.out.println("Reason: " + e.getMessage());
    }
  }

  public static ArrayList<Supplies> getSuppliesBySupplier(Connection conn, String supplier_id) {
    try {
      String query = "SELECT * "
        + "FROM Supplies "
        + "WHERE Supplies.supplier_id = ? ";

      PreparedStatement stmt = conn.prepareStatement(query);
      stmt.setString(1, supplier_id);
      ResultSet rs = stmt.executeQuery();
      ArrayList<Supplies> res = new ArrayList<>();
      while(rs.next()) {
        res.add(new Supplies(rs.getString("supplier_id"), rs.getString("product_id")));
      }
      return res;
    } catch (Exception e) {
      System.out.println("Error: Unable to get supplies by supplier");
      System.out.println("Reason: " + e.getMessage());
    }
    return null;
  }

  public static ArrayList<Supplies> getSuppliesyByProduct(Connection conn, String product_id) {
    try {
      String query = "SELECT * "
        + "FROM Supplies "
        + "WHERE Supplies.product_id = ? ";

      PreparedStatement stmt = conn.prepareStatement(query);
      stmt.setString(1, product_id);
      ResultSet rs = stmt.executeQuery();
      ArrayList<Supplies> res = new ArrayList<>();
      while(rs.next()) {
        res.add(new Supplies(rs.getString("supplier_id"), rs.getString("product_id")));
      }
      return res;
    } catch (Exception e) {
      System.out.println("Error: Unable to get supplies by product");
      System.out.println("Reason: " + e.getMessage());
    }
    return null;
  }

  public static void removeSupplies(Connection conn, Supplies s) {
    try {
      String query = "DELETE FROM Supplies WHERE product_id = ?, supplier_id = ?";

      PreparedStatement stmt = conn.prepareStatement(query);
      stmt.setString(1, s.getProduct_id());
      stmt.setString(2, s.getSupplier_id());
      stmt.executeUpdate();
      System.out.println("Deleted supplies");
    } catch (Exception e) {
      System.out.println("Error: Unable to delete supplies");
      System.out.println("Reason: " + e.getMessage());
    }
  }

  public static ArrayList<Supplies> getAll(Connection conn) {
    try {
      String query = "SELECT * FROM Supplies";

      Statement stmt = conn.createStatement();
      ResultSet rs = stmt.executeQuery(query);
      ArrayList<Supplies> res = new ArrayList<>();
      while(rs.next()) {
        res.add(new Supplies(rs.getString("supplier_id"), rs.getString("product_id")));
      }
      return res;
    } catch(Exception e) {
      System.out.println("Error: Unable to get supplies");
      System.out.println("Reason: " + e.getMessage());    
    }
    return null;
  }

  // public static void menu() {
  //   System.out.println("*** Supplies Menu *** \n");
  //   System.out.println("- Type input");
  //   System.out.println("1. CREATE");
  //   System.out.println("2. GET");
  //   System.out.println("3. REMOVE");

  //   Scanner scanner = new Scanner(System.in);
  //   System.out.println("> ");

  //   int option = scanner.nextInt();
  //   switch (option) {
  //     case 1:

  //       break;
  //   }
  // }
}