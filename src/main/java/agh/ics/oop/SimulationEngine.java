package agh.ics.oop;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.concurrent.ThreadLocalRandom;

<<<<<<< Updated upstream
=======
import agh.ics.oop.animal.Animal;
import agh.ics.oop.behavior.BitOfMadness;
import agh.ics.oop.behavior.FullPredestination;
import agh.ics.oop.interfaces.IBehaviorGenerator;
import agh.ics.oop.interfaces.IReproduction;
import agh.ics.oop.interfaces.IWorldMap;
import agh.ics.oop.map.*;
import agh.ics.oop.reproduction.FullRandomness;
import agh.ics.oop.reproduction.SlightCorrection;
import agh.ics.oop.variants.MapVariant;

>>>>>>> Stashed changes
public class SimulationEngine {
    private IWorldMap map;
    private IBehaviorGenerator behavior;
    private IReproduction reproduction;
    private final LinkedList<Animal> animals = new LinkedList<>();
<<<<<<< Updated upstream
=======
    private final SimulationConfiguration configuration;
>>>>>>> Stashed changes

    //remove later
    

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
            case SLIGHT_CORRECTION -> this.reproduction = new SlightCorrection(MapDirection.NORTH, this.map, this.behavior, this.configuration);
            case RANDOM -> this.reproduction =new FullRandomness(MapDirection.NORTH, this.map, this.behavior, this.configuration);
        }
<<<<<<< Updated upstream
=======

>>>>>>> Stashed changes
    }


   public void run(){
        //initialize
<<<<<<< Updated upstream
        var visual = new MapVisualizer(map);
        generateAnimals(3);

=======
        generateAnimals(configuration.numberOfAnimals());
>>>>>>> Stashed changes
       //phase 1 dead cleanup

       for(Animal animal : animals){
           if( animal.getEnergy() <= 0){
               map.deleteAnimal(animal);
               animals.remove(animal);
           }

       }

       //phase 2 movement
       for (int i = 0; i< 20; i++){
           animals.forEach((Animal::move));
           System.out.println(map);
       }



       // phase 3 eating


       // phase 4 breeding


       // phase 5 grass growth

    }

    private void generateAnimals(int n){
        Random rng = new Random();
        for(int i = 0; i< n; i++){
<<<<<<< Updated upstream
            int x = ThreadLocalRandom.current().nextInt(0, 5);
            int y = ThreadLocalRandom.current().nextInt(0, 5);
            if(map.isOccupied(new Vector2d(x, y))){
                i--;
            }else {
                Animal animal = new Animal( MapDirection.NORTH, new Vector2d(x,y), map, null, null);
=======
            int x = rng.nextInt(configuration.bounds().x());
            int y = rng.nextInt(configuration.bounds().y());

                Animal animal = new Animal( MapDirection.NORTH, new Vector2d(x,y), map, generateGenome(), behavior, configuration.startingEnergy());
>>>>>>> Stashed changes
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
