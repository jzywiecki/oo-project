package agh.ics.oop.terrain;



import agh.ics.oop.SimulationConfiguration;
import agh.ics.oop.Vector2d;
import agh.ics.oop.interfaces.IWorldMap;




public class ForestedEquators extends AbstractTerrain {
    private int lowerEquatorBound;
    private int higherEquatorBound;
    private int mapHeight;
    private int mapWidth;


    public ForestedEquators(IWorldMap map, SimulationConfiguration configuration) {
        super(map, configuration);
        calculateEquatorBounds();
    }

    private void calculateEquatorBounds(){
        Vector2d mapLowerBound = map.getLowerBound();
        Vector2d mapUpperBound = map.getUpperBound();
        mapHeight = mapUpperBound.y() - mapLowerBound.y() + 1;
        mapWidth = mapUpperBound.x() - mapLowerBound.x() + 1;
        lowerEquatorBound = (int) Math.floor(0.40 * mapHeight);
        higherEquatorBound = (int) Math.ceil(0.60 * mapHeight)-1;
    }

    @Override
    protected void placeOnPreferedPosition(int quantity) {
        Vector2d[] equator = new Vector2d[mapWidth * (higherEquatorBound - lowerEquatorBound + 1)];
        int counter = 0;
        for (int i = lowerEquatorBound; i <=higherEquatorBound; i++){
            for (int j = 0; j < mapWidth; j++){
                equator[counter] = new Vector2d(i, j);
                counter++;
            }
        }

        placeGrass(equator, quantity);
    }

    @Override
    protected void placeOnNonPreferedPosition(int quantity) {
        Vector2d[] area = new Vector2d[mapWidth*mapHeight - mapWidth * (higherEquatorBound - lowerEquatorBound + 1)];

        int counter = 0;
        for (int i = 0; i <lowerEquatorBound; i++){
            for (int j = 0; j < mapWidth; j++){
                area[counter] = new Vector2d(i, j);
                counter++;
            }
        }

        counter = 0;
        for (int i = higherEquatorBound+1; i < mapHeight; i++){
            for (int j = 0; j < mapWidth; j++){
                area[counter] = new Vector2d(i, j);
                counter++;
            }
        }

        placeGrass(area, quantity);
    }



}

