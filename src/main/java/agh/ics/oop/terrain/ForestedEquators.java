package agh.ics.oop.terrain;

import agh.ics.oop.Grass;
import agh.ics.oop.Vector2d;
import agh.ics.oop.interfaces.IGrassGenerator;

import static java.lang.Math.abs;

public class ForestedEquators  implements IGrassGenerator {
    @Override
    public void place(Grass grass, Vector2d lowerBound, Vector2d upperBound) {
        int equatorLattitude = (int) abs((0.2*(upperBound.y()-lowerBound.y()+1)));



    }
}

