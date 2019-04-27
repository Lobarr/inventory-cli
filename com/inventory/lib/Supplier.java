package com.inventory.lib;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Scanner;
import java.sql.PreparedStatement;
import java.util.UUID;

public class Supplier {
  private String id;
  private String name;
  private String email;

  public Supplier(){}

  public Supplier(String id, String name, String email) {
    this.id = id;
    this.name = name;
    this.email = email;
  }

  public Supplier(String name, String email) {
    UUID id = UUID.randomUUID();
    this.id = id.toString();
    this.name = name;
    this.email = email;
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

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public void print() {
    System.out.println("ID:\t" + this.getId());
    System.out.println("Name:\t" + this.getName());
    System.out.println("Email:\t" + this.getEmail());
    System.out.println();
  }

  public static void printMany(ArrayList<Supplier> suppliers) {
    System.out.println("*** Suppliers ***");
    suppliers.forEach(supplier -> supplier.print());
  }

  public static void createSupplier(Connection conn, Supplier su) {
    try {
      String query = "INSERT INTO Supplier (id, name, email) VALUES (?,?, ?)";

      PreparedStatement stmt = conn.prepareStatement(query);
      stmt.setString(1, su.getId());
      stmt.setString(2, su.getName());
      stmt.setString(3, su.getEmail());
      stmt.executeUpdate();
      System.out.println("Created supplier " + su.getId());
    } catch (Exception e) {
      System.out.println("Error: Unable to create supplier");
      System.out.println("Reason: " + e.getMessage());
    }
  }
  

  public static Supplier getSupplier(Connection conn, String id) {
    try {
      String query = "SELECT * "
        + "FROM Supplier "
        + "WHERE id = ? ";

      PreparedStatement stmt = conn.prepareStatement(query);
      stmt.setString(1, id);
      ResultSet rs = stmt.executeQuery();
      return new Supplier(rs.getString("id"), rs.getString("name"), rs.getString("email"));
    } catch (Exception e) {
      System.out.println("Error: Unable to get supplier");
      System.out.println("Reason: " + e.getMessage());
    }
    return null;
  }

  public static void updateSupplier(Connection conn, Supplier su) {
    try {
      String query = "UPDATE Supplier "
        + "SET name = ?, email = ? "
        + "WHERE id = ? ";

      PreparedStatement stmt = conn.prepareStatement(query);
      stmt.setString(1, su.getName());
      stmt.setString(2, su.getEmail());
      stmt.setString(3, su.getId());
      stmt.executeUpdate();
      System.out.println("Updated supplier " + su.getId());
    } catch (Exception e) {
      System.out.println("Error: Unable to update supplier");
      System.out.println("Reason: " + e.getMessage());
    }
  }

  public static void removeSupplier(Connection conn, String id) {
    try {
      String query = "DELETE FROM Supplier WHERE id = ?";

      PreparedStatement stmt = conn.prepareStatement(query);
      stmt.setString(1, id);
      stmt.executeUpdate();
      System.out.println("Removedr supplier - " + id);
    } catch (Exception e) {
      System.out.println("Error: Unable to remove supplier");
      System.out.println("Reason: " + e.getMessage());    
    }
  }

  public static ArrayList<Supplier> getAll(Connection conn) {
    try {
      String query = "SELECT * FROM Supplier";

      Statement stmt = conn.createStatement();
      ResultSet rs = stmt.executeQuery(query);
      ArrayList<Supplier> res = new ArrayList<Supplier>();
      while(rs.next()) {
        res.add(new Supplier(rs.getString("id"), rs.getString("name"), rs.getString("email")));
      }
      return res;
    } catch(Exception e) {
      System.out.println("Error: Unable to get suppliers");
      System.out.println("Reason: " + e.getMessage());    
    }
    return null;
  }

  public static void menu(Connection conn) {
    System.out.println("*** Supplier Menu *** \n");
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
        Supplier.createMenu(conn);
        Supplier.menu(conn);
        break;
      case 2:
        Supplier.getMenu(conn);
        Supplier.menu(conn);
        break;
      case 3:
        Supplier.updateMenu(conn);
        Supplier.menu(conn);
        break;
      case 4:
        Supplier.removeMenu(conn);
        Supplier.menu(conn);
      case 8:
        break;
      default: 
        Supplier.menu(conn);
        break;
    }
  }

  public static void createMenu(Connection conn) {
    String name;
    String email;
    Scanner scanner = new Scanner(System.in);

    System.out.println("Name");
    System.out.print("> ");
    name = scanner.next();

    System.out.println("Email");
    System.out.print("> ");
    email = scanner.next();
    scanner.close();

    Supplier input = new Supplier(name, email);
    Supplier.createSupplier(conn, input);
  }

  public static void getMenu(Connection conn) {
    System.out.println("*** Get Supplier ***");
    System.out.println("1 - BY ID");
    System.out.println("2 - ALL");
    System.out.println("8 - BACK");

    int option;
    Scanner scanner = new Scanner(System.in);

    System.out.println("> ");
    option = scanner.nextInt();
    scanner.close();

    switch(option) {
      case 1:
        String id;
   
        System.out.println("ID");
        System.out.print("> ");
        id = scanner.next();
    
        Supplier.getSupplier(conn, id);
        Supplier.getMenu(conn);
        break;
      case 2:
        ArrayList<Supplier> suppliers = Supplier.getAll(conn);
        Supplier.printMany(suppliers);
        Supplier.getMenu(conn);
        break;
      case 8:
        break;
      default:
        Supplier.getMenu(conn);
    }
  }

  public static void updateMenu(Connection conn) {
    String id;
    String input;
    Scanner scanner = new Scanner(System.in);

    System.out.println("ID");
    System.out.print("> ");
    id = scanner.next();

    Supplier supplier = Supplier.getSupplier(conn, id);

    System.out.println("# -> Don't update\n");
    System.out.println("Name");
    System.out.print("> ");
    input = scanner.next();
    if (input != "#") {
      supplier.setName(input);
    }

    System.out.println("Email");
    System.out.print("> ");
    input = scanner.next();
    if (input != "#") {
      supplier.setEmail(input);
    }
    scanner.close();
    Supplier.updateSupplier(conn, supplier);
  }

  public static void removeMenu(Connection conn){
    String id;
    Scanner scanner = new Scanner(System.in);

    System.out.println("ID");
    System.out.print("> ");
    id = scanner.next();
    scanner.close();

    Supplier.removeSupplier(conn, id);
  }

}