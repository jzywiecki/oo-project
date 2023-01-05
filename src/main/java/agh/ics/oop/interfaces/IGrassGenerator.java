package agh.ics.oop.interfaces;

import agh.ics.oop.Vector2d;
import agh.ics.oop.grass.Grass;

import java.util.HashMap;

/**
 * The interface responsible for adding grass according to variant of grass placement, removing it and getting list of grasses.
 */


public interface IGrassGenerator {

    /**
     * Generate grasses on the map. Amount is specified in the SimulationConfiguration
     */
    void placeGrasses();

    HashMap<Vector2d, Grass> getGrass();

    /**
     * Remove grass on given position
     *
     * @param position
     *           Position to remove grass from.
     */
    void removeGrass(Vector2d position);

    /**
     * Remove  given grass
     *
     * @param grass
     *           Grass to remove
     */
    void removeGrass(Grass grass);

    /**
     * Return true if given position is occupied by a grass.
     *
     * @param position
     *           Position to check.
     * @return True if the position is occupied.
     */
    boolean isGrassAt(Vector2d position);


    /**
     * Return a grass at a given position.
     *
     * @param position
     *            The position of the grass.
     * @return Grass or null if the position is not occupied.
     */
    Grass grassAt(Vector2d position);

}
