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
    protected IWorldMap map;
    protected IBehaviorGenerator behavior;
    protected SimulationConfiguration config;

    public AbstractReproduction(IWorldMap map, IBehaviorGenerator behavior, SimulationConfiguration config) {
        this.behavior = behavior;
        this.map = map;
        this.config = config;
    }

    //Obliczenie udziału zwierzęcia przy tworzeniu potomka
    private int calculatePart(Animal parent1, Animal parent2){
        return (parent1.getEnergy() / (parent1.getEnergy() + parent2.getEnergy())) + parent1.getEnergy()>parent2.getEnergy()? 1 : 0;
    }

    abstract protected int[] mutate(int[] genome, int mutationsNumber);

    //tworzenie genomu
    private int[] createGenome(Animal parent1, Animal parent2) {
        int[] genome = new int[config.genomesLength()];
        Random rand = new Random();
        int site = rand.nextInt(2); //0 - left, 1 - right

        AnimalComparator animalComparator = new AnimalComparator();
        Animal strongerAnimal;
        if (animalComparator.compare(parent1, parent2) < 0){
            strongerAnimal = parent1;
        }
        else{
            strongerAnimal = parent2;
        }
        Animal weakerAnimal;
        if (strongerAnimal.equals(parent1)){
            weakerAnimal = parent2;
        }
        else{
            weakerAnimal = parent1;
        }

        int[] strongerAnimalGenome = strongerAnimal.getGenomes();
        int[] weakerAnimalGenome = weakerAnimal.getGenomes();
        if (site == 0){

            if (calculatePart(strongerAnimal, weakerAnimal) >= 0)
                System.arraycopy(strongerAnimalGenome, 0, genome, 0, calculatePart(strongerAnimal, weakerAnimal));
            if (config.genomesLength() - calculatePart(strongerAnimal, weakerAnimal) >= 0)
                System.arraycopy(weakerAnimalGenome, calculatePart(strongerAnimal, weakerAnimal), genome, calculatePart(strongerAnimal, weakerAnimal), config.genomesLength() - calculatePart(strongerAnimal, weakerAnimal));
        }
        else {
            if (calculatePart(weakerAnimal, strongerAnimal) >= 0)
                System.arraycopy(weakerAnimalGenome, 0, genome, 0, calculatePart(weakerAnimal, strongerAnimal));
            if (config.genomesLength() - calculatePart(weakerAnimal, strongerAnimal) >= 0)
                System.arraycopy(strongerAnimalGenome, calculatePart(weakerAnimal, strongerAnimal), genome, calculatePart(weakerAnimal, strongerAnimal), config.genomesLength() - calculatePart(weakerAnimal, strongerAnimal));
        }

        return mutate(genome, rand.nextInt((config.maxMutations() - config.minMutations()) + 1) + config.minMutations());
    }

    //stworzenie zwierzęcia z gotowym genomem i energią
    public Animal createAnimal(Animal parent1, Animal parent2){
        if (parent1.getEnergy() < config.reproductionRequirement() || parent2.getEnergy() < config.reproductionRequirement()){
            return null;
        }
        int[] newGenome = createGenome(parent1, parent2);
        Animal child = new Animal(MapDirection.values()[newGenome[0]], parent1.position(), map, newGenome, behavior, config.startingEnergy());
        parent1.subtractEnergy(config.energyToReproduction());
        parent2.subtractEnergy(config.energyToReproduction());
        parent1.addChild();
        parent2.addChild();
        return child;
    }
}