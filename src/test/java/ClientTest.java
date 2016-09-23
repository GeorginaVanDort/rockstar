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
    Client testClient = new Client("Fred", "503-111-1111", 1);
    assertEquals("Fred", testClient.getName());
  }

  @Test
  public void Client_instantiatesWithPhone_String() {
    Client testClient = new Client("Fred", "503-111-1111", 1);
    assertEquals("503-111-1111", testClient.getPhone());
  }

  @Test
  public void Client_instantiatesWithStylistId_Int() {
    Client testClient = new Client("Fred", "503-111-1111", 1);
    assertEquals(1, testClient.getStylistId());
  }

  @Test
  public void Client_instantiatesWithId_True() {
    Client testClient = new Client("Fred", "503-111-1111", 1);
    testClient.save();
    assertTrue(testClient.getId() > 0);
  }

  @Test
  public void equals_returnsTrueIfClientsAretheSame_True() {
    Client oneClient = new Client("Fred", "503-111-1111", 1);
    oneClient.save();
    Client twoClient = new Client("Fred", "503-111-1111", 1);
    twoClient.save();
    assertEquals(true, Client.all().get(0).equals(oneClient));
    assertEquals(true, Client.all().get(1).equals(twoClient));
  }

  @Test
  public void save_returnsTrueIfClientsAretheSame_True() {
    Client testClient = new Client("Fred", "503-111-1111", 1);
    testClient.save();
    assertTrue(Client.all().get(0).equals(testClient));
  }

  @Test
  public void save_assignsIdToObject_True() {
    Client testClient = new Client("Fred", "503-111-1111", 1);
    testClient.save();
    Client savedClient = Client.all().get(0);
    assertEquals(testClient.getId(), savedClient.getId());
  }

  @Test
  public void find_returnsClientWithSameId_secondClient() {
    Client firstClient = new Client("Fred", "503-111-1111", 1);
    firstClient.save();
    Client secondClient = new Client("Fred", "503-111-1111", 1);
    secondClient.save();
    assertEquals(Client.find(secondClient.getId()), secondClient);
  }

  @Test
  public void update_updatesClientDescription_true() {
    Client testClient = new Client("Fred", "503-111-1111", 1);
    testClient.save();
    testClient.update("Jim", "503-111-2222");
    assertEquals("Jim", Client.find(testClient.getId()).getName());
  }

  @Test
  public void delete_deletesClient_true() {
    Client testClient = new Client("Fred", "503-111-1111", 1);
    testClient.save();
    int testClientId = testClient.getId();
    testClient.delete();
    assertEquals(null, Client.find(testClientId));
  }

  @Test
  public void save_savesStylistIdIntoDB_true() {
    Stylist testStylist = new Stylist("David Bowie");
    testStylist.save();
    Client testClient = new Client("Fred", "503-111-1111", testStylist.getId());
    testClient.save();
    Client savedClient = Client.find(testClient.getId());
    assertEquals(savedClient.getStylistId(), testStylist.getId());
  }

}
