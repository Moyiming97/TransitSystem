
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import view.Window;

public class Main {

  public static void main(String[] args) throws ClassNotFoundException, SQLException {
    Class.forName("org.postgresql.Driver");
    Connection connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/postgres", "postgres", "900811");
    final Window window = new Window(connection);
  }
}
