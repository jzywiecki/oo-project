package agh.ics.oop.gui;

import agh.ics.oop.SimulationConfiguration;
import agh.ics.oop.SimulationEngine;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.text.TextAlignment;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import java.io.*;
import agh.ics.oop.OptionsParser;
import javafx.scene.Scene;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.geometry.Pos;


public class App extends Application {
    private SimulationConfiguration currentConfiguration;
    private Button load;
    private FileChooser fileChooser;
    private Label configurationDescription;
    private Button startButton;
    private Label errorMessage;
    private Stage stage;
    private CheckBox checkBox;


    @Override
    public void start(Stage primaryStage) {
        //menu
        stage = primaryStage;
        stage.setTitle("World simulation!");
        Label welcomeMessage = new Label("Welcome to animal simulation!");
        fileChooser = new FileChooser();
        load = new Button("Load Configuration");
        loadConfiguration(primaryStage);
        checkBox = new CheckBox("Save stats to CSV");
        checkBox.setSelected(false);
        configurationDescription = new Label("");
        configurationDescription.setTextAlignment(TextAlignment.CENTER);
        //start symulacji
        startButton = new Button("Start simulation!");
        startSimulation();
        errorMessage = new Label("");
        VBox main = new VBox();
        main.getChildren().addAll(welcomeMessage, new Label(""), checkBox, new Label(""), load, configurationDescription, startButton, errorMessage);
        main.setAlignment(Pos.CENTER);
        Scene scene = new Scene(main, 500, 500);
        stage.setScene(scene);
        stage.show();
    }

    //wczytana konfiguracja
    private void showCurrentConfig() {
        configurationDescription.setText(currentConfiguration.toString());
    }

    //wczytanie konfiguracji
    private void loadConfiguration(Stage primaryStage) throws RuntimeException {
        load.setOnAction(e -> {
            File configFile = fileChooser.showOpenDialog(primaryStage);
            currentConfiguration = OptionsParser.parseFile(configFile);
            System.out.println(currentConfiguration);
            showCurrentConfig();
            startSimulation();
        });
    }

    //start symulacji
    private void startSimulation() throws RuntimeException{
        if (currentConfiguration == null) {
            startButton.setOnAction(e -> errorMessage.setText("Brak wczytanej konfiguracji!"));
        } else {
            startButton.setOnAction(e -> {
                errorMessage.setText("");
                SimulationEngine engine = new SimulationEngine(currentConfiguration, checkBox.isSelected());
                try {
                    FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/simulationView.fxml"));
                    Scene scene = new Scene(fxmlLoader.load(), 960, 569);
                    SimulationViewController viewController1 = fxmlLoader.getController();
                    engine.addObserver(viewController1);
                    viewController1.generateSimulation(engine);
                    Stage stage = new Stage();
                    stage.setResizable(false);
                    stage.setTitle("Simulation");
                    stage.setScene(scene);
                    stage.show();

                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
            });
        }
    }
}

