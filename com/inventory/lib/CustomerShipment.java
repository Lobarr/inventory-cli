package com.inventory.lib;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.sql.PreparedStatement;

import java.util.UUID;

public class CustomerShipment {
  private String invoice_id;
  private String shipping_id;

  public CustomerShipment(String invoice_id, String shipping_id) {
    this.invoice_id = invoice_id;
    this.shipping_id = shipping_id;
  }

  public CustomerShipment(String invoice_id) {
    UUID id = UUID.randomUUID();
    this.shipping_id = id.toString();
    this.invoice_id = invoice_id;
  }

  public String getInvoice_id() {
    return invoice_id;
  }

  public void setInvoice_id(String invoice_id) {
    this.invoice_id = invoice_id;
  }

  public String getShipping_id() {
    return shipping_id;
  }

  public void setShipping_id(String shipping_id) {
    this.shipping_id = shipping_id;
  }

  public static void createCustomerShipment(Connection conn, CustomerShipment cs) {
    try {
      String query = "INSERT INTO CustomerShipment (invoice_id, shipping_id) VALUES (?,?)";

      PreparedStatement stmt = conn.prepareStatement(query);
      stmt.setString(1, cs.getInvoice_id());
      stmt.setString(2, cs.getShipping_id());
      stmt.executeUpdate();
      System.out.println("Created customer shipment");
    } catch (Exception e) {
      System.out.println("Error: Unable to create customer shipment");
      System.out.println("Reason: " + e.getMessage());
    }
  }
  

  public static CustomerShipment getCustomerShipmentByShipping(Connection conn, String shipping_id) {
    try {
      String query = "SELECT * "
        + "FROM CustomerShipment "
        + "WHERE shipping_id = ? ";

      PreparedStatement stmt = conn.prepareStatement(query);
      stmt.setString(1, shipping_id);
      ResultSet rs = stmt.executeQuery();
      return new CustomerShipment(rs.getString("invoice_id"), rs.getString("shipping_id"));
    } catch (Exception e) {
      System.out.println("Error: Unable to get customer shipment by shipping");
      System.out.println("Reason: " + e.getMessage());
    }
    return null;
  }

  public static CustomerShipment getCustomerShipmentByInvoice(Connection conn, String shipping_id) {
    try {
      String query = "SELECT * "
        + "FROM CustomerShipment "
        + "WHERE invoice_id = ? ";

      PreparedStatement stmt = conn.prepareStatement(query);
      stmt.setString(1, shipping_id);
      ResultSet rs = stmt.executeQuery();
      return new CustomerShipment(rs.getString("invoice_id"), rs.getString("shipping_id"));
    } catch (Exception e) {
      System.out.println("Error: Unable to get customer shipment by invoice");
      System.out.println("Reason: " + e.getMessage());
    }
    return null;
  }

  public static void removeCustomerShipment(Connection conn, CustomerShipment cs) {
    try {
      String query = "DELETE FROM CustomerShipment WHERE invoice_id = ?, shipping_id = ?";

      PreparedStatement stmt = conn.prepareStatement(query);
      stmt.setString(1, cs.getInvoice_id());
      stmt.setString(2, cs.getShipping_id());
      stmt.executeUpdate();
      System.out.println("Removed customer shipment");
    } catch (Exception e) {
      System.out.println("Error: Unable to remove customer shipment");
      System.out.println("Reason: " + e.getMessage());    
    }
  }

  public static ArrayList<CustomerShipment> getAll(Connection conn) {
    try {
      String query = "SELECT * FROM CustomerShipment";

      Statement stmt = conn.createStatement();
      ResultSet rs = stmt.executeQuery(query);
      ArrayList<CustomerShipment> res = new ArrayList<>();
      while(rs.next()) {
        res.add(new CustomerShipment(rs.getString("invoice_id"), rs.getString("shipping_id")));
      }
      return res;
    } catch(Exception e) {
      System.out.println("Error: Unable to get customer shipments");
      System.out.println("Reason: " + e.getMessage());    
    }
    return null;
  }
}