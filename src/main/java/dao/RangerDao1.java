package dao;

import models.Database;
import models.Ranger;
import models.Sighting;
import org.sql2o.Connection;

import java.util.List;

public interface RangerDao1 {
    static List<Ranger> all(){
        try(Connection con = Database.sql2o.open()){
            return con.createQuery("SELECT * FROM rangers")
                    .executeAndFetch(Ranger.class);
        }
    }

    static Ranger find(int searchId){
        try(Connection con = Database.sql2o.open()){
            return con.createQuery("SELECT * FROM rangers WHERE id=:id")
                    .addParameter("id",searchId)
                    .executeAndFetchFirst(Ranger.class);
        }
    }

    @Override
    boolean equals(Object o);

    @Override
    int hashCode();

    int getId();

    String getName();

    //DAO OPERATIONS
    void save();

    List<Sighting> mySightings();

    void delete();
}
