package agh.ics.oop;

import java.util.LinkedList;
import java.util.Objects;

public record Vector2d(int x, int y) {
    @Override
    public String toString() {
        return "(%d,%d)".formatted(x, y);
    }
    public boolean precedes(Vector2d other) {
        return other.x >= x && other.y >= y;
    }

    public boolean follows(Vector2d other) {
        return other.x <= x && other.y <= y;
    }

    public Vector2d add(Vector2d other) {
        return new Vector2d(x + other.x, y + other.y);
    }

    public Vector2d subtract(Vector2d other) {
        return new Vector2d(x - other.x, y - other.y);
    }

    public Vector2d upperRight(Vector2d other) {
        return new Vector2d(Math.max(x, other.x), Math.max(y, other.y));
    }

    public Vector2d lowerLeft(Vector2d other) {
        return new Vector2d(Math.min(x, other.x), Math.min(y, other.y));
    }

    public Vector2d opposite() {
        return new Vector2d(-x, -y);
    }

    @Override
    public int y() {
        return y;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Vector2d vector2d = (Vector2d) o;
        return x == vector2d.x && y == vector2d.y;
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y);
    }

    public LinkedList<Vector2d> toList(){
        var newList = new LinkedList<Vector2d>();
        newList.add(this);
        return newList;
    }
}
