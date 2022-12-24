package agh.ics.oop.animal;

import agh.ics.oop.Vector2d;

import static agh.ics.oop.SimulationVariables.genomsLength;

public class Animal {
    private Vector2d position;
    private int age;
    private int numberOfChildren;
    private int energy;
    private int[] genoms = new int[genomsLength];

    public Animal(Vector2d position, int energy, int[] genoms) {
        this.position = position;
        this.energy = energy;
        this.genoms = genoms;
        this.age = 0;
        this.numberOfChildren = 0;
    }

    public Vector2d getPosition() {
        return position;
    }

    public int[] getGenoms(){
        return genoms;
    }

    public int getEnergy(){
        return energy;
    }

    public int getAge() {
        return age;
    }

    public int getNumberOfChildren() {
        return numberOfChildren;
    }
}
