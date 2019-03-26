package com.inventory.lib;

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
}