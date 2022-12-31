package agh.ics.oop.gui;

import agh.ics.oop.OptionsParser;
import agh.ics.oop.SimulationConfiguration;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.*;
import java.util.Properties;

public class App extends Application {
    private SimulationConfiguration currentConfiguration;

    @Override
    public void start(Stage primaryStage) throws Exception {

        FileChooser fileChooser = new FileChooser();
        Button load = new Button("Load Configuration");

        load.setOnAction(e -> {
            File configFile = fileChooser.showOpenDialog(primaryStage);
            currentConfiguration = OptionsParser.parse(configFile);
        });



        HBox main = new HBox(load);
        Scene scene = new Scene(main, 500,500);
        primaryStage.setScene(scene);
        primaryStage.show();
    }


    private void ShowCurrentConfig(Properties prop){

    }
<<<<<<< Updated upstream
=======

>>>>>>> Stashed changes
}
