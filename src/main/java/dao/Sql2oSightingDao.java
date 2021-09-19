package dao;


import models.Sighting;
import org.sql2o.*;

import java.util.List;

public class Sql2oSightingDao implements SightingDao {


    private final Sql2o sql2o;

      public Sql2oSightingDao(Sql2o sql2o){
       this.sql2o = sql2o;

   }


//    public void add(Sighting sighting) {
//        String sql = "INSERT INTO sightings (description, categoryId , animal , age , health ,zone) VALUES(:description,:categoryId :animal, :age, :health, :zone )"; //raw sql
//        try(Connection con = sql2o.open()){
//            int id = (int) con.createQuery(sql, true)
//                    .bind(sighting)
//                    .executeUpdate()
//                    .getKey();
//            sighting.setId(id);
//        } catch (Sql2oException ex) {
//            System.out.println(ex); //oops we have an error!
//        }
//    }
@Override
    public void add(Sighting sighting){
        String sql = "INSERT INTO sightings (description, categoryId , animal , age , health ,zone) VALUES(:description,:categoryId :animal, :age, :health, :zone )";
        try(Connection con = sql2o.open()){
            int id = (int) con.createQuery(sql,true)
                    .bind(sighting)
                    .executeUpdate()
                    .getKey();
        } catch (Sql2oException ex) {
            System.out.println(ex);
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


}