package agh.ics.oop.behavior;

import agh.ics.oop.interfaces.IBehaviorGenerator;


public class FullPredestination implements IBehaviorGenerator {

    @Override
    public int turn(int activeGene, int genomesLength) {
            return (activeGene+1) % genomesLength;
    }
}
