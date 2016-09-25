package models;

import java.util.ArrayList;
import java.util.List;
import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.Id;

import play.data.validation.Constraints.Required;
import play.db.ebean.Model;
@Entity
public class Budzet extends Model {

  @Id
  public Long id;

  @Required
  public String opis;
  @Required
  public double cifra;
  public double suma;
  @Required
  public Date datum = new Date();

  public static Finder<Long, Budzet> find = new Finder<Long, Budzet>(Long.class, Budzet.class);

  public static List<Budzet> all() {
    return find.all();
  }

  public static void create(Budzet budzet) {
    budzet.save();
  }
  public static void delete(Long id) {
    find.ref(id).delete();
  }
  public static Budzet update(Long id) {
    return find.ref(id);
  }
}