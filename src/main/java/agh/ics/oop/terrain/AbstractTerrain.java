package agh.ics.oop.terrain;

import agh.ics.oop.Grass;
import agh.ics.oop.SimulationConfiguration;
import agh.ics.oop.Vector2d;
import agh.ics.oop.interfaces.IGrassGenerator;
import agh.ics.oop.interfaces.IWorldMap;

import java.util.ArrayList;
import java.util.List;

public abstract class AbstractTerrain implements IGrassGenerator {
    protected IWorldMap map;
    protected SimulationConfiguration configuration;
    protected final List<Grass> grasses = new ArrayList<>();

    public AbstractTerrain(IWorldMap map, SimulationConfiguration configuration) {
        this.map = map;
        this.configuration = configuration;
    }

    @Override
    public void placeGrasses() {
        int grassToPlaceOnPreferedPosition = (int) Math.ceil(0.8 * configuration.grassGrowth());
        int grassToPlaceOnNonPreferedPosition = configuration.grassGrowth() - grassToPlaceOnPreferedPosition;
        placeOnPreferedPosition(grassToPlaceOnPreferedPosition);
        placeOnNonPreferedPosition(grassToPlaceOnNonPreferedPosition);
    }

    @Override
    public List<Grass> getGrass() {
        return grasses;
    }

    @Override
    public void removeGrass(Grass grass) {
        this.grasses.remove(grass);
    }


    protected abstract void placeOnPreferedPosition(int quantity);

    protected abstract void placeOnNonPreferedPosition(int quantity);

    protected void placeGrass(Vector2d[] availablePositions, int quantityOfGrass){
        for (Vector2d position: availablePositions){
            if (map.getGrassAtPosition(position) == null){
                Grass grass = new Grass(position, configuration.grassEnergy());
                grasses.add(grass);
                if (quantityOfGrass > 0){
                    quantityOfGrass -= 1;
                }
                else{
                    break;
                }

            }
        }
    }
}
