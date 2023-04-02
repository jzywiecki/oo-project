package agh.ics.oop.map;


import agh.ics.oop.SimulationConfiguration;
import agh.ics.oop.Vector2d;
import agh.ics.oop.animal.Animal;
import agh.ics.oop.interfaces.IGrassGenerator;

import java.util.Random;

public class HellishMap extends AbstractWorldMap {

    public HellishMap(SimulationConfiguration configuration, IGrassGenerator terrain) {
        super(configuration, terrain);
    }


    @Override
    public Vector2d moveAnimal(Animal animal, MapDirection direction) {

        var newPosition = animal.position().add(direction.toUnitVector());
        if(newPosition.follows(lowerBound) && newPosition.precedes(upperBound.subtract(new Vector2d(1, 1)))){
            return newPosition;
        }

        Random random = new Random();
        animal.subtractEnergy(configuration.energyToReproduction());
        return new Vector2d(random.nextInt(upperBound.x() - 1), random.nextInt(upperBound.y() - 1));
    }


}
