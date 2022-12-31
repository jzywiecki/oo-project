<<<<<<< Updated upstream:src/main/java/agh/ics/oop/Animal.java
package agh.ics.oop;
=======
package agh.ics.oop.animal;

import agh.ics.oop.map.MapDirection;
import agh.ics.oop.interfaces.IBehaviorGenerator;
import agh.ics.oop.interfaces.IMapElement;
import agh.ics.oop.interfaces.IPositionChangeObserver;
import agh.ics.oop.interfaces.IWorldMap;
import agh.ics.oop.Vector2d;
import javafx.beans.InvalidationListener;
import javafx.beans.Observable;
>>>>>>> Stashed changes:src/main/java/agh/ics/oop/animal/Animal.java

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Objects;

<<<<<<< Updated upstream:src/main/java/agh/ics/oop/Animal.java
=======

>>>>>>> Stashed changes:src/main/java/agh/ics/oop/animal/Animal.java
public class Animal implements IMapElement{
    private final IWorldMap map;
    private final ArrayList<IPositionChangeObserver> observerList = new ArrayList<>();
    private final IBehaviorGenerator behavior;
<<<<<<< Updated upstream:src/main/java/agh/ics/oop/Animal.java

    private final int[] genoms;
    private int activeGene = 0;
=======
    private final int[] genomes;
    protected int activeGene = 0;
>>>>>>> Stashed changes:src/main/java/agh/ics/oop/animal/Animal.java

    private MapDirection direction;
    private Vector2d position;
    private int energy = 0;
    private int age = 0;
    private int children = 0;

<<<<<<< Updated upstream:src/main/java/agh/ics/oop/Animal.java
    public Animal(MapDirection direction, Vector2d position, IWorldMap map, int[] genoms, IBehaviorGenerator behavior) {
=======
    public Animal(MapDirection direction, Vector2d position, IWorldMap map, int[] genomes, IBehaviorGenerator behavior, int energy) {
>>>>>>> Stashed changes:src/main/java/agh/ics/oop/animal/Animal.java
        this.direction = direction;
        this.position = position;
        this.map = map;
        this.genomes = genomes;
        this.behavior = behavior;
    }


<<<<<<< Updated upstream:src/main/java/agh/ics/oop/Animal.java

=======
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
>>>>>>> Stashed changes:src/main/java/agh/ics/oop/animal/Animal.java

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

<<<<<<< Updated upstream:src/main/java/agh/ics/oop/Animal.java

    public void move(){
        //direction = behavior.turn();
        direction = direction.add(2);

=======
    public void subtractEnergy(int subtractedEnergy) {
        this.energy -= subtractedEnergy;
    }

    public void move(){
        activeGene = behavior.turn(activeGene, genomes.length);
        this.turn(genomes[activeGene]);
>>>>>>> Stashed changes:src/main/java/agh/ics/oop/animal/Animal.java
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
