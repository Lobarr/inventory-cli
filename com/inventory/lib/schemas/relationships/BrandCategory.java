package com.inventory.lib.schemas.relationships;

public class BrandCategory {
  private String brand_id;
  private String category_id;

  public BrandCategory(String brand_id, String category_id){
    this.brand_id = brand_id;
    this.category_id = category_id;
  }

  public String getBrand_id() {
    return brand_id;
  }

  public void setBrand_id(String brand_id) {
    this.brand_id = brand_id;
  }

  public String getCategory_id() {
    return category_id;
  }

  public void setCategory_id(String category_id) {
    this.category_id = category_id;
  }
}