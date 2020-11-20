package task_4.graphics.scene;


import javafx.scene.layout.Pane;
import task_4.graphics.graphic_objects.models.Model;
import task_4.graphics.graphic_objects.polygons.GraphicPolygon;
import task_4.graphics.graphic_objects.polygons.RealPolygon;
import task_4.graphics.graphic_objects.primitives.GraphicPrimitive;
import task_4.graphics.scene.camera.Camera;
import task_4.transforms.Transform;

import java.util.ArrayList;
import java.util.List;


public class Scene
    extends javafx.scene.Scene
{
    private static final int DEFAULT_SCREEN_WIDTH = 1920;
    private static final int DEFAULT_SCREEN_HEIGHT = 1080;

    private final Pane mainPane;
    private final List<Model> models = new ArrayList<>();
    private final Camera camera = new Camera(
        // TODO: add camera event handlers
    );

    {
        setOnMousePressed(mouseEvent -> {
            camera.handleEvent(mouseEvent);
            render();
        });
        setOnMouseReleased(mouseEvent -> {
            camera.handleEvent(mouseEvent);
            render();
        });
        setOnMouseDragged(mouseEvent -> {
            camera.handleEvent(mouseEvent);
            render();
        });
        setOnScroll(mouseEvent -> {
            camera.handleEvent(mouseEvent);
            render();
        });
    }

    public Scene(Pane pane) {
        super(pane);
        mainPane = pane;
    }

    public void addGraphic(Model model) {
        models.add(model);
    }

    public void render() {
        mainPane.getChildren().clear();

        Screen screen = new Screen(
            getWidth() <= 0 ? DEFAULT_SCREEN_WIDTH : (int) getWidth(),
            getHeight() <= 0 ? DEFAULT_SCREEN_HEIGHT : (int) getHeight()
        );

        // TODO: render models

        mainPane.getChildren().add(screen);
    }
}
