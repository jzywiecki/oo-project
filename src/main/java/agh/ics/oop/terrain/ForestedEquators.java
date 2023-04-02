package agh.ics.oop.terrain;

import agh.ics.oop.SimulationConfiguration;
import agh.ics.oop.Vector2d;

import java.util.LinkedList;
import java.util.List;


public class    ForestedEquators extends AbstractTerrain {
    private int lowerEquatorBound;
    private int higherEquatorBound;
    private int mapHeight;
    private int mapWidth;


    public ForestedEquators(SimulationConfiguration configuration) {
        super(configuration);
        calculateEquatorBounds();
    }

    private void calculateEquatorBounds(){
        mapHeight = configuration.bounds().y();
        mapWidth =  configuration.bounds().x();

        int equatorWidth = Math.toIntExact(Math.round(mapHeight * 0.2));
        lowerEquatorBound = (mapHeight - equatorWidth)/2;
        higherEquatorBound = (mapHeight - equatorWidth)/2 + equatorWidth - 1;
    }

    @Override
    protected List<Vector2d> getPreferredPosition() {
        List<Vector2d> equator = new LinkedList<>();
        for (int i = lowerEquatorBound; i <=higherEquatorBound; i++){
            for (int j = 0; j < mapWidth; j++){
                equator.add(new Vector2d(j, i));
            }
        }
        return equator;
    }

    @Override
    protected List<Vector2d> getNonPreferredPosition() {
        List<Vector2d> area = new LinkedList<>();

        for (int i = 0; i <lowerEquatorBound; i++){
            for (int j = 0; j < mapWidth; j++){
                area.add(new Vector2d(j, i));
            }
        }
        for (int i = higherEquatorBound+1; i < mapHeight; i++){
            for (int j = 0; j < mapWidth; j++){
                area.add(new Vector2d(j, i));
            }
        }

        return area;
    }



}

