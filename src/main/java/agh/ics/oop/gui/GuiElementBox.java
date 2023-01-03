package agh.ics.oop.gui;
import agh.ics.oop.Vector2d;
import agh.ics.oop.animal.Animal;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class GuiElementBox {
    private VBox content = new VBox();
    private Image image;

    public GuiElementBox(Object object, Vector2d position) throws FileNotFoundException {
        Label label;
        if (object instanceof Animal animal){
            try {
                switch (animal.getDirection()) {
                    case NORTH -> image = new Image(new FileInputStream("src/main/resources/up.png"));
                    case NORTH_EAST -> new Image(new FileInputStream("src/main/resources/up_right.png"));
                    case EAST -> image = new Image(new FileInputStream("src/main/resources/right.png"));
                    case SOUTH_EAST -> new Image(new FileInputStream("src/main/resources/down_right.png"));
                    case SOUTH -> image = new Image(new FileInputStream("src/main/resources/down.png"));
                    case SOUTH_WEST -> new Image(new FileInputStream("src/main/resources/down_left.png"));
                    case WEST -> image = new Image(new FileInputStream("src/main/resources/left.png"));
                    case NORTH_WEST -> new Image(new FileInputStream("src/main/resources/up_left.png"));
                }
            } catch (FileNotFoundException ex){
                System.out.println(ex.getMessage());
            }
            label = new Label("z" + position.toString());
        }
        else{
            try {
                image = new Image(new FileInputStream("src/main/resources/grass.png"));
            }catch (FileNotFoundException ex){
                System.out.println(ex.getMessage());
            }
            label = new Label("Trawa");
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
