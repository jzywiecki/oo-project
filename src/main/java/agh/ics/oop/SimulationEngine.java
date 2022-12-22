package agh.ics.oop;

import java.util.LinkedList;
import java.util.concurrent.ThreadLocalRandom;

public class SimulationEngine {
    private IWorldMap map;
    private final LinkedList<Animal> animals = new LinkedList<>();

    //remove later
    private final Vector2d b1 = new Vector2d(0,0);
    private final Vector2d b2 =  new Vector2d(11, 11);

    public SimulationEngine(MapVariant mapVariant){
        switch (mapVariant){
            case GLOBE_MAP: this.map = new GlobeMap(b1, b2);
            case HELLISH_MAP: this.map =new HellishMap(b1, b2);
        }



    }


   public void run(){
        //initialize
        var visual = new MapVisualizer(map);
        generateAnimals(5);
       //phase 1 dead cleanup
       System.out.println(map);

       //phase 2 movement
//       for (int i = 0; i< 20; i++){
//
//           animals.forEach((Animal::move));
//           System.out.println(map);
//       }


       // phase 3 eating


       // phase 4 breeding


       // phase 5 grass growth

    }

    //to potem zmienie
    private void generateAnimals(int n){
        for(int i = 0; i< n; i++){
            int x = ThreadLocalRandom.current().nextInt(0, 10);
            int y = ThreadLocalRandom.current().nextInt(0, 10);
            if(map.isOccupied(new Vector2d(x, y))){
                i--;
            }else {
                Animal animal = new Animal( MapDirection.NORTH, new Vector2d(x,y), map, null, null);
                map.place(animal);
                this.animals.add(animal);
            }
        }
    }
}
