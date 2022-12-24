package agh.ics.oop.reproduction;


import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class FullRandomness extends AbstractReproduction {
    @Override
    int[] mutate(int[] genom, int mutationsNumber) {
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





