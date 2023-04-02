package agh.ics.oop.animal;

import java.util.Comparator;
import java.util.Random;

public class AnimalComparator implements Comparator<Animal> {


    //returns stronger animal
    public int compare(Animal animal1, Animal animal2){
        //energia
        if (animal1.getEnergy() > animal2.getEnergy()){
            return animal1.getEnergy()-animal2.getEnergy();
        }
        if (animal2.getEnergy() > animal1.getEnergy()){
            return animal1.getEnergy()-animal2.getEnergy();
        }

        //wiek
        if (animal1.getAge() > animal2.getAge()){
            return animal1.getAge() - animal2.getAge();
        }
        if (animal2.getAge() > animal1.getAge()){
            return animal1.getAge() - animal2.getAge();
        }

        //ilosc dzieci
        if (animal1.getNumberOfChildren() > animal2.getNumberOfChildren()){
            return animal1.getNumberOfChildren() - animal2.getNumberOfChildren();
        }
        if (animal2.getNumberOfChildren() > animal1.getNumberOfChildren()){
            return animal1.getNumberOfChildren() - animal2.getNumberOfChildren();
        }

        //wybor losowy
        int[] animals = new int[]{-1, 1};
        return animals[new Random().nextInt(animals.length)];
    }


}