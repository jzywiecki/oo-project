package agh.ics.oop;

public class SimulationEngine {
    private IWorldMap map;

    public SimulationEngine(int mapVariant){
        switch (mapVariant){
            case 1: this.map = new GlobeMap();
            case 2: this.map =new HellishMap();
        }
    }

}
