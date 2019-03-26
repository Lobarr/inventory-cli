package com.inventory.lib.schemas.relationships;

public class CustomerInvoice {
  private String product_id;
  private String invoice_id;

  public CustomerInvoice(String product_id, String invoice_id) {
    this.product_id = product_id;
    this.invoice_id = invoice_id;
  }

  public String getProduct_id() {
    return product_id;
  }

  public void setProduct_id(String product_id) {
    this.product_id = product_id;
  }

  public String getInvoice_id() {
    return invoice_id;
  }

  public void setInvoice_id(String invoice_id) {
    this.invoice_id = invoice_id;
  }
}