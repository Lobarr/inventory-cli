package com.inventory.lib;

import com.inventory.lib.Product;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Scanner;
import java.sql.PreparedStatement;

public class ProductBrand {
  private String product_id;
  private String brand_id;

  public ProductBrand(String product_id, String brand_id){
    this.product_id = product_id;
    this.brand_id = brand_id;
  }

  public String getProduct_id() {
    return product_id;
  }

  public void setProduct_id(String product_id) {
    this.product_id = product_id;
  }

  public String getBrand_id() {
    return brand_id;
  }

  public void setBrand_id(String brand_id) {
    this.brand_id = brand_id;
  }

  public void print() {
    System.out.println("Product ID:\t" + this.getProduct_id());
    System.out.println("Brand ID:\t" + this.getBrand_id());
    System.out.println();
  }

  public static void printMany(ArrayList<ProductBrand> pb) {
    System.out.println("*** ProductBrands ***");
    pb.forEach(productBrand -> productBrand.print());
  }

  public static void createProductBrand(Connection conn, ProductBrand pb) {
    try {
      String query = "INSERT INTO ProductBrand (product_id, brand_id) VALUES (?,?)";

      PreparedStatement stmt = conn.prepareStatement(query);
      stmt.setString(1, pb.getProduct_id());
      stmt.setString(2, pb.getBrand_id());
      stmt.executeUpdate();
      System.out.println("Created product brand");
    } catch (Exception e) {
      System.out.println("Error: Unable to create product brand");
      System.out.println("Reason: " + e.getMessage());
    }
  }
  

  public static ArrayList<Product> getBrandProducts(Connection conn, String brand_id) {
    try {
      String query = "SELECT Product.id, Product.name, Product.stock, Product.price "
        + "FROM ProductBrand "
        + "JOIN Product ON ProductBrand.product_id = Product.id "
        + "WHERE ProductBrand.brand_id = ? ";

      PreparedStatement stmt = conn.prepareStatement(query);
      stmt.setString(1, brand_id);
      ResultSet rs = stmt.executeQuery();
      ArrayList<Product> res = new ArrayList<>();
      while(rs.next()) {
        res.add(new Product(rs.getString("id"), rs.getString("name"), rs.getInt("stock"), rs.getFloat("price")));
      }
    } catch (Exception e) {
      System.out.println("Error: Unable to get brand products");
      System.out.println("Reason: " + e.getMessage());
    }
    return null;
  }

  public static void removeProductBrand(Connection conn, ProductBrand pb) {
    try {
      String query = "DELETE FROM ProductBrand WHERE product_id = ?, brand_id = ?";

      PreparedStatement stmt = conn.prepareStatement(query);
      stmt.setString(1, pb.getProduct_id());
      stmt.setString(2, pb.getBrand_id());
      stmt.executeUpdate();
      System.out.println("Removed product brand");
    } catch (Exception e) {
      System.out.println("Error: Unable to remove product brand");
      System.out.println("Reason: " + e.getMessage());    
    }
  }

  public static ArrayList<ProductBrand> getAll(Connection conn) {
    try {
      String query = "SELECT * FROM ProductBrand";

      Statement stmt = conn.createStatement();
      ResultSet rs = stmt.executeQuery(query);
      ArrayList<ProductBrand> res = new ArrayList<>();
      while(rs.next()) {
        res.add(new ProductBrand(rs.getString("product_id"), rs.getString("brand_id")));
      }
      return res;
    } catch(Exception e) {
      System.out.println("Error: Unable to get product brands");
      System.out.println("Reason: " + e.getMessage());    
    }
    return null;
  }

  public static void menu(Connection conn) {
    System.out.println("*** ProductBrand Menu *** \n");
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
        ProductBrand.createMenu(conn);
        ProductBrand.menu(conn);
        break;
      case 2:
        ProductBrand.getMenu(conn);
        ProductBrand.menu(conn);
        break;
      case 3:
        ProductBrand.removeMenu(conn);
        ProductBrand.menu(conn);
      case 8:
        break;
      default: 
        ProductBrand.menu(conn);
        break;
    }
  }

  public static void createMenu(Connection conn) {
    String product;
    String brand;
    Scanner scanner = new Scanner(System.in);

    System.out.println("Product ID");
    System.out.print("> ");
    product = scanner.next();

    System.out.println("Brand ID");
    System.out.print("> ");
    brand = scanner.next();
    scanner.close();

    ProductBrand input = new ProductBrand(product, brand);
    ProductBrand.createProductBrand(conn, input);
  }

  public static void getMenu(Connection conn) {
    String brand;
    Scanner scanner = new Scanner(System.in);
    
    System.out.println("*** Get ProductBrand ***");
    System.out.print("> ");
    brand = scanner.next();
    scanner.close();

    ArrayList<Product> po = ProductBrand.getBrandProducts(conn, brand);
    Product.printMany(po);
  }

  public static void removeMenu(Connection conn){
    String product;
    String brand;
    Scanner scanner = new Scanner(System.in);

    System.out.println("Product ID");
    System.out.print("> ");
    product = scanner.next();

    System.out.println("Brand ID");
    System.out.print("> ");
    brand = scanner.next();
    scanner.close();

    ProductBrand input = new ProductBrand(product, brand);
    ProductBrand.removeProductBrand(conn, input);
  }
}