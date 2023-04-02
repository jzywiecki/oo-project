package agh.ics.oop.map;

import agh.ics.oop.SimulationConfiguration;
import agh.ics.oop.Vector2d;
import agh.ics.oop.animal.Animal;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class HellishMapTest {

    @Test
    void AnimalGetsTeleported() {
        //given
        SimulationConfiguration bounds = new SimulationConfiguration(new Vector2d(5, 5), null, 0, 0, 0, null, 0, 0, 0, 0, 0, 0, null, 0, null, 0);
        GlobeMap map = new GlobeMap(bounds , null);
        Animal cat = new Animal(MapDirection.SOUTH_EAST, new Vector2d(4, 4), null ,null, null, 0);
        Animal dog = new Animal(MapDirection.WEST, new Vector2d(0, 0), null ,null, null, 0);

        //when
        Vector2d catPosition = map.moveAnimal(cat, cat.getDirection());
        Vector2d dogPosition = map.moveAnimal(dog, dog.getDirection());

        //then
        assertNotEquals(catPosition, cat.position());
        assertNotEquals(dogPosition, dog.position());
    }


    @Test
    void AnimalLosesEnergy() {
        //given
        SimulationConfiguration bounds = new SimulationConfiguration(new Vector2d(5, 5), null, 0, 0, 0, null, 0, 0, 0, 20, 0, 0, null, 0, null, 0);
        GlobeMap map = new GlobeMap(bounds , null);
        Animal cat = new Animal(MapDirection.SOUTH_EAST, new Vector2d(4, 4), null ,null, null, 50);
        Animal dog = new Animal(MapDirection.WEST, new Vector2d(0, 0), null ,null, null, 50);

        //when
        Vector2d catPosition = map.moveAnimal(cat, cat.getDirection());
        Vector2d dogPosition = map.moveAnimal(dog, dog.getDirection());

        //then
        assertTrue(cat.getEnergy() < 50);
        assertTrue(dog.getEnergy() < 50);
    }
}