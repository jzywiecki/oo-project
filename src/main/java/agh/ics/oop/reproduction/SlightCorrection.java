package agh.ics.oop.reproduction;

import agh.ics.oop.SimulationConfiguration;
import agh.ics.oop.interfaces.IBehaviorGenerator;
import agh.ics.oop.interfaces.IWorldMap;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class SlightCorrection extends AbstractReproduction {
    public static Random rng = new Random();

    public SlightCorrection(IWorldMap map, IBehaviorGenerator behavior, SimulationConfiguration config) {
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
            if(rng.nextInt(2) == 0){
                genome[genomes.get(i)] += 1;
                if(genome[genomes.get(i)] == 8){
                    genome[genomes.get(i)] = 0;
                }
            }
            else{
                genome[genomes.get(i)] -= 1;
                if(genome[genomes.get(i)] == -1){
                    genome[genomes.get(i)] = 7;
                }
            }
        }
        return genome;
    }
}


