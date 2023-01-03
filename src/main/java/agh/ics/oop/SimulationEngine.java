package agh.ics.oop;

import java.util.LinkedList;
import java.util.Random;
import agh.ics.oop.animal.Animal;
import agh.ics.oop.behavior.BitOfMadness;
import agh.ics.oop.behavior.FullPredestination;
import agh.ics.oop.interfaces.IBehaviorGenerator;
import agh.ics.oop.animal.AnimalComparator;
import agh.ics.oop.interfaces.IReproduction;
import agh.ics.oop.interfaces.IWorldMap;
import agh.ics.oop.map.*;
import agh.ics.oop.reproduction.FullRandomness;
import agh.ics.oop.reproduction.SlightCorrection;


public class SimulationEngine {
    private IWorldMap map;
    private IBehaviorGenerator behavior;
    private IReproduction reproduction;
    private final LinkedList<Animal> animals = new LinkedList<>();
    private final SimulationConfiguration configuration;

    public SimulationEngine(SimulationConfiguration configuration){
        this.configuration = configuration;
        switch (configuration.map()){
            case GLOBE_MAP -> this.map = new GlobeMap(configuration);
            case HELLISH_MAP -> this.map =new HellishMap(configuration);
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


   public void run(){
        //initialize
        generateAnimals(configuration.numberOfAnimals());


        //simulation
       for (int i = 0; i< 20; i++) {
           //phase 1 dead cleanup

           for (Animal animal : animals) {
               if (animal.getEnergy() <= 0) {
                   map.deleteAnimal(animal);
                   animals.remove(animal);
               }
           }

           //phase 2 movement

           animals.forEach(Animal::move);
           System.out.println(map);

           // phase 3 eating

       }
           // phase 4 breeding
           LinkedList<Vector2d> positions = new LinkedList<>();
           animals.forEach(animal -> {
               if (!positions.contains(animal.position())) {
                   positions.add(animal.position());
               }
           });
           positions.forEach(position -> {
               Object[] animalsToReproduction = animals.stream().filter(animal -> animal.position().equals(position)).sorted((a1, a2) -> new AnimalComparator().compare((Animal) a1, (Animal) a2)).limit(2).toArray();
               if (animalsToReproduction.length == 2) {
                   Animal newAnimal = reproduction.createAnimal((Animal) animalsToReproduction[0], (Animal) animalsToReproduction[1]);
                   map.place(newAnimal);
                   this.animals.add(newAnimal);
               }
           });

           // phase 5 grass growth

    }

    private void generateAnimals(int n){
        Random rng = new Random();
        for(int i = 0; i< n; i++){
            int x = rng.nextInt(configuration.bounds().x());
            int y = rng.nextInt(configuration.bounds().y());
                Animal animal = new Animal( MapDirection.NORTH, new Vector2d(x,y), map, generateGenome(), behavior, configuration.startingEnergy());
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

}
