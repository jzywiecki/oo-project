package agh.ics.oop;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Random;
import agh.ics.oop.animal.Animal;
import agh.ics.oop.behavior.BitOfMadness;
import agh.ics.oop.behavior.FullPredestination;
import agh.ics.oop.gui.IGuiObserver;
import agh.ics.oop.interfaces.*;
import agh.ics.oop.animal.AnimalComparator;
import agh.ics.oop.map.*;
import agh.ics.oop.reproduction.FullRandomness;
import agh.ics.oop.reproduction.SlightCorrection;
import agh.ics.oop.terrain.ForestedEquators;
import agh.ics.oop.terrain.ToxicCorpses;


public class SimulationEngine implements Runnable {
    private IWorldMap map;
    private IBehaviorGenerator behavior;
    private IReproduction reproduction;
    private IGrassGenerator terrain;
    private final LinkedList<Animal> animals = new LinkedList<>();
    private final SimulationConfiguration configuration;
    private MapStats mapStats;
    private final LinkedList<Integer> dayOfAnimalsDeath = new LinkedList<Integer>();
    private boolean csv;


    private final ArrayList<IGuiObserver> iGuiObservers = new ArrayList<>();
    private final ArrayList<IDeathObserver> iDeathObservers = new ArrayList<>();

    public SimulationEngine(SimulationConfiguration configuration, boolean csv){
        this.csv = csv;
        this.configuration = configuration;
        switch (configuration.grassVariant()){
            case MIDDLE -> this.terrain = new ForestedEquators(this.configuration);
            case DEAD -> {
                this.terrain = new ToxicCorpses(this.configuration);
                this.addObserver((ToxicCorpses) this.terrain);
            }
        }
        switch (configuration.map()){
            case GLOBE_MAP -> this.map = new GlobeMap(configuration, terrain);
            case HELLISH_MAP -> this.map =new HellishMap(configuration, terrain);
        }
        switch (configuration.behavior()){
            case FULL_PREDESTINATION -> this.behavior = new FullPredestination();
            case CRAZY -> this.behavior =new BitOfMadness();
        }
        switch (configuration.mutation()){
            case SLIGHT_CORRECTION -> this.reproduction = new SlightCorrection(this.map, this.behavior, this.configuration);
            case RANDOM -> this.reproduction =new FullRandomness(this.map, this.behavior, this.configuration);
        }
    }


   public void run() {
       //initialize
       generateAnimals(configuration.numberOfAnimals());
       mapStats = new MapStats(this.configuration, this);
       System.out.println(map);
        //simulation
       for (int i = 0; i< 40; i++) {
           //phase 1 dead cleanup
          var deadAnimals = animals.stream().filter(animal -> animal.getEnergy() <= 0).toList();
            deadAnimals.forEach(animal -> {
                for (IDeathObserver observer : iDeathObservers) {
                        observer.animalDied(animal.position());
                }
                dayOfAnimalsDeath.add(animal.getAge());
                  map.deleteAnimal(animal);
                  animals.remove(animal);
            });
           //phase 2 movement and energy loss
           animals.forEach(animal -> {
               animal.move();
               animal.subtractEnergy(configuration.dailyEnergyCost());

           });
           for (IGuiObserver observer : iGuiObservers) {
               try {
                   observer.positionChanged();
               } catch (InterruptedException e) {
                   throw new RuntimeException(e);
               }
           }
           System.out.println(map);


           // phase 3 eating
            map.eatGrass();


           // phase 4 breeding
           LinkedList<Vector2d> positions = new LinkedList<>();
           animals.forEach(animal -> {
               if (!positions.contains(animal.position())) {
                   positions.add(animal.position());
               }
           });
           positions.forEach(position -> {
               Object[] animalsToReproduction = animals.stream().filter(animal -> animal.position().equals(position) && animal.getEnergy() >= configuration.energyToReproduction()).sorted(new AnimalComparator()).limit(2).toArray();
               if (animalsToReproduction.length == 2) {
                   Animal newAnimal = this.reproduction.createAnimal((Animal) animalsToReproduction[0], (Animal) animalsToReproduction[1]);
                   if (newAnimal != null){
                       map.place(newAnimal);
                       this.animals.add(newAnimal);

                   }
               }
           });

           // phase 5 grass growth
            terrain.placeGrasses();

            this.mapStats.update(this, configuration);
       }
    }

    private void generateAnimals(int n){
        Random rng = new Random();
        for(int i = 0; i< n; i++){
            int x = rng.nextInt(configuration.bounds().x());
            int y = rng.nextInt(configuration.bounds().y());
                Animal animal = new Animal( MapDirection.EAST, new Vector2d(x,y), map, generateGenome(), behavior, configuration.startingEnergy());
                map.place(animal);
                this.animals.add(animal);
        }
    }


    private int[] generateGenome(){
        Random rng = new Random();
        int[] genomes = new int[configuration.genomesLength()];
        for (int j = 0; j < configuration.genomesLength(); j++){
            genomes[j] = rng.nextInt(8);
        }
        return genomes;
    }

    public IWorldMap getMap() {
        return map;
    }

    public IGrassGenerator getTerrain() {
        return terrain;
    }

    public LinkedList<Animal> getAnimals() {
        return animals;
    }

    public void addObserver(IGuiObserver observer){
        iGuiObservers.add(observer);
    }

    public void removeObserver(IGuiObserver observer){
        iGuiObservers.remove(observer);
    }

    public void addObserver(IDeathObserver observer){
        iDeathObservers.add(observer);
    }

    public void removeObserver(IDeathObserver observer){
        iDeathObservers.remove(observer);
    }

    public MapStats getStats() {
        return mapStats;
    }

    public SimulationConfiguration getConfiguration() {
        return configuration;
    }

    public LinkedList<Integer> getDayOfAnimalsDeath() {
        return dayOfAnimalsDeath;
    }

    public boolean isCsv() {
        return csv;
    }
}
