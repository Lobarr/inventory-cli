import com.inventory.utils.DB;

import java.util.UUID;
import java.util.ArrayList;

import com.inventory.lib.Invoice;
import com.inventory.lib.InvoiceActions;

class Main {
  public static void main(String args[]) {
    var db = new DB();
    try {
      // Invoice test = InvoiceActions.getInvoice(db.connection(), "ea47e732-2133-4252-b230-6070ae7054be");
      // test.print();
      // test.setName("ye");
      // System.out.println(test.getName());
      // InvoiceActions.updateInvoice(db.connection(), test);
      // test = InvoiceActions.getInvoice(db.connection(), "ea47e732-2133-4252-b230-6070ae7054be");
      // test.print();

      // Invoice test = new Invoice("test", "test", 31234234234f);
      
      ArrayList<Invoice> invoices = InvoiceActions.getAll(db.connection());
      for (Invoice invoice : invoices) {
        invoice.print();
        System.out.println("\n");
      }
    } catch(Exception e) {
      System.out.println(e.getMessage());
    } finally {
      db.close();
    }
  }
}