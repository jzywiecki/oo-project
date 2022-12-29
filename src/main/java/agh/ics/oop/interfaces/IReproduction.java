package agh.ics.oop.interfaces;

/**
 * The interface responsible for genes mutation according to variant of genes mutation.
 */



public interface IReproduction {
    int[] mutate(int[] genom, int mutationsNumber);
}
