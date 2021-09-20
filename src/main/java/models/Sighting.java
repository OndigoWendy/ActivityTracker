package models;
import java.sql.Timestamp;
import java.util.Objects;
import java.util.Date;


public class Sighting {

    private String description;
    private int categoryId;
    private String animal;
    private int id;
    private String age;
    private String health;
    private String zone;
    private String location;
    private Timestamp timestamp;

    public Sighting(String description, int categoryId, String animal, String age, String health, String zone) {
        this.description = description;
        this.categoryId = categoryId;
        this.animal = animal;
        this.age = age;
        this.health = health;
        this.zone = zone;
//        this.location = location.trim();
//        this.timestamp = new Timestamp(new Date().getTime());

    }



    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Sighting)) return false;
        Sighting sighting = (Sighting) o;
        return id == sighting.id &&
                Objects.equals(description, sighting.description) &&
                Objects.equals(categoryId, sighting.categoryId) &&
                Objects.equals(animal, sighting.animal) &&
                Objects.equals(age, sighting.age) &&
                Objects.equals(health, sighting.health) &&
                Objects.equals(zone, sighting.zone);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, description, categoryId, animal, age, health, zone);
    }

    public int getCategoryId() {
        return categoryId;
    }

    public String getDescription() {
        return description;
    }


    public int getId() {
        return id;
    }

    public String getAge() {
        return age;
    }

    public String getHealth() {
        return health;
    }

    public String getZone() {
        return zone;
    }

    public String getAnimal() {
        return animal;
    }

    public void setId(int id) {
        this.id = id;
    }


}

