package agh.ics.oop.interfaces;

import agh.ics.oop.Grass;
import agh.ics.oop.Vector2d;

/**
 * The interface responsible for adding grass according to variant of grass placement.
 */


public interface IGrassGenerator {
    void place(Grass grass, Vector2d lowerBound, Vector2d upperBound);
}
