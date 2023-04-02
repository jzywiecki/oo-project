package agh.ics.oop.interfaces;

import agh.ics.oop.animal.Animal;

/**
 * The interface responsible for genes mutation according to variant of genes mutation.
 */
public interface IReproduction {
    /**
     * Return animal with a genome created from combination of two given animals.
     * Both parents lose energy and new animal is given energy equal to amount lost by parents
     *
     * @param parent1
     *            First parent
     * @param parent2
     *            Second parent
     * @return Animal with new genome
     */
    Animal createAnimal(Animal parent1, Animal parent2);

}
