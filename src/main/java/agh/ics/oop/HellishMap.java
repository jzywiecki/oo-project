package agh.ics.oop;

<<<<<<< Updated upstream:src/main/java/agh/ics/oop/HellishMap.java
public class HellishMap extends AbstractWorldMap{
=======
import agh.ics.oop.SimulationConfiguration;
import agh.ics.oop.Vector2d;
import agh.ics.oop.animal.Animal;
import agh.ics.oop.map.AbstractWorldMap;

import java.util.Random;

public class HellishMap extends AbstractWorldMap {
>>>>>>> Stashed changes:src/main/java/agh/ics/oop/map/HellishMap.java

    public HellishMap(SimulationConfiguration configuration) {
        super(configuration);
    }


    @Override
    public Vector2d moveAnimal(Animal animal, MapDirection direction) {

        var newPosition = animal.position().add(direction.toUnitVector());
        if(newPosition.follows(lowerBound) && newPosition.precedes(upperBound)){
            return newPosition;
        }

        Random random = new Random();
        animal.subtractEnergy(configuration.energyToReproduction());
        return new Vector2d(random.nextInt(upperBound.x()), random.nextInt(upperBound.y()));
    }


}
