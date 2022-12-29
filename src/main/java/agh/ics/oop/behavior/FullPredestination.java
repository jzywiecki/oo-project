package agh.ics.oop.behavior;

import agh.ics.oop.interfaces.IBehaviorGenerator;

public class FullPredestination implements IBehaviorGenerator {


    @Override
    public int turn(int activeGene, int genomsLength) {
        if (activeGene == genomsLength - 1){
            return 0;
        }
        else{
            return (activeGene+1);
        }
    }
}
