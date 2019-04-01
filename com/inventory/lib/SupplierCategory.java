package com.inventory.lib;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Scanner;
import java.sql.PreparedStatement;

public class SupplierCategory {
  private String supplier_id;
  private String category_id;

  public SupplierCategory(String supplier_id, String category_id) {
    this.supplier_id = supplier_id;
    this.category_id = category_id;
  }

  public String getSupplier_id() {
    return supplier_id;
  }

  public void setSupplier_id(String supplier_id) {
    this.supplier_id = supplier_id;
  }

  public String getCategory_id() {
    return category_id;
  }

  public void setCategory_id(String category_id) {
    this.category_id = category_id;
  }  

  public void print() {
    System.out.println("Supplier ID:\t" + this.getSupplier_id());
    System.out.println("Category ID:\t" + this.getCategory_id());
    System.out.println();
  }

  public static void printMany(ArrayList<SupplierCategory> sc) {
    System.out.println("*** SupplierCategories ***");
    sc.forEach(s -> s.print());
  }

  public static void createSupplierCategory(Connection conn, SupplierCategory sc) {
    try {
      String query = "INSERT INTO SupplierCategory (supplier_id, category_id) VALUES (?,?)";

      PreparedStatement stmt = conn.prepareStatement(query);
      stmt.setString(1, sc.getSupplier_id());
      stmt.setString(2, sc.getCategory_id());
      stmt.executeUpdate();
      System.out.println("Created supplier category");
    } catch (Exception e) {
      System.out.println("Error: Unable to create supplier category");
      System.out.println("Reason: " + e.getMessage());
    }
  }
  

  public static ArrayList<SupplierCategory> getSupplierCagetoryBySupplier(Connection conn, String supplier_id) {
    try {
      String query = "SELECT * "
        + "FROM SupplierCategory "
        + "WHERE SupplierCategory.supplier_id = ? ";

      PreparedStatement stmt = conn.prepareStatement(query);
      stmt.setString(1, supplier_id);
      ResultSet rs = stmt.executeQuery();
      ArrayList<SupplierCategory> res = new ArrayList<>();
      while(rs.next()) {
        res.add(new SupplierCategory(rs.getString("supplier_id"), rs.getString("category_id")));
      }
      return res;
    } catch (Exception e) {
      System.out.println("Error: Unable to get supplier category by supplier");
      System.out.println("Reason: " + e.getMessage());
    }
    return null;
  }

  public static ArrayList<SupplierCategory> getSupplierCagetoryByCategory(Connection conn, String category_id) {
    try {
      String query = "SELECT * "
        + "FROM ProductOrder "
        + "WHERE ProductOrder.category_id = ? ";

      PreparedStatement stmt = conn.prepareStatement(query);
      stmt.setString(1, category_id);
      ResultSet rs = stmt.executeQuery();
      ArrayList<SupplierCategory> res = new ArrayList<>();
      while(rs.next()) {
        res.add(new SupplierCategory(rs.getString("supplier_id"), rs.getString("category_id")));
      }
      return res;
    } catch (Exception e) {
      System.out.println("Error: Unable to get supplier category by category");
      System.out.println("Reason: " + e.getMessage());
    }
    return null;
  }

  public static void removeSupplierCategory(Connection conn, SupplierCategory sc) {
    try {
      String query = "DELETE FROM SupplierCategory WHERE category_id = ? and supplier_id = ?";

      PreparedStatement stmt = conn.prepareStatement(query);
      stmt.setString(1, sc.getCategory_id());
      stmt.setString(2, sc.getSupplier_id());
      stmt.executeUpdate();
      System.out.println("Deleted supplier category");
    } catch (Exception e) {
      System.out.println("Error: Unable to remove supplier category");
      System.out.println("Reason: " + e.getMessage());
    }
  }

  public static ArrayList<SupplierCategory> getAll(Connection conn) {
    try {
      String query = "SELECT * FROM SupplierCategory";

      Statement stmt = conn.createStatement();
      ResultSet rs = stmt.executeQuery(query);
      ArrayList<SupplierCategory> res = new ArrayList<>();
      while(rs.next()) {
        res.add(new SupplierCategory(rs.getString("supplier_id"), rs.getString("category_id")));
      }
      return res;
    } catch(Exception e) {
      System.out.println("Error: Unable to get supplier categories");
      System.out.println("Reason: " + e.getMessage());    
    }
    return null;
  }

  public static void menu(Connection conn) {
    System.out.println("*** SupplierCategory Menu ***");
    System.out.println("1 - CREATE");
    System.out.println("2 - GET");
    System.out.println("3 - REMOVE");
    System.out.println("8 - BACK\n");

    
    Scanner scanner = new Scanner(System.in);
    System.out.print("> ");

    int option = scanner.nextInt();
    switch (option) {
      case 1:
        SupplierCategory.createMenu(conn);
        SupplierCategory.menu(conn);
        break;
      case 2:
        SupplierCategory.getMenu(conn);
        SupplierCategory.menu(conn);
        break;
      case 3:
        SupplierCategory.removeMenu(conn);
        SupplierCategory.menu(conn);
      case 8:
        break;
      default: 
        SupplierCategory.menu(conn);
        break;
    }
  }

  public static void createMenu(Connection conn) {
    String supplier_id;
    String category_id;
    Scanner scanner = new Scanner(System.in);

    System.out.println("Supplier ID");
    System.out.print("> ");
    supplier_id = scanner.next();

    System.out.println("Category ID");
    System.out.print("> ");
    category_id = scanner.next();

    SupplierCategory input = new SupplierCategory(supplier_id, category_id);
    SupplierCategory.createSupplierCategory(conn, input);
  }

  public static void getMenu(Connection conn) {
    System.out.println("*** Get SupplierCategory ***");
    System.out.println("1 - BY SUPPLIER");
    System.out.println("2 - BY CATEGORY");
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
        ArrayList<SupplierCategory> sc_supplier = SupplierCategory.getSupplierCagetoryBySupplier(conn, supplier);
        SupplierCategory.printMany(sc_supplier);
        SupplierCategory.getMenu(conn);
        break;
      case 2:
        String category;
        System.out.print("> ");
        category = scanner.next();
        ArrayList<SupplierCategory> sc_category = SupplierCategory.getSupplierCagetoryByCategory(conn, category);
        SupplierCategory.printMany(sc_category);
        SupplierCategory.getMenu(conn);
        break;
      case 3:
        ArrayList<SupplierCategory> sc_all = SupplierCategory.getAll(conn);
        SupplierCategory.printMany(sc_all);
        SupplierCategory.getMenu(conn);
        break;
      case 8:
        break;
      default:
        SupplierCategory.getMenu(conn);
        break;
    }
  }

  public static void removeMenu(Connection conn){
    String supplier_id;
    String category_id;
    Scanner scanner = new Scanner(System.in);

    System.out.println("Supplier ID");
    System.out.print("> ");
    supplier_id = scanner.next();

    System.out.println("Category ID");
    System.out.print("> ");
    category_id = scanner.next();

    Supplies input = new Supplies(supplier_id, category_id);
    Supplies.removeSupplies((conn), input);
  }
}