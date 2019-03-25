import com.inventory.utils.DB;

class Main {
  public static void main(String args[]) {
    var db = new DB();
    try {
      
    } catch(Exception e) {
      System.out.println(e.getMessage());
    } finally {
      db.close();
    }
  }
}