package com.inventory.lib.schemas.relationships;

public class SupplierCategory {
  private String supplier_id;
  private String category_id;

  public SupplierCategory(String supplier_id, String category_id) {
    this.supplier_id = supplier_id;
    this.category_id = category_id;
  }

  public String getSupplier_id() {
    return supplier_id;
  }

  public void setSupplier_id(String supplier_id) {
    this.supplier_id = supplier_id;
  }

  public String getCategory_id() {
    return category_id;
  }

  public void setCategory_id(String category_id) {
    this.category_id = category_id;
  }  
}