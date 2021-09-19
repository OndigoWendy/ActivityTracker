//package models;
//
//import org.junit.Test;
//
//import java.time.LocalDateTime;
//
//import static org.junit.Assert.*;
//public class SightingTest {
//
//    //helper methods
//    public Sighting setupNewSighting(){
//        return new Sighting("Endangered", 1 ,"LION","age","health","A");
//    }
//    @Test
//    public void NewTaskObjectGetsCorrectlyCreated_true() throws Exception {
//        Sighting sighting = setupNewSighting();
//        assertEquals(true, sighting instanceof Sighting);
//    }
//    @Test
//    public void TaskInstantiatesWithDescription_true() throws Exception {
//        Sighting sighting = setupNewSighting();
//        assertEquals("Endangered", sighting.getDescription());
//        assertEquals("1", sighting.getCategoryId());
//        assertEquals("LION", sighting.getAnimal());
//        assertEquals("age", sighting.getAge());
//        assertEquals("health", sighting.getHealth());
//        assertEquals("A", sighting.getZone());
//    }
//
//    @Test
//    public void isCompletedPropertyIsFalseAfterInstantiation() throws Exception {
//        Sighting sighting = setupNewSighting();
//        assertEquals(false, sighting.getCompleted()); //should never start as completed
//    }
//
//    @Test
//    public void getCreatedAtInstantiatesWithCurrentTimeToday() throws Exception {
//        Sighting sighting = setupNewSighting();
//        assertEquals(LocalDateTime.now().getDayOfWeek(), sighting.getCreatedAt().getDayOfWeek());
//    }
//
//
//}
//
