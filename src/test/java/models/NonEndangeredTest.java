package models;
import org.junit.Test;

import static org.junit.Assert.*;

public class NonEndangeredTest {
    private NonEndangered newAnimal() {
        return new NonEndangered("Goat","Healthy","Young");
    }

    @Test
    public void animal_instantiatesCorrectly_true(){
        NonEndangered normalAnimal = newAnimal();
        assertTrue(normalAnimal instanceof NonEndangered);
    }

    @Test
    public void getName_returnAnimalsName(){
        NonEndangered normalAnimal = newAnimal();
        assertEquals("Goat", normalAnimal.getName());
    }

    @Test
    public void getHealth_returnAnimalsHealth(){
        NonEndangered normalAnimal = newAnimal();
        assertEquals("Healthy", normalAnimal.getHealth());
    }

    @Test
    public void getAge_returnAnimalsAge(){
        NonEndangered normalAnimal = newAnimal();
        assertEquals("Young", normalAnimal.getAge());
    }

    @Test
    public void getStatus_returnAnimalsStatus(){
        NonEndangered normalAnimal = newAnimal();
        assertEquals("Not Endangered", normalAnimal.getSpecies());
    }

    @Test
    public void save_savedToDb_int(){
        NonEndangered normalAnimal = newAnimal();
        normalAnimal.save();
        assertEquals(normalAnimal.getId(),NonEndangered.all().get(0).getId());
    }

    @Test
    public void find_locateNormalAnimal_Name(){
        NonEndangered normalAnimal = newAnimal();
        normalAnimal.save();
        NonEndangered foundAnimal = NonEndangered.find(normalAnimal.getId());
        assertTrue(normalAnimal.equals(foundAnimal));
    }


}
