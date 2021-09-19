package models;

import org.sql2o.Connection;
import java.time.LocalDateTime;
import java.util.Objects;

import org.sql2o.Sql2o;
import org.sql2o.Sql2oException;

public class Sighting {

    private String description;
    private boolean endangered;
    private boolean completed;
    private LocalDateTime createdAt;
    private int id;
    private int categoryId;
    private String animal;
 //   private int rangerId;
    private String age;
    private String health;
    private String zone;



//    public Sighting(String description, int categoryId , boolean endangered , String animal,int rangerId,String age,String health,String zone){
//
//    }

    public Sighting(String description, int categoryId, String animal, String age, String health, String zone) {
        this.description = description;

        this.createdAt = LocalDateTime.now();
        this.categoryId = categoryId;
//        this.completed = false;
//        this.endangered = false;
        this.animal = animal;
    //    this.rangerId = rangerId;
        this.age = age;
        this.health = health;
         this.zone = zone;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Sighting)) return false;
        Sighting sighting = (Sighting) o;
        return
//                endangered == sighting.endangered &&
                id == sighting.id &&
                Objects.equals(description, sighting.description)&&
              Objects.equals(categoryId, sighting.categoryId)&&
        Objects.equals(animal, sighting.animal)&&
        Objects.equals(age, sighting.age)&&
        Objects.equals(health, sighting.health)&&
                        Objects.equals(zone, sighting.zone);

       // ,animal, sighting.animal,age, sighting.age, health,sighting.health
    }
    @Override
    public int hashCode() {
        return Objects.hash( id,description,categoryId,animal ,age,health,zone);
    }

    public int getCategoryId() {
        return categoryId;
    }

    public String getDescription() {
        return description;
    }

    public boolean getCompleted(){
        return this.completed;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public int getId() {
        return id;
    }

    public String getAge() {
        return age;
    }

    public String getHealth() { return health; }

    public String getZone() {
        return zone;
    }

    public String getAnimal() {
        return animal;
    }

    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setCompleted(boolean completed) {
        this.completed = completed;
    }

    public void setId(int id) {
        this.id = id;
    }



//    public void save(){
//        String sql = "INSERT INTO sightings(description,categoryId,animal,age,health,zone) values (:description,:categoryId,:animal,:age,:health,:zone)";
//        try(Connection con =Database.sql2o.open()){
//            this.id = (int) con.createQuery(sql,true)
//                    .addParameter("description",this.description)
//                    .addParameter("categoryId",this.categoryId)
//                    .addParameter("animal",this.animal)
//                    .addParameter("age",this.age)
//                    .addParameter("health",this.health)
//                    .addParameter("zone",this.zone)
//                    .executeUpdate()
//                    .getKey();
//        } catch (Sql2oException ex) {
//            System.out.println(ex);
//        }
//    }
}

