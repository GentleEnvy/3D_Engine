package task_4.graphics.scane;


import javafx.scene.canvas.Canvas;
import task_4.graphics.BaseGraphic;

import java.util.ArrayList;
import java.util.List;


public class Scene {
    private final List<BaseGraphic> graphics = new ArrayList<>();

    public void render() {
        Canvas canvas = new Canvas();
        for (BaseGraphic graphic : graphics) {

        }
    }
}
