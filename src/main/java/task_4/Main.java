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
        Scene scene = new Scene(new Pane());
        scene.addModel(new Box(960, 100, 100));
        scene.render();

        stage.setScene(scene);
        stage.show();
    }
}
