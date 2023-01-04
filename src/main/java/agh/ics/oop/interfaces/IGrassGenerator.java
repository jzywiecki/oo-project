package agh.ics.oop.interfaces;

import agh.ics.oop.Vector2d;
import agh.ics.oop.grass.Grass;

import java.util.HashMap;
import java.util.List;

/**
 * The interface responsible for adding grass according to variant of grass placement, removing it and getting list of grasses.
 */


public interface IGrassGenerator {

    void placeGrasses();

    HashMap<Vector2d, Grass> getGrass();

    void removeGrass(Grass grass);
    void removeGrass(Vector2d position);
    public boolean isGrassAt(Vector2d position);
    Grass grassAt(Vector2d position);

}
