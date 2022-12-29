package agh.ics.oop.interfaces;

import agh.ics.oop.map.MapDirection;
import agh.ics.oop.animal.Animal;
import agh.ics.oop.Vector2d;

import java.util.List;

public interface IWorldMap {

   // boolean canMoveTo(Vector2d position);

    void place(Animal animal);

    /**
     * Return true if given position on the map is occupied. Should not be
     * confused with canMove since there might be empty positions where the animal
     * cannot move.
     *
     * @param position
     *            Position to check.
     * @return True if the position is occupied.
     */
    default boolean isOccupied(Vector2d position) {
        return objectAt(position) != null;
    }



    IMapElement objectAt(Vector2d position);

    List<IMapElement> objectsAt(Vector2d position);

    /**
     * Move animal in given direction
     *
     * @param animal
     *            Animal that will be given new position
     * @param direction
     *      *     Direction in which animal will move
     */
    Vector2d moveAnimal(Animal animal, MapDirection direction);
}

