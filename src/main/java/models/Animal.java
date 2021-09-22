package models;

import org.sql2o.Connection;
import org.sql2o.Sql2o;
import org.sql2o.Sql2oException;

import java.util.List;
import java.util.Objects;

@SuppressWarnings("ALL")
public abstract class Animal {

    Sql2o sql2o = new Sql2o("jdbc:postgresql://ec2-44-198-223-154.compute-1.amazonaws.com:5432:5432/daa7t7lmet2sou?ssl=true&sslfactory=org.postgresql.ssl.NonValidatingFactory", "zmpkonnizwzucd","c795ec211f480b77e7d471dbf87206d4dce5b428b0f59f062fb4da72c5bda9f3");
    public int id;
    public String name;
    public String health;
    public String age;
    public String species;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Animal animal = (Animal) o;
        return  Objects.equals(name, animal.name) &&
                Objects.equals(health, animal.health) &&
                Objects.equals(age, animal.age) &&
                Objects.equals(species, animal.species);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, health, age, species);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getHealth() {
        return health;
    }

    public void setHealth(String health) {
        this.health = health;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getSpecies() {
        return species;
    }

    //  DAO
    public void save(){
        try(Connection con = Database.sql2o.open()) {
            String sql = "INSERT INTO animals(name,health, age, species) values (:name,:health,:age,:species)";
            this.id = (int) con.createQuery(sql,true)
                    .addParameter("name", this.name)
                    .addParameter("health", this.health)
                    .addParameter("age",this.age)
                    .addParameter("species",this.species)
                    .executeUpdate()
                    .getKey();
        }catch (Sql2oException ex) {
            System.out.println(ex);
        }
    }

    public static List<String> allAnimalNames(){
        try(Connection con = Database.sql2o.open()){
            return con.createQuery("SELECT name FROM animals")
                    .executeAndFetch(String.class);
        }
    }

}