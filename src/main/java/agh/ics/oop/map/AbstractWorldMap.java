package agh.ics.oop.map;

import agh.ics.oop.SimulationConfiguration;
import agh.ics.oop.Vector2d;
import agh.ics.oop.animal.Animal;
import agh.ics.oop.animal.AnimalComparator;
import agh.ics.oop.grass.Grass;
import agh.ics.oop.interfaces.IGrassGenerator;
import agh.ics.oop.interfaces.IMapElement;
import agh.ics.oop.interfaces.IPositionChangeObserver;
import agh.ics.oop.interfaces.IWorldMap;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;


abstract class AbstractWorldMap implements IWorldMap, IPositionChangeObserver {
    private final IGrassGenerator terrain;
    protected final Vector2d upperBound;
    protected final SimulationConfiguration configuration;
    private final Map<Vector2d, LinkedList<Animal>> animalMap = new HashMap<>();
    protected final Vector2d lowerBound = new Vector2d(0, 0);

    public AbstractWorldMap(SimulationConfiguration configuration, IGrassGenerator terrain) {
        this.upperBound = configuration.bounds();
        this.configuration = configuration;
        this.terrain = terrain;
    }

    @Override
    public LinkedList<IMapElement> objectsAt(Vector2d position) {
        if(animalMap.get(position) != null){
            LinkedList<IMapElement> elements = new LinkedList<>(animalMap.get(position));
            if(terrain.isGrassAt(position)){
                elements.add(terrain.grassAt(position));
            }
            return elements;
        }
        if(terrain.grassAt(position) != null){
            LinkedList<IMapElement> grass =  new LinkedList<>();
            grass.add(terrain.grassAt(position));
            return grass;
        }

        return null;
    }

    @Override
    public void place(Animal animal) {
        Vector2d position = animal.position();
        if(!position.follows(lowerBound) && !position.precedes(upperBound)){
            throw new IllegalArgumentException("Illegal animal placement on position: " + position);
        }
        animal.addObserver(this);
        if(animalMap.containsKey(animal.position())){
            animalMap.get(position).push(animal);
            return;
        }
        LinkedList<Animal> val = new LinkedList<>();
        val.add(animal);
        animalMap.put(position,val);
    }

    //required for mapVisualizer, returns Animal if position is not empty
    @Override
    public IMapElement objectAt(Vector2d position){
        if(animalMap.containsKey(position)){
            return new Animal(MapDirection.NORTH, new Vector2d(3, 3), this, null, null, 20);
        }
        if(terrain.grassAt(position) != null){
            return new Grass(new Vector2d(1, 1));
        }
        return null;
    }


    @Override
    public void deleteAnimal(Animal animal) {
        animal.removeObserver(this);
        var objectsOnPosition = animalMap.get(animal.position());
        objectsOnPosition.remove(animal);

        if(objectsOnPosition.isEmpty()){
            animalMap.remove(animal.position());
        }
    }

    public Grass getGrassAtPosition(Vector2d position){
        return terrain.grassAt(position);
    }

    //move animal in mapElements
    public void positionChanged(Vector2d oldPosition, Vector2d newPosition, Animal animal){
        var objectsOnPosition = animalMap.get(oldPosition);
        objectsOnPosition.remove(animal);

        if(objectsOnPosition.isEmpty()){
            animalMap.remove(oldPosition);
        }

        if(animalMap.containsKey(newPosition)){
            animalMap.get(newPosition).push(animal);
            return;
        }
        LinkedList<Animal> val = new LinkedList<>();
        val.add(animal);
        animalMap.put(newPosition,val);

    }

    public void eatGrass(){
        var positions = animalMap.keySet();
        positions.forEach((key)->{
            if(terrain.grassAt(key) != null){
               var strongest =  animalMap.get(key).stream().max( new AnimalComparator());
               if(strongest.isEmpty()){ return;}
               terrain.removeGrass(key);
               strongest.get().addEnergy(configuration.grassEnergy());
            }
        });
    }

    @Override
    public Vector2d getUpperBound() {
        return upperBound;
    }

    @Override
    public Vector2d getLowerBound() {
        return lowerBound;
    }

    @Override
    public String toString() {
        MapVisualizer map = new MapVisualizer(this);
        return map.draw(lowerBound, upperBound.subtract(new Vector2d(1, 1)));
    }
}
