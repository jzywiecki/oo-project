package agh.ics.oop;

import agh.ics.oop.variants.BehaviorVariant;
import agh.ics.oop.variants.GrassVariant;
import agh.ics.oop.variants.MapVariant;
import agh.ics.oop.variants.MutationVariant;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;


public class OptionsParser {

    public static SimulationConfiguration parseFile(File configFile) {

        Properties props = new Properties();
        try {
            InputStream input = new FileInputStream(configFile);
            props.load(input);
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }
        return parse(props);
    }

    public static SimulationConfiguration parse(Properties props) {

        MapVariant mapVariant = switch(props.getProperty("mapVariant")) {
            case "GLOBE_MAP" -> MapVariant.GLOBE_MAP;
            case "HELLISH_MAP" -> MapVariant.HELLISH_MAP;
            default -> throw new IllegalArgumentException("nieprawidłowy wariant mapy");
        };
        GrassVariant grassVariant = switch(props.getProperty("grassVariant")) {
            case "DEAD" -> GrassVariant.DEAD;
            case "MIDDLE" -> GrassVariant.MIDDLE;
            default -> throw new IllegalArgumentException("nieprawidłowy wariant trawy");
        };
        BehaviorVariant behaviorVariant = switch(props.getProperty("behaviorVariant")) {
            case "FULL_PREDESTINATION" -> BehaviorVariant.FULL_PREDESTINATION;
            case "CRAZY" -> BehaviorVariant.CRAZY;
            default -> throw new IllegalArgumentException("nieprawidłowy wariant zachowania");
        };
        MutationVariant mutationVariant = switch(props.getProperty("mutationVariant")) {
            case "SLIGHT_CORRECTION" -> MutationVariant.SLIGHT_CORRECTION;
            case "RANDOM" -> MutationVariant.RANDOM;
            default -> throw new IllegalArgumentException("nieprawidłowy wariant mutacji");
        };

        return new SimulationConfiguration(
            new Vector2d(Integer.parseInt(props.getProperty("height")), Integer.parseInt(props.getProperty("width"))),
            mapVariant,
            Integer.parseInt(props.getProperty("startingGrass")),
            Integer.parseInt(props.getProperty("grassEnergy")),
            Integer.parseInt(props.getProperty("grassGrowth")),
            grassVariant,
            Integer.parseInt(props.getProperty("numberOfAnimals")),
            Integer.parseInt(props.getProperty("startingEnergy")),
            Integer.parseInt(props.getProperty("reproductionRequirement")),
            Integer.parseInt(props.getProperty("energyToReproduction")),
            Integer.parseInt(props.getProperty("minMutations")),
            Integer.parseInt(props.getProperty("maxMutations")),
            mutationVariant,
            Integer.parseInt(props.getProperty("genomesLength")),
            behaviorVariant,
            Integer.parseInt(props.getProperty("dailyEnergyCost"))
        );


    }
}
