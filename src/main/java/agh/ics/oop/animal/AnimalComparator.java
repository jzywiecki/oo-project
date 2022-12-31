package agh.ics.oop.animal;


import agh.ics.oop.animal.Animal;

import java.util.Random;

public class AnimalComparator {


    //returns stronger animal
    public Animal animalCompare(Animal animal1, Animal animal2){
        //energia
        if (animal1.getEnergy() > animal2.getEnergy()){
            return animal1;
        }
        if (animal2.getEnergy() > animal1.getEnergy()){
            return animal2;
        }

        //wiek
        if (animal1.getAge() > animal2.getAge()){
            return animal1;
        }
        if (animal2.getAge() > animal1.getAge()){
            return animal2;
        }

        //ilosc dzieci
        if (animal1.getNumberOfChildren() > animal2.getNumberOfChildren()){
            return animal1;
        }
        if (animal2.getNumberOfChildren() > animal1.getNumberOfChildren()){
            return animal2;
        }

        //wybor losowy
        Animal[] animals = new Animal[]{animal1, animal2};
        return animals[new Random().nextInt(animals.length)];
    }

}