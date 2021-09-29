import java.util.HashMap;
import java.util.List;
import java.util.Map;


import dao.RangerDao;
import models.Endangered;
import models.NonEndangered;
import models.Ranger;
import models.Sighting;
import spark.ModelAndView;
import spark.template.handlebars.HandlebarsTemplateEngine;
import static spark.Spark.*;


@SuppressWarnings("MismatchedQueryAndUpdateOfCollection")
public class App {
    static int getHerokuAssignedPort() {
        ProcessBuilder processBuilder = new ProcessBuilder();
        if (processBuilder.environment().get("PORT") != null) {
            return Integer.parseInt(processBuilder.environment().get("PORT"));
        }
        return 4567;
    }

    public static void main(String[] args) {

        port(getHerokuAssignedPort());
        staticFileLocation("/public");


        //get: index page
        get("/",(request, response) -> {
            Map<String, Object> model = new HashMap<>();
            List<Sighting> allSightings = Sighting.all();
            model.put("sightings", allSightings);
            return new ModelAndView(model,"index.hbs");
        },new HandlebarsTemplateEngine());

        //get:endangered animals
        get("/animals/endangered",(request, response) -> {
            Map<String, Object> model = new HashMap<>();
            model.put("endangered", Endangered.all());
            return new ModelAndView(model,"endangered.hbs");
        },new HandlebarsTemplateEngine());

        //get: non-endangered animals
        get("/animals/not-endangered",(request, response) -> {
            Map<String, Object> model = new HashMap<>();
            model.put("normal", NonEndangered.all());
            return new ModelAndView(model,"nonendangered.hbs");
        },new HandlebarsTemplateEngine());

        //get: new sighting form-view
        get("/sighting/new",(request, response) -> {
            Map<String, Object> model = new HashMap<>();
            model.put("sightings", Sighting.all());
            return new ModelAndView(model,"sighting-form.hbs");
        },new HandlebarsTemplateEngine());

        //Post: post sighting
        post("/sighting/new",(request, response) -> {
            Map<String, Object> model = new HashMap<>();
            String rangerName = request.queryParams("rangerName").trim();
            String animalName = request.queryParams("animalName").trim();
            String animalAge = request.queryParams("animalAge").trim();
            String animalHealth = request.queryParams("animalHealth").trim();
            String location = request.queryParams("location").trim();
            String animalType = request.queryParams("animalType").trim();

            Ranger newRanger = new Ranger(rangerName);
            newRanger.save();

            if(animalType.equalsIgnoreCase("Endangered")){
                Endangered endangered = new Endangered(animalName,animalHealth,animalAge);
                endangered.save();
                Sighting newSighting = new Sighting(endangered.getName(),location,newRanger.getId());
                newSighting.save();
            }
            else{
                NonEndangered nonEndangered = new NonEndangered(animalName,animalHealth,animalAge);
                nonEndangered.save();
                Sighting newSighting = new Sighting(nonEndangered.getName(),location,newRanger.getId());
                newSighting.save();
            }
            return new ModelAndView(model,"success.hbs");
        },new HandlebarsTemplateEngine());

        //get: sightings per location view
        get("/sightings",(request, response) -> {
            Map<String, Object> model = new HashMap<>();
            model.put("sightings", Sighting.all());
            return new ModelAndView(model,"sighting-locations.hbs");
        },new HandlebarsTemplateEngine());

        //get:sightings per location form
        get("/sightings/:location/details",(request, response) -> {
            Map<String, Object> model = new HashMap<>();
            String filter = request.params("location");
            model.put("location",filter);
            model.put("sightings", Sighting.getAllSightingsInLocation(filter));
            return new ModelAndView(model,"sighting-location-details.hbs");
        },new HandlebarsTemplateEngine());

        //get: ranger sightings
        get("/rangers/:id/details",(request, response) -> {
            Map<String, Object> model = new HashMap<>();
            int id = Integer.parseInt(request.params("id"));
            Ranger foundRanger = RangerDao.find(id);
            List<Sighting> mySightings = foundRanger.mySightings();
            model.put("ranger",foundRanger);
            model.put("sightings",mySightings);
            return new ModelAndView(model,"rangerlist.hbs");
        },new HandlebarsTemplateEngine());

        //get: all rangers
        get("/rangers",(request, response) -> {
            Map<String, Object> model = new HashMap<>();
            model.put("rangers", RangerDao.all());
            return new ModelAndView(model,"rangerlist.hbs");
        },new HandlebarsTemplateEngine());

        //get:sighting per ranger
        get("/rangers/:id/sighting/new",(request, response) -> {
            Map<String, Object> model = new HashMap<>();
            int id = Integer.parseInt(request.params("id"));
            Ranger specificRanger = RangerDao.find(id);
            model.put("specificRanger",specificRanger);
            model.put("sightings", Sighting.all());
            return new ModelAndView(model,"sighting-form.hbs");
        },new HandlebarsTemplateEngine());

        //post:sighting per ranger
        post("/rangers/:id/sighting/new",(request, response) -> {
            Map<String, Object> model = new HashMap<>();
            int id = Integer.parseInt(request.params("id"));
            Ranger specificRanger = RangerDao.find(id);
            String animalName = request.queryParams("animalName").trim();
            String animalAge = request.queryParams("animalAge").trim();
            String animalHealth = request.queryParams("animalHealth").trim();
            String location = request.queryParams("location").trim();
            String animalType = request.queryParams("animalType").trim();

            if(animalType.equalsIgnoreCase("Endangered")){
                Endangered endangered = new Endangered(animalName,animalHealth,animalAge);
                endangered.save();
                Sighting newSighting = new Sighting(endangered.getName(),location,specificRanger.getId());
                newSighting.save();
            }
            else{
                NonEndangered nonEndangered = new NonEndangered(animalName,animalHealth,animalAge);
                nonEndangered.save();
                Sighting newSighting = new Sighting(nonEndangered.getName(),location,specificRanger.getId());
                newSighting.save();
            }

            return new ModelAndView(model,"success.hbs");
        },new HandlebarsTemplateEngine());

    }
    }
