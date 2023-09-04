package agh.ics.oop;

import agh.ics.oop.animal.Animal;
import agh.ics.oop.interfaces.IWorldMap;

import java.util.*;

public class MapStats {
    private int numberOfAnimals;
    private int numberOfGrass;
    private int[] mostPopularGenome;
    private int emptySpaces;
    private int averageEnergy;
    private final SimulationEngine simulationEngine;
    private int currentDay = 0;
    private int averageDaysLived = 0;

    public MapStats(SimulationEngine simulationEngine) {
        this.simulationEngine = simulationEngine;
        update();
    }

    public void update(){
        this.currentDay += 1;
        this.numberOfAnimals = simulationEngine.getAnimals().size();
        this.numberOfGrass = simulationEngine.getTerrain().getGrass().size();
        this.emptySpaces = calculateEmptySpaces();
        this.mostPopularGenome = calculateMostPopularGenome();
        this.averageDaysLived = calculateAverageDaysLived();
        this.averageEnergy = calculateAverageEnergy();
    }

    private int[] calculateMostPopularGenome(){
        List<Animal> animals = simulationEngine.getAnimals();
        Map<List<Integer>, Integer> genomeCounts = new HashMap<>();

        for (Animal animal : animals) {
            List<Integer> genomeList = Arrays.stream(animal.getGenomes()).boxed().toList();
            genomeCounts.put(genomeList, genomeCounts.getOrDefault(genomeList, 0) + 1);
        }

        Map.Entry<List<Integer>, Integer> mostPopularEntry = Collections.max(
                genomeCounts.entrySet(), Map.Entry.comparingByValue()
        );

        return mostPopularEntry.getKey().stream().mapToInt(Integer::intValue).toArray();
    }

    private int calculateEmptySpaces(){
        IWorldMap map = simulationEngine.getMap();
        int counter = 0;
        for (int i = 0; i < map.getUpperBound().x(); i++){
            for (int j = 0; j < map.getUpperBound().y(); j++){
                if (!map.isOccupied(new Vector2d(i, j))){
                    counter += 1;
                }
            }
        }
        return counter;
    }


    private int calculateAverageEnergy(){
        return simulationEngine.getAnimals()
                      .stream()
                      .reduce(0, (subtotal, animal) -> subtotal + animal.getEnergy(), Integer::sum)/simulationEngine.getAnimals().size();
    }

    public int calculateAverageDaysLived(){
        if (simulationEngine.getDayOfAnimalsDeath().size() > 0) {
            return simulationEngine.getDayOfAnimalsDeath().stream().reduce(0, Integer::sum) / simulationEngine.getDayOfAnimalsDeath().size();
        }
        else{
            return 0;
        }
    }

    public int getAverageDaysLived() {
        return averageDaysLived;
    }

    public int getCurrentDay() {
        return currentDay;
    }

    public int getAverageEnergy() {
        return averageEnergy;
    }

    public int getEmptySpaces() {
        return emptySpaces;
    }

    public int getNumberOfAnimals() {
        return numberOfAnimals;
    }

    public int getNumberOfGrass() {
        return numberOfGrass;
    }

    public int[] getMostPopularGenome() {
        return mostPopularGenome;
    }
}
