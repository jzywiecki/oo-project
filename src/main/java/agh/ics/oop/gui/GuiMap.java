package agh.ics.oop.gui;

import agh.ics.oop.Vector2d;
import agh.ics.oop.interfaces.IWorldMap;
import javafx.application.Application;
import javafx.geometry.HPos;
import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.FileNotFoundException;

public class GuiMap {
    IWorldMap map;
    GridPane gridPane;
    public GuiMap(IWorldMap map, GridPane gridPane) {
        this.map = map;
        this.gridPane = gridPane;
    }

    public GridPane drawMap(){
        for (int i = 0; i < map.getUpperBound().x(); i++){
            for (int j = 0; j < map.getUpperBound().y(); j++){
                Vector2d pos = new Vector2d(i, j);
                if (map.isOccupied(pos)) {
                    var obj = map.objectsAt(pos);
                    GuiElementBox guiElementBox;
                    try {
                        guiElementBox = new GuiElementBox(obj, pos);
                    } catch (FileNotFoundException exception) {
                        throw new RuntimeException(exception);
                    }
                    VBox vbox = guiElementBox.getContent();
                    this.gridPane.add(vbox, i, j);
                    GridPane.setHalignment(vbox, HPos.CENTER);

                }
            }
        }
        return gridPane;
    }

}
