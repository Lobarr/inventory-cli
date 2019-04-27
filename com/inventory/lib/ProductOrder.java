package com.inventory.lib;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Scanner;
import java.sql.PreparedStatement;

public class ProductOrder {
  private String product_id;
  private String supplier_id;
  private int stock;

  public ProductOrder(String product_id, String supplier_id) {
    this.product_id = product_id;
    this.supplier_id = supplier_id;
  }
  public ProductOrder(String product_id, String supplier_id, int stock) {
    this.product_id = product_id;
    this.supplier_id = supplier_id;
    this.stock = stock;
  }

  public String getProduct_id() {
    return product_id;
  }

  public void setProduct_id(String product_id) {
    this.product_id = product_id;
  }

  public String getSupplier_id() {
    return supplier_id;
  }

  public void setSupplier_id(String supplier_id) {
    this.supplier_id = supplier_id;
  }

  public int getStock() {
    return stock;
  }

  public void setStock(int stock) {
    this.stock = stock;
  }

  public void print() {
    System.out.println("Product ID:\t" + this.getProduct_id());
    System.out.println("Supplier ID:\t" + this.getSupplier_id());
    System.out.println("Stock:\t" + this.getStock());
    System.out.println();
  }

  public static void printMany(ArrayList<ProductOrder> po) {
    System.out.println("*** ProductOrders ***");
    po.forEach(productOrder -> productOrder.print());
  }


  public static void createProductOrder(Connection conn, ProductOrder po) {
    try {
      String query = "INSERT INTO ProductOrder (product_id, supplier_id, stock) VALUES (?,?,?)";

      PreparedStatement stmt = conn.prepareStatement(query);
      stmt.setString(1, po.getProduct_id());
      stmt.setString(2, po.getSupplier_id());
      stmt.setInt(3, po.getStock());
      stmt.executeUpdate();
      System.out.println("Created product order");
    } catch (Exception e) {
      System.out.println("Error: Unable to create product order");
      System.out.println("Reason: " + e.getMessage());
    }
  }
  

  public static ArrayList<ProductOrder> getProductOrdersByProduct(Connection conn, String product_id) {
    try {
      String query = "SELECT * "
        + "FROM ProductOrder "
        + "WHERE ProductOrder.product_id = ? ";

      PreparedStatement stmt = conn.prepareStatement(query);
      stmt.setString(1, product_id);
      ResultSet rs = stmt.executeQuery();
      ArrayList<ProductOrder> res = new ArrayList<>();
      while(rs.next()) {
        res.add(new ProductOrder(rs.getString("product_id"), rs.getString("supplier_id"), rs.getInt("stock")));
      }
      return res;
    } catch (Exception e) {
      System.out.println("Error: Unable to get product orders");
      System.out.println("Reason: " + e.getMessage());
    }
    return null;
  }

  public static ArrayList<ProductOrder> getProductOrdersBySupplier(Connection conn, String supplier_id) {
    try {
      String query = "SELECT * "
        + "FROM ProductOrder "
        + "WHERE ProductOrder.supplier_id = ? ";

      PreparedStatement stmt = conn.prepareStatement(query);
      stmt.setString(1, supplier_id);
      ResultSet rs = stmt.executeQuery();
      ArrayList<ProductOrder> res = new ArrayList<>();
      while(rs.next()) {
        res.add(new ProductOrder(rs.getString("product_id"), rs.getString("supplier_id"), rs.getInt("stock")));
      }
      return res;
    } catch (Exception e) {
      System.out.println("Error: Unable to get product orders");
      System.out.println("Reason: " + e.getMessage());
    }
    return null;
  }

  public static void removeProductOrder(Connection conn, ProductOrder po) {
    try {
      String query = "DELETE FROM ProductOrder WHERE product_id = ?, supplier_id = ?";

      PreparedStatement stmt = conn.prepareStatement(query);
      stmt.setString(1, po.getProduct_id());
      stmt.setString(2, po.getSupplier_id());
      stmt.executeUpdate();
      System.out.println("Removed product order");
    } catch (Exception e) {
      System.out.println("Error: Unable to remove product order");
      System.out.println("Reason: " + e.getMessage());
    }
  }

  public static ArrayList<ProductOrder> getAll(Connection conn) {
    try {
      String query = "SELECT * FROM ProductOrder";

      Statement stmt = conn.createStatement();
      ResultSet rs = stmt.executeQuery(query);
      ArrayList<ProductOrder> res = new ArrayList<>();
      while(rs.next()) {
        res.add(new ProductOrder(rs.getString("product_id"), rs.getString("supplier_id"), rs.getInt("stock")));
      }
      return res;
    } catch(Exception e) {
      System.out.println("Error: Unable to get product orders");
      System.out.println("Reason: " + e.getMessage());    
    }
    return null;
  }

  public static void menu(Connection conn) {
    System.out.println("*** ProductOrder Menu *** \n");
    System.out.println("1 - CREATE");
    System.out.println("2 - GET");
    System.out.println("3 - REMOVE");
    System.out.println("8 - BACK\n");

    Scanner scanner = new Scanner(System.in);
    System.out.print("> ");

    int option = scanner.nextInt();
    scanner.close();
    switch (option) {
      case 1:
        ProductOrder.createMenu(conn);
        ProductOrder.menu(conn);
        break;
      case 2:
        ProductOrder.getMenu(conn);
        ProductOrder.menu(conn);
        break;
      case 3:
        ProductOrder.removeMenu(conn);
        ProductOrder.menu(conn);
      case 8:
        break;
      default: 
        ProductOrder.menu(conn);
        break;
    }
  }

  public static void createMenu(Connection conn) {
    String supplier_id;
    String product_id;
    int stock;
    Scanner scanner = new Scanner(System.in);

    System.out.println("Supplier ID");
    System.out.print("> ");
    supplier_id = scanner.next();

    System.out.println("Product ID");
    System.out.print("> ");
    product_id = scanner.next();

    System.out.println("Stock");
    System.out.print("> ");
    stock = scanner.nextInt();
    scanner.close();

    ProductOrder input = new ProductOrder(product_id, supplier_id, stock);
    ProductOrder.createProductOrder(conn, input);
  }

  public static void getMenu(Connection conn) {
    System.out.println("*** Get Supplies ***");
    System.out.println("1 - BY SUPPLIER");
    System.out.println("2 - BY PRODUCT");
    System.out.println("3 - ALL");
    System.out.println("8 - BACK");

    int option;
    Scanner scanner = new Scanner(System.in);

    System.out.print("> ");
    option = scanner.nextInt();
    scanner.close();

    switch(option){
      case 1:
        String supplier;
        System.out.print("> ");
        supplier = scanner.next();
        ArrayList<ProductOrder> supplies_supplier = ProductOrder.getProductOrdersBySupplier(conn, supplier);
        ProductOrder.printMany(supplies_supplier);
        ProductOrder.getMenu(conn);
        break;
      case 2:
        String product;
        System.out.print("> ");
        product = scanner.next();
        ArrayList<ProductOrder> supplies_product = ProductOrder.getProductOrdersByProduct(conn, product);
        ProductOrder.printMany(supplies_product);
        ProductOrder.getMenu(conn);
        break;
      case 3:
        ArrayList<ProductOrder> supplies_all = ProductOrder.getAll(conn);
        ProductOrder.printMany(supplies_all);
        ProductOrder.getMenu(conn);
        break;
      case 8:
        break;
      default:
      ProductOrder.getMenu(conn);
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
    scanner.close();

    ProductOrder input = new ProductOrder(product_id, supplier_id);
    ProductOrder.removeProductOrder(conn, input);
  }
}