package com.inventory.lib;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Scanner;
import java.sql.PreparedStatement;
import java.util.UUID;

public class Shipping {
  private String id;
  private String address;

  public Shipping() {}

  public Shipping(String id, String address) {
    this.id = id;
    this.address = address;
  }

  public Shipping(String address) {
    UUID id = UUID.randomUUID();
    this.id = id.toString();
    this.address = address;
  }

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public String getAddress() {
    return address;
  }

  public void setAddress(String address) {
    this.address = address;
  }

  public void print() {
    System.out.println("ID:\t" + this.getId());
    System.out.println("Address:\t" + this.getAddress());
    System.out.println();
  }

  public static void printMany(ArrayList<Shipping> sh) {
    System.out.println("*** Shipping ***");
    sh.forEach(shipping -> shipping.print());
  }


  public static void createShipping(Connection conn, Shipping sh) {
    try {
      String query = "INSERT INTO Shipping (id, address) VALUES (?,?)";

      PreparedStatement stmt = conn.prepareStatement(query);
      stmt.setString(1, sh.getId());
      stmt.setString(2, sh.getAddress());
      stmt.executeUpdate();
      System.out.println("Created shipping " + sh.getId());
    } catch (Exception e) {
      System.out.println("Error: Unable to create shipping");
      System.out.println("Reason: " + e.getMessage());
    }
  }

  public static Shipping getShipping(Connection conn, String id) {
    try {
      String query = "SELECT * "
        + "FROM Shipping "
        + "WHERE id = ? ";

      PreparedStatement stmt = conn.prepareStatement(query);
      stmt.setString(1, id);
      ResultSet rs = stmt.executeQuery();
      return new Shipping(rs.getString("id"), rs.getString("address"));
    } catch (Exception e) {
      System.out.println("Error: Unable to get shipping");
      System.out.println("Reason: " + e.getMessage());
    }
    return null;
  }

  public static void updateShipping(Connection conn, Shipping sh) {
    try {
      String query = "UPDATE Shipping "
        + "SET address = ? "
        + "WHERE id = ? ";

      PreparedStatement stmt = conn.prepareStatement(query);
      stmt.setString(1, sh.getAddress());
      stmt.setString(2, sh.getId());
      stmt.executeUpdate();
      System.out.println("Updated shipping " + sh.getId());
    } catch (Exception e) {
      System.out.println("Error: Unable to update shipping");
      System.out.println("Reason: " + e.getMessage());
    }
  }

  public static void removeShipping(Connection conn, String id) {
    try {
      String query = "DELETE FROM Shipping WHERE id = ?";

      PreparedStatement stmt = conn.prepareStatement(query);
      stmt.setString(1, id);
      stmt.executeUpdate();
      System.out.println("Removed shipping - " + id);
    } catch (Exception e) {
      System.out.println("Error: Unable to remove shipping");
      System.out.println("Reason: " + e.getMessage());    
    }
  }

  public static ArrayList<Shipping> getAll(Connection conn) {
    try {
      String query = "SELECT * FROM Shipping";

      Statement stmt = conn.createStatement();
      ResultSet rs = stmt.executeQuery(query);
      ArrayList<Shipping> res = new ArrayList<Shipping>();
      while(rs.next()) {
        res.add(new Shipping(rs.getString("id"), rs.getString("address")));
      }
      return res;
    } catch(Exception e) {
      System.out.println("Error: Unable to get shippings");
      System.out.println("Reason: " + e.getMessage());    
    }
    return null;
  }

  public static void menu(Connection conn) {
    System.out.println("*** Shipping Menu *** \n");
    System.out.println("1 - CREATE");
    System.out.println("2 - GET");
    System.out.println("3 - UPDATE");
    System.out.println("4 - REMOVE");
    System.out.println("8 - BACK\n");

    Scanner scanner = new Scanner(System.in);
    System.out.print("> ");

    int option = scanner.nextInt();
    switch (option) {
      case 1:
        Shipping.createMenu(conn);
        Shipping.menu(conn);
        break;
      case 2:
        Shipping.getMenu(conn);
        Shipping.menu(conn);
        break;
      case 3:
        Shipping.updateMenu(conn);
        Shipping.menu(conn);
        break;
      case 4:
        Shipping.removeMenu(conn);
        Shipping.menu(conn);
      case 8:
        break;
      default: 
        Shipping.menu(conn);
        break;
    }
  }

  public static void createMenu(Connection conn) {
    String address;
    Scanner scanner = new Scanner(System.in);

    System.out.println("Address");
    System.out.print("> ");
    address = scanner.next();

    Shipping input = new Shipping(address);
    Shipping.createShipping(conn, input);
  }

  public static void getMenu(Connection conn) {
    System.out.println("*** Get Shipping ***");
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
    
        Shipping.getShipping(conn, id);
        Shipping.getMenu(conn);
        break;
      case 2:
        ArrayList<Shipping> shippings = Shipping.getAll(conn);
        Shipping.printMany(shippings);
        Shipping.getMenu(conn);
        break;
      case 8:
        break;
      default:
        Shipping.getMenu(conn);
    }
  }

  public static void updateMenu(Connection conn) {
    String id;
    String input;
    Scanner scanner = new Scanner(System.in);

    System.out.println("ID");
    System.out.print("> ");
    id = scanner.next();

    Shipping shipping = Shipping.getShipping(conn, id);

    System.out.println("# -> Don't update\n");
    System.out.println("Address");
    System.out.print("> ");
    input = scanner.next();
    if (input != "#") {
      shipping.setAddress(input);
    }
    Shipping.updateShipping(conn, shipping);
  }

  public static void removeMenu(Connection conn){
    String id;
    Scanner scanner = new Scanner(System.in);

    System.out.println("ID");
    System.out.print("> ");
    id = scanner.next();

    Shipping.removeShipping(conn, id);
  }

}