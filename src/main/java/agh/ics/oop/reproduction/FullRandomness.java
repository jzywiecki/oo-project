package agh.ics.oop.reproduction;


import agh.ics.oop.map.MapDirection;
import agh.ics.oop.SimulationConfiguration;
import agh.ics.oop.interfaces.IBehaviorGenerator;
import agh.ics.oop.interfaces.IWorldMap;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class FullRandomness extends AbstractReproduction {
    public FullRandomness(IWorldMap map, IBehaviorGenerator behavior, SimulationConfiguration config) {
        super(map, behavior, config);

    }

    @Override
    public int[] mutate(int[] genom, int mutationsNumber) {
        List<Integer> genoms = new ArrayList<>();
        for (int i = 0; i < genom.length; i++){
            genoms.add(i);
        }
        shuffle(genoms);

        for (int i = 0; i < mutationsNumber; i++) {
            genom[genoms.get(i)] = rng.nextInt(8);
        }
        return genom;
    }

    public static Random rng = new Random();


    public static void shuffle(List<?> collection) {
        Collections.shuffle(collection, rng);
    }
}





