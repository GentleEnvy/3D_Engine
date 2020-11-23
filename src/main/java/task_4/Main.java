package task_4;

import javafx.application.Application;
import javafx.stage.Stage;

import task_4.graphics.graphic_objects.primitives.Box;
import task_4.graphics.lighting.ColorLight;
import task_4.graphics.scene.Scene;

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

//        GraphicTriangle triangle1 = new GraphicTriangle(
//            new Point(0, 1, 1),
//            new Point(0, 1, 0),
//            new Point(0, 0, 0)
//        );
//        triangle1.setColor(new ColorLight(0, 0, 0, 0.5));
//        GraphicTriangle triangle2 = new GraphicTriangle(
//            new Point(-1, 0, 0),
//            new Point(0, 1, 0),
//            new Point(0, 0, 0)
//        );
//        triangle2.setColor(new ColorLight(255, 0, 0, 0.5));

        scene.setVisibleAxes(true);

        Box box = createBox();

        Box floor = new Box(150, 1, 150);
        floor.setColor(getRandomColor());
        floor.getTransform().modifyRotateXY(45);

        scene.addGraphic(box);
        scene.addGraphic(floor);

        scene.render();

        stage.setScene(new javafx.scene.Scene(scene));
        stage.show();
    }

    private static Box createBox() {
        Box box = new Box(100, 70, 50);
        for (Box.DirectionSide directionSide : Box.DirectionSide.values()) {
            ColorLight randomColor = getRandomColor();
            //randomColor.setOpacity(0.7);
            box.getSide(directionSide).setColor(randomColor);
        }
        return box;
    }
}
