package models;
import org.junit.Rule;
import org.junit.Test;

import static org.junit.Assert.*;

public class EndangeredTest {


    private Endangered newAnimal() {
        return new Endangered("Goat","Healthy","Young");
    }

    @Test
    public void animal_instantiatesCorrectly_true(){
        Endangered endangeredAnimal = newAnimal();
        assertTrue(endangeredAnimal instanceof Endangered);
    }

    @Test
    public void getName_returnAnimalsName(){
        Endangered endangeredAnimal = newAnimal();
        assertEquals("Goat", endangeredAnimal.getName());
    }

    @Test
    public void getHealth_returnAnimalsHealth(){
        Endangered endangeredAnimal = newAnimal();
        assertEquals("Healthy", endangeredAnimal.getHealth());
    }

    @Test
    public void getAge_returnAnimalsAge(){
        Endangered endangeredAnimal = newAnimal();
        assertEquals("Young", endangeredAnimal.getAge());
    }

    @Test
    public void getStatus_returnAnimalsStatus(){
        Endangered endangeredAnimal = newAnimal();
        assertEquals("Endangered", endangeredAnimal.getSpecies());
    }

    @Test
    public void save_savedToDb_int(){
        Endangered endangeredAnimal = newAnimal();
        endangeredAnimal.save();
        assertEquals(endangeredAnimal.getId(), Endangered.all().get(0).getId());
    }

    @Test
    public void find_locateEndangeredAnimal_Name(){
        Endangered endangeredAnimal = newAnimal();
        endangeredAnimal.save();
        Endangered foundAnimal = Endangered.find(endangeredAnimal.getId());
        assertEquals(endangeredAnimal,foundAnimal);
    }

}