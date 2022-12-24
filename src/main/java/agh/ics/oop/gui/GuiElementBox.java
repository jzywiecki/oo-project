package agh.ics.oop;
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
//            switch (animal.getOrientation()){
//                case NORTH -> image = new Image(new FileInputStream("src/main/resources/up.png"));
//                case EAST -> image = new Image(new FileInputStream("src/main/resources/right.png"));
//                case SOUTH -> image = new Image(new FileInputStream("src/main/resources/down.png"));
//                case WEST -> image = new Image(new FileInputStream("src/main/resources/left.png"));
//            }
            label = new Label("z" + position.toString());
        }
        else{
            image = new Image(new FileInputStream("src/main/resources/grass.png"));
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
