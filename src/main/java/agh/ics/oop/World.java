package agh.ics.oop;

import agh.ics.oop.gui.App;
import javafx.application.Application;

<<<<<<< Updated upstream

public class World {
    public static void main(String[] args) {
//        var engine = new SimulationEngine(MapVariant.GLOBE_MAP);
=======
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class World {
    public static void main(String[] args) {
//
//        Properties prop = new Properties();
//        InputStream input = null;
//        try {
//            input = new FileInputStream("src/main/resources/config.txt");
//            prop.load(input);
//        } catch (IOException e) {
//            throw new RuntimeException(e);
//        }
//        SimulationConfiguration currentConfiguration = OptionsParser.parse(prop);
//        System.out.println(currentConfiguration);
//
//
//
//        var engine = new SimulationEngine(currentConfiguration);
>>>>>>> Stashed changes
//
//        try{
//            engine.run();
//        }catch (IllegalArgumentException err){
//            System.out.println(err);
//            System.exit(-2);
//        }

        try {
            Application.launch(App.class, args);
        } catch (IllegalArgumentException err) {
            System.exit(-2);
        }

    }
}
