package agh.ics.oop;

import agh.ics.oop.variants.BehaviorVariant;
import agh.ics.oop.variants.GrassVariant;
import agh.ics.oop.variants.MapVariant;
import agh.ics.oop.variants.MutationVariant;

public record SimulationConfiguration(
        Vector2d bounds,
        MapVariant map,
        int startingGrass,
        int grassEnergy,
        int grassGrowth,
        GrassVariant grassVariant,
        int numberOfAnimals,
        int startingEnergy,
        int reproductionRequirement,
        int energyToReproduction,
        int minMutations,
        int maxMutations,
        MutationVariant mutation,
        int genomesLength,
        BehaviorVariant behavior,
        int dailyEnergyCost
){
    @Override
    public String toString() {
        return "Current configuration: \n" +
                "bounds=" + bounds +
                "\n mapVariant=" + map +
                "\n startingGrass=" + startingGrass +
                "\n grassEnergy=" + grassEnergy +
                "\n grassGrowth=" + grassGrowth +
                "\n grassVariant=" + grassVariant +
                "\n numberOfAnimals=" + numberOfAnimals +
                "\n startingEnergy=" + startingEnergy +
                "\n reproductionRequirement=" + reproductionRequirement +
                "\n energyToReproduction=" + energyToReproduction +
                "\n minMutations=" + minMutations +
                "\n maxMutations=" + maxMutations +
                "\n mutationVariant=" + mutation +
                "\n genomesLength=" + genomesLength +
                "\n dailyEnergyCost=" + dailyEnergyCost +
                "\n behaviorVariant=" + behavior;
    }
}
