package agh.ics.oop.map;

import agh.ics.oop.SimulationConfiguration;
import agh.ics.oop.Vector2d;
import agh.ics.oop.animal.Animal;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class GlobeMapTest {

    @Test
    void AnimalCantPassUpperAndLowerBounds() {
        //given
        SimulationConfiguration bounds = new SimulationConfiguration(new Vector2d(5, 5), null, 0, 0, 0, null, 0, 0, 0, 0, 0, 0, null, 0, null, 0);
        GlobeMap map = new GlobeMap(bounds , null);
        Animal cat = new Animal(MapDirection.NORTH, new Vector2d(2, 4), null ,null, null, 0);
        Animal dog = new Animal(MapDirection.SOUTH_WEST, new Vector2d(0, 0), null ,null, null, 0);

        //when
        Vector2d catPosition = map.moveAnimal(cat, cat.getDirection());
        Vector2d dogPosition = map.moveAnimal(dog, dog.getDirection());

        //then
        assertEquals(catPosition, cat.position());
        assertEquals(dogPosition, dog.position());
    }


    @Test
    void LeftAndRightBoundsAreConnected() {
        //given
        SimulationConfiguration bounds = new SimulationConfiguration(new Vector2d(5, 5), null, 0, 0, 0, null, 0, 0, 0, 0, 0, 0, null, 0, null, 0);
        GlobeMap map = new GlobeMap(bounds , null);
        Animal cat = new Animal(MapDirection.SOUTH_EAST, new Vector2d(4, 4), null ,null, null, 0);
        Animal dog = new Animal(MapDirection.WEST, new Vector2d(0, 0), null ,null, null, 0);

        //when
        Vector2d catPosition = map.moveAnimal(cat, cat.getDirection());
        Vector2d dogPosition = map.moveAnimal(dog, dog.getDirection());

        //then
        assertEquals(catPosition, new Vector2d(0, 3));
        assertEquals(dogPosition, new Vector2d(4, 0));
    }
}