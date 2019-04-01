package com.inventory.cli;

import java.sql.Connection;
import java.util.Scanner;

import com.inventory.lib.SupplierCategory;
import com.inventory.lib.Supplies;

public class Menu {
  public static void run(Connection conn) {
    System.out.println("*** Inventory CLI ***");
    System.out.println("1 - Supplies");
    System.out.println("8 - QUIT");

    Scanner scanner = new Scanner(System.in);
    System.out.print("> ");

    int option = scanner.nextInt();
    switch(option){
      case 1:
        Supplies.menu(conn);
        Menu.run(conn);
        break;
      case 2:
        SupplierCategory.menu(conn);
        Menu.run(conn);
        break;
      default:
        Menu.run(conn);
        break;
    }
  }
}