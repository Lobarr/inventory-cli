package com.inventory.lib;

import com.inventory.utils.DB;
import java.sql.Connection;

public class Invoice implements DB {
  private int id;
  private String name;
  private String email;
  private float timestamp;

  Invoice(int id, String name, String email, float timestamp) {
    this.id = id;
    this.name = name;
    this.email = email;
    this.timestamp = timestamp;
  }

  public int getId() {
    return id;
  }

  public void setId(int id) {
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

  public float getTimestamp() {
    return timestamp;
  }

  public void setTimestamp(float timestamp) {
    this.timestamp = timestamp;
  }

  public static Invoice get(String id) {

  }

}