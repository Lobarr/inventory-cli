package com.inventory.lib.schemas.relationships;

public class Supplies {
  private String supplier_id;
  private String product_id;

  public Supplies(String supplier_id, String product_id) {
    this.supplier_id = supplier_id;
    this.product_id = product_id;
  }

  public String getSupplier_id() {
    return supplier_id;
  }

  public void setSupplier_id(String supplier_id) {
    this.supplier_id = supplier_id;
  }

  public String getProduct_id() {
    return product_id;
  }

  public void setProduct_id(String product_id) {
    this.product_id = product_id;
  }
}