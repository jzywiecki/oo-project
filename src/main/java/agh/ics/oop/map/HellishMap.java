package agh.ics.oop.map;

import agh.ics.oop.Vector2d;
import agh.ics.oop.map.AbstractWorldMap;

public class HellishMap extends AbstractWorldMap {

    public HellishMap(Vector2d upperBound, Vector2d lowerBound) {
        super(upperBound, lowerBound);
    }

    public boolean canMoveTo(Vector2d position){
        return false;
    }


}
