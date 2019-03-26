package com.inventory.lib.schemas.relationships;

public class ProductOrder {
  private String product_id;
  private String supplier_id;
  private int stock;

  public ProductOrder(String product_id, String supplier_id, int stock) {
    this.product_id = product_id;
    this.supplier_id = supplier_id;
    this.stock = stock;
  }

  public String getProduct_id() {
    return product_id;
  }

  public void setProduct_id(String product_id) {
    this.product_id = product_id;
  }

  public String getSupplier_id() {
    return supplier_id;
  }

  public void setSupplier_id(String supplier_id) {
    this.supplier_id = supplier_id;
  }

  public int getStock() {
    return stock;
  }

  public void setStock(int stock) {
    this.stock = stock;
  }

  
}