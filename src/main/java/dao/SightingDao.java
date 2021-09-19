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


}