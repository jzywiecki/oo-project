package agh.ics.oop.reproduction;



import agh.ics.oop.animal.Animal;
import agh.ics.oop.animal.AnimalComparator;

import java.util.Random;

import static agh.ics.oop.SimulationVariables.*;
import static agh.ics.oop.SimulationVariables.minMutations;


public abstract class AbstractReproduction {
    //Obliczenie udziału zwierzęcia przy tworzeniu potomka
    private int calculatePart(Animal parent1, Animal parent2){
        return (parent1.getEnergy() / (parent1.getEnergy() + parent2.getEnergy())) + parent1.getEnergy()>parent2.getEnergy()? 1 : 0;
    }

    //tworzenie genomu
    private int[] createGenom(Animal parent1, Animal parent2) {
        int[] genom = new int[genomsLength];
        Random rand = new Random();
        int site = rand.nextInt(2); //0 - left, 1 - right

        AnimalComparator animalComparator = new AnimalComparator();
        Animal strongerAnimal = animalComparator.animalCompare(parent1, parent2);
        Animal weakerAnimal;
        if (strongerAnimal.equals(parent1)){
            weakerAnimal = parent2;
        }
        else{
            weakerAnimal = parent1;
        }


        int[] strongerAnimalGenom = strongerAnimal.getGenoms();
        int[] weakerAnimalGenom = weakerAnimal.getGenoms();
        if (site == 0){

            if (calculatePart(strongerAnimal, weakerAnimal) >= 0)
                System.arraycopy(strongerAnimalGenom, 0, genom, 0, calculatePart(strongerAnimal, weakerAnimal));
            if (genomsLength - calculatePart(strongerAnimal, weakerAnimal) >= 0)
                System.arraycopy(weakerAnimalGenom, calculatePart(strongerAnimal, weakerAnimal), genom, calculatePart(strongerAnimal, weakerAnimal), genomsLength - calculatePart(strongerAnimal, weakerAnimal));
        }
        else {
            if (calculatePart(weakerAnimal, strongerAnimal) >= 0)
                System.arraycopy(weakerAnimalGenom, 0, genom, 0, calculatePart(weakerAnimal, strongerAnimal));
            if (genomsLength - calculatePart(weakerAnimal, strongerAnimal) >= 0)
                System.arraycopy(strongerAnimalGenom, calculatePart(weakerAnimal, strongerAnimal), genom, calculatePart(weakerAnimal, strongerAnimal), genomsLength - calculatePart(weakerAnimal, strongerAnimal));
        }


        mutate(genom, rand.nextInt((maxMutations - minMutations) + 1) + minMutations);
        return genom;
    }

    //dokonanie mutacji, różne zależnie od wariantu
    abstract int[] mutate(int[] genom, int mutationsNumber);

    //stworzenie zwierzęcia z gotowym genomem i energią
    private Animal createAnimal(Animal parent1, Animal parent2){
        Animal child = new Animal(parent1.getPosition(), 2*energyToReproduction, createGenom(parent1, parent2));
        return child;
    }
}
