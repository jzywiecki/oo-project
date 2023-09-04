package agh.ics.oop.behavior;


import agh.ics.oop.interfaces.IBehaviorGenerator;


public class BitOfMadness implements IBehaviorGenerator {
    @Override
    public int turn(int activeGene, int genomesLength) {
        double randomValue = Math.random();
        if (randomValue <= 0.8){
            return (int) (Math.random() * genomesLength);
        }
        else{
            return (activeGene + 1) % genomesLength;
        }
    }

}
