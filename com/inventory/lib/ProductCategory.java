package com.inventory.lib;

import com.inventory.lib.Category;
import com.inventory.lib.Product;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Scanner;
import java.sql.PreparedStatement;

public class ProductCategory {
  private String product_id;
  private String category_id;

  public ProductCategory(String product_id, String category_id){
    this.product_id = product_id;
    this.category_id = category_id;
  }

  public String getProduct_id() {
    return product_id;
  }

  public void setProduct_id(String product_id) {
    this.product_id = product_id;
  }

  public String getCategory_id() {
    return category_id;
  }

  public void setCategory_id(String category_id) {
    this.category_id = category_id;
  }

  public void print() {
    System.out.println("Product ID:\t" + this.getProduct_id());
    System.out.println("Category ID:\t" + this.getCategory_id());
    System.out.println();
  }

  public static void printMany(ArrayList<ProductCategory> pc) {
    System.out.println("*** ProductCategory ***");
    pc.forEach(productCategory -> productCategory.print());
  }

  public static void createProductCategory(Connection conn, ProductCategory pc) {
    try {
      String query = "INSERT INTO ProductCategory (product_id, category_id) VALUES (?,?)";

      PreparedStatement stmt = conn.prepareStatement(query);
      stmt.setString(1, pc.getProduct_id());
      stmt.setString(2, pc.getCategory_id());
      stmt.executeUpdate();
      System.out.println("Created product category");
    } catch (Exception e) {
      System.out.println("Error: Unable to create product category");
      System.out.println("Reason: " + e.getMessage());
    }
  }
  

  public static ArrayList<Product> getCategoryProducts(Connection conn, String category_id) {
    try {
      String query = "SELECT Product.id, Product.name, Product.stock, Product.price "
        + "FROM ProductCategory "
        + "JOIN Product ON ProductCategory.product_id = Product.id "
        + "WHERE ProductCategory.category_id = ? ";

      PreparedStatement stmt = conn.prepareStatement(query);
      stmt.setString(1, category_id);
      ResultSet rs = stmt.executeQuery();
      ArrayList<Product> res = new ArrayList<>();
      while(rs.next()) {
        res.add(new Product(rs.getString("id"), rs.getString("name"), rs.getInt("stock"), rs.getFloat("price")));
      }
      return res;
    } catch (Exception e) {
      System.out.println("Error: Unable to get category products");
      System.out.println("Reason: " + e.getMessage());
    }
    return null;
  }

  public static ArrayList<Category> getProductCategories(Connection conn, String product_id) {
    try {
      String query = "SELECT Category.id, Category.name "
        + "FROM ProductCategory "
        + "JOIN Category ON ProductCategory.category_id = Category.id "
        + "WHERE ProductCategory.product_id = ? ";

      PreparedStatement stmt = conn.prepareStatement(query);
      stmt.setString(1, product_id);
      ResultSet rs = stmt.executeQuery();
      ArrayList<Category> res = new ArrayList<>();
      while(rs.next()) {
        res.add(new Category(rs.getString("id"), rs.getString("name")));
      }
      return res;
    } catch (Exception e) {
      System.out.println("Error: Unable to get product's categories");
      System.out.println("Reason: " + e.getMessage());
    }
    return null;
  }

  public static void removeProductCategory(Connection conn, ProductCategory pc) {
    try {
      String query = "DELETE FROM ProductCategory WHERE product_id = ?, category_id = ?";

      PreparedStatement stmt = conn.prepareStatement(query);
      stmt.setString(1, pc.getProduct_id());
      stmt.setString(2, pc.getCategory_id());
      stmt.executeUpdate();
      System.out.println("Deleted product category");
    } catch (Exception e) {
      System.out.println("Error: Unable to delete product category");
      System.out.println("Reason: " + e.getMessage());    
    }
  }

  public static ArrayList<ProductCategory> getAll(Connection conn) {
    try {
      String query = "SELECT * FROM ProductCategory";

      Statement stmt = conn.createStatement();
      ResultSet rs = stmt.executeQuery(query);
      ArrayList<ProductCategory> res = new ArrayList<>();
      while(rs.next()) {
        res.add(new ProductCategory(rs.getString("product_id"), rs.getString("category_id")));
      }
      return res;
    } catch(Exception e) {
      System.out.println("Error: Unable to get product categories");
      System.out.println("Reason: " + e.getMessage());    
    }
    return null;
  }

  public static void menu(Connection conn) {
    System.out.println("*** ProductCategory Menu *** \n");
    System.out.println("1 - CREATE");
    System.out.println("2 - GET");
    System.out.println("3 - REMOVE");
    System.out.println("8 - BACK\n");

    Scanner scanner = new Scanner(System.in);
    System.out.print("> ");

    int option = scanner.nextInt();
    switch (option) {
      case 1:
        ProductCategory.createMenu(conn);
        ProductCategory.menu(conn);
        break;
      case 2:
        ProductCategory.getMenu(conn);
        ProductCategory.menu(conn);
        break;
      case 3:
        ProductCategory.removeMenu(conn);
        ProductCategory.menu(conn);
      case 8:
        break;
      default: 
        ProductCategory.menu(conn);
        break;
    }
  }

  public static void createMenu(Connection conn) {
    String product;
    String category;
    Scanner scanner = new Scanner(System.in);

    System.out.println("Product ID");
    System.out.print("> ");
    product = scanner.next();

    System.out.println("Category ID");
    System.out.print("> ");
    category = scanner.next();

    ProductCategory input = new ProductCategory(product, category);
    ProductCategory.createProductCategory(conn, input);
  }

  public static void getMenu(Connection conn) {
    System.out.println("*** Get ProductCategory ***");
    System.out.println("1 - CATEGORY PRODUCTS");
    System.out.println("2 - PRODUCT CATEGORIES");
    System.out.println("3 - ALL");
    System.out.println("8 - BACK");

    int option;
    Scanner scanner = new Scanner(System.in);

    System.out.println("> ");
    option = scanner.nextInt();

    switch(option) {
      case 1:
        System.out.println("Category ID");
        System.out.print("> ");
        String category = scanner.next();

        ArrayList<Product> category_products = ProductCategory.getCategoryProducts(conn, category);
        Product.printMany(category_products);
        ProductCategory.getMenu(conn);
        break;
      case 2:
        System.out.println("Product ID");
        System.out.print("> ");
        String product = scanner.next();

        ArrayList<Category> product_categories = ProductCategory.getProductCategories(conn, category);
        Category.printMany(product_categories);
        ProductCategory.getMenu(conn);
        break;
      case 3:
        ArrayList<ProductCategory> pc = ProductCategory.getAll(conn);
        ProductCategory.printMany(pc);
        ProductCategory.getMenu(conn);
        break;
      case 8:
        break;
      default:
        ProductCategory.getMenu(conn);
    }
  }

  public static void removeMenu(Connection conn){
    String product;
    String category;
    Scanner scanner = new Scanner(System.in);

    System.out.println("Product ID");
    System.out.print("> ");
    product = scanner.next();

    System.out.println("Category ID");
    System.out.print("> ");
    category = scanner.next();

    ProductCategory input = new ProductCategory(product, category);

    ProductCategory.removeProductCategory(conn, input);
  }
}