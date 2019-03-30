package com.inventory.lib.schemas.relationships;

import com.inventory.lib.schemas.Product;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.sql.PreparedStatement;

public class CustomerInvoice {
  private String product_id;
  private String invoice_id;

  public CustomerInvoice(String product_id, String invoice_id) {
    this.product_id = product_id;
    this.invoice_id = invoice_id;
  }

  public String getProduct_id() {
    return product_id;
  }

  public void setProduct_id(String product_id) {
    this.product_id = product_id;
  }

  public String getInvoice_id() {
    return invoice_id;
  }

  public void setInvoice_id(String invoice_id) {
    this.invoice_id = invoice_id;
  }

  public static void createCustomerInvoice(Connection conn, CustomerInvoice ci) {
    try {
      String query = "INSERT INTO CustomerInvoice (product_id, invoice_id) VALUES (?,?)";

      PreparedStatement stmt = conn.prepareStatement(query);
      stmt.setString(1, ci.getProduct_id());
      stmt.setString(2, ci.getInvoice_id());
      stmt.executeUpdate();
      System.out.println("Created customer invoice - " + ci.getInvoice_id());
    } catch (Exception e) {
      System.out.println("Error: Unable to create customer invoice");
      System.out.println("Reason: " + e.getMessage());
    }
  }
  

  public static ArrayList<Product> getCustomerInvoiceProducts(Connection conn, String invoice_id) {
    try {
      String query = "SELECT Product.id, Product.name, Product.stock, Product.price "
        + "FROM CustomerInvoice "
        + "JOIN Product ON CustomerInvoice.product_id = Product.id "
        + "WHERE CustomerInvoice.invoice_id = ? ";

      PreparedStatement stmt = conn.prepareStatement(query);
      stmt.setString(1, invoice_id);
      ResultSet rs = stmt.executeQuery();
      ArrayList<Product> res = new ArrayList<>();
      while(rs.next()) {
        res.add(new Product(rs.getString("id"), rs.getString("name"), rs.getInt("stock"), rs.getFloat("price")));
      }
    } catch (Exception e) {
      System.out.println("Error: Unable to get customer invoice products");
      System.out.println("Reason: " + e.getMessage());
    }
    return null;
  }

  public static void removeCustomerInvoiceProduct(Connection conn, CustomerInvoice ci) {
    try {
      String query = "DELETE FROM CustomerInvoice WHERE product_id = ?, invoice_id = ?";

      PreparedStatement stmt = conn.prepareStatement(query);
      stmt.setString(1, ci.getProduct_id());
      stmt.setString(2, ci.getInvoice_id());
      stmt.executeUpdate();
      System.out.println("Removed customer invoice product");
    } catch (Exception e) {
      System.out.println("Error: Unable to delete invoice product");
      System.out.println("Reason: " + e.getMessage());    
    }
  }

  public static ArrayList<CustomerInvoice> getAll(Connection conn) {
    try {
      String query = "SELECT * FROM CustomerInvoice";

      Statement stmt = conn.createStatement();
      ResultSet rs = stmt.executeQuery(query);
      ArrayList<CustomerInvoice> res = new ArrayList<>();
      while(rs.next()) {
        res.add(new CustomerInvoice(rs.getString("product_id"), rs.getString("invoice_id")));
      }
      return res;
    } catch(Exception e) {
      System.out.println("Error: Unable to get customer invoices");
      System.out.println("Reason: " + e.getMessage());    
    }
    return null;
  }
}