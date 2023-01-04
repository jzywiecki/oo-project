package agh.ics.oop.gui;

import agh.ics.oop.SimulationConfiguration;
import agh.ics.oop.SimulationEngine;
import agh.ics.oop.interfaces.IWorldMap;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.text.TextAlignment;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import java.io.*;
import java.util.Properties;
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
    private CheckBox checkBox;
    private Label configurationDescription;
    private Button startButton;
    private Label errorMessage;
    private Label welcomeMessage;
    private Stage stage;


    @Override
    public void start(Stage primaryStage) {
        //menu
        stage = primaryStage;
        stage.setTitle("World simulation!");
        welcomeMessage = new Label("Welcome to animal simulation!");
        fileChooser = new FileChooser();
        load = new Button("Load Configuration");
        loadConfiguration(primaryStage);
        checkBox = new CheckBox("Save stats to CSV");
        checkBox.setIndeterminate(false);
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
            try {
                InputStream input = new FileInputStream(configFile);
                Properties prop = new Properties();
                prop.load(input);
                currentConfiguration = OptionsParser.parse(prop);
                System.out.println(currentConfiguration);
                showCurrentConfig();
                startSimulation();
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        });
    }

    //start symulacji
    private void startSimulation() throws RuntimeException{
        if (currentConfiguration == null) {
            startButton.setOnAction(e -> errorMessage.setText("Brak wczytanej konfiguracji!"));
        } else {
            startButton.setOnAction(e -> {
                errorMessage.setText("");
                SimulationEngine engine = new SimulationEngine(currentConfiguration);
                Scene scene;
                try {
                    FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/simulationView.fxml"));
                    scene = new Scene(fxmlLoader.load(), 960, 569);
                    SimulationViewController viewController1 = fxmlLoader.getController();
                    viewController1.generateSimulation(engine);

                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
                stage.setScene(scene);
                stage.show();
            });
        }
    }
}

