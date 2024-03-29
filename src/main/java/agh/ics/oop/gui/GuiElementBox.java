package agh.ics.oop.gui;
import agh.ics.oop.MapStats;
import agh.ics.oop.Vector2d;
import agh.ics.oop.animal.Animal;
import agh.ics.oop.grass.Grass;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Arrays;

public class GuiElementBox {
    private VBox content = new VBox();

    private Image image;

    public GuiElementBox(Object object, MapStats stats) throws FileNotFoundException {
        Label label;
        if (object instanceof Animal animal){
            try {
                image = switch (animal.getDirection()) {
                    case NORTH -> new Image(new FileInputStream("src/main/resources/up.png"));
                    case NORTH_EAST -> new Image(new FileInputStream("src/main/resources/up_right.png"));
                    case EAST -> new Image(new FileInputStream("src/main/resources/right.png"));
                    case SOUTH_EAST -> new Image(new FileInputStream("src/main/resources/down_right.png"));
                    case SOUTH -> new Image(new FileInputStream("src/main/resources/down.png"));
                    case SOUTH_WEST -> new Image(new FileInputStream("src/main/resources/down_left.png"));
                    case WEST -> new Image(new FileInputStream("src/main/resources/left.png"));
                    case NORTH_WEST -> new Image(new FileInputStream("src/main/resources/up_left.png"));
                };
            } catch (FileNotFoundException ex){
                System.out.println(ex.getMessage());
            }
            label = new Label("Energy: " + animal.getEnergy());
            if (Arrays.equals(animal.getGenomes(), stats.getMostPopularGenome())){
                label.setTextFill(Color.color(1, 0, 0));
            }
            else{
                label.setTextFill(Color.color(0, 0, 0));
            }
        }
        else if (object instanceof Grass){
            try {
                image = new Image(new FileInputStream("src/main/resources/grass.png"));
            }catch (FileNotFoundException ex){
                System.out.println(ex.getMessage());
            }
            label = new Label("");
        }
        else{
            label = new Label("");
        }



        ImageView imageView = new ImageView(image);
        imageView.setFitWidth(20);
        imageView.setFitHeight(20);
        content.getChildren().add(imageView);
        content.getChildren().add(label);
        content.setAlignment(Pos.CENTER);
    }

    public VBox getContent() {
        return this.content;
    }
}
