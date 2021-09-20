package models;

import org.sql2o.Sql2o;

public class Database {
//    public static Sql2o sql2o = new Sql2o("jdbc:postgresql://localhost:5432/wildlife_tracker"," moringa", "Access1");
    ////To run app locally
      public static String connectionString = "jdbc:postgresql://localhost:5432/wildlife_tracker";
   public static    Sql2o sql2o = new Sql2o(connectionString, "moringa", "Access1");

}
