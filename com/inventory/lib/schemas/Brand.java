package com.inventory.lib.schemas;

import java.util.UUID;

public class Brand {
  private String id;
  private String name;

  public Brand() {}

  public Brand(String id, String name) {
    this.id = id;
    this.name = name;
  }

  public Brand(String name) {
    UUID id = UUID.randomUUID();
    this.id = id.toString();
    this.name = name;
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

}