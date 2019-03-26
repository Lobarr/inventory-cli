package com.inventory.lib.schemas.relationships;

public class ProductCategory {
  private String product_id;
  private String category_id;

  public ProductCategory(String product_id, String category_id){
    this.product_id = product_id;
    this.category_id = category_id;
  }

  public String getProduct_id() {
    return product_id;
  }

  public void setProduct_id(String product_id) {
    this.product_id = product_id;
  }

  public String getCategory_id() {
    return category_id;
  }

  public void setCategory_id(String category_id) {
    this.category_id = category_id;
  }
}