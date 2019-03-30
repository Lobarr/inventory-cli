package com.inventory.lib.schemas.relationships;

import com.inventory.lib.schemas.Category;
import com.inventory.lib.schemas.Product;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
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
}