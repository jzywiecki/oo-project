package agh.ics.oop.interfaces;

/**
 * The interface responsible for managing the moves of the animals.
 * Assumes that Vector2d and MoveDirection classes are defined.
 */

public interface IEngine {
    /**
    * Move the animal on the map according to the provided move directions.
    */
    void run();
}
