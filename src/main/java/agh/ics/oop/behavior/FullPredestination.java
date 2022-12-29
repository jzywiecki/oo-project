package agh.ics.oop.behavior;

import agh.ics.oop.MapDirection;
import agh.ics.oop.Vector2d;
import agh.ics.oop.animal.Animal;
import agh.ics.oop.interfaces.IBehaviorGenerator;
import agh.ics.oop.interfaces.IWorldMap;

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
