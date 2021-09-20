package models;

import org.sql2o.Connection;

import java.util.List;

public class Endangered extends Animal {
    private static final String STATUS = "Endangered";

    public Endangered(String name, String health, String age) {
        this.name = name;
        this.health = health;
        this.age = age;
        this.species = STATUS;
    }

   //DAO OPERATIONS

    public static List<Endangered> all(){
        String sql = "SELECT * FROM animals where species=:species";
        try(Connection con = Database.sql2o.open()) {
            return con.createQuery(sql)
                    .addParameter("species", STATUS)
                    .executeAndFetch(Endangered.class);
        }
    }

    public static Endangered find(int searchId){
        String sql = "SELECT * FROM animals where (id=:id AND species=:species)";
        try(Connection con = Database.sql2o.open()) {
            return con.createQuery(sql)
                    .addParameter("id",searchId)
                    .addParameter("species", STATUS)
                    .executeAndFetchFirst(Endangered.class);
        }
    }

}
