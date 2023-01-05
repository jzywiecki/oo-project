package agh.ics.oop.interfaces;

import agh.ics.oop.animal.Animal;
import agh.ics.oop.map.MapDirection;
import agh.ics.oop.Vector2d;

import java.util.LinkedList;


public interface IWorldMap {


    /**
     * Place an animal on the map.
     * @param animal
     *            The animal to place on the map.
     */
    void place(Animal animal);

    /**
     * Return true if given position on the map is occupied.
     *
     * @param position
     *            Position to check.
     * @return True if the position is occupied.
     */
    default boolean isOccupied(Vector2d position) {
        return objectsAt(position) != null;
    }

    /**
     * Required for mapVisualizer, doesn't return actual IMapElement Object on map
     *
     * @param position
     *            Position to check.
     * @return Returns animal before grass, or null if no object is present
     */
    IMapElement objectAt(Vector2d position);


    /**
     * Return list of objects at a given position.
     *
     * @param position
     *            The position of the objects.
     * @return List of objects or null if the position is not occupied.
     */
    LinkedList<IMapElement> objectsAt(Vector2d position);

    /**
     * Move animal in given direction
     *
     * @param animal
     *            Animal that will be given new position
     * @param direction
     *      *     Direction in which animal will move
     */
    Vector2d moveAnimal(Animal animal, MapDirection direction);


    /**
     * Delete animal from the map
     * @param animal
     *            The animal to remove from the map.
     */
    void deleteAnimal(Animal animal);

    /**
     * Make the strongest animal on each position eat grass if one present
     */
    void eatGrass();


    Vector2d getUpperBound();
    Vector2d getLowerBound();
}

