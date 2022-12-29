package agh.ics.oop.reproduction;



import agh.ics.oop.interfaces.IReproduction;
import agh.ics.oop.map.MapDirection;
import agh.ics.oop.SimulationConfiguration;
import agh.ics.oop.animal.Animal;
import agh.ics.oop.animal.AnimalComparator;
import agh.ics.oop.interfaces.IBehaviorGenerator;
import agh.ics.oop.interfaces.IWorldMap;

import java.util.Random;


public abstract class AbstractReproduction implements IReproduction {
    MapDirection direction;
    IWorldMap map;
    IBehaviorGenerator behavior;
    SimulationConfiguration config;

    public AbstractReproduction(MapDirection direction, IWorldMap map, IBehaviorGenerator behavior, SimulationConfiguration config) {
        this.direction = direction;
        this.behavior = behavior;
        this.map = map;
        this.config = config;
    }

    //Obliczenie udziału zwierzęcia przy tworzeniu potomka
    private int calculatePart(Animal parent1, Animal parent2){
        return (parent1.getEnergy() / (parent1.getEnergy() + parent2.getEnergy())) + parent1.getEnergy()>parent2.getEnergy()? 1 : 0;
    }

    //tworzenie genomu
    private int[] createGenom(Animal parent1, Animal parent2) {
        int[] genom = new int[config.genomsLength()];
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
            if (config.genomsLength() - calculatePart(strongerAnimal, weakerAnimal) >= 0)
                System.arraycopy(weakerAnimalGenom, calculatePart(strongerAnimal, weakerAnimal), genom, calculatePart(strongerAnimal, weakerAnimal), config.genomsLength() - calculatePart(strongerAnimal, weakerAnimal));
        }
        else {
            if (calculatePart(weakerAnimal, strongerAnimal) >= 0)
                System.arraycopy(weakerAnimalGenom, 0, genom, 0, calculatePart(weakerAnimal, strongerAnimal));
            if (config.genomsLength() - calculatePart(weakerAnimal, strongerAnimal) >= 0)
                System.arraycopy(strongerAnimalGenom, calculatePart(weakerAnimal, strongerAnimal), genom, calculatePart(weakerAnimal, strongerAnimal), config.genomsLength() - calculatePart(weakerAnimal, strongerAnimal));
        }

        mutate(genom, rand.nextInt((config.maxMutations() - config.minMutations()) + 1) + config.minMutations());

        return genom;
    }

    //stworzenie zwierzęcia z gotowym genomem i energią
    public Animal createAnimal(Animal parent1, Animal parent2){
        Animal child = new Animal(direction, parent1.position(), map, createGenom(parent1, parent2), behavior, 2*config.energyToReproduction());
        parent1.substractEnergy(config.energyToReproduction());
        parent2.substractEnergy(config.energyToReproduction());
        return child;
    }
}
