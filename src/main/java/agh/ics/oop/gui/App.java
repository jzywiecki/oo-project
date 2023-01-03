package agh.ics.oop.gui;


import agh.ics.oop.SimulationConfiguration;
import javafx.application.Application;
import javafx.scene.control.Button;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import java.io.File;
import agh.ics.oop.OptionsParser;
import javafx.scene.Scene;
import javafx.scene.layout.HBox;



public class App extends Application {
    private SimulationConfiguration currentConfiguration;

    @Override
    public void start(Stage primaryStage) throws Exception {

        FileChooser fileChooser = new FileChooser();
        Button load = new Button("Load Configuration");

        load.setOnAction(e -> {
            File configFile = fileChooser.showOpenDialog(primaryStage);
            currentConfiguration = OptionsParser.parseFile(configFile);

        });



        HBox main = new HBox(load);
        Scene scene = new Scene(main, 500,500);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

}
