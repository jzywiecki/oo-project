package agh.ics.oop;

public class HellishMap extends AbstractWorldMap{

    public HellishMap() {
        super(xMin);
    }

    public boolean canMoveTo(Vector2d position){
        return false;
    }

    public boolean place(Animal animal){
        return false;
    }

    public boolean isOccupied(Vector2d position){
        return false;
    }

}
