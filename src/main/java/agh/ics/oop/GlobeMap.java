package agh.ics.oop;

<<<<<<< Updated upstream:src/main/java/agh/ics/oop/GlobeMap.java
public class GlobeMap extends AbstractWorldMap{
    public GlobeMap(Vector2d upperBound, Vector2d lowerBound) {
        super(upperBound, lowerBound);
=======
import agh.ics.oop.SimulationConfiguration;
import agh.ics.oop.Vector2d;
import agh.ics.oop.animal.Animal;

public class GlobeMap extends AbstractWorldMap {
    public GlobeMap(SimulationConfiguration configuration) {
        super(configuration);
>>>>>>> Stashed changes:src/main/java/agh/ics/oop/map/GlobeMap.java
    }


    @Override
    public Vector2d moveAnimal(Animal animal, MapDirection direction) {

       var newPosition = animal.position().add(direction.toUnitVector());
       if(newPosition.y() > upperBound.y() || newPosition.y() < lowerBound.y()){
           animal.turn(4);
           return animal.position();
       }
        if(newPosition.x() > upperBound.x()){
            return new Vector2d(lowerBound.x(), newPosition.y());
        }
        if(newPosition.x() < lowerBound.x()){
            return new Vector2d(upperBound.x(), newPosition.y());
        }
        return newPosition;
    }

}

