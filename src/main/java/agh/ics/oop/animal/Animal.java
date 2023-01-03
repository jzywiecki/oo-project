package agh.ics.oop.animal;

import agh.ics.oop.map.MapDirection;
import agh.ics.oop.interfaces.IBehaviorGenerator;
import agh.ics.oop.interfaces.IMapElement;
import agh.ics.oop.interfaces.IPositionChangeObserver;
import agh.ics.oop.interfaces.IWorldMap;
import agh.ics.oop.Vector2d;
import java.util.ArrayList;
import java.util.Objects;



public class Animal implements IMapElement{
    private final IWorldMap map;
    private final ArrayList<IPositionChangeObserver> observerList = new ArrayList<>();
    private final IBehaviorGenerator behavior;

    private final int[] genomes;
    protected int activeGene = 0;

    private MapDirection direction;
    private Vector2d position;
    private int energy;
    private int age = 0;
    private int numberOfChildren = 0;

    public Animal(MapDirection direction, Vector2d position, IWorldMap map, int[] genomes, IBehaviorGenerator behavior, int energy) {

        this.direction = direction;
        this.position = position;
        this.map = map;
        this.genomes = genomes;
        this.behavior = behavior;
        this.energy = energy;
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

    public void turn(int x){
        this.direction = this.direction.add(x);
    }

    public boolean isAt(Vector2d target){
        return Objects.equals(this.position, target);
    }

    public boolean isFacing(MapDirection target){ return direction.equals(target); }

    public void addObserver(IPositionChangeObserver observer){
        observerList.add(observer);
    }

    public void removeObserver(IPositionChangeObserver observer){
        observerList.remove(observer);
    }

    public void subtractEnergy(int subtractedEnergy) {
        this.energy -= subtractedEnergy;
    }

    public void move(){
        activeGene = behavior.turn(activeGene, genomes.length);
        this.turn(genomes[activeGene]);
        Vector2d newPosition = map.moveAnimal(this, direction);
        for (IPositionChangeObserver observer : observerList) {
            observer.positionChanged(position, newPosition, this);
        }
        position = newPosition;
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
