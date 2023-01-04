package agh.ics.oop.terrain;

import agh.ics.oop.SimulationConfiguration;
import agh.ics.oop.Vector2d;
import agh.ics.oop.grass.Grass;
import agh.ics.oop.interfaces.IGrassGenerator;
import java.util.Collections;
import agh.ics.oop.interfaces.IWorldMap;
import java.util.ArrayList;
import java.util.List;
import java.util.*;


public abstract class AbstractTerrain implements IGrassGenerator {
    protected SimulationConfiguration configuration;
    protected final HashMap<Vector2d, Grass> grasses= new HashMap<>();

    public AbstractTerrain(SimulationConfiguration configuration) {
        this.configuration = configuration;
    }

    @Override
    public void placeGrasses() {
        Random rng = new Random();
        int wildGrass = 0;
        for(int i = 0; i < configuration.grassGrowth(); i++){
            if(rng.nextInt(5) == 4){
                wildGrass+=1;
            }

        }
        placeOnPreferredPosition(configuration.grassGrowth()-wildGrass);
        placeOnNonPreferredPosition(wildGrass);
    }

    @Override
    public HashMap<Vector2d, Grass> getGrass() {
        return grasses;
    }

    @Override
    public void removeGrass(Grass grass) {
        this.grasses.remove(grass.position());
    }

    @Override
    public void removeGrass(Vector2d position) {
        this.grasses.remove(position);
    }

    @Override
    public Grass grassAt(Vector2d position){
        return grasses.get(position);
    }

    public boolean isGrassAt(Vector2d position){
        return grassAt(position) != null;
    }

    protected abstract void placeOnPreferredPosition(int quantity);

    protected abstract void placeOnNonPreferredPosition(int quantity);

    protected void placeGrass(List<Vector2d> availablePositions, int quantityOfGrass){
        Collections.shuffle(availablePositions);
        for (Vector2d position: availablePositions){
            if (this.grassAt(position) == null){
                Grass grass = new Grass(position);
                grasses.put(grass.position(), grass);
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
