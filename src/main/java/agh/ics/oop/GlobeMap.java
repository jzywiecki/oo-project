package agh.ics.oop;

public class GlobeMap extends AbstractWorldMap{
    public GlobeMap() {
    }

    public boolean canMoveTo(Vector2d position){
        if (position.y() > getUpperBound().y() || position.y() < getUpperBound().y()){
            return false;
        }
        return true;

    }

    public boolean place(Animal animal){

        return false;
    }

    public boolean isOccupied(Vector2d position){

        return false;
    }


}

