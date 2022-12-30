package agh.ics.oop;

import java.util.*;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.Stream;

import agh.ics.oop.animal.Animal;
import agh.ics.oop.animal.AnimalComparator;
import agh.ics.oop.behavior.BitOfMadness;
import agh.ics.oop.interfaces.IMapElement;
import agh.ics.oop.interfaces.IReproduction;
import agh.ics.oop.interfaces.IWorldMap;
import agh.ics.oop.map.*;
import agh.ics.oop.reproduction.FullRandomness;
import agh.ics.oop.variants.MapVariant;

public class SimulationEngine {
    private IWorldMap map;
    private IReproduction reproduction;
    private final LinkedList<Animal> animals = new LinkedList<>();
    private SimulationConfiguration configuration;

    //remove later
    private final Vector2d b1 = new Vector2d(0,0);
    private final Vector2d b2 =  new Vector2d(6, 6);

    public SimulationEngine(MapVariant mapVariant){
        switch (mapVariant){
            case GLOBE_MAP -> this.map = new GlobeMap(b2, b1);
            case HELLISH_MAP -> this.map =new HellishMap(b2, b1);
        }



    }


   public void run(){
        //initialize
        generateAnimals(3);
       //phase 1 dead cleanup
       System.out.println(map);

       //phase 2 movement
       for (int i = 0; i< 20; i++){

           animals.forEach(Animal::move);
           System.out.println(map);
       }


       // phase 3 eating


       // phase 4 breeding
       FullRandomness makeAnimal = new FullRandomness(map, new BitOfMadness(), this.configuration);
       LinkedList<Vector2d> positions = new LinkedList<>();
       animals.forEach(animal -> {
           if(!positions.contains(animal.position())){
               positions.add(animal.position());
           }
       });
       positions.forEach(position -> {
                   Object[] animalsToReproduction = animals.stream().filter(animal -> animal.position().equals(position)).sorted((a1, a2) -> new AnimalComparator().compare((Animal) a1, (Animal) a2)).limit(2).toArray();
                   if (animalsToReproduction.length == 2){
                       Animal newAnimal = reproduction.createAnimal((Animal) animalsToReproduction[0], (Animal) animalsToReproduction[1]);
                       map.place(newAnimal);
                       this.animals.add(newAnimal);
                   }
               }

       );

       }

       // phase 5 grass growth



    //to potem zmienie
    private void generateAnimals(int n){
        for(int i = 0; i< n; i++){
            int x = ThreadLocalRandom.current().nextInt(0, 5);
            int y = ThreadLocalRandom.current().nextInt(0, 5);
            if(map.isOccupied(new Vector2d(x, y))){
                i--;
            }else {
                Random rng = new Random();
                int[] genoms = new int[2];
                for (int j = 0; j < 2; j++){
                    genoms[j] = rng.nextInt(8);
                }
                Animal animal = new Animal( MapDirection.NORTH, new Vector2d(x,y), map, genoms, new BitOfMadness(), 20);
                map.place(animal);
                this.animals.add(animal);
            }
        }
    }
}
