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

  @Test
  public void Stylist_instantiatesCorrectly_true() {
    Stylist testStylist = new Stylist("David Bowie");
    assertEquals(true, testStylist instanceof Stylist);
  }

  @Test
  public void Stylist_instantiatesWithName_String() {
    Stylist myStylist = new Stylist("David Bowie");
    assertEquals("David Bowie", myStylist.getName());
  }

  @Test
   public void equals_returnsTrueIfNamesAretheSame() {
     Stylist firstStylist = new Stylist("David Bowie");
     Stylist secondStylist = new Stylist("David Bowie");
     assertTrue(firstStylist.equals(secondStylist));
   }

   @Test
    public void save_savesIntoDatabase_true() {
      Stylist myStylist = new Stylist("David Bowie");
      myStylist.save();
      assertTrue(Stylist.all().get(0).equals(myStylist));
    }

}
