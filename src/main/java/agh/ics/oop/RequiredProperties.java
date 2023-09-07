package agh.ics.oop;

public enum RequiredProperties {
    MAP_VARIANT("mapVariant"),
    GRASS_VARIANT("grassVariant"),
    BEHAVIOR_VARIANT("behaviorVariant"),
    MUTATION_VARIANT("mutationVariant"),
    HEIGHT("height"),
    WIDTH("width"),
    STARTING_GRASS("startingGrass"),
    GRASS_ENERGY("grassEnergy"),
    GRASS_GROWTH("grassGrowth"),
    NUMBER_OF_ANIMALS("numberOfAnimals"),
    STARTING_ENERGY("startingEnergy"),
    REPRODUCTION_REQUIREMENT("reproductionRequirement"),
    ENERGY_TO_REPRODUCTION("energyToReproduction"),
    MIN_MUTATIONS("minMutations"),
    MAX_MUTATIONS("maxMutations"),
    GENOMES_LENGTH("genomesLength"),
    DAILY_ENERGY_COST("dailyEnergyCost");

    private final String propertyName;

    RequiredProperties(String propertyName) {
        this.propertyName = propertyName;
    }

    public String getPropertyName() {
        return propertyName;
    }
}
