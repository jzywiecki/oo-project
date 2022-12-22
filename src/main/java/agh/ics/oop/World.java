package agh.ics.oop;

public class World {
    public static void main(String[] args) {
        var engine = new SimulationEngine(MapVariant.GLOBE_MAP);

        try{
            engine.run();
        }catch (IllegalArgumentException err){
            System.out.println(err);
            System.exit(-2);
        }

    }
}
