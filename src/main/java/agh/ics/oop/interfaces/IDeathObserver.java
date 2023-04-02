package agh.ics.oop.interfaces;

import agh.ics.oop.Vector2d;

public interface IDeathObserver {

    /**
     * Trigger action based on death of animal given position
     * @param position
     *            Position of death.
     */
    void animalDied(Vector2d position);
}
