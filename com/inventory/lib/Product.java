package com.inventory.lib;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.UUID;

public class Product {
  private String id;
  private String name;
  private int stock;
  private float price;

  public Product(){}

  public Product(String id, String name, int stock, float price) {
    this.id = id;
    this.name = name;
    this.stock = stock;
    this.price = price;
  }

  public Product(String name, int stock, float price) {
    UUID id = UUID.randomUUID();
    this.id = id.toString();
    this.name = name;
    this.stock = stock;
    this.price = price;
  }

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public int getStock() {
    return stock;
  }

  public void setStock(int stock) {
    this.stock = stock;
  }

  public float getPrice() {
    return price;
  }

  public void setPrice(float price) {
    this.price = price;
  }

  public void print() {
    System.out.println("ID:\t" + this.getId());
    System.out.println("Name:\t" + this.getName());
    System.out.println("Stock:\t" + this.getStock());
    System.out.println("Price:\t" + this.getPrice());
    System.out.println();
  }

  public static void printMany(ArrayList<Product> products) {
    System.out.println("*** Products ***");
    products.forEach(product -> product.print());
  }
  
  public static void createProduct(Connection conn, Product prod) {
    try {
      String query = "INSERT INTO Product (id, name, stock, price) VALUES (?,?,?,?)";

      PreparedStatement stmt = conn.prepareStatement(query);
      stmt.setString(1, prod.getId());
      stmt.setString(2, prod.getName());
      stmt.setInt(3, prod.getStock());
      stmt.setFloat(4, prod.getPrice());
      stmt.executeUpdate();
      System.out.println("Created product " + prod.getId());
    } catch (Exception e) {
      System.out.println("Error: Unable to create product");
      System.out.println("Reason: " + e.getMessage());
    }
  }

  public static Product getProduct(Connection conn, String id) {
    try {
      String query = "SELECT * "
        + "FROM Product "
        + "WHERE id = ?";
      
        PreparedStatement stmt = conn.prepareStatement(query);
        stmt.setString(1, id);
        ResultSet rs = stmt.executeQuery();
        return new Product(rs.getString("id"), rs.getString("name"), rs.getInt("stock"), rs.getFloat("price"));
    } catch(Exception e) {
      System.out.println("Error: Unable to get product");
      System.out.println("Reason: " + e.getMessage());
    }
    return null;
  }

  public static void updateProduct(Connection conn, Product prod) {
    try {
      String query = "UPDATE Product "
        + "SET name = ?, stock = ?, price = ? "
        + "WHERE id = ? ";

      PreparedStatement stmt = conn.prepareStatement(query);
      stmt.setString(1, prod.getName());
      stmt.setInt(2, prod.getStock());
      stmt.setFloat(3, prod.getPrice());
      stmt.setString(4, prod.getId());
      System.out.println("Updated product - " + prod.getId());
    } catch (Exception e) {
      System.out.println("Error: Unable to update product");
      System.out.println("Reason: " + e.getMessage());
    }
  }
  
  public static void removeProduct(Connection conn, String id) {
    try {
      String query = "DELETE FROM Product WHERE id = ?";

      PreparedStatement stmt = conn.prepareStatement(query);
      stmt.setString(1, id);
      stmt.executeUpdate();
      System.out.println("Remove product - " + id);
    } catch (Exception e) {
      System.out.println("Error: Unable to remove product");
      System.out.println("Reason: " + e.getMessage());    
    }
  }

  public static ArrayList<Product> getAll(Connection conn) {
    try {
      String query = "SELECT * FROM Product";

      Statement stmt = conn.createStatement();
      ResultSet rs = stmt.executeQuery(query);
      ArrayList<Product> res = new ArrayList<Product>();
      while(rs.next()) {
        res.add(new Product(rs.getString("id"), rs.getString("name"), rs.getInt("stock"), rs.getFloat("price")));
      }
      return res;
    } catch(Exception e) {
      System.out.println("Error: Unable to get products");
      System.out.println("Reason: " + e.getMessage());    
    }
    return null;
  }

  public static void menu(Connection conn) {
    System.out.println("*** Product Menu *** \n");
    System.out.println("1 - CREATE");
    System.out.println("2 - GET");
    System.out.println("3 - UPDATE");
    System.out.println("4 - REMOVE");
    System.out.println("8 - BACK\n");

    Scanner scanner = new Scanner(System.in);
    System.out.print("> ");

    int option = scanner.nextInt();
    scanner.close();
    switch (option) {
      case 1:
        Product.createMenu(conn);
        Product.menu(conn);
        break;
      case 2:
        Product.getMenu(conn);
        Product.menu(conn);
        break;
      case 3:
        Product.updateMenu(conn);
        Product.menu(conn);
        break;
      case 4:
        Product.removeMenu(conn);
        Product.menu(conn);
      case 8:
        break;
      default: 
        Product.menu(conn);
        break;
    }
  }

  public static void createMenu(Connection conn) {
    String name;
    int stock;
    float price;

    Scanner scanner = new Scanner(System.in);

    System.out.println("Name");
    System.out.print("> ");
    name = scanner.next();

    System.out.println("Stock");
    System.out.print("> ");
    stock = scanner.nextInt();

    System.out.println("Price");
    System.out.print("> ");
    price = scanner.nextFloat();

    scanner.close();

    Product input = new Product(name, stock, price);
    Product.createProduct(conn, input);
  }

  public static void getMenu(Connection conn) {
    System.out.println("*** Get Products ***");
    System.out.println("1 - BY ID");
    System.out.println("2 - ALL");
    System.out.println("8 - BACK");

    int option;
    Scanner scanner = new Scanner(System.in);

    System.out.println("> ");
    option = scanner.nextInt();

    switch(option) {
      case 1:
        String id;
   
        System.out.println("ID");
        System.out.print("> ");
        id = scanner.next();
    
        Product.getProduct(conn, id);
        Product.getMenu(conn);
        break;
      case 2:
        ArrayList<Product> products = Product.getAll(conn);
        Product.printMany(products);
        Product.getMenu(conn);
        break;
      case 8:
        break;
      default:
        Supplier.getMenu(conn);
    }
    scanner.close();
  }

  public static void updateMenu(Connection conn) {
    String id;
    String input_str;
    int input_int;
    float input_flt;
    Scanner scanner = new Scanner(System.in);

    System.out.println("ID");
    System.out.print("> ");
    id = scanner.next();

    Product product = Product.getProduct(conn, id);

    System.out.println("# -> Don't update\n");
    System.out.println("Name");
    System.out.print("> ");
    input_str = scanner.next();
    if (input_str != "#") {
      product.setName(input_str);
    }

    System.out.println("-1 -> Don't update\n");
    System.out.println("Stock");
    System.out.print("> ");
    input_int = scanner.nextInt();
    if (input_int != -1) {
      product.setStock(input_int);
    }

    System.out.println("-1 -> Don't update\n");
    System.out.println("Price");
    System.out.print("> ");
    input_flt = scanner.nextFloat();
    if (input_flt != -1) {
      product.setPrice(input_flt);
    }
    scanner.close();
    Product.updateProduct(conn, product);
  }

  public static void removeMenu(Connection conn){
    String id;
    Scanner scanner = new Scanner(System.in);

    System.out.println("ID");
    System.out.print("> ");
    id = scanner.next();
    scanner.close();
    Product.removeProduct(conn, id);
  }
}