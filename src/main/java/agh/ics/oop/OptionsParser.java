package agh.ics.oop;

import agh.ics.oop.variants.BehaviorVariant;
import agh.ics.oop.variants.GrassVariant;
import agh.ics.oop.variants.MapVariant;
import agh.ics.oop.variants.MutationVariant;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
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

    private static SimulationConfiguration parse(Properties props) {

        var missingProperties = new ArrayList<RequiredProperties>();

        for (RequiredProperties property : RequiredProperties.values()) {
            if (!props.containsKey(property.getPropertyName())) {
                missingProperties.add(property);
            }
        }

        if(!missingProperties.isEmpty()){
            StringBuilder errorMessage = new StringBuilder("Missing properties: ");
            for (RequiredProperties property : missingProperties) {
                errorMessage.append(property.getPropertyName()).append(", ");
            }
            errorMessage.delete(errorMessage.length() - 2, errorMessage.length());
            throw new IllegalArgumentException(errorMessage.toString());
        }

        MapVariant mapVariant = switch(props.getProperty("mapVariant")) {
            case "GLOBE_MAP" -> MapVariant.GLOBE_MAP;
            case "HELLISH_MAP" -> MapVariant.HELLISH_MAP;
            default -> throw new IllegalArgumentException("Incorrect map variant");
        };
        GrassVariant grassVariant = switch(props.getProperty("grassVariant")) {
            case "DEAD" -> GrassVariant.DEAD;
            case "MIDDLE" -> GrassVariant.MIDDLE;
            default -> throw new IllegalArgumentException("Incorrect grass variant");
        };
        BehaviorVariant behaviorVariant = switch(props.getProperty("behaviorVariant")) {
            case "FULL_PREDESTINATION" -> BehaviorVariant.FULL_PREDESTINATION;
            case "CRAZY" -> BehaviorVariant.CRAZY;
            default -> throw new IllegalArgumentException("Incorrect behavior variant");
        };
        MutationVariant mutationVariant = switch(props.getProperty("mutationVariant")) {
            case "SLIGHT_CORRECTION" -> MutationVariant.SLIGHT_CORRECTION;
            case "RANDOM" -> MutationVariant.RANDOM;
            default -> throw new IllegalArgumentException("Incorrect mutation variant");
        };

        try{
            int height = Integer.parseInt(props.getProperty("height"));
            int width = Integer.parseInt(props.getProperty("width"));
            int startingGrass = Integer.parseInt(props.getProperty("startingGrass"));
            int grassEnergy = Integer.parseInt(props.getProperty("grassEnergy"));
            int grassGrowth = Integer.parseInt(props.getProperty("grassGrowth"));
            int numberOfAnimals = Integer.parseInt(props.getProperty("numberOfAnimals"));
            int startingEnergy = Integer.parseInt(props.getProperty("startingEnergy"));
            int reproductionRequirement = Integer.parseInt(props.getProperty("reproductionRequirement"));
            int energyToReproduction = Integer.parseInt(props.getProperty("energyToReproduction"));
            int minMutations = Integer.parseInt(props.getProperty("minMutations"));
            int maxMutations = Integer.parseInt(props.getProperty("maxMutations"));
            int genomesLength = Integer.parseInt(props.getProperty("genomesLength"));
            int dailyEnergyCost = Integer.parseInt(props.getProperty("dailyEnergyCost"));

            if (startingGrass < 0 || grassEnergy < 0 || grassGrowth < 0 || numberOfAnimals < 0 ||
                    startingEnergy < 0 || reproductionRequirement < 0 || energyToReproduction < 0 ||
                    minMutations < 0 || maxMutations < 0 || genomesLength < 0 || dailyEnergyCost < 0) {
                throw new IllegalArgumentException("All values must be greater than or equal to 0.");
            }

            if (height <= 0 || width <= 0) {
                throw new IllegalArgumentException("Height and width must be greater than 0.");
            }

            if (maxMutations > genomesLength) {
                throw new IllegalArgumentException("maxMutations cannot be greater than genomesLength.");
            }

            if (energyToReproduction > reproductionRequirement) {
                throw new IllegalArgumentException("energyToReproduction cannot be greater than reproductionRequirement.");
            }

            return new SimulationConfiguration(
                    new Vector2d(height, width),
                    mapVariant,
                    startingGrass,
                    grassEnergy,
                    grassGrowth,
                    grassVariant,
                    numberOfAnimals,
                    startingEnergy,
                    reproductionRequirement,
                    energyToReproduction,
                    minMutations,
                    maxMutations,
                    mutationVariant,
                    genomesLength,
                    behaviorVariant,
                    dailyEnergyCost
            );
        }catch(NumberFormatException e){
            throw new IllegalArgumentException("Invalid property format. All values must be integers.");
        }
    }
}
