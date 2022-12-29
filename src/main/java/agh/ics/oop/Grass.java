package agh.ics.oop;

import agh.ics.oop.interfaces.IMapElement;

import java.util.Objects;

public record Grass(Vector2d position) implements IMapElement {

    @Override
    public String toString() {
        return "*";
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Grass grass = (Grass) o;
        return Objects.equals(position, grass.position);
    }

    @Override
    public int hashCode() {
        return Objects.hash(position);
    }
}