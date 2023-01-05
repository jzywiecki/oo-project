package agh.ics.oop;

import agh.ics.oop.animal.Animal;
import agh.ics.oop.interfaces.IWorldMap;

import java.util.*;

public class MapStats {
    private int numberOfAnimals;
    private int numberOfGrass;
    private int[] mostPopularGenome;
    private int averageDaysLived;
    private int emptySpaces;
    private int averageEnergy;
    private int currentDay;

    public MapStats(SimulationConfiguration config, SimulationEngine engine) {
        this.currentDay = 0;
        this.numberOfAnimals = engine.getAnimals().size();
        this.numberOfGrass = config.startingGrass();
        this.emptySpaces = calculateEmptySpaces(engine);
        this.mostPopularGenome = calculateMostPopularGenome(engine, config);
        this.averageDaysLived = 0;
        this.averageEnergy = calculateAverageEnergy(engine);
    }

    private int[] calculateMostPopularGenome(SimulationEngine engine, SimulationConfiguration configuration){
        Object[] animals = engine.getAnimals().toArray();
        int[][] animalsGenomes = new int[animals.length][configuration.genomesLength()];
        for (int i = 0; i < animals.length; i++){
            Animal animal = (Animal) animals[i];
            animalsGenomes[i] = animal.getGenomes();
        }
        int[] counter = new int[animals.length];
        for (int i = 0; i < animalsGenomes.length; i++){
            for (int j = 0; j < animalsGenomes.length; j++){
                if (!(i==j)){
                    if (animalsGenomes[i].equals(animalsGenomes[j])){
                        counter[i]++;
                        counter[j]++;
                    }
                }
            }
        }
        int mostPopularGenomeNumber = Arrays.stream(counter).max().getAsInt();
        int answer[] = new int[configuration.genomesLength()];
        for (int i = 0; i < counter.length; i++){
            if (counter[i] == mostPopularGenomeNumber){
                answer = animalsGenomes[i];
            }
        }
        return answer;
    }

    private int calculateEmptySpaces(SimulationEngine engine){
        IWorldMap map = engine.getMap();
        int counter = 0;
        for (int i = 0; i < map.getUpperBound().x(); i++){
            for (int j = 0; j < map.getUpperBound().y(); j++){
                if (!map.isOccupied(new Vector2d(i, j))){
                    counter++;
                }
            }
        }
        return counter;
    }


    private int calculateAverageEnergy(SimulationEngine engine){
        return (engine.getAnimals().stream().reduce(0, (subtotal, animal) -> subtotal + animal.getEnergy(), Integer::sum)/engine.getAnimals().size());
    }

    public int calculateAverageDaysLived(SimulationEngine engine){
        if (engine.getDayOfAnimalsDeath().size() > 0) {
            return engine.getDayOfAnimalsDeath().stream().reduce(0, Integer::sum) / engine.getDayOfAnimalsDeath().size();
        }
        else{
            return 0;
        }
    }

    public void update(SimulationEngine engine, SimulationConfiguration config){
        this.currentDay +=1;
        this.numberOfAnimals = engine.getAnimals().size();
        this.numberOfGrass = engine.getTerrain().getGrass().size();
        this.emptySpaces = calculateEmptySpaces(engine);
        this.mostPopularGenome = calculateMostPopularGenome(engine, config);
        this.averageDaysLived = calculateAverageDaysLived(engine);
        this.averageEnergy = calculateAverageEnergy(engine);
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
