package task_4.graphics.scane;


import javafx.scene.canvas.Canvas;
import javafx.scene.layout.Pane;
import task_4.graphics.BaseGraphic;
import task_4.graphics.geometry.camera.Camera;

import java.util.ArrayList;
import java.util.List;


public class Scene extends Pane {
    private final List<BaseGraphic> graphics = new ArrayList<>();
    private final Camera camera = new Camera();

    {
        setOnMousePressed(mouseEvent -> {
            camera.handleMouseEvent(mouseEvent);
            render();
        });
        setOnMouseReleased(mouseEvent -> {
            camera.handleMouseEvent(mouseEvent);
            render();
        });
        setOnMouseDragged(mouseEvent -> {
            camera.handleMouseEvent(mouseEvent);
            render();
        });
        setOnScroll(mouseEvent -> {
            camera.handleMouseEvent(mouseEvent);
            render();
        });
    }



    public void render() {
        Canvas canvas = new Canvas();
        for (BaseGraphic graphic : graphics) {

        }
    }
}
