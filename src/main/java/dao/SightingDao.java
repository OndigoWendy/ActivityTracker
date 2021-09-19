package dao;

import models.Sighting;

import java.util.List;

public interface SightingDao {

    // LIST
    List<Sighting> getAll();

    // CREATE
    void add(Sighting sighting);

    // READ
    Sighting findById(int id);

   // UPDATE
//    void update(int id, String content, int categoryId,String description, boolean endangered , String animal,int rangerId,String age,String health,String zone);

    // DELETE
    void deleteById(int id);
    void clearAllSightings();

    void update( String newDescription, int newCategoryId,String newAnimal,String newAge,String newHealth,String newZone);

    void updateSingle(int sightingToEditId, String newDescription, int newCategoryId, String newAnimal, String newAge, String newHealth, String newZone);
}