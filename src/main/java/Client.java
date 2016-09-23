import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import org.sql2o.*;


public class Client {
  private int id;
  private String name;
  private String phone;
  private int stylist_id;

  public Client(String name, String phone, int stylist_id) {
    this.name = name;
    this.phone = phone;
    this.stylist_id = stylist_id;
  }

  public String getName() {
    return name;
  }

  public int getId() {
    return id;
  }

  public String getPhone() {
    return phone;
  }

  public int getStylistId() {
    return stylist_id;
  }

  public static List<Client> all() {
      String sql = "SELECT id, name, phone, stylist_id FROM clients";
      try(Connection con = DB.sql2o.open()) {
        return con.createQuery(sql).executeAndFetch(Client.class);
      }
    }

  @Override
  public boolean equals(Object otherClient) {
    if (!(otherClient instanceof Client)) {
      return false;
    } else {
      Client newClient = (Client) otherClient;
      return this.getName().equals(newClient.getName())
      && this.getId() == newClient.getId()
      && this.getPhone().equals(newClient.getPhone())
      && this.getStylistId() == newClient.getStylistId();
    }
  }

  public void save() {
    try(Connection con = DB.sql2o.open()) {
      String sql = "INSERT INTO clients(name, phone, stylist_id) VALUES(:name, :phone, :stylist_id)";
      this.id = (int) con.createQuery(sql, true)
        .addParameter("name", this.name)
        .addParameter("phone", this.phone)
        .addParameter("stylist_id", this.stylist_id)
        .executeUpdate()
        .getKey();
      }
    }

  public static Client find(int id) {
  try(Connection con = DB.sql2o.open()) {
    String sql = "SELECT * FROM clients where id=:id";
    Client client = con.createQuery(sql)
      .addParameter("id", id)
      .executeAndFetchFirst(Client.class);
      return client;
    }
  }

  public void delete() {
    try(Connection con = DB.sql2o.open()) {
    String sql = "DELETE FROM clients WHERE id = :id;";
    con.createQuery(sql)
      .addParameter("id", id)
      .executeUpdate();
    }
  }

  public void update(String name, String phone) {
    try(Connection con = DB.sql2o.open()) {
    String sql = "UPDATE clients SET name = :name, phone = :phone WHERE id = :id";
    con.createQuery(sql)
      .addParameter("name", name)
      .addParameter("phone", phone)
      .addParameter("id", id)
      .executeUpdate();
    }
  }





}
