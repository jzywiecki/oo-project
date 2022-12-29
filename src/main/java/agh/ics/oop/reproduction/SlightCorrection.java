package agh.ics.oop.reproduction;


import agh.ics.oop.MapDirection;
import agh.ics.oop.SimulationConfiguration;
import agh.ics.oop.interfaces.IBehaviorGenerator;
import agh.ics.oop.interfaces.IWorldMap;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class SlightCorrection extends AbstractReproduction {
    public SlightCorrection(MapDirection direction, IWorldMap map, IBehaviorGenerator behavior, SimulationConfiguration config) {
        super(direction, map, behavior, config);
    }

    @Override
    int[] mutate(int[] genom, int mutationsNumber) {
        List<Integer> genoms = new ArrayList<>();
        for (int i = 0; i < genom.length; i++){
            genoms.add(i);
        }
        shuffle(genoms);

        for (int i = 0; i < mutationsNumber; i++) {
            if(rng.nextInt(2) == 0){
                genom[genoms.get(i)] += 1;
            }
            else{
                genom[genoms.get(i)] -= 1;
            }

        }
        return genom;
    }

    public static Random rng = new Random();


    public static void shuffle(List<?> collection) {
        Collections.shuffle(collection, rng);
    }
}


