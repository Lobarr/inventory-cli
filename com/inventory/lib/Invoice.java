package com.inventory.lib;

import java.util.UUID;

public class Invoice {
  private String id;
  private String name;
  private String email;
  private float timestamp;

  public Invoice() {
  }
  
  public Invoice(String id, String name, String email, float timestamp) {
    this.id = id;
    this.name = name;
    this.email = email;
    this.timestamp = timestamp;
  }

  public Invoice (String name, String email, float timestamp) {
    UUID id = UUID.randomUUID();
    this.id = id.toString();
    this.name = name;
    this.email = email;
    this.timestamp = timestamp;
  }

  public String getId() {
    return this.id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public String getName() {
    return this.name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getEmail() {
    return this.email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public float getTimestamp() {
    return this.timestamp;
  }

  public void setTimestamp(float timestamp) {
    this.timestamp = timestamp;
  }

  public void print() {
    System.out.println("ID: " + this.getId());
    System.out.println("Name: " + this.getName());
    System.out.println("Email: " + this.getEmail());
    System.out.println("Timestamp: " + this.getTimestamp());
  }
}