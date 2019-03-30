package com.inventory.lib.utils;

import java.sql.Connection;
import java.sql.DriverManager;

public class DB {
  private Connection conn;
  final static String url = "jdbc:sqlite:./db/inventory.db";

  public DB() {
    this.connect();
  }

  // connects to db
  public void connect() {
    try {
      Class.forName("org.sqlite.JDBC");
      Connection conn = DriverManager.getConnection(DB.url);
      System.out.println("Connected to database");
      this.setConn(conn);
    } catch (Exception e) {
      System.out.println("Unable to connect to database");
      System.err.println( e.getClass().getName() + ": " + e.getMessage() );
      System.exit(1);
    }
  }

  //terminates db connection
  public void close() {
    try {
      this.conn.close();
      System.out.println("Terminated DB connection");
    } catch (Exception e) {
      System.out.println("Unable to connect to database");
      System.out.println(e.getMessage());
      System.exit(1);
    }
  }
  
  //return db connection
  public Connection connection() {
    return conn;
  }

   // @param conn the conn to set
  public void setConn(Connection conn) {
    this.conn = conn;
  }
}