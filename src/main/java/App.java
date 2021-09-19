import java.util.HashMap;
import java.util.List;
import java.util.Map;

import dao.Sql2oRangerDao;
import dao.Sql2oSightingDao;
import models.Ranger;
import models.Sighting;
import org.sql2o.Sql2o;
import spark.ModelAndView;
import spark.template.handlebars.HandlebarsTemplateEngine;
import static spark.Spark.*;


@SuppressWarnings("MismatchedQueryAndUpdateOfCollection")
public class App {
    //static int getHerokuAssignedPort() {
//    ProcessBuilder processBuilder = new ProcessBuilder();
//    if (processBuilder.environment().get("PORT") != null) {
//      return Integer.parseInt(processBuilder.environment().get("PORT"));
//    }
//    return 4567; //return default port if heroku-port isn't set (i.e. on localhost)
//  }

    public static void main(String[] args) {
        staticFileLocation("/public");


        //After
//To run app locally
        String connectionString = "jdbc:postgresql://localhost:5432/wildlife_tracker";
        Sql2o sql2o = new Sql2o(connectionString, "moringa", "Access1");

        Sql2oSightingDao sightingDao = new Sql2oSightingDao(sql2o);
        Sql2oRangerDao rangerDao = new Sql2oRangerDao(sql2o);


        //get: ranger view and form
        get("/rangers/new", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            List<Ranger> rangers = rangerDao.getAll();
            model.put("rangers", rangers);
            return new ModelAndView(model, "ranger-form.hbs"); //new layout
        }, new HandlebarsTemplateEngine());

        //post: ranger registration inputs
        post("/rangers", (req, res) -> { //new
            Map<String, Object> model = new HashMap<>();
            String name = req.queryParams("name");
           Ranger newRanger = new Ranger(name);
            rangerDao.add(newRanger);
            res.redirect("/");
            return null;
        }, new HandlebarsTemplateEngine());

        //get: form to record sightings
        get("/sightings/new", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            List<Sighting> sightings = sightingDao.getAll();
            model.put("sightings", sightings);
            return new ModelAndView(model, "sighting-form.hbs");
        }, new HandlebarsTemplateEngine());

//post: sighting  inputs
        post("/sightings", (req, res) -> { //new
            Map<String, Object> model = new HashMap<>();
            String description = req.queryParams("description");
            int categoryId = Integer.parseInt(req.queryParams("categoryId"));
            String animal = req.queryParams("animal");
            String age = req.queryParams("age");
            String health = req.queryParams("health");
            String zone = req.queryParams("zone");
            Sighting newSighting = new Sighting( description, categoryId, animal,age,health,zone);
            sightingDao.add(newSighting);
            res.redirect("/");
            return null;
        }, new HandlebarsTemplateEngine());

//        //get: all rangers and their sightings
//        get("/", (req, res) -> {
//            Map<String, Object> model = new HashMap<>();
//            List<Ranger> allRangers = rangerDao.getAll();
//            model.put("rangers", allRangers);
//            List<Sighting> sightings = sightingDao.getAll();
//            model.put("sightings", sightings);
//            return new ModelAndView(model, "index.hbs");
//        }, new HandlebarsTemplateEngine());
//
//
//
//        //get  specific ranger and their sighting
//        get("/rangers/:id", (req, res) -> {
//            Map<String, Object> model = new HashMap<>();
//            int idOfRangerToFind = Integer.parseInt(req.params("id")); //new
//            Ranger foundRanger = rangerDao.findById(idOfRangerToFind);
//            model.put("ranger", foundRanger);
//            List<Sighting> allSightingsByRanger = rangerDao.getAllSightingsByRanger(idOfRangerToFind);
//            model.put("sightings", allSightingsByRanger);
//            model.put("rangers", rangerDao.getAll());
//            return new ModelAndView(model, "ranger-detail.hbs"); //new
//        }, new HandlebarsTemplateEngine());
//
//
//
//        //task: post sightings
//        post("/sightings/new", (req, res) -> { //URL to make new task on POST route
//            Map<String, Object> model = new HashMap<>();
//         List<Ranger> allRangers = rangerDao.getAll();
//           model.put("rangers", allRangers);
////            List<Sighting> sightings = sightingDao.getAll();
////            model.put("sightings", sightings);
//            String description = req.queryParams("description");
//            int categoryId = Integer.parseInt(req.queryParams("categoryId"));
//            String animal = req.queryParams("animal");
//            String age = req.queryParams("age");
//            String health = req.queryParams("health");
//            String zone = req.queryParams("zone");
//            Sighting newSighting = new Sighting( description, categoryId, animal,age,health,zone);
//            sightingDao.add(newSighting);
//            res.redirect("/");
//            return null;
//        }, new HandlebarsTemplateEngine());
//
//        //get: show sightings by rangers
//        get("/rangers/:category_id/sightings/:sighting_id", (req, res) -> {
//            Map<String, Object> model = new HashMap<>();
//            int idOfSightingToFind = Integer.parseInt(req.params("sighting_id")); //pull id - must match route segment
//            Sighting foundSighting = sightingDao.findById(idOfSightingToFind); //use it to find task
//            int idOfRangerToFind = Integer.parseInt(req.params("category_id"));
//            Ranger foundRanger = rangerDao.findById(idOfRangerToFind);
//            model.put("ranger", foundRanger);
//            model.put("sighting", foundSighting); //add it to model for template to display
//            model.put("rangers", rangerDao.getAll()); //refresh list of links for navbar
//            return new ModelAndView(model, "sighting-detail.hbs"); //individual task page.
//        }, new HandlebarsTemplateEngine());
//
//    }
    }
}