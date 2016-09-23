import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import org.sql2o.*;


public class Stylist {
  private String name;
  private int id;
  private String imgurl;

  public Stylist (String name, String imgurl) {
    this.name = name;
    this.imgurl = imgurl;
  }

  public String getName(){
    return name;
  }

  public String getImgurl(){
    return imgurl;
  }

  public int getId() {
    return id;
  }

  public static List<Stylist> all() {
    String sql = "SELECT id, name FROM stylists";
    try(Connection con = DB.sql2o.open()) {
      return con.createQuery(sql).executeAndFetch(Stylist.class);
    }
  }

  @Override
  public boolean equals(Object otherStylist) {
    if (!(otherStylist instanceof Stylist)) {
      return false;
    } else {
      Stylist newStylist = (Stylist) otherStylist;
      return this.getName().equals(newStylist.getName());
    }
  }

  public void save() {
    try(Connection con = DB.sql2o.open()) {
      String sql = "INSERT INTO stylists (name, imgurl) VALUES (:name, :imgurl)";
      this.id = (int) con.createQuery(sql, true)
        .addParameter("name", this.name)
        .addParameter("imgurl", this.imgurl)
        .executeUpdate()
        .getKey();
  }
}

  public static Stylist find(int id) {
    try(Connection con = DB.sql2o.open()) {
        String sql = "SELECT * FROM stylists where id=:id";
        Stylist stylist = con.createQuery(sql)
          .addParameter("id", id)
          .executeAndFetchFirst(Stylist.class);
        return stylist;
      }
    }

  public List<Client> getClients() {
    try(Connection con = DB.sql2o.open()) {
      String sql = "SELECT * FROM clients where stylist_id=:id";
      return con.createQuery(sql)
        .addParameter("id", this.id)
        .executeAndFetch(Client.class);
    }
  }

  public void update(String name, String imgurl) {
    try(Connection con = DB.sql2o.open()) {
    String sql = "UPDATE stylists SET name = :name, imgurl = :imgurl WHERE id = :id";
    con.createQuery(sql)
      .addParameter("name", name)
      .addParameter("imgurl", imgurl)
      .addParameter("id", id)
      .executeUpdate();
    }
  }

  public void delete() {
    try(Connection con = DB.sql2o.open()) {
    String sql = "DELETE FROM stylists WHERE id = :id;";
    con.createQuery(sql)
      .addParameter("id", id)
      .executeUpdate();
    }
  }










}
