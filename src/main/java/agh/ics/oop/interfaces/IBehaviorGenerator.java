package agh.ics.oop.interfaces;

/**
 * The interface responsible for animal behavior according to variant of animal behavior.
 */

public interface IBehaviorGenerator {

    /**
     * Turn animal based on its genom
     *
     * @param activeGene
     *            Gene that was activated last time animal moved
     * @param genomesLength
     *            Length of animal's genome
     */
    int turn(int activeGene, int genomesLength);

}
