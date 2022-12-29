package agh.ics.oop;
import agh.ics.oop.animal.Animal;
import agh.ics.oop.interfaces.IGrassGenerator;
import agh.ics.oop.interfaces.IMapElement;
import agh.ics.oop.interfaces.IPositionChangeObserver;
import agh.ics.oop.interfaces.IWorldMap;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;



abstract class AbstractWorldMap implements IWorldMap, IPositionChangeObserver {

    protected Map<Vector2d, LinkedList<IMapElement>> mapElements = new HashMap<>();
    protected final Vector2d upperBound;
    protected final Vector2d lowerBound;
    private IGrassGenerator grassGenerator;


    public AbstractWorldMap(Vector2d upperBound, Vector2d lowerBound) {
        this.upperBound = upperBound;
        this.lowerBound = lowerBound;
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
        LinkedList<IMapElement> val = new LinkedList<>();
        val.add(animal);
        mapElements.put(position,val);
        animal.addObserver(this);
        System.out.println("lista: " + val);
        System.out.println("wszystko: " + mapElements);
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
    public Vector2d moveAnimal(Animal animal, MapDirection direction) {

        return animal.position().add(direction.toUnitVector());
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
