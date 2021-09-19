 SET MODE PostgreSQL;

 CREATE DATABASE wildlife_tacker IF NOT EXISTS;

\c wildlife_tracker;


 CREATE TABLE rangers (
 id  SERIAL PRIMARY KEY,
 name  VARCHAR,
  );

 CREATE TABLE sightings (
 id SERIAL PRIMARY KEY,
 description VARCHAR ,
 categoryid INTEGER,
 animal TEXT ,
 age TEXT,
 health TEXT,
 zone TEXT);

 CREATE DATABASE wildlife_tracker_test  WITH TEMPLATE wildlife_tracker;


