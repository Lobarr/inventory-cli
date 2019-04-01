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

  public void print() {
    System.out.println("Product ID:\t" + this.getProduct_id());
    System.out.println("Supplier ID:\t" + this.getSupplier_id());
    System.out.println();
  }

  public static void printMany(ArrayList<Supplies> supplies) {
    System.out.println("*** Supplies ***");
    supplies.forEach(supply -> supply.print());
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
      String query = "DELETE FROM Supplies WHERE product_id = ? and supplier_id = ?";

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

  public static void menu(Connection conn) {
    System.out.println("*** Supplies Menu *** \n");
    System.out.println("1 - CREATE");
    System.out.println("2 - GET");
    System.out.println("3 - REMOVE");
    System.out.println("8 - BACK\n");

    Scanner scanner = new Scanner(System.in);
    System.out.print("> ");

    int option = scanner.nextInt();
    switch (option) {
      case 1:
        Supplies.createMenu(conn);
        Supplies.menu(conn);
        break;
      case 2:
        Supplies.getMenu(conn);
        Supplies.menu(conn);
        break;
      case 3:
        Supplies.removeMenu(conn);
        Supplies.menu(conn);
      case 8:
        break;
      default: 
        Supplies.menu(conn);
        break;
    }
  }

  public static void createMenu(Connection conn) {
    String supplier_id;
    String product_id;
    Scanner scanner = new Scanner(System.in);

    System.out.println("Supplier ID");
    System.out.print("> ");
    supplier_id = scanner.next();

    System.out.println("Product ID");
    System.out.print("> ");
    product_id = scanner.next();

    Supplies input = new Supplies(supplier_id, product_id);
    Supplies.createSuppies(conn, input);
  }

  public static void getMenu(Connection conn) {
    System.out.println("*** Get Supplies ***");
    System.out.println("1 - BY SUPPLIER");
    System.out.println("2 - BY PRODUCT");
    System.out.println("3 - ALL");
    System.out.println("8 - BACK");

    int option;
    Scanner scanner = new Scanner(System.in);

    System.out.print(">");
    option = scanner.nextInt();

    switch(option){
      case 1:
        String supplier;
        System.out.print("> ");
        supplier = scanner.next();
        ArrayList<Supplies> supplies_supplier = Supplies.getSuppliesBySupplier(conn, supplier);
        Supplies.printMany(supplies_supplier);
        Supplies.getMenu(conn);
        break;
      case 2:
        String product;
        System.out.print("> ");
        product = scanner.next();
        ArrayList<Supplies> supplies_product = Supplies.getSuppliesyByProduct(conn, product);
        Supplies.printMany(supplies_product);
        Supplies.getMenu(conn);
        break;
      case 3:
        ArrayList<Supplies> supplies_all = Supplies.getAll(conn);
        Supplies.printMany(supplies_all);
        Supplies.getMenu(conn);
        break;
      case 8:
        break;
      default:
        Supplies.getMenu(conn);
        break;
    }
  }

  public static void removeMenu(Connection conn){
    String supplier_id;
    String product_id;
    Scanner scanner = new Scanner(System.in);

    System.out.println("Supplier ID");
    System.out.print("> ");
    supplier_id = scanner.next();

    System.out.println("Product ID");
    System.out.print("> ");
    product_id = scanner.next();

    Supplies input = new Supplies(supplier_id, product_id);
    Supplies.removeSupplies((conn), input);
  }
}