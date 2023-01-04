package agh.ics.oop;

import agh.ics.oop.variants.BehaviorVariant;
import agh.ics.oop.variants.GrassVariant;
import agh.ics.oop.variants.MapVariant;
import agh.ics.oop.variants.MutationVariant;

public record SimulationConfiguration(
//    wysokość i szerokość mapy,
        Vector2d bounds,
//    wariant mapy (wyjaśnione w sekcji poniżej),
        MapVariant map,
//    startowa liczba roślin,
        int startingGrass,
//    energia zapewniana przez zjedzenie jednej rośliny,
        int grassEnergy,
//    liczba roślin wyrastająca każdego dnia,
        int grassGrowth,
//    wariant wzrostu roślin (wyjaśnione w sekcji poniżej),
        GrassVariant grassVariant,
//    startowa liczba zwierzaków,
        int numberOfAnimals,
//    startowa energia zwierzaków,
        int startingEnergy,
//    energia konieczna, by uznać zwierzaka za najedzonego (i gotowego do rozmnażania),
        int reproductionRequirement,
//    energia rodziców zużywana by stworzyć potomka,
        int energyToReproduction,
//    minimalna i maksymalna liczba mutacji u potomków (może być równa 0),
        int minMutations,
        int maxMutations,
//    wariant mutacji (wyjaśnione w sekcji poniżej),
        MutationVariant mutation,
//    długość genomu zwierzaków,
        int genomesLength,
//    wariant zachowania zwierzaków (wyjaśnione w sekcji poniżej),
        BehaviorVariant behavior
) {


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
                "\n behaviorVariant=" + behavior;
    }
}


