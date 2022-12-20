package agh.ics.oop;
import java.util.HashMap;
import java.util.Map;

abstract class AbstractWorldMap implements IWorldMap{
    protected Map<Vector2d, IMapElement> mapElements = new HashMap<>();
    protected Vector2d lowerBound;
    protected Vector2d upperBound;


    public Vector2d getLowerBound() {
        return lowerBound;
    }

    public Vector2d getUpperBound() {
        return upperBound;
    }

    @Override
    public IMapElement objectAt(Vector2d position) {
        return mapElements.get(position);
    }
}
