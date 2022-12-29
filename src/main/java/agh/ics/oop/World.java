package agh.ics.oop;

import agh.ics.oop.gui.App;
import javafx.application.Application;


public class World {
    public static void main(String[] args) {
//        var engine = new SimulationEngine(MapVariant.GLOBE_MAP);
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