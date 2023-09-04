package agh.ics.oop.animal;

import agh.ics.oop.map.MapDirection;
import agh.ics.oop.interfaces.IBehaviorGenerator;
import agh.ics.oop.interfaces.IMapElement;
import agh.ics.oop.interfaces.IPositionChangeObserver;
import agh.ics.oop.interfaces.IWorldMap;
import agh.ics.oop.Vector2d;

import java.util.ArrayList;

public class Animal implements IMapElement{
    private final IWorldMap map;
    private final IBehaviorGenerator behavior;
    private final int[] genomes;
    private MapDirection direction;
    private Vector2d position;
    private int energy;
    private int activeGene = 0;
    private int age = 0;
    private int numberOfChildren = 0;
    private final ArrayList<IPositionChangeObserver> observerList = new ArrayList<>();

    public Animal(MapDirection direction, Vector2d position, IWorldMap map, int[] genomes, IBehaviorGenerator behavior, int energy) {
        this.direction = direction;
        this.position = position;
        this.map = map;
        this.genomes = genomes;
        this.behavior = behavior;
        this.energy = energy;
    }

    public void move(){
        activeGene = behavior.turn(activeGene, genomes.length);
        this.age++;
        this.turn(genomes[activeGene]);
        Vector2d newPosition = map.moveAnimal(this, direction);
        for (IPositionChangeObserver observer : observerList) {
            observer.positionChanged(position, newPosition, this);
        }
        position = newPosition;
    }

    public void addObserver(IPositionChangeObserver observer){
        observerList.add(observer);
    }

    public void removeObserver(IPositionChangeObserver observer){
        observerList.remove(observer);
    }

    public void subtractEnergy(int subtractedEnergy) {
        this.energy -= subtractedEnergy;
    }

    public void addEnergy(int addedEnergy) {
        this.energy += addedEnergy;
    }

    public void turn(int x){
        this.direction = this.direction.add(x);
    }

    public void addChild(){
        numberOfChildren +=1;
    }

    public MapDirection getDirection() {
        return direction;
    }

    public int[] getGenomes(){
        return genomes;
    }

    public int getEnergy(){
        return energy;
    }

    public int getAge() {
        return age;
    }

    public int getNumberOfChildren() {
        return numberOfChildren;
    }

    @Override
    public Vector2d position() {
        return position;
    }

    @Override
    public String toString() {
        return "A";
    }

}
