package agh.ics.oop.interfaces;

import agh.ics.oop.grass.Grass;

import java.util.List;

/**
 * The interface responsible for adding grass according to variant of grass placement, removing it and getting list of grasses.
 */


public interface IGrassGenerator {

    void placeGrasses();

    List<Grass> getGrass();

    void removeGrass(Grass grass);

}
