package agh.ics.oop.interfaces;

import agh.ics.oop.Vector2d;
import agh.ics.oop.animal.Animal;


/**
 * The interface responsible for position change observer.
 */
public interface IPositionChangeObserver {
    void positionChanged(Vector2d oldPosition, Vector2d newPosition, Animal animal);
}
