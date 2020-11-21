package task_4;

import javafx.application.Application;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import task_4.graphics.graphic_objects.primitives.Box;
import task_4.graphics.scene.Scene;


public class Main
    extends Application
{
    public static void main(String[] args) {
        launch();
    }

    @Override
    public void start(Stage stage) {
        Scene scene = new Scene();

        Box box = new Box(300, 100, 200);

        scene.addModel(box);
        scene.render();

        stage.setScene(new javafx.scene.Scene(scene));
        stage.show();
    }
}
