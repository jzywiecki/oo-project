package agh.ics.oop.animal;

import java.util.Comparator;
import java.util.Random;

public class AnimalComparator implements Comparator<Animal> {

    /**
     *
     * @param animal1 the first object to be compared.
     * @param animal2 the second object to be compared.
     * @return stronger animal
     */

    public int compare(Animal animal1, Animal animal2){

        if (animal1.getEnergy() - animal2.getEnergy() != 0){
            return animal1.getEnergy()-animal2.getEnergy();
        }

        if (animal1.getAge() - animal2.getAge() != 0){
            return animal1.getAge() - animal2.getAge();
        }

        if (animal1.getNumberOfChildren() - animal2.getNumberOfChildren() != 0){
            return animal1.getNumberOfChildren() - animal2.getNumberOfChildren();
        }

        int[] animals = new int[]{-1, 1};
        return animals[new Random().nextInt(2)];
    }
}
