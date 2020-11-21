package task_4;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import javafx.util.Duration;
import task_4.graphics.graphic_objects.models.Group;
import task_4.graphics.graphic_objects.primitives.Box;
import task_4.graphics.lighting.ColorLight;
import task_4.graphics.scene.Scene;
import task_4.graphics.scene.camera.Transform;

import static task_4.Utils.getRandomColor;


public class Main
    extends Application
{
    public static void main(String[] args) {
        launch();
    }

    @Override
    public void start(Stage stage) {
        Scene scene = new Scene();

        Box box = new Box(100, 100, 100);

        for (Box.DirectionSide directionSide : Box.DirectionSide.values()) {
            box.getSide(directionSide).setColor(getRandomColor());
        }

        scene.setVisibleAxes(true);

        scene.addModel(box);

        scene.render();

        stage.setScene(new javafx.scene.Scene(scene));
        stage.show();
    }
}
