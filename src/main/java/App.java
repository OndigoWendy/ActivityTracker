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

    public static void main(String[] args) { //type “psvm + tab” to autocreate this
        staticFileLocation("/public");

        //  @Before
        //  String connectionString = "jdbc:h2:~/todolist.db;INIT=RUNSCRIPT from 'classpath:db/create.sql'";
//        String connectionString = "jdbc:h2:~/todolist.db;INIT=RUNSCRIPT from 'classpath:db/create.sql'";
//        Sql2o sql2o = new Sql2o(connectionString, "moringa", "Access1");

//       //After
//To run app locally
        String connectionString = "jdbc:postgresql://localhost:5432/wildlife_tracker"; //connect to todolist, not todolist_test!
        Sql2o sql2o = new Sql2o(connectionString, "moringa", "Access1");

        //heroku
//    String connectionString = "jdbc:postgresql://ctltbvmicxbhgj:8b436bdaa113916123ce31199313cbd745d79500c88b40c3d7a371582cbaab53@ec2-54-147-126-173.compute-1.amazonaws.com:5432/d7l3nhtu97jegi:5432/d7l3nhtu97jegi"; //!
//    Sql2o sql2o = new Sql2o(connectionString, "d7l3nhtu97jegi", "8b436bdaa113916123ce31199313cbd745d79500c88b40c3d7a371582cbaab53"); //!

        Sql2oSightingDao sightingDao = new Sql2oSightingDao(sql2o);
        Sql2oRangerDao rangerDao = new Sql2oRangerDao(sql2o);


        //get: show all tasks in all categories and show all categories
        get("/", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            List<Ranger> allRangers = rangerDao.getAll();
            model.put("rangers", allRangers);
            List<Sighting> sightings = sightingDao.getAll();
            model.put("sightings", sightings);
            return new ModelAndView(model, "index.hbs");
        }, new HandlebarsTemplateEngine());

        //get: show a form to create a new category
        get("/rangers/new", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            List<Ranger> rangers = rangerDao.getAll(); //refresh list of links for navbar
            model.put("rangers", rangers);
            return new ModelAndView(model, "ranger-form.hbs"); //new layout
        }, new HandlebarsTemplateEngine());

        //post: process a form to create a new category
        post("/rangers", (req, res) -> { //new
            Map<String, Object> model = new HashMap<>();
            String name = req.queryParams("name");
//            String zone = req.queryParams("zone");
//            String ranger = req.queryParams("ranger");
         //   Ranger newRanger = new Ranger(name,zone,ranger);
           Ranger newRanger = new Ranger(name);
            rangerDao.add(newRanger);
            res.redirect("/");
            return null;
        }, new HandlebarsTemplateEngine());


        //get: delete all categories and all tasks
        get("/rangers/delete", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            rangerDao.clearAllRangers();
            sightingDao.clearAllSightings();
            res.redirect("/");
            return null;
        }, new HandlebarsTemplateEngine());

        //get: delete all tasks
        get("/sightings/delete", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            sightingDao.clearAllSightings();
            res.redirect("/");
            return null;
        }, new HandlebarsTemplateEngine());

        //get a specific category (and the tasks it contains)
        get("/rangers/:id", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            int idOfRangerToFind = Integer.parseInt(req.params("id")); //new
            Ranger foundRanger = rangerDao.findById(idOfRangerToFind);
            model.put("ranger", foundRanger);
            List<Sighting> allSightingsByRanger = rangerDao.getAllSightingsByRanger(idOfRangerToFind);
            model.put("sightings", allSightingsByRanger);
            model.put("rangers", rangerDao.getAll()); //refresh list of links for navbar
            return new ModelAndView(model, "ranger-detail.hbs"); //new
        }, new HandlebarsTemplateEngine());

        //get: show a form to update a category
        get("/rangers/:id/edit", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            model.put("editRanger", true);
            Ranger ranger = rangerDao.findById(Integer.parseInt(req.params("id")));
            model.put("ranger", ranger);
            model.put("rangers", rangerDao.getAll()); //refresh list of links for navbar
            return new ModelAndView(model, "ranger-form.hbs");
        }, new HandlebarsTemplateEngine());

        //post: process a form to update a category
        post("/rangers/:id", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            int idOfRangerToEdit = Integer.parseInt(req.params("id"));
            String newName = req.queryParams("newRangerName");
            String newRanger = req.queryParams("newRangerRanger");
            String newZone= req.queryParams("newRangerZone");
            rangerDao.update(idOfRangerToEdit, newName,newZone,newRanger);
            res.redirect("/");
            return null;
        }, new HandlebarsTemplateEngine());

        //get: delete an individual task
        get("/rangers/:ranger_id/sightings/:sighting_id/delete", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            int idOfSightingToDelete = Integer.parseInt(req.params("sighting_id"));
            sightingDao.deleteById(idOfSightingToDelete);
            res.redirect("/");
            return null;
        }, new HandlebarsTemplateEngine());

        //get: show new task form
        get("/sightings/new", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            List<Ranger> rangers = rangerDao.getAll();
            model.put("rangers", rangers);
            return new ModelAndView(model, "sighting-form.hbs");
        }, new HandlebarsTemplateEngine());

        //task: process new task form
        post("/sightings", (req, res) -> { //URL to make new task on POST route
            Map<String, Object> model = new HashMap<>();
            List<Ranger> allRangers = rangerDao.getAll();
            model.put("rangers", allRangers);
            String description = req.queryParams("description");
            int categoryId = Integer.parseInt(req.queryParams("categoryId"));
            String animal = req.queryParams("animal");
            String age = req.queryParams("age");
            String health = req.queryParams("health");
            String zone = req.queryParams("zone");
            Sighting newSighting = new Sighting( description, categoryId, animal,age,health,zone);        //See what we did with the hard coded categoryId?
            sightingDao.add(newSighting);
            res.redirect("/");
            return null;
        }, new HandlebarsTemplateEngine());

        //get: show an individual task that is nested in a category
        get("/rangers/:category_id/sightings/:sighting_id", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            int idOfSightingToFind = Integer.parseInt(req.params("sighting_id")); //pull id - must match route segment
            Sighting foundSighting = sightingDao.findById(idOfSightingToFind); //use it to find task
            int idOfRangerToFind = Integer.parseInt(req.params("category_id"));
            Ranger foundRanger = rangerDao.findById(idOfRangerToFind);
            model.put("ranger", foundRanger);
            model.put("sighting", foundSighting); //add it to model for template to display
            model.put("rangers", rangerDao.getAll()); //refresh list of links for navbar
            return new ModelAndView(model, "sighting-detail.hbs"); //individual task page.
        }, new HandlebarsTemplateEngine());

        //get: show  form to update sighting
        get("/sightings/:id/edit", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            List<Ranger> allRangers = rangerDao.getAll();
            model.put("rangers", allRangers);
            Sighting sighting = sightingDao.findById(Integer.parseInt(req.params("id")));
            model.put("sighting", sighting);
            model.put("editSighting", true);
            return new ModelAndView(model, "sighting-form.hbs");
        }, new HandlebarsTemplateEngine());

        //task: process form to update a sighting
        post("/sightings/:id", (req, res) -> { //URL to update task on POST route
            Map<String, Object> model = new HashMap<>();
            int sightingToEditId = Integer.parseInt(req.params("id"));
            String newDescription = req.queryParams("description");
            int newCategoryId = Integer.parseInt(req.queryParams("categoryId"));
            String newAnimal = req.queryParams("animal");
            String newAge = req.queryParams("age");
            String newHealth = req.queryParams("health");
            String newZone = req.queryParams("zone");
            sightingDao.updateSingle(sightingToEditId, newDescription, newCategoryId, newAnimal,newAge, newHealth, newZone);  // remember the hardcoded categoryId we placed? See what we've done to/with it?
            res.redirect("/");
            return null;
        }, new HandlebarsTemplateEngine());


//        get("/sighting/new",(request, response) -> {
//            Map<String, Object> model = new HashMap<>();
//            model.put("sightings", Sighting.all());
//            return new ModelAndView(model,"sighting-form.hbs");
//        },new HandlebarsTemplateEngine());

    }
}
