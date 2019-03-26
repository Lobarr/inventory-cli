package com.inventory.lib.schemas.relationships;

public class ProductBrand {
  private String product_id;
  private String brand_id;

  public ProductBrand(String product_id, String brand_id){
    this.product_id = product_id;
    this.brand_id = brand_id;
  }

  public String getProduct_id() {
    return product_id;
  }

  public void setProduct_id(String product_id) {
    this.product_id = product_id;
  }

  public String getBrand_id() {
    return brand_id;
  }

  public void setBrand_id(String brand_id) {
    this.brand_id = brand_id;
  }
}