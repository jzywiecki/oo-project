package agh.ics.oop.behavior;


import agh.ics.oop.interfaces.IBehaviorGenerator;



import java.util.Random;

public class BitOfMadness implements IBehaviorGenerator {

    @Override
    public int turn(int activeGene, int genomsLength) {
        Random random = new Random();
        double r = random.nextDouble();
        boolean ifRandom = !(r <= 0.8);

        if (!ifRandom){
            if (activeGene == genomsLength - 1){
                return 0;
            }
            else{
                return (activeGene+1);
            }
        }
        else{
            return random.nextInt(genomsLength-1);
        }
    }

}
