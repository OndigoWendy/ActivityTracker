package dao;

import models.Ranger;
import models.Sighting;
import java.util.List;

public interface RangerDao {

    //LIST
    List<Ranger> getAll();
    List<Sighting> getAllSightingsByRanger(int categoryId);

    //CREATE
    void add (Ranger ranger);

    //READ
    Ranger findById(int id);

    //UPDATE
    void update(int id, String ranger,String zone,String name);

    //DELETE
    void deleteById(int id);
    void clearAllRangers();
}
