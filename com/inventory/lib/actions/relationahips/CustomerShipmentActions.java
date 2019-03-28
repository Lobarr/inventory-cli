package com.inventory.lib.actions.relationahips;

import com.inventory.lib.schemas.Invoice;
import com.inventory.lib.schemas.Product;
import com.inventory.lib.schemas.relationships.CustomerShipment;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.sql.PreparedStatement;


public class CustomerShipmentActions {
  public static void createCustomerShipment(Connection conn, CustomerShipment cs) {
    try {
      String query = "INSERT INTO CustomerShipment (invoice_id, shipping) VALUES (?,?)";

      PreparedStatement stmt = conn.prepareStatement(query);
      stmt.setString(1, cs.getInvoice_id());
      stmt.setString(2, cs.getShipping_id());
      stmt.executeUpdate();
      System.out.println("Added customer shipment");
    } catch (Exception e) {
      System.out.println("Error: Unable to create customer shipment");
      System.out.println("Reason: " + e.getMessage());
    }
  }
  

  public static CustomerShipment getCustomerShipment(Connection conn, String shipping_id) {
    try {
      String query = "SELECT * "
        + "FROM CustomerShipment "
        + "WHERE shipping_id = ? ";

      PreparedStatement stmt = conn.prepareStatement(query);
      stmt.setString(1, shipping_id);
      ResultSet rs = stmt.executeQuery();
      return new CustomerShipment(rs.getString("invoice_id"), rs.getString("shipping_id"));
    } catch (Exception e) {
      System.out.println("Error: Unable to get customer shipment");
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
      System.out.println("Deleted customer shipment");
    } catch (Exception e) {
      System.out.println("Error: Unable to delete customer shipment");
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