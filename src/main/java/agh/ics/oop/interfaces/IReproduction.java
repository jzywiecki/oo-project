package agh.ics.oop.interfaces;

import agh.ics.oop.animal.Animal;

/**
 * The interface responsible for genes mutation according to variant of genes mutation.
 */



public interface IReproduction {
    int[] mutate(int[] genom, int mutationsNumber);

    Animal createAnimal(Animal parent1, Animal parent2);
}
