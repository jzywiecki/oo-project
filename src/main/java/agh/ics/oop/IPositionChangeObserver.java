package agh.ics.oop;

/**
 * The interface responsible for position change observer.
 */

public interface IPositionChangeObserver {
    void positionChanged(Vector2d oldPosition, Vector2d newPosition, Animal animal);
}
