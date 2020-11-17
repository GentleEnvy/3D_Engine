package task_4;

import javafx.application.Application;
import javafx.scene.canvas.Canvas;
import javafx.stage.Stage;


public class Main extends Application {
    public static void main(String[] args) {
        launch();
    }

    @Override
    public void start(Stage stage) {
        Canvas canvas = new Canvas();
        canvas.getGraphicsContext2D().fillRect();
        stage.show();
    }
}
