package agh.ics.oop;

import agh.ics.oop.animal.Animal;

public class HellishMap extends AbstractWorldMap{

    public HellishMap(Vector2d upperBound, Vector2d lowerBound) {
        super(upperBound, lowerBound);
    }

    public boolean canMoveTo(Vector2d position){
        return false;
    }


}
