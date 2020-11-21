package task_4;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import javafx.util.Duration;
import task_4.graphics.graphic_objects.models.Group;
import task_4.graphics.graphic_objects.primitives.Box;
import task_4.graphics.scene.Scene;
import task_4.graphics.scene.camera.Transform;


public class Main
    extends Application
{
    public static void main(String[] args) {
        launch();
    }

    @Override
    public void start(Stage stage) {
        Scene scene = new Scene();

        Group group = new Group();

        Box box1 = new Box(100, 100, 100);
        Box box2 = new Box(50, 50, 50);
        box2.getTransform().modifyOffsetX(75);

        group.addChild(box1);
        group.addChild(box2);

        scene.addModel(group);

        scene.render();

        stage.setScene(new javafx.scene.Scene(scene));
        stage.show();
    }
}
