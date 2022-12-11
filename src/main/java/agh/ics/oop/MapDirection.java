package agh.ics.oop;

public enum MapDirection {
    NORTH,
    NORTH_EAST,
    EAST,
    SOUTH_EAST,
    SOUTH,
    SOUTH_WEST,
    WEST,
    NORTH_WEST;

    public MapDirection next(){
        return MapDirection.values()[(ordinal() + 1) % MapDirection.values().length];
    }

    public MapDirection add(int n){
        return MapDirection.values()[(ordinal() + n) % MapDirection.values().length];
    }

    public MapDirection previous(){
        return MapDirection.values()[(ordinal() - 1 + MapDirection.values().length) % MapDirection.values().length];
    }

    public Vector2d toUnitVector(){
        return switch (this){
            case NORTH -> new Vector2d(0, 1);
            case NORTH_EAST -> new Vector2d(1, 1);
            case EAST -> new Vector2d(1, 0);
            case SOUTH_EAST -> new Vector2d(1, -1);
            case SOUTH -> new Vector2d(0, -1);
            case SOUTH_WEST -> new Vector2d(-1, -1);
            case WEST -> new Vector2d(-1, 0);
            case NORTH_WEST -> new Vector2d(-1, 1);
        };
    }

    @Override
    public String toString() {
        return switch (this) {
            case NORTH -> "Północ";
            case NORTH_EAST -> "Północny wschód";
            case EAST -> "Wschód";
            case SOUTH_EAST -> "Południowy wschód";
            case SOUTH -> "Południe";
            case SOUTH_WEST -> "Południowy zachód";
            case WEST -> "Zachód";
            case NORTH_WEST -> "Północny zachód";
        };
    }
}
