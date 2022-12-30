package agh.ics.oop.gui;


import agh.ics.oop.SimulationConfiguration;

import java.util.Properties;
import javafx.application.Application;
import javafx.scene.control.Button;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import agh.ics.oop.OptionsParser;
import agh.ics.oop.SimulationConfiguration;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.*;


public class App extends Application {
    private SimulationConfiguration currentConfiguration;

    @Override
    public void start(Stage primaryStage) throws Exception {

        FileChooser fileChooser = new FileChooser();
        Button load = new Button("Load Configuration");

        load.setOnAction(e -> {
            File configFile = fileChooser.showOpenDialog(primaryStage);
            try {
                InputStream input = new FileInputStream(configFile);
                Properties prop = new Properties();
                prop.load(input);
                currentConfiguration = OptionsParser.parse(prop);
                System.out.println(currentConfiguration);
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        });



        HBox main = new HBox(load);

        Scene scene = new Scene(main, 500,500);
        primaryStage.setScene(scene);
        primaryStage.show();
    }


    private void ShowCurrentConfig(Properties prop){

    }

}
