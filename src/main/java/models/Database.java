package models;

import org.sql2o.Sql2o;

public class Database {

//  public static Sql2o sql2o = new Sql2o("jdbc:postgresql://localhost:5432/wildlife_tracker"," moringa", "Access1");
    ////To run app locally
      public static String connectionString = "jdbc:postgresql://localhost:5432/wildlife_tracker";
   public static    Sql2o sql2o = new Sql2o(connectionString, "moringa", "Access1");

 //   public static Sql2o sql2o = new Sql2o("jdbc:postgresql://ec2-44-198-223-154.compute-1.amazonaws.com:5432/daa7t7lmet2sou?ssl=true&sslfactory=org.postgresql.ssl.NonValidatingFactory", "zmpkonnizwzucd","c795ec211f480b77e7d471dbf87206d4dce5b428b0f59f062fb4da72c5bda9f3");


}
