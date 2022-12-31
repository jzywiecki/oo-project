package agh.ics.oop.map;

import agh.ics.oop.SimulationConfiguration;
import agh.ics.oop.Vector2d;
import agh.ics.oop.animal.Animal;
import agh.ics.oop.interfaces.IMapElement;
import agh.ics.oop.interfaces.IPositionChangeObserver;
import agh.ics.oop.interfaces.IWorldMap;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

abstract class AbstractWorldMap implements IWorldMap, IPositionChangeObserver{

    private final Map<Vector2d, LinkedList<IMapElement>> mapElements = new HashMap<>();
    protected final Vector2d upperBound;
    protected final Vector2d lowerBound = new Vector2d(0, 0);

    protected final SimulationConfiguration configuration;

    public AbstractWorldMap(SimulationConfiguration configuration) {
        this.upperBound = configuration.bounds();
        this.configuration = configuration;
    }


    @Override
    public LinkedList<IMapElement> objectsAt(Vector2d position) {
        return mapElements.get(position);
    }

    @Override
    public void place(Animal animal) {
        Vector2d position = animal.position();
        if(!position.follows(lowerBound) && !position.precedes(upperBound)){
            throw new IllegalArgumentException("illegal animal placement on position: " + position);
        }

        if(mapElements.containsKey(animal.position())){
            mapElements.get(position).add(animal);
            return;
        }
        animal.addObserver(this);
        LinkedList<IMapElement> val = new LinkedList<>();
        val.add(animal);
        mapElements.put(position,val);
    }

    //required for mapVisualizer, returns Animal if position is not empty
    @Override
    public IMapElement objectAt(Vector2d position){
        if(mapElements.containsKey(position)){
            return new Animal(MapDirection.NORTH, new Vector2d(3, 3), this, null, null, 20);
        }
        return null;
    }


    @Override
    public void deleteAnimal(Animal animal) {
        animal.removeObserver(this);
        var objectsOnPosition = mapElements.get(animal.position());
        objectsOnPosition.remove(animal);

        if(objectsOnPosition.isEmpty()){
            mapElements.remove(animal.position());
        }
    }

    //move animal in mapElements
    public void positionChanged(Vector2d oldPosition, Vector2d newPosition, Animal animal){

        var objectsOnPosition = mapElements.get(oldPosition);
        objectsOnPosition.remove(animal);

        if(objectsOnPosition.isEmpty()){
            mapElements.remove(oldPosition);
        }

        if(mapElements.containsKey(newPosition)){
            mapElements.get(newPosition).push(animal);
        }
        LinkedList<IMapElement> val = new LinkedList<>();
        val.add(animal);
        mapElements.put(newPosition,val);
    }

    @Override
    public String toString() {
        MapVisualizer map = new MapVisualizer(this);
        return map.draw(lowerBound, upperBound);
    }
}
