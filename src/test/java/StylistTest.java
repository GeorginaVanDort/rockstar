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
    Stylist testStylist = new Stylist("David Bowie");
    assertEquals("David Bowie", testStylist.getName());
  }

  @Test
  public void equals_returnsTrueIfNamesAretheSame() {
     Stylist firstStylist = new Stylist("David Bowie");
     Stylist secondStylist = new Stylist("David Bowie");
     assertTrue(firstStylist.equals(secondStylist));
   }

  @Test
  public void save_savesIntoDatabase_true() {
    Stylist testStylist = new Stylist("David Bowie");
    testStylist.save();
    assertTrue(Stylist.all().get(0).equals(testStylist));
  }

  @Test
    public void all_returnsAllInstancesOfStylist_true() {
      Stylist firstStylist = new Stylist("David Bowie");
      firstStylist.save();
      Stylist secondStylist = new Stylist("Iggy Pop");
      secondStylist.save();
      assertEquals(true, Stylist.all().get(0).equals(firstStylist));
      assertEquals(true, Stylist.all().get(1).equals(secondStylist));
    }

    @Test
    public void save_assignsIdToObject_True() {
      Stylist testStylist = new Stylist("David Bowie");
      testStylist.save();
      Stylist savedStylist = Stylist.all().get(0);
      assertEquals(testStylist.getId(), savedStylist.getId());
    }

    @Test
    public void getId_stylistsInstantiateWithAnId_True() {
      Stylist testStylist = new Stylist("David Bowie");
      testStylist.save();
      assertTrue(testStylist.getId() > 0);
    }

    @Test
    public void find_returnsStylistWithSameId_secondStylist() {
      Stylist firstStylist = new Stylist("David Bowie");
      firstStylist.save();
      Stylist secondStylist = new Stylist("David Bowie");
      secondStylist.save();
      assertEquals(Stylist.find(secondStylist.getId()), secondStylist);
    }

    @Test
    public void getClients_retrievesALlClientsFromDatabase_ClientsList() {
      Stylist testStylist = new Stylist("David Bowie");
      testStylist.save();
      Client firstClient = new Client("Fred", "503-111-1111", testStylist.getId());
      firstClient.save();
      Client secondClient = new Client("Fred", "503-111-1111", testStylist.getId());
      secondClient.save();
      Client[] clients = new Client[] { firstClient, secondClient };
      assertTrue(testStylist.getClients().containsAll(Arrays.asList(clients)));
    }


}
