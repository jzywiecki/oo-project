package agh.ics.oop.terrain;


import agh.ics.oop.SimulationConfiguration;
import agh.ics.oop.Vector2d;
import agh.ics.oop.interfaces.IDeathObserver;

import java.util.*;


public class ToxicCorpses extends AbstractTerrain implements IDeathObserver {
    private record DeadCounter(int numberOfDead, LinkedList<Vector2d> positions){}
    private TreeSet<DeadCounter> set = new TreeSet<>(Comparator.comparingInt(DeadCounter::numberOfDead));
    private HashMap<Vector2d, Integer> map = new HashMap<>();
    private final int area;
    public ToxicCorpses(SimulationConfiguration configuration) {
        super(configuration);
        initializeCountingCorpses();
        area = configuration.bounds().x() * configuration.bounds().y();
    }

    private void initializeCountingCorpses(){
        LinkedList<Vector2d> initialPositions = new LinkedList<>();
        for(int i = 0; i < configuration.bounds().x(); i++){
            for(int j = 0; j < configuration.bounds().y(); j++) {
                initialPositions.add(new Vector2d(i, j));
                map.put(new Vector2d(i, j), 0);
            }
        }
        set.add(new DeadCounter(0, initialPositions));
    }


    public void animalDied(Vector2d position){
        System.out.println(position);
        int oldDeaths = map.get(position);
        map.replace(position, oldDeaths + 1);

        set.stream()
                .filter(element -> element.numberOfDead == oldDeaths)
                .findAny()
                .ifPresent(element ->{
                    element.positions().remove(position);
                });

        set.stream()
                .filter(element -> element.numberOfDead == oldDeaths + 1)
                .findAny()
                .ifPresentOrElse(
                        element -> element.positions().add(position),
                        () -> {
                            LinkedList<Vector2d> newList = new LinkedList<>();
                            newList.add(position);
                            set.add(new DeadCounter(oldDeaths + 1, newList));
                        }
                );
    }
    @Override
    protected List<Vector2d> getPreferredPosition(){
        double grass = Math.floor(0.2 * area);
        DeadCounter current =  set.first();
        int counter = current.numberOfDead();
        LinkedList<Vector2d> area = new LinkedList<>(current.positions());
        while(counter < grass) {
            current = set.lower(current);
             if(current != null){
              counter += current.numberOfDead();
              area.addAll(current.positions());
             }else{
               counter = (int) Math.round(grass);
             }
        }

        return area;
    }

    @Override
    protected List<Vector2d> getNonPreferredPosition(){
        double grass = area - Math.floor(0.2 * area);
        DeadCounter current =  set.last();
        int counter = current.numberOfDead();
        LinkedList<Vector2d> area = new LinkedList<>(current.positions());
        while(counter < grass) {
            current = set.higher(current);
            if(current != null){
                counter += current.numberOfDead();
                area.addAll(current.positions());
            }else{
                counter = (int) Math.round(grass);
            }
        }
        return area;
    }


}
