package com.inventory.lib.schemas.relationships;

public class CustomerShipment {
  private String invoice_id;
  private String shipping_id;

  public CustomerShipment(String invoice_id, String shipping_id) {
    this.invoice_id = invoice_id;
    this.shipping_id = shipping_id;
  }

  public String getInvoice_id() {
    return invoice_id;
  }

  public void setInvoice_id(String invoice_id) {
    this.invoice_id = invoice_id;
  }

  public String getShipping_id() {
    return shipping_id;
  }

  public void setShipping_id(String shipping_id) {
    this.shipping_id = shipping_id;
  }
}