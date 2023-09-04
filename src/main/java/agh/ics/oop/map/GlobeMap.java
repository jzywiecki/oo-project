package agh.ics.oop.map;

import agh.ics.oop.SimulationConfiguration;
import agh.ics.oop.Vector2d;
import agh.ics.oop.animal.Animal;
import agh.ics.oop.interfaces.IGrassGenerator;

public class GlobeMap extends AbstractWorldMap {
    public GlobeMap(SimulationConfiguration configuration, IGrassGenerator terrain) {
        super(configuration, terrain);
    }

    @Override
    public Vector2d moveAnimal(Animal animal, MapDirection direction) {

       var newPosition = animal.position().add(direction.toUnitVector());
       if(newPosition.y() > upperBound.y() - 1 || newPosition.y() < lowerBound.y()){
           animal.turn(4);
           return animal.position();
       }
        if(newPosition.x() > upperBound.x() - 1){
            return new Vector2d(lowerBound.x(), newPosition.y());
        }
        if(newPosition.x() < lowerBound.x()){
            return new Vector2d(upperBound.x() -1 , newPosition.y());
        }
        return newPosition;
    }


}

