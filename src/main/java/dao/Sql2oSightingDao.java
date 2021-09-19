package dao;


import models.Sighting;
import org.sql2o.*;

import java.time.LocalDateTime;
import java.util.List;

public class Sql2oSightingDao implements SightingDao { //implementing our interface


    private final Sql2o sql2o;

      public Sql2oSightingDao(Sql2o sql2o){
       this.sql2o = sql2o; //making the sql2o object available everywhere so we can call methods in it

   }

//





    @Override
    public void add(Sighting sighting) {
        String sql = "INSERT INTO sightings (description, categoryId , animal , age , health ,zone) VALUES(:description,:categoryId :animal, :age, :health, :zone )"; //raw sql
        try(Connection con = sql2o.open()){
            int id = (int) con.createQuery(sql, true)
                    .bind(sighting)
                    .executeUpdate()
                    .getKey();
            sighting.setId(id);
        } catch (Sql2oException ex) {
            System.out.println(ex); //oops we have an error!
        }
    }

    @Override
    public List<Sighting> getAll() {
        try(Connection con = sql2o.open()){
            return con.createQuery("SELECT * FROM sightings") //raw sql
                    .executeAndFetch(Sighting.class); //fetch a list
        }
    }

    @Override
    public Sighting findById(int id) {
        try(Connection con = sql2o.open()){
            return con.createQuery("SELECT * FROM sightings WHERE id = :id")
                    .addParameter("id", id) //key/value pair, key must match above
                    .executeAndFetchFirst(Sighting.class); //fetch an individual item
        }
    }



    @Override
    public void update( String newDescription, int newCategoryId,String newAnimal,String newAge,String newHealth,String newZone){
        String sql = "UPDATE sightings SET (description, categoryId, animal,age,health,zone) = (:description, :categoryId ,:animal,:age,:health,:zone) WHERE id=:id";   //raw sql
        try(Connection con = sql2o.open()){
            con.createQuery(sql)
                    .addParameter("description", newDescription)
                    .addParameter("categoryId", newCategoryId)
                    .addParameter("animal", newAnimal)
                    .addParameter("age", newAge)
                    .addParameter("health", newHealth)
                    .addParameter("zone", newZone)
                    .executeUpdate();
        } catch (Sql2oException ex) {
            System.out.println(ex);
        }
    }

    @Override
    public void updateSingle(int sightingToEditId, String newDescription, int newCategoryId, String newAnimal, String newAge, String newHealth, String newZone) {
        String sql = "UPDATE sightings SET (description, categoryId, animal,age,health,zone)  VALUES(:description, :categoryId ,:animal ,:age,:health,:zone) WHERE id=:id";   //raw sql
        try(Connection con = sql2o.open()){
            con.createQuery(sql)
                    .addParameter("description", newDescription)
                    .addParameter("categoryId", newCategoryId)
                    .addParameter("animal", newAnimal)
                    .addParameter("age", newAge)
                    .addParameter("health", newHealth)
                    .addParameter("zone", newZone)
                    .executeUpdate();
        } catch (Sql2oException ex) {
            System.out.println(ex);
        }
    }

    @Override
    public void deleteById(int id) {
        String sql = "DELETE FROM sightings WHERE id=:id";
        try (Connection con = sql2o.open()) {
            con.createQuery(sql)
                    .addParameter("id", id)
                    .executeUpdate();
        } catch (Sql2oException ex){
            System.out.println(ex);
        }
    }

    @Override
    public void clearAllSightings() {
        String sql = "DELETE FROM sightings";
        try (Connection con = sql2o.open()) {
            con.createQuery(sql)
                    .executeUpdate();
        } catch (Sql2oException ex){
            System.out.println(ex);
        }
    }


}