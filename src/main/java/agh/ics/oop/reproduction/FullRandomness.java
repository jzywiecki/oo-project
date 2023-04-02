package agh.ics.oop.reproduction;

import agh.ics.oop.SimulationConfiguration;
import agh.ics.oop.interfaces.IBehaviorGenerator;
import agh.ics.oop.interfaces.IWorldMap;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class FullRandomness extends AbstractReproduction {
    public static Random rng = new Random();

    public FullRandomness(IWorldMap map, IBehaviorGenerator behavior, SimulationConfiguration config) {
        super(map, behavior, config);
    }

    @Override
    protected int[] mutate(int[] genome, int mutationsNumber) {
        List<Integer> genomes = new ArrayList<>();
        for (int i = 0; i < genome.length; i++){
            genomes.add(i);
        }
        Collections.shuffle(genomes);

        for (int i = 0; i < mutationsNumber; i++) {
            genome[genomes.get(i)] = rng.nextInt(8);
        }
        return genome;
    }
}





