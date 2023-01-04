package agh.ics.oop.terrain;


import agh.ics.oop.SimulationConfiguration;
import agh.ics.oop.interfaces.IWorldMap;


public class ToxicCorpses extends AbstractTerrain {

    public ToxicCorpses(IWorldMap map, SimulationConfiguration configuration) {super(map, configuration);}

    @Override
    protected void placeOnPreferedPosition(int quantity) {

    }

    @Override
    protected void placeOnNonPreferedPosition(int quantity) {

    }


}
