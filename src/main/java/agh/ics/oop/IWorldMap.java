package agh.ics.oop;

public interface IWorldMap {

    boolean canMoveTo(Vector2d position);

    boolean place(Animal animal);

    boolean isOccupied(Vector2d position);

    IMapElement objectAt(Vector2d position);
}

