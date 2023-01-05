package agh.ics.oop.gui;

import agh.ics.oop.SimulationEngine;
import agh.ics.oop.Vector2d;
import agh.ics.oop.interfaces.IMapElement;
import agh.ics.oop.interfaces.IWorldMap;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.geometry.HPos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;

import java.io.FileNotFoundException;
import java.util.Arrays;

public class SimulationViewController implements IGuiObserver{
    //controls
    @FXML
    private GridPane mapGridPane;

    @FXML
    private Label numberOfAnimals;

    @FXML
    private Label numberOfGrass;

    @FXML
    private Label numberOfEmptySpaces;

    @FXML
    private Label mostPopularGenome;

    @FXML
    private Label averageEnergy;

    @FXML
    private Label averageDaysLived;

    @FXML
    private Label selectedGenome;

    @FXML
    private Label selectedActiveGenome;

    @FXML
    private Label selectedEnergy;

    @FXML
    private Label selectedGrassEaten;

    @FXML
    private Label selectedNumberOfChildren;

    @FXML
    private Label selectedDaysLived;

    @FXML
    private Label selectedDeath;

    @FXML
    private Button start;

    @FXML
    private Button stop;

    @FXML
    private Button newSimulationButton;

    //simulation
    SimulationEngine engine;
    Thread thread;

    protected void drawMap(IWorldMap map){
        this.mapGridPane = new GridPane();
        for (int i = 0; i < map.getUpperBound().x(); i++){
            for (int j = 0; j < map.getUpperBound().y(); j++){
                Vector2d pos = new Vector2d(i, j);
                IMapElement obj = null;
                if(map.objectsAt(pos) != null){
                    obj = map.objectsAt(pos).getFirst();
                }
                GuiElementBox guiElementBox;
                try {
                    guiElementBox = new GuiElementBox(obj, pos);
                } catch (FileNotFoundException exception) {
                    throw new RuntimeException(exception);
                }

                VBox vbox = guiElementBox.getContent();

                this.mapGridPane.add(vbox, i, j);
                GridPane.setHalignment(vbox, HPos.CENTER);
                }
            }
    }

    private void changeStats(int newNumberOfAnimals, int newNumberOfGrass, int newEmptySpaces, int[] newMostPopularGenome, int newAverageEnergy, int newAverageDaysLived){
        numberOfAnimals.setText("Number of animals: " + newNumberOfAnimals);
        numberOfGrass.setText("Number of grass: " + newNumberOfGrass);
        numberOfEmptySpaces.setText("Empty spaces: " + newEmptySpaces);
        mostPopularGenome.setText("Most popular genome: " + Arrays.toString(newMostPopularGenome));
        averageEnergy.setText("Average energy: " + newAverageEnergy);
        averageDaysLived.setText("Average days lived: " + newAverageDaysLived);
    }

    private void changeSelectedStats(int[] newSelectedGenome, int newSelectedActiveGenome, int newSelectedEnergy, int newSelectedGrassEaten, int newSelectedNumberOfChildren, int newSelectedDaysLived, int newSelectedDeath){
        selectedGenome.setText("Genome: " + Arrays.toString(newSelectedGenome));
        selectedActiveGenome.setText("Active genome: " + newSelectedActiveGenome);
        selectedEnergy.setText("Animal energy: " + newSelectedEnergy);
        selectedGrassEaten.setText("Grass eaten: " + newSelectedGrassEaten);
        selectedNumberOfChildren.setText("Number of children: " + newSelectedNumberOfChildren);
        selectedDaysLived.setText("Days lived: " + newSelectedDaysLived);
        selectedDeath.setText("Day of death: " + newSelectedDeath);
    }

    public void generateSimulation(SimulationEngine engine){
        this.engine = engine;
        this.thread = new Thread(this.engine);
        this.thread.start();
        drawMap(this.engine.getMap());
    }

    @Override
    public void positionChanged() {
        try {
            Platform.runLater(()->{
                drawMap(this.engine.getMap());
                System.out.println("Moved animal");
            });
            Thread.sleep(200);
        } catch (InterruptedException exception) {
            System.out.println(exception.getMessage());
        }

    }


}
