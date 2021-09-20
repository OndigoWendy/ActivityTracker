 SET MODE PostgreSQL;

 CREATE DATABASE wildlife_tacker IF NOT EXISTS;

\c wildlife_tracker;
 CREATE DATABASE wildlife_tracker_test  WITH TEMPLATE wildlife_tracker;

 CREATE TABLE rangers (id  SERIAL PRIMARY KEY, name TEXT);

   CREATE TABLE animals (id  SERIAL PRIMARY KEY, name TEXT,health TEXT,age TEXT,species TEXT);

  CREATE TABLE sightings (id  SERIAL PRIMARY KEY,animalname TEXT,location TEXT,rangerid INTEGER);


