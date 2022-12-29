package agh.ics.oop.interfaces;

/**
 * The interface responsible for position change observer.
 */

import agh.ics.oop.Vector2d;
import agh.ics.oop.animal.Animal;

public interface IPositionChangeObserver {
    void positionChanged(Vector2d oldPosition, Vector2d newPosition, Animal animal);
}
