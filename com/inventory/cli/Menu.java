package com.inventory.cli;

import java.sql.Connection;
import java.util.Scanner;

import com.inventory.lib.Supplier;
import com.inventory.lib.SupplierCategory;
import com.inventory.lib.Supplies;

public class Menu {
  public static void run(Connection conn) {
    System.out.println("*** Inventory CLI ***");
    System.out.println("1 - Supplies");
    System.out.println("2 - SupplierCategory");
    System.out.println("3 - Supplier");
    System.out.println("8 - QUIT");

    int option;
    Scanner scanner = new Scanner(System.in);
    System.out.print("> ");
    option = scanner.nextInt();

    switch(option){
      case 1:
        Supplies.menu(conn);
        Menu.run(conn);
        break;
      case 2:
        SupplierCategory.menu(conn);
        Menu.run(conn);
        break;
      case 3:
        Supplier.menu(conn);
        Menu.run(conn);
        break;
      case 8:
        break;
      default:
        Menu.run(conn);
        break;
    }
  }
}