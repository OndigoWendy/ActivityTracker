package dao;

import models.Ranger;
import models.Sighting;
import org.sql2o.Connection;
import org.sql2o.Sql2o;
import org.sql2o.Sql2oException;
import java.util.List;

public class Sql2oRangerDao implements RangerDao {

    private final Sql2o sql2o;

    public Sql2oRangerDao(Sql2o sql2o) {
        this.sql2o = sql2o;
    }

    @Override
    public void add(Ranger ranger) {
        //  String sql = "INSERT INTO rangers (ranger,zone,name)  VALUES (:ranger :zone :name)";
        String sql = "INSERT INTO rangers (name)  VALUES (:name)";
        try (Connection con = sql2o.open()) {
            int id = (int) con.createQuery(sql, true)
                    .bind(ranger)
                    .executeUpdate()
                    .getKey();
            ranger.setId(id);
        } catch (Sql2oException ex) {
            System.out.println(ex);
        }
    }

    @Override
    public List<Ranger> getAll() {
        try (Connection con = sql2o.open()) {
            return con.createQuery("SELECT * FROM rangers")
                    .executeAndFetch(Ranger.class);
        }
    }




    @Override
    public Ranger findById(int id) {
        try (Connection con = sql2o.open()) {
            return con.createQuery("SELECT * FROM rangers WHERE id = :id")
                    .addParameter("id", id)
                    .executeAndFetchFirst(Ranger.class);
        }
    }

    @Override
    public void update(int id, String newRanger, String newZone, String newName) {
        String sql = "UPDATE rangers SET  name = :name WHERE id=:id";
        try (Connection con = sql2o.open()) {
            con.createQuery(sql)
                    .addParameter("name", newName)
                    .addParameter("id", id)
                    .addParameter("zone", newZone)
                    .addParameter("ranger", newRanger)
                    .executeUpdate();
        } catch (Sql2oException ex) {
            System.out.println(ex);
        }
    }

    @Override
    public void deleteById(int id) {
        String sql = "DELETE FROM rangers WHERE id=:id"; //raw sql
        try (Connection con = sql2o.open()) {
            con.createQuery(sql)
                    .addParameter("id", id)
                    .executeUpdate();
        } catch (Sql2oException ex) {
            System.out.println(ex);
        }
    }

    @Override
    public void clearAllRangers() {
        String sql = "DELETE FROM rangers"; //raw sql
        try (Connection con = sql2o.open()) {
            con.createQuery(sql)
                    .executeUpdate();
        } catch (Sql2oException ex) {
            System.out.println(ex);
        }
    }

   @Override
    public  List<Sighting> getAllSightingsByRanger(int categoryId) {
        String sql = "SELECT * FROM sightings WHERE categoryId = :categoryId";
        try (Connection con = sql2o.open()) {
            return con.createQuery(sql)
                    .addParameter("categoryId", categoryId)
                    .executeAndFetch(Sighting.class);
        }
    }

}