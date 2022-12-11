package agh.ics.oop;
import java.util.HashMap;
import java.util.Map;

abstract class AbstractWorldMap implements IWorldMap{

    protected Map<Vector2d, IMapElement> mapElements = new HashMap<>();

    @Override
    public IMapElement objectAt(Vector2d position) {
        return mapElements.get(position);
    }

}
