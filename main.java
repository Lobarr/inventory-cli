import com.inventory.lib.utils.DB;
import com.inventory.cli.Menu;

class Main {
  public static void main(String args[]) {
    var db = new DB();
    try {      
      Menu.run(db.connection());
    } catch(Exception e) {
      System.out.println(e.getMessage());
    } finally {
      db.close();
    }
  }
}