import org.sql2o.*;
import org.junit.*;
import static org.junit.Assert.*;
import java.util.Arrays;
import java.util.List;
import java.util.ArrayList;


public class ClientTest {

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

  @Test
    public void Client_instantiatesCorrectly_true() {
      Client testClient = new Client("Fred", "503-111-1111", 1);
      assertEquals(true, testClient instanceof Client);
    }

    @Test
    public void Client_instantiatesWithName_String() {
      Client myClient = new Client("Fred", "503-111-1111", 1);
      assertEquals("Fred", myClient.getName());
    }

    @Test
    public void Client_instantiatesWithPhone_String() {
      Client myClient = new Client("Fred", "503-111-1111", 1);
      assertEquals("503-111-1111", myClient.getPhone());
    }

    @Test
    public void Client_instantiatesWithStylistId_Int() {
      Client myClient = new Client("Fred", "503-111-1111", 1);
      assertEquals(1, myClient.getStylistId());
    }
}
