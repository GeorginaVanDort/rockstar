import org.sql2o.*;
import org.junit.*;
import static org.junit.Assert.*;
import java.util.Arrays;
import java.util.List;
import java.util.ArrayList;


public class StylistTest {

  @Before
    public void setUp() {
      DB.sql2o = new Sql2o("jdbc:postgresql://localhost:5432/hair_salon_test", null, null);
    }

  @After
  public void tearDown() {
    try(Connection con = DB.sql2o.open()) {
      String deleteStylistQuery = "DELETE FROM stylists *;";
      String deleteClientQuery = "DELETE FROM clients *;";
      con.createQuery(deleteStylistQuery).executeUpdate();
      con.createQuery(deleteClientQuery).executeUpdate();
    }
  }
